package seichilike.seichilike;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
public class LocationCalc {


    public double calc(double x, double y, double z, Location location, Block block,  Player player){

        location.setX(x);
        location.setY(y);
        location.setZ(z);
        block = location.getBlock();
        double break_blocks = 0.0;


        //for debug
        //player.sendMessage("Type().name():"+block.getType().name());

        //if ignored, the block is not breaking
        if(block.getType().name().contains("COMMAND") || block.getType().name().contains("PORTAL") || block.getType().name().contains("BEDROCK")){
            //system does not break
        }else{
            if(block.getType().isBlock()){
                if(block.getType().name().contains("_ORE")){
                    //if a mined block is an ore.
                    break_blocks += 3.0;
                    chance(player);
                }else if(block.getType().name().contains("AIR") || block.getType().name().contains("WATER")){
                    //air or water
                }else{
                    //other blocks
                    break_blocks += 1.0;
                    chance(player);
                }

                //block replace (any -> air)
                block.setType(Material.AIR);

            }


        }

        return break_blocks;
    }


    /*
    treasure chest drop process
     */
    public void chance(Player player){

        int random1 = new Random().nextInt(100);
        int random2 = new Random().nextInt(100);

        if(random1==random2){
            //player.sendMessage(("§b1:"+String.format("%.2f",random1)+"/2:"+String.format("%.2f",random2)));
            Location player_loc = player.getLocation();
            ItemStack lotteryChest = new ItemStack(Material.CHEST);

            ItemMeta chestMeta = lotteryChest.getItemMeta();
            // bold and color(aqua)
            chestMeta.setDisplayName("§o§b宝箱");
            chestMeta.addEnchant(Enchantment.MENDING,1,true);
            chestMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            chestMeta.setLore(new SettingsLoad().getLore());

            lotteryChest.setItemMeta(chestMeta);

            //debug
            //player.playSound(player_loc,Sound.BLOCK_ANVIL_PLACE,0.5f,0.7f);

            String actionMessage = "§3You got a treasure chest!!!";
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionMessage));

            World world = player.getWorld();
            String warn = new SettingsLoad().getTreasureWarning();

            for (int i=0;i<=35;i++){
                // add treasure chest for players inventory
                ItemStack itemStack = player.getInventory().getItem(i);
                if(itemStack == null){
                    // no item on the slot
                    // add lottery chest to the empty slot
                    player.getInventory().setItem(i,lotteryChest);
                    break;
                }else if(itemStack.getType() == Material.CHEST && itemStack.containsEnchantment(Enchantment.MENDING)){
                    if(itemStack.getAmount() < 64){
                        // the slots item, less than 64
                        // plus 1 item
                        itemStack.setAmount(itemStack.getAmount()+1);
                        break;
                    }else{
                        //over 64 -> pass
                        if(i==35){
                            player.sendMessage(warn);
                            world.dropItemNaturally(player_loc,lotteryChest);
                        }
                    }

                }else if(i==35 && (itemStack.getType() != Material.CHEST) && !(itemStack.containsEnchantment(Enchantment.MENDING))){
                    // no slot was found
                    player.sendMessage(warn);
                    world.dropItemNaturally(player_loc,lotteryChest);
                }
            }
        }
    }
}
