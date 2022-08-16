package seichilike.seichilike;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CreateBossBar implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        if(!(sender instanceof Player)){
            return false;
        }
        //write here
        Player player = (Player) sender;
        String Name = player.getName();

        if(!(player.getScoreboardTags().contains("WorldMiner"))){
            // add a tag(WorldMiner)
            player.addScoreboardTag("WorldMiner");
            player.addScoreboardTag("Seichi-Like-bar_0.0");

            //create Mining bar
            Plugin pl = Bukkit.getPluginManager().getPlugin("Seichi-Like");
            String BarKey = "Seichi-Like.bar."+Name+".amount.seichi";
            NamespacedKey NK = new NamespacedKey(pl,BarKey);

            BossBar bb = Bukkit.createBossBar(NK,"Mine", BarColor.GREEN, BarStyle.SOLID);
            // display to the players screen.
            bb.addPlayer(player);


            //about level
            //Seichi-Like-level_[level(int)]
            player.addScoreboardTag("Seichi-Like-level_0");

            //about total seichi amount
            //Seichi-Like-total_[total amount(double)]
            player.addScoreboardTag("Seichi-Like-total_0.0");
        }




        return false;
    }
}
