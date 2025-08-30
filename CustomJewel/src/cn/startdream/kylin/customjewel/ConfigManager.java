package cn.startdream.kylin.customjewel;

import cn.startdream.kylin.customjewel.data.JData;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConfigManager {

    public static List<JData> loadConfig(){
        Configuration config = Main.config;
        ConfigurationSection section = config.getConfigurationSection("itemList");
        ArrayList<JData> list = new ArrayList<>();
        if (section != null) {
            Set<String> keys = section.getKeys(false);
            if (keys != null) {
                JData j;
                for (String temp : keys) {
                    int id = section.getInt(temp + ".Id");
                    int data = section.getInt(temp + ".Data");
                    String name = section.getString(temp + ".Name");
                    List<String> lore = section.getStringList(temp + ".Lore");
                    List<String> type = section.getStringList(temp + ".Type");
                    List<String> addLore = section.getStringList(temp + ".addLore");
                    String identify = section.getString(temp + ".Identify");
                    j = new JData();
                    j.setId(id);
                    j.setData(data);
                    j.setName(name);
                    j.setLore(lore);
                    j.setType(type);
                    j.setAddLore(addLore);
                    j.setIdentify(identify);
                    list.add(j);
                }

            }
            return list;
        }
        return null;
    }

}
