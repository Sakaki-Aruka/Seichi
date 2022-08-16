package seichilike.seichilike;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class RemoveBossBar implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        if(!(sender instanceof Player)){
            return false;
        }
        //write here
        Player player = (Player) sender;
        String name = player.getName();

        Plugin pl = Bukkit.getPluginManager().getPlugin("Seichi-Like");
        String BarKey = "Seichi-Like.bar."+name+".amount.seichi";
        NamespacedKey NK = new NamespacedKey(pl,BarKey);
        BossBar bb = Bukkit.getBossBar(NK);


        Random random = new Random();
        double randomDouble = random.nextDouble();
        bb.setProgress(randomDouble);


        return false;
    }
}
