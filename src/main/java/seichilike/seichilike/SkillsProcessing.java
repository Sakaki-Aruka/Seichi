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
    public void Break(int break_blocks,BlockBreakEvent event){
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
        int player_x = player.getLocation().getBlockX();
        int player_z = player.getLocation().getBlockZ();
        int player_y = player.getLocation().getBlockY();

        //get players direction
        float player_yaw = player.getLocation().getYaw();
        float player_pitch = player.getLocation().getPitch();

        //about around blocks
        Location location;
        Block block1;
        World world = player.getWorld();

        if(y <= player_y){
            player.sendMessage("Do not mining the blocks that under the your location.");
            event.setCancelled(true);
            return;
        }

        if(player_pitch > 30){
            // if player look the block that is under the player.
            //ignore to mining.
            event.setCancelled(true);
            player.sendMessage("Do not mining the blocks that under the your location.");
            return;
        }else{
            //
            if(break_blocks == 3){
                //using small-miner
                if(45 <= player_yaw && player_yaw <= 134){
                    //player look at west
                    if(player_yaw == 90){
                        //west
                    }else{
                        //south-west or north-west
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


    }
}
