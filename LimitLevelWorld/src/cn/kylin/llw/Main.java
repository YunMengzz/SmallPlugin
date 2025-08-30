package cn.kylin.llw;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main plugin;

    @Override
    public void onEnable() {
        if (plugin != null) {
            this.getLogger().info("请勿启动两次本插件！");
            return;
        }
        plugin = this;

        this.saveDefaultConfig();
        ConfigLoader.load();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        this.getCommand("llw").setExecutor(new CmdExe());

        this.getLogger().info("LimitLevelWorld > 插件启动成功！");
    }

    @Override
    public void onDisable() {
        plugin = null;
        Bukkit.getScheduler().cancelTasks(this);
        this.getLogger().info("LimitLevelWorld已成功卸载！");
    }
}
