package seichilike.seichilike;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mining implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player Miner = e.getPlayer();
        if(e.getBlock().getType().isBlock()){
            // A block that miner mined is a kind of blocks.
            String name = Miner.getName();
            Plugin pl = Bukkit.getPluginManager().getPlugin("Seichi-Like");
            String BarKey = "Seichi-Like.bar."+name+".amount.seichi";
            NamespacedKey NK = new NamespacedKey(pl,BarKey);
            BossBar bb = Bukkit.getBossBar(NK);

            //Calc
            Set tags = Miner.getScoreboardTags();
            ArrayList<String> ArrayTags = new ArrayList<>(tags);
            for(String tag:ArrayTags){
                //for loop
                Pattern pattern = Pattern.compile("Seichi-Like-bar_");
                Matcher matcher = pattern.matcher(tag);

                Pattern pattern2 = Pattern.compile("Seichi-Like-level_");
                Matcher matcher2 = pattern2.matcher(tag);

                Pattern pattern3 = Pattern.compile("Seichi-Like-total_");
                Matcher matcher3 = pattern3.matcher(tag);

                if(matcher.find() && matcher2.find() && matcher3.find()){
                    // when a player have "Seichi-Like-bar_"tag.
                    double D = Double.parseDouble(tag.substring(16,tag.length()));
                    int level = Integer.getInteger(tag.substring(18,tag.length()));
                    double total = Double.parseDouble(tag.substring(18,tag.length()));

                    if(D+0.01 < 1.0){
                        D += 0.01;
                        bb.setProgress(D);
                        Miner.removeScoreboardTag(tag);
                        Miner.addScoreboardTag("Seichi-Like-bar_"+D);
                    }else if(D+0.01 == 1.0){
                        bb.setProgress(0);
                        Miner.removeScoreboardTag(tag);
                        Miner.addScoreboardTag("Seichi-Like-bar_0.0");

                    }else{
                        bb.setProgress(0);
                        Miner.removeScoreboardTag(tag);
                        Miner.addScoreboardTag("Seichi-Like-bar_0.0");
                    }


                }
            }


            /*
            if(e.getBlock().getType().name().toLowerCase(Locale.ROOT).contains("ore")){
                Miner.sendMessage("§bThis block is RARE.");
                //bar-title = Break , BarColor = Green, BarStyle = no split,

            }else{
                Miner.sendMessage("§7This block is NORMAL.");
            }
            
             */
        }
    }
}
