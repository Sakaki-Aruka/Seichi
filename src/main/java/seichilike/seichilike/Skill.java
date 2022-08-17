package seichilike.seichilike;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Skill implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        if(!(sender instanceof Player)){
            System.out.println("Please Login");
            return false;
        }
        //write here
        Player player = (Player) sender;
        String Name = player.getName();
        Set<String> Tags = player.getScoreboardTags();

        ArrayList<String> SkillList = new ArrayList<>();

        double level;

        for(String tag :args){
            if(tag.contains("-set:")){
                
                //
            }else if(tag.contains("-list")){
                //send message (can use skill list)
                Pattern level_pattern = Pattern.compile("Seichi-Like-level_");
                Matcher matcher = level_pattern.matcher(tag);
                if(matcher.find()){
                    // if user have "Seichi-Like-level" tag
                    level = Double.parseDouble(tag.substring(18,tag.length()));
                    if(Tags.contains("-d")){
                        //if send description option
                        SkillList = new SkillList().SkillList(level);

                        player.sendMessage("---Skill List---");
                        for (String i:SkillList){
                            sender.sendMessage(i);
                        }
                        return false;
                    }else{
                        SkillList = new SkillList().SkillList(level);

                        player.sendMessage("--SKill List---");
                        for (String i:SkillList){
                            sender.sendMessage(i);
                        }
                        return false;
                    }

                }else{
                    player.sendMessage("You can't skills.");
                    return false;
                }

            }else if(tag.contains("-change")){
                //change
            }
        }

        return false;
    }
}
