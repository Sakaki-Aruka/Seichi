package seichilike.seichilike;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class LocationCalc {
    public double calc(double x, double y, double z, Location location, Block block, double return_total, Player pLayer){

        location.setX(x);
        location.setY(y);
        location.setZ(z);
        block = location.getBlock();

        //for debug
        pLayer.sendMessage("Type().name():"+block.getType().name());

        //if ignored, the block is not breaking
        if(block.getType().name().contains("COMMAND") || block.getType().name().contains("PORTAL")){
            //system does not break
        }else{
            if(block.getType().name().contains("_ORE")){
                //if a mined block is an ore.
                return_total += 3.0;
            }else{
                //other blocks
                return_total += 1.0;
            }
            //block replace (any -> air)
            block.setType(Material.AIR);
        }

        return return_total;
    }
}
