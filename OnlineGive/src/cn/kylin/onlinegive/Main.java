package cn.kylin.onlinegive;

import cn.kylin.onlinegive.cmd.CmdExe;
import cn.kylin.onlinegive.config.ConfigLoader;
import com.connorlinfoot.actionbarapi.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {

    public static Main plugin = null;
    public static HashMap<Integer, OnlineData> onlineCmd = new HashMap<>();
    public static HashMap<String, Integer> playerTime = new HashMap<>();


    @Override
    public void onEnable() {
        if (plugin != null) {
            this.getLogger().warning("OnlineGive >> 请勿启动两次本插件");
            return;
        }
        plugin = this;

        saveDefaultConfig();
        onlineCmd = new HashMap<>();
        playerTime = new HashMap<>();
        ConfigLoader.loadConfig();

        // registerEvents    ConfigurationSerialization   setExecutor
        this.getCommand("og").setExecutor(new CmdExe());


        // runnable
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    playerTime.put(player.getName(), playerTime.getOrDefault(player.getName(), 0) + 1);
                    int time = playerTime.getOrDefault(player.getName(), 0);
                    for (int i : onlineCmd.keySet()) {
                        if (time % i == 0) {
                            // 执行指令 给提示信息 set PlayerLastTime
                            OnlineData data = onlineCmd.get(i);
                            String msg = data.getMsg();
                            List<String> cmds = data.getCmds();
                            for (String s : cmds) {
                                String cmd = s.replaceAll("<playerName>", player.getName()).replaceAll("<onlineTime>", time + "");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                            }
                            if (msg != null) {
                                String m = msg.replaceAll("<playerName>", player.getName()).replaceAll("<onlineTime>", time + "");
                                String s = ChatColor.translateAlternateColorCodes('&', m);
                                ActionBarAPI.sendActionBar(player, s);
                            }
                        }
                    }
                }
            }


        }.runTaskTimer(this, 0, 1200L);
    }


    @Override
    public void onDisable() {
        plugin = null;
        Bukkit.getScheduler().cancelTasks(this);

    }


    public static void reload(CommandSender sender){
        sender.sendMessage(ChatColor.BOLD + "" +  ChatColor.AQUA + "OnlineGive > 正在重载插件...");

        onlineCmd = new HashMap<>();
        playerTime = new HashMap<>();

        ConfigLoader.loadConfig();
        sender.sendMessage(ChatColor.BOLD + "" +  ChatColor.AQUA + "OnlineGive > 重载插件成功");
    }
}
