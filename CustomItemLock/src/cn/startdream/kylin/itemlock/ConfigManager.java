package cn.startdream.kylin.itemlock;

import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;

public class ConfigManager {

    public static void init(){
        Configuration config = ItemLock.config;
        if (config.get("lang.color") == null) {
            config.set("lang.color", "0");
        }
        if (config.get("lang.noTrade") == null) {
            config.set("lang.noTrade", "不可交易物品");
        }
        if (config.get("lang.lockLore") == null) {
            config.set("lang.lockLore", "已锁定");
        }
        ItemLock.getPlugin().saveConfig();
    }

    public static String loadItemLockMsg(){
        Configuration config = ItemLock.config;
        String msg = config.get("lang.itemlock") == null ? "此物品已上锁，再次输入/itemlock解锁" : config.getString("lang.itemlock");
        return process(msg);
    }

    public static String loadColor(){
        Configuration config = ItemLock.config;
        return ChatColor.COLOR_CHAR + (config.get("lang.color") == null ? "1" : config.getString("lang.color"));
    }

    public static String process(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String loadNoTradeLore(){
        Configuration config = ItemLock.config;
        return config.get("lang.noTrade") == null ? "不可交易物品" : config.getString("lang.noTrade");
    }

    public static String loadLockLore(){
        Configuration config = ItemLock.config;
        return config.get("lang.lockLore") == null ? "已锁定" : config.getString("lang.lockLore");
    }

}
