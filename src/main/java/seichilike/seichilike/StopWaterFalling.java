package seichilike.seichilike;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class StopWaterFalling implements Listener {
    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event){
        if(event.getBlock().getType().name().equalsIgnoreCase("WATER")){
            event.setCancelled(true);
        }

    }
}