package seichilike.seichilike;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Lottery {
    public void lottery(BlockPlaceEvent e){
        Player player = e.getPlayer();
        Server server = player.getServer();

        if(player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.MENDING)){
            if(player.getInventory().getItemInMainHand().getType()== Material.CHEST){

                int player_have = player.getInventory().getItemInMainHand().getAmount();
                if(1 < player_have){
                    player.getInventory().getItemInMainHand().setAmount(player_have-1);
                }else{
                    player.getInventory().getItemInMainHand().setAmount(0);
                }

                e.setCancelled(true);

                Location location = e.getBlock().getLocation();
                location.setY(location.getY()+0.25);
                World world = player.getWorld();
                double result = Math.random();

                if(result< 0.50){
                    this.template(Material.COBBLESTONE,Enchantment.MENDING,1,true,"§fはずれ",location,world);

                }else if(0.5 <= result && result < 0.9){
                    this.template(Material.END_STONE,Enchantment.MENDING,1,true,"あたり",location,world);

                }else{
                    this.template(Material.DIAMOND_ORE,Enchantment.MENDING,1,true,"§eレア",location,world);

                    this.fanfare(player,location,"BLOCK_ANVIL_PLACE",0.50F,2F,server);
                }


            }
        }
    }

    public void template(Material item,Enchantment enchantment,int level,boolean restriction,String name,Location location,World world){
        ItemStack itemStack = new ItemStack(item);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(enchantment,level,restriction);

        //hide enchantments descriptions.
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);

        world.dropItemNaturally(location,itemStack);
    }

    public void fanfare(Player player,Location location,String soundName,float volume,float pitch,Server server){

        try{
            Sound sound = Sound.valueOf(soundName);
            player.playSound(location,sound,volume,pitch);

        }catch(Exception exception){
            server.getLogger().info("[Seichi]:SoundException:"+exception);
        }
    }
}
