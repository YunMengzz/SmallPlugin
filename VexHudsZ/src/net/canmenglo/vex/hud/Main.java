package net.canmenglo.vex.hud;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.canmenglo.vex.hud.Listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main plugin;
    public YamlConfiguration vexview;
    public static ConcurrentHashMap cd = new ConcurrentHashMap();
    // true为隐藏 false为未隐藏
    public static Map<Player, Boolean> lock = new HashMap<>();


    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }

        File file = new File(this.getDataFolder(), "config.yml");
        if (!file.exists()) {
            this.saveDefaultConfig();
        }

        Bukkit.getServer().getConsoleSender().sendMessage("VexHuds > 正在加载");
        String s = Bukkit.getPluginManager().getPlugin("VexView").getDescription().getVersion();
        if (s.contains("1.0") || s.contains("1.1") || s.contains("1.2") || s.contains("1.3") || s.contains("1.4") || s.contains("1.5") || s.contains("1.6.2") || s.contains("1.7") || s.contains("1.8") || s.contains("1.9.5")) {
            Bukkit.getConsoleSender().sendMessage("§6> §c错误：你的VexView插件版本为：" + s + "，本插件无法在此版本上运行，请使用VexView1.9.7或更高版本！");
            Bukkit.getConsoleSender().sendMessage("§6> §c正在关闭插件...");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        Bukkit.getServer().getConsoleSender().sendMessage("服务器版本: " + Bukkit.getVersion());
        this.getCommand("vhuds").setExecutor(new Commands());
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getServer().getConsoleSender().sendMessage("正在加载配置");
        this.saveDefaultConfig();
        Bukkit.getServer().getConsoleSender().sendMessage("检测是否存在可兼容插件");
        if (Bukkit.getPluginManager().isPluginEnabled("VexView")) {
            Bukkit.getConsoleSender().sendMessage("§a> VexView 已成功兼容！");
            Bukkit.getConsoleSender().sendMessage("§a  — 插件即将启动");
        } else {
            Bukkit.getConsoleSender().sendMessage("§a> 未找到 VexView 已跳过兼容！");
            Bukkit.getConsoleSender().sendMessage("§a  — 插件将无法使用");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        Bukkit.getServer().getConsoleSender().sendMessage("VexHuds > 加载成功");
    }

    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage("成功卸载");
        this.saveConfig();
    }

    public static Main getPlugin() {
        return plugin;
    }
}