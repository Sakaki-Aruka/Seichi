package seichilike.seichilike;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;

public class InstantlyBreak {
    public void instant(BlockDamageEvent e){
        //set instantly break(when the player has pickaxe in main hand.)

        String theBlock = e.getBlock().getType().name();
        Player player = e.getPlayer();

        if(e.getPlayer().getInventory().getItemInMainHand().getType().name().contains("PICKAXE") && player.getScoreboardTags().contains("Seichi-Like-skill_using")){
            if(e.getBlock().getType().isBlock() && (!(theBlock.equals("BEDROCK") || theBlock.equals("PORTAL") || theBlock.contains("COMMAND")))){
                e.setInstaBreak(true);
            }

        }else if(!(player.getScoreboardTags().contains("Seichi-Like-skill_using"))){
            Material breakBLock = e.getBlock().getType();
            if(breakBLock==Material.GRAVEL || breakBLock==Material.SAND || breakBLock.name().contains("_LOG") || breakBLock.name().contains("DIRT") || breakBLock==Material.GRASS_BLOCK || breakBLock.name().contains("_LEAVES")){
                e.setInstaBreak(true);
            }
        }
    }
}
