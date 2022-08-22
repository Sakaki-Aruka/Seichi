package seichilike.seichilike;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class LocationCalc {
    public double calc(double x, double y, double z, Location location, Block block,  Player player){

        location.setX(x);
        location.setY(y);
        location.setZ(z);
        block = location.getBlock();
        double break_blocks = 0.0;


        //for debug
        //player.sendMessage("Type().name():"+block.getType().name());

        //if ignored, the block is not breaking
        if(block.getType().name().contains("COMMAND") || block.getType().name().contains("PORTAL") || block.getType().name().contains("BEDROCK")){
            //system does not break
        }else{
            if(block.getType().isBlock()){
                if(block.getType().name().contains("_ORE")){
                    //if a mined block is an ore.
                    break_blocks += 3.0;
                }else if(block.getType().name().contains("AIR") || block.getType().name().contains("WATER")){
                    //air or water
                }else{
                    //other blocks
                    break_blocks += 1.0;
                }

                //block replace (any -> air)
                block.setType(Material.AIR);

            }


        }

        return break_blocks;
    }
}
