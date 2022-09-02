package seichilike.seichilike;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class DropCheck {
    public void dropCheck(BlockBreakEvent event){

        Material material = event.getBlock().getType();
        Player player = event.getPlayer();
        ArrayList<ItemStack> dropItems = new ArrayList<>(event.getBlock().getDrops());
        Random random = new Random();

        if(dropItems.size()==0){
            return;
        }

        Material drop = dropItems.get(0).getType();
        for (int i=0;i<=35;i++){
            if(player.getInventory().getItem(i)==null){
                continue;
            }
            if(player.getInventory().getItem(i).getType()==drop){
                //when a player has this drop item, this event set cancel
                event.setDropItems(false);
                break;
            }else if(i==35 && player.getInventory().getItem(i).getType() != drop){
                player.getWorld().dropItemNaturally(player.getLocation(),new ItemStack(dropItems.get(0)));
                break;
            }else{
                continue;
            }
        }
    }
}
