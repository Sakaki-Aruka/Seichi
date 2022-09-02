package seichilike.seichilike;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
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

        //debug
        //player.sendMessage("empty slot:"+player.getInventory().firstEmpty());
        if(player.getInventory().firstEmpty()==-1){
            event.setDropItems(false);
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
                return;
            }else if(i==35 && player.getInventory().getItem(i).getType() != drop){
                break;
            }else{
                continue;
            }
        }
    }
}
