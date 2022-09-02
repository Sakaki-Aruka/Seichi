package seichilike.seichilike;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import static seichilike.seichilike.SettingsLoad.*;

public class BlockSharePlace {
    public void blockSharePlace(BlockPlaceEvent event){


        Player player = event.getPlayer();
        boolean enchantExist = player.getInventory().getItemInMainHand().getEnchantments().isEmpty();
        Material placeBlock = event.getBlock().getType();
        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if(!(enchantExist)){
            //if enchantExist is not true, the block that a player placed contains enchantment(s).
            return;
        }


        if(placeBlock.name().contains("_STAIRS") && stairs >= 1){
            // stairs
            stairs--;
            refill(player,itemStack);
        }else if(placeBlock.name().contains("_ORE") && ores >= 1){
            // ores
            ores--;
            refill(player,itemStack);
        }else if(placeBlock.name().contains("_SLAB") && slabs >= 1){
            // slabs
            slabs--;
            refill(player,itemStack);
        }else if(placeBlock.name().contains("_LOG") && logs >= 1){
            // logs
            logs--;
            refill(player,itemStack);
        }else if(placeBlock==Material.TNT || placeBlock==Material.END_CRYSTAL || placeBlock==Material.WITHER_SKELETON_SKULL){
            // dangerous
            dangerous--;
            refill(player,itemStack);
        }else{
            commons--;
            refill(player,itemStack);

            //debug
            player.sendMessage("common:"+commons);
        }
    }

    public void refill(Player player,ItemStack itemStack){
        player.getInventory().getItemInMainHand().setAmount(2);

        //debug
        player.sendMessage("have:"+itemStack.getAmount());
    }
}
