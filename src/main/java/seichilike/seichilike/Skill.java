package seichilike.seichilike;

import org.bukkit.block.Block;
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

        double level = 0.0;
        int list_bool = 0;
        int set_bool = 0;
        int miner_bool = 0;
        int description_bool = 0;

        for (String ii:args){
            if(ii.equals("-list")){
                list_bool = 3;
            }

            if(ii.equals("-d")){
                description_bool = 3;
            }

            if(ii.equals("-set")){
                set_bool = 3;
            }
        }

        if(set_bool ==3 && description_bool==3){
            //the player send options that "set" and "description"
            player.sendMessage("§cDo not use '-set' option and '-d' option in a same time.");
            return false;
        }else if(set_bool==3 && list_bool==3){
            player.sendMessage("§cDo not use '-set' option and '-list' option in a same time.");
            return false;
        }

        if(set_bool ==3){
            //set the skill

        }

        for (String i:Tags){
            //
            Pattern minerPattern = Pattern.compile("WorldMiner");
            Matcher minerMatcher = minerPattern.matcher(i);
            if(minerMatcher.find()){
                //if the player has "WorldMiner" tag
                miner_bool = 3;
            }else{
                //
            }

            Pattern levelPattern = Pattern.compile("Seichi-Like-level_");
            Matcher levelMatcher = levelPattern.matcher(i);
            if(levelMatcher.find()){
                //get players level
                level = Double.parseDouble(i.substring(18,i.length()));
            }
        }

        if(miner_bool==0){
            //the player does not have "WorldMiner"
            player.sendMessage("§cYou are not a 'WorldMiner'.\nWhen you run '/bar-make', you will be a 'WorldMiner'.");
            return false;
        }else if(level < 3.0){
            //no skills that the player can use
            player.sendMessage("§cYour level is "+(int)level+". So you can not use any skills.");
            return false;
        }


        player.sendMessage("§bYou can use these skills.");
        if(description_bool == 3){
            SkillList = new SkillList().SkillListDescription(level);
            for (String iii:SkillList){
                player.sendMessage("§b"+iii);
            }
        }else if(description_bool == 0){
            SkillList = new SkillList().SkillListNormal(level);
            //no description
            for (String i4:SkillList){
                player.sendMessage("§b"+i4);
            }
        }



        return false;
    }
}
