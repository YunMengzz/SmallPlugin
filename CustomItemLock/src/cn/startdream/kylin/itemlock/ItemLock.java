package cn.startdream.kylin.itemlock;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ItemLock extends JavaPlugin {

    private static ItemLock plugin;
    public static Configuration config;
    public static File dataFolder;
    public static String color;
    public static String noTradeLore;
    public static String lockLore;

    @Override
    public void onEnable() {
        if (plugin != null) {
            this.getLogger().info("请勿启动两次本插件");
        }
        plugin = this;
        saveDefaultConfig();
        config = getConfig();
        dataFolder = getDataFolder();
        ConfigManager.init();
        color = ConfigManager.loadColor();
        noTradeLore = ConfigManager.loadNoTradeLore();
        lockLore = ConfigManager.loadLockLore();
        Bukkit.getPluginManager().registerEvents(new LockListener(), this);
        getLogger().info("ItemLock v1.0.1已成功加载！");
    }

    @Override
    public void onDisable() {
        plugin = null;
        Bukkit.getScheduler().cancelTasks(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CmdManager.onCommand(sender, command, label, args);
        return true;
    }

    public void reload(){
        onDisable();
        onEnable();
    }

    public static ItemLock getPlugin(){
        return plugin;
    }
}
