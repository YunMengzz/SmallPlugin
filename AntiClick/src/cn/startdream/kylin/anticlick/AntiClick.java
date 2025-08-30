package cn.startdream.kylin.anticlick;

import cn.startdream.kylin.anticlick.listener.AntiListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

public class AntiClick extends JavaPlugin {

    private static AntiClick plugin;
    public static File dataFolder;
    public static Configuration config;
    public static List<Integer> items;
    public static List<Integer> blocks;

    @Override
    public void onEnable() {
        if (plugin != null) {
            this.getLogger().log(Level.WARNING, "请勿启动两次本插件！");
            return;
        }
        saveDefaultConfig();
        dataFolder = this.getDataFolder();
        config = this.getConfig();
        blocks = ConfigManager.loadClickBlock();
        items = ConfigManager.loadClickItem();
        Bukkit.getPluginManager().registerEvents(new AntiListener(), this);
        this.getLogger().info("插件启动成功！");
    }

    @Override
    public void onDisable() {
        plugin = null;
        Bukkit.getScheduler().cancelTasks(this);
        this.getLogger().info("插件卸载成功！");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (args == null || args.length == 0) {
                sender.sendMessage(ChatColor.GOLD + "输入/ac reload 以重载插件!");
                return true;
            }
            if ("reload".equalsIgnoreCase(args[0])) {
                onDisable();
                onEnable();
            }
        }
        return true;
    }
}
