package seichilike.seichilike;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class SkillsProcessing {
    public double Break(int break_blocks,BlockBreakEvent event){
        //skill write here
        ArrayList<String> IgnoreBlocks = new ArrayList<>(Arrays.asList("bedrock","end_portal_frame","command_block","repeating_command_block","chain_command_block"));

        //break blocks location
        int x = event.getBlock().getX();
        int y = event.getBlock().getY();
        int z = event.getBlock().getZ();

        Player player = event.getPlayer();

        //breaking block
        Block block = event.getBlock();

        //players location
        //int player_x = player.getLocation().getBlockX();
        //int player_z = player.getLocation().getBlockZ();
        int player_y = player.getLocation().getBlockY();

        //get players direction
        float player_yaw = player.getLocation().getYaw();
        float player_pitch = player.getLocation().getPitch();

        //about around blocks
        Location location;
        Block block1;
        World world = player.getWorld();
        double dx;
        double dy;
        double dz;
        boolean ignored = true;

        //return total amount
        double return_total = 0.0;

        if(y <= player_y){
            player.sendMessage("Do not mining the blocks that under the your location.");
            event.setCancelled(true);
            return return_total;
        }

        if(player_pitch > 30){
            // if player look the block that is under the player.
            //ignore to mining.
            event.setCancelled(true);
            player.sendMessage("Do not mining the blocks that under the your location.");
            return return_total;
        }else{
            //
            if(break_blocks == 3){
                //using small-miner
                if(45 <= player_yaw && player_yaw <= 134){
                    //player look at west
                    if(player_yaw == 90.0) {
                        //west
                        event.setCancelled(true);
                        dy = (double) y;
                        dx = (double) x;
                        dz = (double) z;
                        location = new Location(world, dx, dy, dz);
                        block = location.getBlock();
                        block.setType(Material.AIR);


                        for(double i=dy-1.0;i<dy+(double) break_blocks-1;i++){
                            for(double ii=dz-1.0;ii<dz+2.0;ii++){
                                for(double iii=dx;iii>dx-(double)break_blocks;iii--){
                                    location.setY(i);
                                    location.setZ(ii);
                                    location.setX(iii);
                                    block = location.getBlock();

                                    //for debug
                                    player.sendMessage("Type().name():"+block.getType().name());

                                    for(String Si:IgnoreBlocks){
                                        if(block.getType().name().toLowerCase(Locale.ROOT).contains(Si)){
                                            //ignored
                                            ignored = true;
                                        }else{
                                            ignored = false;
                                        }
                                    }

                                    //if ignored, the block is not breaking.
                                    if(ignored){
                                        //system does not break
                                    }else{
                                        block.setType(Material.AIR);
                                        if(block.getType().name().toLowerCase(Locale.ROOT).contains("ore")){
                                            return_total += 3.0;
                                        }else{
                                            return_total += 1.0;
                                        }
                                    }

                                    //debug message
                                    //player.sendMessage("replaced");
                                }
                                //
                            }

                        }

                        return return_total-1.0;
                    }


                }else if((-136 >= player_yaw && -180 <= player_yaw) || (135 <= player_yaw && 180 >= player_yaw)){
                    //player look at north
                }else if(-135 <= player_yaw && -46 > player_yaw){
                    //player look at east
                }else if(-45 <= player_yaw && player_yaw < 44){
                    //player look at south
                }
            }
        }

        return return_total;
    }
}

