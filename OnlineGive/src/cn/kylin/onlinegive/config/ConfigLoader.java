package cn.kylin.onlinegive.config;

import cn.kylin.onlinegive.Main;
import cn.kylin.onlinegive.OnlineData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Set;

public class ConfigLoader {

    public static void loadConfig(){
        FileConfiguration config = Main.plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("online");
        Set<String> keys = section.getKeys(false);
        List<String> list;
        for (String key : keys) {
            int time = section.getInt(key + ".time");
            List<String> cmds = section.getStringList(key + ".commands");
            String msg = section.getString(key + ".msg");
            Main.onlineCmd.put(time, new OnlineData(cmds, msg));
        }
    }

}
