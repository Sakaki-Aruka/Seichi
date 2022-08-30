package seichilike.seichilike;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;

public class SneakEvent implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){

        Player player = event.getPlayer();

        if(event.getAction()==Action.RIGHT_CLICK_AIR || event.getAction()==Action.RIGHT_CLICK_BLOCK){
            // the player do right-click



            if(player.getInventory()==null){
                return;
            }

            
            ItemStack mainHand = player.getInventory().getItemInMainHand();

            if(mainHand.getType()==Material.WOODEN_PICKAXE && mainHand.getEnchantmentLevel(Enchantment.MENDING)==5){
                //wooden pickaxe -> coal pickaxe
                ArrayList<String> lore = new ArrayList<>(Arrays.asList("§r§fThis item is an unbreakable tool.","","§r§fNext tool is StonePickaxe","PublicServer 2022"));

                this.upgrade(Material.WOODEN_PICKAXE,Material.WOODEN_PICKAXE,lore,"§r§fCoalPickaxe",Enchantment.MENDING,5,6,player);

            }else if(mainHand.getType()==Material.WOODEN_PICKAXE && mainHand.getEnchantmentLevel(Enchantment.MENDING)==6){
                //coal pickaxe -> stone pickaxe
                ArrayList<String> lore = new ArrayList<>(Arrays.asList("§r§fThis item is an unbreakable tool.","","§r§fNext tool is IronPickaxe","PublicServer 2022"));

                this.upgrade(Material.WOODEN_PICKAXE,Material.STONE_PICKAXE,lore,"§r§fStonePickaxe",Enchantment.MENDING,6,7,player);
            }else if(mainHand.getType()==Material.STONE_PICKAXE && mainHand.getEnchantmentLevel(Enchantment.MENDING)==7){
                // stone pickaxe -> iron pickaxe
                ArrayList<String> lore = new ArrayList<>(Arrays.asList("§r§fThis items is an unbreakable tool.","","§r§fNext tool is CopperPickaxe","PublicServer 2022"));

                this.upgrade(Material.STONE_PICKAXE,Material.IRON_PICKAXE,lore,"§r§fIronPickaxe",Enchantment.MENDING,7,8,player);
            }

        }
    }

    public void upgrade(Material before, Material after, ArrayList<String> lore,String itemName,Enchantment enchantment,int enchantLevelBefore,int enchantLevelAfter,Player player){

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        World world = player.getWorld();

        if(!(itemStack==null) && itemStack.getType()==before){
            // the player has wooden pickaxe in their main hand.
            if(itemStack.getEnchantmentLevel(enchantment)==enchantLevelBefore){
                // an item that the player has in main hand contains an enchantment (mending LV.5)
                int pickaxeCounter = 0;
                for (int i=0;i<=35;i++){
                    ItemStack checkItem = player.getInventory().getItem(i);

                    if(player.getInventory().getItem(i)==null){
                        continue;
                    }

                    if(!(checkItem==null) && checkItem.getType()==before && checkItem.getEnchantmentLevel(enchantment)==enchantLevelBefore){
                        pickaxeCounter++;
                    }
                }

                if(pickaxeCounter >= 5){
                    ItemStack giveTool = new ItemStack(after);
                    ItemMeta ToolMeta = giveTool.getItemMeta();

                    // iron pickaxes setting
                    ToolMeta.setUnbreakable(true);
                    ToolMeta.setDisplayName(itemName);
                    ToolMeta.setLore(lore);
                    ToolMeta.addEnchant(enchantment,enchantLevelAfter,true);
                    ToolMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    giveTool.setItemMeta(ToolMeta);

                    int deleteCounter = 0;

                    for (int i=1;i<=pickaxeCounter / 5;i++){
                        world.dropItemNaturally(player.getLocation(),giveTool);
                        for (int ii=0;i<=35;ii++){
                            ItemStack giveCheck = player.getInventory().getItem(ii);

                            if(player.getInventory().getItem(ii)==null){
                                continue;
                            }

                            if(!(player.getInventory()==null) && giveCheck.getType()==before && giveCheck.getEnchantmentLevel(enchantment)==enchantLevelBefore && deleteCounter < 5){
                                giveCheck.setAmount(0);
                                deleteCounter++;
                            }else if(deleteCounter >= 5){
                                deleteCounter = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
