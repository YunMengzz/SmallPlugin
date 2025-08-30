package cn.kylin.vipbag;

import cn.kylin.vipbag.cmd.CmdExecutor;
import cn.kylin.vipbag.config.ConfigLoader;
import cn.kylin.vipbag.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin {

    public static Main plugin;
    // 1 <= height <= 5
    public static int height;
    // 总页数
    public static int totalAmount;
    // 初始解锁行数
    public static int initialAmount;
    // 1 <= height <= 6
    public static int vipHeight;

    public static HashMap<Integer, String> money;

    @Override
    public void onEnable() {
        if (plugin != null) {
            this.getLogger().info("请勿启动多个VIPBag！");
            this.getServer().shutdown();
            return;
        }
        plugin = this;
        PlayerData.datas = new HashMap<>();

        // Listener ConfigurationSerialization CommandExecutor
        ConfigurationSerialization.registerClass(PlayerData.class);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginCommand("vbag").setExecutor(new CmdExecutor());
        //saveConfig
        saveDefaultConfig();
        // load config
        ConfigLoader.loadConfig();

        this.getLogger().info("VIPBag成功加载！");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        this.getLogger().info("VIPBag已卸载！");
    }

    public static String getMoney(double j, int amount){
        int i = (int) (Math.floor(j / 9) + 1);
        return money.get(amount * height + i - 1);
    }
}
