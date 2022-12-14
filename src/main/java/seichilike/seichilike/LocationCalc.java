package seichilike.seichilike;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.*;

import static seichilike.seichilike.SettingsLoad.*;

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
                    ores ++;

                }else if(block.getType().name().contains("AIR") || block.getType().name().contains("WATER")){
                    //air or water
                }else{
                    //other blocks
                    break_blocks += 1.0;
                    chance(player);
                    if(block.getType().name().contains("_STAIRS")){
                        System.out.println("stairs:"+stairs);
                        stairs++;
                    }else if(block.getType().name().contains("_SLAB")){
                        slabs++;
                    }else if(block.getType().name().contains("_LOG")){
                        logs++;
                    }else if(block.getType()==Material.TNT || block.getType()==Material.WITHER_SKELETON_SKULL || block.getType()==Material.END_CRYSTAL){
                        System.out.println("dangerous:"+dangerous);
                        dangerous++;
                    }else{
                        System.out.println("commons:"+commons);
                        commons++;
                    }
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
            //player.sendMessage(("??b1:"+String.format("%.2f",random1)+"/2:"+String.format("%.2f",random2)));
            Location player_loc = player.getLocation();
            ItemStack lotteryChest = new ItemStack(Material.CHEST);

            ItemMeta chestMeta = lotteryChest.getItemMeta();
            // bold and color(aqua)
            chestMeta.setDisplayName("??o??b??????");
            chestMeta.addEnchant(Enchantment.MENDING,1,true);
            chestMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            chestMeta.setLore(new SettingsLoad().getLore());

            lotteryChest.setItemMeta(chestMeta);

            //debug
            //player.playSound(player_loc,Sound.BLOCK_ANVIL_PLACE,0.5f,0.7f);

            String actionMessage = "??3You got a treasure chest!!!";
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

    public void orb(Player player){
        String biomeName = player.getWorld().getBiome(player.getLocation()).toString();

        //debug
        //player.sendMessage("World:"+player.getWorld().getName());

        if(biomeName.equals("DARK_FOREST") && this.dice(100,100)){
            this.itemSetting(Material.DRIED_KELP,"??3??????",new ArrayList<>(Arrays.asList("??r??fclass ??????????????????","??r??f??????:??????","","PublicServer 2022")),player);

        }else if(biomeName.equals("PLAINS") && this.dice(100,100)){
            this.itemSetting(Material.SPORE_BLOSSOM,"??3??????????????????",new ArrayList<>(Arrays.asList("??r??fclass ????????????????????????","??r??f??????:???","","PublicServer 2022")),player);

        }else if(biomeName.equals("DESERT") && this.dice(100,100)){
            this.itemSetting(Material.SHROOMLIGHT,"??3????????????",new ArrayList<>(Arrays.asList("??r??fclass ?????????","??r??f??????:???","","PublicServer 2022")),player);

        }else if(biomeName.equals("OCEAN") && this.dice(1000,1000)){
            this.itemSetting(Material.WATER_BUCKET,"??3??????????????????",new ArrayList<>(Arrays.asList("??r??fclass ???????????????","??r??f??????:???","","PublicServer 2022")),player);

        }else if(biomeName.equals("JAGGED_PEAKS") && this.dice(100,100)){
            this.itemSetting(Material.TORCH,"??3???????????????",new ArrayList<>(Arrays.asList("??r??fclass ????????????","??r??f??????:??????","","PublicServer 2022")),player);

        }else if(biomeName.equals("THE_END") && this.dice(100,100)){
            this.itemSetting(Material.CHORUS_FLOWER,"??3???????????????",new ArrayList<>(Arrays.asList("??r??fclass ??????","??r??f??????:?","","PublicServer 2022")),player);

        }else if(player.getWorld().getName().equalsIgnoreCase("WORLD_NETHER") && this.dice(100,100)){
            this.itemSetting(Material.COBBLED_DEEPSLATE,"??3?????????????????????-???",new ArrayList<>(Arrays.asList("??r??fclsas ???????????????","??r??f??????:??????","","PublicServer 2022")),player);

        }else if(biomeName.equals("SAVANNA") && this.dice(100,100)){
            this.itemSetting(Material.COBBLED_DEEPSLATE,"??3?????????????????????-???",new ArrayList<>(Arrays.asList("??r??fclass ???????????????","??r??f??????:??????","","PublicServer 2022")),player);

        }else if(biomeName.equals("FOREST") && this.dice(100,100)){
            this.itemSetting(Material.FEATHER,"??3???????????????-??????",new ArrayList<>(Arrays.asList("??r??fclass ????????????","??r??f??????:???","","PublicServer 2022")),player);

        }

        // all biome
        if(this.dice(1000,1000)){
            this.itemSetting(Material.DIAMOND,"??3?????????",new ArrayList<>(Arrays.asList("??r??fclass ???????????????","??f??????:???","","PublicServer 2022")),player);
        }
    }

    public boolean dice(int range1,int range2){
        int random1 = new Random().nextInt(range1);
        int random2 = new Random().nextInt(range2);

        if(random1==random2){
            return true;
        }else{
            return false;
        }
    }

    public void itemSetting(Material material,String itemName,ArrayList<String> lore,Player player){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(itemName);
        itemMeta.addEnchant(Enchantment.ARROW_INFINITE,1,false);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        //item drops
        player.getWorld().dropItemNaturally(player.getLocation(),itemStack);
    }
}
