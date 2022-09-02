package seichilike.seichilike;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static seichilike.seichilike.SettingsLoad.*;

public final class SeichiLike extends JavaPlugin implements Listener {

    public static FileConfiguration fileConfiguration = new YamlConfiguration();
    public void load(){
        fileConfiguration = getConfig();
        new SettingsLoad().fc(fileConfiguration);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        // load chest lore
        this.load();
        getServer().getPluginManager().registerEvents(this,this);
        // load chest lore end


        // block break  event
        getServer().getPluginManager().registerEvents(new Mining(),this);

        // bar-make command
        getCommand("bar-make").setExecutor(new CreateBossBar());

        //skill command
        getCommand("skill").setExecutor(new Skill());

        // stop water falling
        getServer().getPluginManager().registerEvents(new StopWaterFalling(),this);

        // pickaxe update event
        getServer().getPluginManager().registerEvents(new SneakEvent(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.miningBlockAmountWrite();

    }
    @EventHandler
    public void onPlayerCommandProcess(PlayerCommandPreprocessEvent event){

        if(event.getMessage().equalsIgnoreCase("/reload")){
            Bukkit.broadcastMessage("Now reloading.");
            this.miningBlockAmountWrite();
        }
    }

    public void miningBlockAmountWrite(){
        // set values to the config.yml
        fileConfiguration.set("stairs",stairs);
        fileConfiguration.set("ores",ores);
        fileConfiguration.set("slabs",slabs);
        fileConfiguration.set("logs",logs);
        fileConfiguration.set("commons",commons);
        fileConfiguration.set("dangerous",dangerous);
        saveConfig();
    }
}
