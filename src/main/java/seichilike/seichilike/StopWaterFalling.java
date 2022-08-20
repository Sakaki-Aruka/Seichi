package seichilike.seichilike;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class StopWaterFalling implements Listener {
    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event){
        System.out.println("ToBlock:"+event.getToBlock().getType().name());
        System.out.println("Block:"+event.getBlock().getType().name());
        if(event.getBlock().getType().name().equals("WATER")){
            //water falling event
            event.setCancelled(true);
            System.out.println("Event cancelled.");
        }

    }
}
