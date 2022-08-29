package seichilike.seichilike;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class SeichiLike extends JavaPlugin {

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
        // load chest lore end

        getServer().getPluginManager().registerEvents(new Mining(),this);
        getCommand("bar-make").setExecutor(new CreateBossBar());
        //getCommand("bar-remove").setExecutor(new RemoveBossBar());
        getCommand("skill").setExecutor(new Skill());
        getServer().getPluginManager().registerEvents(new StopWaterFalling(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
