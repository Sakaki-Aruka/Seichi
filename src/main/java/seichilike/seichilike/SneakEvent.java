package seichilike.seichilike;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SneakEvent implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){

        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        World world = player.getWorld();

        if(event.getAction()==Action.RIGHT_CLICK_AIR || event.getAction()==Action.RIGHT_CLICK_BLOCK){
            // the player do right-click
            if(!(itemStack==null) && itemStack.getType()==Material.WOODEN_PICKAXE){
                // the player has wooden pickaxe in their main hand.
                if(itemStack.getEnchantmentLevel(Enchantment.MENDING)==5){
                    // an item that the player has in main hand contains an enchantment (mending LV.5)
                    int pickaxeCounter = 0;
                    for (int i=0;i<=35;i++){
                        ItemStack checkItem = player.getInventory().getItem(i);

                        if(player.getInventory().getItem(i)==null){
                            continue;
                        }

                        if(!(checkItem==null) && checkItem.getType()==Material.WOODEN_PICKAXE && checkItem.getEnchantmentLevel(Enchantment.MENDING)==5){
                            pickaxeCounter++;
                        }
                    }

                    if(pickaxeCounter >= 5){
                        ItemStack giveIronPic = new ItemStack(Material.IRON_PICKAXE);
                        ItemMeta IronMeta = giveIronPic.getItemMeta();

                        // iron pickaxes setting
                        IronMeta.setUnbreakable(true);
                        IronMeta.setDisplayName("§r§fIron Pickaxe");
                        IronMeta.setLore(Arrays.asList("§r§fThis item is an unbreakable tool.","","PublicServer 2022"));
                        IronMeta.addEnchant(Enchantment.MENDING,6,true);
                        IronMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                        giveIronPic.setItemMeta(IronMeta);

                        int deleteCounter = 0;

                        for (int i=1;i<=pickaxeCounter / 5;i++){
                            world.dropItemNaturally(player.getLocation(),giveIronPic);
                            for (int ii=0;i<=35;ii++){
                                ItemStack giveCheck = player.getInventory().getItem(ii);

                                if(player.getInventory().getItem(ii)==null){
                                    continue;
                                }

                                if(!(player.getInventory()==null) && giveCheck.getType()==Material.WOODEN_PICKAXE && giveCheck.getEnchantmentLevel(Enchantment.MENDING)==5 && deleteCounter < 5){
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
}
