package seichilike.seichilike;

import org.bukkit.plugin.java.JavaPlugin;

public final class SeichiLike extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
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
