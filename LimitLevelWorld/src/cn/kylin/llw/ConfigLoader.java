package cn.kylin.llw;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigLoader {

    // WorldName, level
    public static List<LimitData> list;


    public static void load(){
        list = new ArrayList<>();

        FileConfiguration config = Main.plugin.getConfig();
        config.set("version", "1.0");
        List<String> l = config.getStringList("levelWorld");
        for (String str : l) {
            String[] args = str.split(":");
            if (args.length != 3) {
                Main.plugin.getLogger().info("LimitLevelWorld > 您的配置文件出现问题！ 问题所在文本：" + str);
            } else {
                list.add(new LimitData(args[0], args[1], Integer.parseInt(args[2])));
            }
        }
    }

    public static boolean containsWorld(String worldName){
        if (worldName == null) return false;
        for (LimitData data : list) {
            if (worldName.equals(data.getWorldName())) return true;
        }
        return false;
    }

    public static LimitData getData(String worldName){
        if (worldName == null) return null;
        for (LimitData data : list) {
            if (worldName.equals(data.getWorldName())) return data;
        }
        return null;
    }


}
