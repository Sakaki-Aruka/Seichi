package seichilike.seichilike;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class Freeze {
    public void freeze(PlayerMoveEvent e){
        Player player = e.getPlayer();
        double PlayerX = player.getLocation().getBlockX();
        double PlayerY = player.getLocation().getBlockY();
        double PlayerZ = player.getLocation().getBlockZ();
        Location location = new Location(player.getWorld(),PlayerX,PlayerY,PlayerZ);
        Location removeKelp = new Location(player.getWorld(),PlayerX+1.0,PlayerY,PlayerZ+1.0);

        for(double X=PlayerX-3;X < PlayerX+4;X++){
            for(double Y=PlayerY-1;Y < PlayerY+6;Y++){
                for(double Z=PlayerZ-3;Z < PlayerZ+4;Z++){
                    location.setX(X);
                    location.setY(Y);
                    location.setZ(Z);

                    removeKelp.setX(X+1.0);
                    removeKelp.setY(Y);
                    removeKelp.setZ(Z+1.0);

                    String BlockName = location.getBlock().getType().name();
                    if(BlockName.contains("WATER") || BlockName.contains("KELP") ||BlockName.contains("KELP_PLANT")|| BlockName.contains("SEAGRASS") || BlockName.contains("CORAL")){
                        // remove kelp when before freeze water
                        if(removeKelp.getBlock().getType().name().contains("KELP")){
                            removeKelp.getBlock().setType(Material.WATER);
                        }
                        location.getBlock().setType(Material.ICE);

                    }else if(BlockName.contains("LAVA")){
                        location.getBlock().setType(Material.MAGMA_BLOCK);
                    }
                }
            }
        }
    }
}
