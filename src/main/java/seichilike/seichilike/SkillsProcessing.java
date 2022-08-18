package seichilike.seichilike;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class SkillsProcessing {
    public void Break(int x, int y, int z, String skill, Player player,Block block){
        //skill write here
        ArrayList<String> IgnoreBlocks = new ArrayList<>(Arrays.asList("bedrock","end_portal_frame","command_block","repeating_command_block","chain_command_block"));
        //
        int player_x = player.getLocation().getBlockX();
        int player_z = player.getLocation().getBlockZ();
        int player_y = player.getLocation().getBlockY();

        float player_yaw = player.getLocation().getYaw();


    }
}
