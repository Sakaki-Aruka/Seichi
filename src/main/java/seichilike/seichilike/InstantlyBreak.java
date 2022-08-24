package seichilike.seichilike;

import org.bukkit.event.block.BlockDamageEvent;

public class InstantlyBreak {
    public void instant(BlockDamageEvent e){
        //set instantly break(when the player has pickaxe in main hand.)

        String theBlock = e.getBlock().getType().name();
        if(e.getPlayer().getInventory().getItemInMainHand().getType().name().contains("PICKAXE")){
            if(e.getBlock().getType().isBlock() && (!(theBlock.equals("BEDROCK") || theBlock.equals("PORTAL") || theBlock.contains("COMMAND")))){
                e.setInstaBreak(true);
            }

        }
    }
}
