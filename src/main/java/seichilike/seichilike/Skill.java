package seichilike.seichilike;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Skill implements CommandExecutor, TabCompleter {
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
            /*
            command ex; /skill -set small-miner
             */
            if(args.length <=1){
                return false;
            }
            String skillName = args[1];
            if(Tags.contains("Seichi-Like-skill_using") && !(skillName.equalsIgnoreCase("remove"))){
                player.sendMessage("Cannot allow to use "+skillName+".\nPlease release the skill that is you selected.");
                return false;
            }else{
                //write here
                if(skillName.equals("remove")){
                    if(Tags.contains("Seichi-Like-skill_using")){
                        //remove using tag
                        player.removeScoreboardTag("Seichi-Like-skill_using");

                        /*
                        remove a skill that the player is using.
                         */
                        for (String regexLoop : player.getScoreboardTags()){
                            Pattern skillNamePattern = Pattern.compile("Seichi-Like-skill_.{5,12}");
                            Matcher matcher = skillNamePattern.matcher(regexLoop);
                            if(matcher.find()){
                                if(!(matcher.group().equalsIgnoreCase("Seichi-Like-skill_using"))){
                                    player.removeScoreboardTag(matcher.group());
                                    break;
                                }
                            }
                        }


                        player.sendMessage("---------\n\nYou unselected the skill.\n\n---------");
                        return true;
                    }else{
                        player.sendMessage("---------\n\nNo skill using.\n\n---------");
                        return false;
                    }
                }else{
                    ArrayList<String> ArrayTags = new ArrayList<>(player.getScoreboardTags());
                    double D;
                    double total;
                    double total_copy;

                    /*
                    to get some parameters
                     */

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

                    /*
                    check the players level and requested skill.
                     */

                    if(3 <= level && level < 10 && (!(skillName.equalsIgnoreCase("small-miner")))){
                        //illegal skill pattern
                        player.sendMessage("§cYou cannot select this skill.");
                        return false;
                    }else if(10 <= level && level < 15 && (!(skillName.equalsIgnoreCase("small-miner")) || (skillName.equalsIgnoreCase("medium-miner")))){
                        player.sendMessage("§cYou cannot select this skill.");
                        return false;
                    }else if(15 <= level && level < 25 && (!(skillName.equalsIgnoreCase("small-miner") || skillName.equalsIgnoreCase("medium-miner") || skillName.equalsIgnoreCase("miner")))){
                        player.sendMessage("§cYou cannot select this skill.");
                        return false;
                    }else if(25 <= level && level < 40 && (!(skillName.equalsIgnoreCase("small-miner") || skillName.equalsIgnoreCase("medium-miner") || skillName.equalsIgnoreCase("miner") || skillName.equalsIgnoreCase("big-miner")))){
                        player.sendMessage("§cYou cannot select this skill.");
                        return false;
                    }else if(40 <= level  && (!(skillName.equalsIgnoreCase("small-miner") || skillName.equalsIgnoreCase("medium-miner") || skillName.equalsIgnoreCase("miner") || skillName.equalsIgnoreCase("large-miner")))){
                        player.sendMessage("§cYou cannot select this skill.");
                        return false;
                    }

                    skillName = skillName.toLowerCase(Locale.ROOT);
                    player.addScoreboardTag("Seichi-Like-skill_"+skillName);
                    player.addScoreboardTag("Seichi-Like-skill_using");
                    player.sendMessage("You can use "+skillName+".");
                    return true;
                }
            }
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


        player.sendMessage("---§bYou can use these skills.§f---");
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
        if(command.getName().equalsIgnoreCase("skill")){
            //
            if(args.length==1){
                return Arrays.asList("-d","-list","-set");
            }else if(args.length==2){
                if(args[0].startsWith("-d")){
                    return Arrays.asList("-list");
                }else if(args[0].startsWith("-list")){
                    return Arrays.asList("-d");
                }else if(args[0].startsWith("-set")){
                    return Arrays.asList("");
                }
            }
        }

        return null;
    }
}
