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
        World world = player.getWorld();
        double dx;
        double dy;
        double dz;

        int block_counter=0;


        //return total amount
        double return_total = 0.0;


        if(y <= player_y){
            //the player looks down


            event.setCancelled(true);
            return return_total;
        }




        if(player_pitch > 30){
            // if player look the block that is under the player.
            //ignore to mining.
            event.setCancelled(true);
            //error message
            //player.sendMessage("Do not mining the blocks that under the your location.");
            return return_total;
        }else{
            //break_blocks -> skills range(=width = height = depth)
            if(break_blocks == 3 || break_blocks ==5 || break_blocks == 7 || break_blocks ==9 || break_blocks ==11){
                //using small-miner
                if((45 < player_yaw && player_yaw < 135) || -135 < player_yaw && player_yaw < -45){
                    //
                    //player look at west or east

                    event.setCancelled(true);
                    dy = (double) y;
                    dx = (double) x;
                    dz = (double) z;
                    location = new Location(world,dx,dy,dz);
                    block = location.getBlock();
                    if(block.getType().name().contains("_ORE")){
                        return_total += 3.0;
                    }else if(block.getType().name().contains("AIR")){
                        //no plus
                    }else{
                        return_total += 1.0;
                    }
                    block.setType(Material.AIR);

                    double z_edge = ((double) break_blocks -1.0)/2;
                    double end_edge;

                    LocationCalc LC = new LocationCalc();

                    for(double i=dy-1.0;i<dy+(double)break_blocks-1;i++){
                        for(double ii=dz-z_edge;ii<dz+z_edge+1.0;ii++){
                            if(45 < player_yaw && player_yaw < 135){
                                //the player looks west
                                end_edge = dx - (double)break_blocks;
                                for(double iii=dx;iii>end_edge;iii--){
                                    //west
                                    return_total += LC.calc(iii,i,ii,location,block,player);

                                    //for debug
                                    //block_counter ++;
                                }
                            }else{
                                //the player looks east
                                end_edge = dx + (double)break_blocks;
                                for(double iii=dx;iii<end_edge;iii++){
                                    //east
                                    return_total += LC.calc(iii,i,ii,location,block,player);

                                    //for debug
                                    //block_counter ++;
                                }
                            }
                        }
                    }
                    //for debug
                    //player.sendMessage("BlockCounter:"+block_counter);
                    return return_total;

                }else if(( -180 <= player_yaw && player_yaw <= -135) || (135 <= player_yaw && player_yaw <= 180) || (-45 <= player_yaw && player_yaw <= 45)){
                    //player look at north and south
                    event.setCancelled(true);

                    dy = (double) y;
                    dx = (double) x;
                    dz = (double) z;
                    location = new Location(world,dx,dy,dz);
                    block = location.getBlock();
                    if(block.getType().name().contains("_ORE")){
                        return_total += 3.0;
                    }else if(block.getType().name().contains("AIR")){
                        //no plus
                    }else{
                        return_total += 1.0;
                    }
                    block.setType(Material.AIR);

                    double x_edge = ((double)break_blocks -1.0)/2;
                    double end_edge;

                    LocationCalc LC = new LocationCalc();

                    for(double i=dy-1.0;i<dy + (double)break_blocks-1.0;i++){
                        for(double ii=dx-x_edge;ii<dx+x_edge+1.0;ii++){
                            if(-45 <= player_yaw && player_yaw <= 0){
                                //
                                end_edge = dz + (double)break_blocks;
                                for(double iii=dz;iii<end_edge;iii++){
                                    return_total += LC.calc(ii,i,iii,location,block,player);
                                }

                            }else if(0 < player_yaw && player_yaw <= 45){
                                //north-pattern:2
                                end_edge = dz + (double)break_blocks;
                                for(double iii=dz;iii<end_edge;iii++){
                                    return_total += LC.calc(ii,i,iii,location,block,player);
                                }

                            }else if(135 <= player_yaw && player_yaw <= 180){
                                //north-pattern:1
                                end_edge = dz - (double)break_blocks;
                                for(double iii=dz;iii>end_edge;iii--){
                                    return_total += LC.calc(ii,i,iii,location,block,player);
                                }

                            }else if(-180 <= player_yaw && player_yaw <= -135){
                                //north pattern:2
                                end_edge = dz - (double)break_blocks;
                                for(double iii=dz;iii>end_edge;iii--){
                                    return_total += LC.calc(ii,i,iii,location,block,player);
                                }

                            }
                        }
                    }

                    return return_total;


                }else if(-45 <= player_yaw && player_yaw <= 45){
                    //player look at south
                }
            }
        }

        return return_total;
    }
}

