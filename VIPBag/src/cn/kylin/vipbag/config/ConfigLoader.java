package cn.kylin.vipbag.config;

import cn.kylin.vipbag.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;

public class ConfigLoader {

    public static void loadConfig(){
        FileConfiguration config = Main.plugin.getConfig();
        Main.height = config.getInt("height");
        Main.totalAmount = config.getInt("totalAmount");
        Main.initialAmount = config.getInt("initialAmount");
        Main.vipHeight = config.getInt("vipHeight");
        Main.money = parseMoney(config.getStringList("money"));

    }

    public static HashMap<Integer, String> parseMoney(List<String> list){
        try {
            HashMap<Integer, String> map = new HashMap<>();
            for (String s : list) {
                String[] args = s.split(":");
                map.put(Integer.parseInt(args[0]), args[1]);
            }
            return map;
        } catch (Exception e) {
            Main.plugin.getLogger().info("VIPBag初始化money config.yml出现问题 请仔细检查配置！");
        }
        return new HashMap<>();
    }

}
