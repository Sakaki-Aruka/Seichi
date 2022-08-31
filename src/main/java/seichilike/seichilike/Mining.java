package seichilike.seichilike;

import org.bukkit.*;

import org.bukkit.boss.BossBar;;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Math;

public class Mining implements Listener {

    @EventHandler
    public void onBlockDamage(BlockDamageEvent e){
        new InstantlyBreak().instant(e);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        new Freeze().freeze(e);
    }



    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player Miner = e.getPlayer();
        if(e.getBlock().getType().isBlock() && Miner.getScoreboardTags().contains("WorldMiner")){

            e.setDropItems(false);

            // A block that miner mined is a kind of blocks.
            String name = Miner.getName();
            Plugin pl = Bukkit.getPluginManager().getPlugin("Seichi-Like");
            String BarKey = "Seichi-Like.bar."+name+".amount.seichi";
            NamespacedKey NK = new NamespacedKey(pl,BarKey);
            BossBar bb = Bukkit.getBossBar(NK);

            /*
            sound
             */
            try{
                if(e.getBlock().getType().isBlock()){
                    String soundName = "BLOCK_STONE_BREAK";
                    Sound sound = Sound.valueOf(soundName);
                    e.getPlayer().playSound(e.getPlayer().getLocation(),sound,1,1);
                }
            }catch (Exception exception){
                System.out.println("SoundException:"+exception);
            }

            //Calc
            Set tags = Miner.getScoreboardTags();
            ArrayList<String> ArrayTags = new ArrayList<>(tags);

            double D=0.0;
            double level=0;
            double total=0.0;
            double total_copy=0.0;
            double total_temporary = 0.0;
            //for debug
            //double total_debug=0.0;

            //debug
            //Miner.sendMessage("Yaw:"+Miner.getLocation().getYaw());

            if(Miner.getScoreboardTags().contains("Seichi-Like-skill_small-miner")){
                //small-miner
                if(Miner.isSneaking()){

                }else{
                    //use for debug
                    //total_temporary += new SkillsProcessing().Break(3,e);
                    total_temporary += new SkillsProcessing().Break(3,e);
                    if(total_temporary == 0){
                        return;
                    }

                }


            }else if(Miner.getScoreboardTags().contains("Seichi-Like-skill_medium-miner")){
                //medium-miner
                if(!(Miner.isSneaking())){
                    //the miner is not sneaking
                    total_temporary += new SkillsProcessing().Break(5,e);
                    if(total_temporary == 0){
                        return;
                    }
                }
            }else if(Miner.getScoreboardTags().contains("Seichi-Like-skill_miner")){
                //miner
                if(!(Miner.isSneaking())){
                    //the miner is not sneaking
                    total_temporary += new SkillsProcessing().Break(7,e);
                    if(total_temporary == 0){
                        return;
                    }
                }
            }else if(Miner.getScoreboardTags().contains("Seichi-Like-skill_big-miner")){
                //big-miner
                if(!(Miner.isSneaking())){
                    //the miner is not sneaking
                    total_temporary += new SkillsProcessing().Break(9,e);
                    if(total_temporary == 0){
                        return;
                    }
                }
            }else if(Miner.getScoreboardTags().contains("Seichi-Like-skill_large-miner")){
                //large-miner
                if(!(Miner.isSneaking())){
                    //the miner is not sneaking
                    total_temporary += new SkillsProcessing().Break(11,e);
                    if(total_temporary == 0){
                        return;
                    }
                }
            }else if(Miner.getScoreboardTags().contains("Seichi-Like-skill_using")){
                //when the player has this tag, allowed using a skill. Only means this.
            }

            // orb processing
            new LocationCalc().orb(Miner);
            //orb processing finish

            for(String tag:ArrayTags){
                //for loop
                Pattern pattern = Pattern.compile("Seichi-Like-bar_");
                Matcher matcher = pattern.matcher(tag);

                Pattern pattern2 = Pattern.compile("Seichi-Like-level_");
                Matcher matcher2 = pattern2.matcher(tag);

                Pattern pattern3 = Pattern.compile("Seichi-Like-total__");
                Matcher matcher3 = pattern3.matcher(tag);

                if(matcher.find()){
                    try{
                        D = Double.parseDouble(tag.substring(16,tag.length()));
                        System.out.println("D:"+D);
                    }catch (NullPointerException exception){
                        System.out.println(exception);
                    }

                }else if(matcher2.find()){
                    try{
                        level = Double.parseDouble(tag.substring(18,tag.length()));
                        System.out.println("level:"+level);
                    }catch (NullPointerException exception){
                        System.out.println(exception);
                    }

                }else if(matcher3.find()){
                    try{
                        total = Double.parseDouble(tag.substring(19,tag.length()));
                        total_copy = total;
                        System.out.println("total:"+total);
                    }catch (NullPointerException exception){
                        System.out.println(exception);
                    }
                }

            }
            //calc total (start)
            if(e.getBlock().getType().name().contains("_ORE")){
                total += 3.0;
                //bar-title = Break , BarColor = Green, BarStyle = no split,

            }else if(e.getBlock().getType().name().contains("AIR")) {
                //air no count
            }else{
                total += 1.0;
            }
            //calc total (finish)

            //total plus total_temporary(by skills)
            total += total_temporary;

            double progress=0.0;
            double border = 0.0;
            double before_border = 0.0;
            double range;

            if(level != 0.0 && level <= 100){
                //border = 2*level^3 + 3*level^2 + 50*level
                // a border to level-up to the next level
                for(int i=1;i<=level;i++){
                    border += 2*i*i*i + 3*i*i + 50*i;
                }

                double before_level = level -1.0;
                for(int i=1;i<=before_level;i++){
                    before_border += 2*i*i*i + 3*i*i + 50*i;;
                }
                range = border - before_border;

                progress = (total - before_border)/range;
                System.out.println("border:"+border+" / before-border:"+before_border+" / range:"+range+" / progress:"+progress);

            }else if(level != 0.0 && level > 100){
                // level over 100
                border = 51244973;
                border += (level-100)*2050000;
                before_border = (level-101)*2050000;
                range = border - before_border;

                progress = (total - before_border)/range;

            }

            if(progress < 1.0){
                //in the level (no level up)
                bb.setProgress(progress);

                //tag update
                Miner.removeScoreboardTag("Seichi-Like-total__"+total_copy);
                Miner.addScoreboardTag("Seichi-Like-total__"+total);

                Miner.removeScoreboardTag("Seichi-Like-bar_"+D);
                Miner.addScoreboardTag("Seichi-Like-bar_"+progress);

                double percentage = progress * 100;
                bb.setTitle("Lv:"+(int)level+" | Total:"+(int)total+" | Progress:"+String.format("%.2f",percentage)+"% | Remaining:"+(int)(border-total));

            }else{
                //over the level (level up)
                bb.setProgress(0.0);

                double updated_level = level + 1.0;

                double percentage = progress * 100;
                bb.setTitle("Lv:"+(int)level+" / Total:"+(int)total+" / Progress:"+String.format("%.2f",percentage)+"%");

                //level's tag update
                Miner.removeScoreboardTag("Seichi-Like-level_"+level);
                Miner.addScoreboardTag("Seichi-Like-level_"+updated_level);

                Miner.removeScoreboardTag("Seichi-Like-bar_"+D);
                Miner.addScoreboardTag("Seichi-Like-bar_"+0.0);

                Miner.removeScoreboardTag("Seichi-Like-total__"+total_copy);
                Miner.addScoreboardTag("Seichi-Like-total__"+total);

                //say fanfare to a Miner

                // [title] [subtitle] [fade(in/tick)] [stay(tick)] [fade(out/tick)]
                Miner.sendTitle("Level up!!!["+(int)level+" -> "+(int)updated_level+"]","Congratulations!!!",30,100,30);

                try{
                    //sound
                    Sound sound = Sound.valueOf("ENTITY_PLAYER_LEVELUP");
                    for (int i=0;i<3;i++){
                        Miner.playSound(Miner.getLocation(),sound,1,1);
                    }

                }catch (Exception exception){
                    System.out.println("Sound Exception:"+exception);
                }

                }


            }



        }
    }

