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
    }
}
