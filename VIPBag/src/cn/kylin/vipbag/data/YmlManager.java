package cn.kylin.vipbag.data;

import cn.kylin.vipbag.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class YmlManager {

    public static PlayerData getPlayerData(Player player){
        File file = new File(Main.plugin.getDataFolder(), "data/" + player.getName() + ".yml");
        if (!file.exists()) return null;
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return (PlayerData) config.get("data");
    }

    public static void insertPlayerData(PlayerData data){
        File file = new File(Main.plugin.getDataFolder(), "data/" + data.player.getName() + ".yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("data", data);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
