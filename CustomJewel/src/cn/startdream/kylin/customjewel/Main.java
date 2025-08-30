package cn.startdream.kylin.customjewel;

import cn.startdream.kylin.customjewel.data.JData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class Main extends JavaPlugin {

    private static Main plugin;
    public static Configuration config;
    public static File dataFolder;
    public static List<JData> datas;

    @Override
    public void onEnable() {
        if (plugin != null) {
            this.getLogger().info("请勿启动两次本插件！");
        }
        plugin = this;
        saveDefaultConfig();
        config = getConfig();
        dataFolder = getDataFolder();
        datas = ConfigManager.loadConfig();
    }

    @Override
    public void onDisable() {
        plugin = null;
        Bukkit.getScheduler().cancelTasks(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args == null || args.length == 0) {
            sender.sendMessage("输入/cj reload 以重载插件");
            return true;
        }
        if ("reload".equalsIgnoreCase(args[0])) {
            sender.sendMessage(ChatColor.GREEN + "[CustomJewel] 正在重载插件！");
            onDisable();
            onEnable();
            sender.sendMessage(ChatColor.GREEN + "[CustomJewel] 重载完毕！");
        }
        return true;
    }
}
