package cn.startdream.kylin.anticlick;

import org.bukkit.configuration.Configuration;

import java.util.List;

public class ConfigManager {

    public static List<Integer> loadClickBlock(){
        Configuration config = AntiClick.config;
        List<Integer> list = config.getIntegerList("rightClickBlock");
        return list;
    }

    public static List<Integer> loadClickItem(){
        Configuration config = AntiClick.config;
        return config.getIntegerList("rightClickItem");
    }

}
