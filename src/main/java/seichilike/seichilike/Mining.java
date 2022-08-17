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
import java.lang.Math;

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

                if(matcher.find() || matcher2.find() || matcher3.find()){
                    // when a player have "Seichi-Like-bar_"tag.
                    double D = Double.parseDouble(tag.substring(16,tag.length()));
                    int level = Integer.getInteger(tag.substring(18,tag.length()));
                    double total = Double.parseDouble(tag.substring(18,tag.length()));
                    double total_copy = total;

                    //calc total (start)
                    if(e.getBlock().getType().name().toLowerCase(Locale.ROOT).contains("ore")){
                        total += 3.0;
                        //bar-title = Break , BarColor = Green, BarStyle = no split,

                    }else{
                        total += 1.0;
                    }
                    //calc total (finish)

                    // Needs = 2.0 * level^3 + 3.0*level^2 + 50*level
                    double Needs = 2.0 * level * level * level + 3.0*level*level + 50 *level;
                    double LevelUpLimit;
                    double now = 2*level*level*level + 3*level*level + 50*level;
                    double progress=0;

                    if(level != 0 && level <= 100){
                        //integral 1 -> level (2*level^3 + 3*level^2 + 50*level) dLevel
                        // 0.5 * level^4 + level^3 + 25*level^2 - 0.5^4 - 1 - 25^2

                        //level += 1;

                        // a border to level-up to the next level
                        LevelUpLimit = Math.floor(0.5*level*level*level*level + level*level*level + 25*level*level - 0.5*0.5*0.5*0.5 - 1 -25*25);

                        progress = (total - LevelUpLimit)/now;




                    }else if(level != 0 && level > 100){
                        // level over 100

                        //level += 1;

                        // integral 1-> 100 (2*level^3 + 3*level^2 + 50*level) dLevel && level = 100
                        LevelUpLimit = Math.floor(0.5*100*100*100*100 + 100*100*100 + 25*100*100 - 0.5*0.5*0.5*0.5 - 1 -25*25);
                        LevelUpLimit += (level-100)*2500000;
                        progress = (total - LevelUpLimit)/now;


                    }else if(level == 0){
                        //level 0
                        if(total < 55){
                            progress = total / 55;
                        }else{
                            //double temporary = total - 55;
                            progress = (total-55)/Math.floor(2*2*2*2 + 3*2*2+50*2);
                        }
                    }

                    if(progress <= 1.0){
                        //in the level (no level up)
                        bb.setProgress(progress);

                        //tag update
                        Miner.removeScoreboardTag("Seichi-Like-total_"+total_copy);
                        Miner.addScoreboardTag("Seichi-Like-total_"+total);

                        Miner.removeScoreboardTag("Seichi-Like-bar_"+D);
                        Miner.addScoreboardTag("Seichi-Like-bar_"+progress);

                    }else{
                        //over the level (level up)
                        bb.setProgress(0.0);

                        //level's tag update
                        Miner.removeScoreboardTag("Seichi-Like-level_"+level);
                        Miner.addScoreboardTag("Seichi-Like-level_"+level+1);

                        Miner.removeScoreboardTag("Seichi-Like-bar_"+D);
                        Miner.addScoreboardTag("Seichi-Like-bar_"+0.0);

                        Miner.removeScoreboardTag("Seichi-Like-total_"+total_copy);
                        Miner.addScoreboardTag("Seichi-Like-total_"+total);

                        //say fanfare to a Miner
                        int updated_level = level + 1;
                        // [title] [subtitle] [fade(in/tick)] [stay(tick)] [fade(out/tick)]
                        Miner.sendTitle("Level up!!!["+level+" -> "+updated_level+"]","congratulations",30,100,30);

                    }


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
