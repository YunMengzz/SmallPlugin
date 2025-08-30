package cn.startdream.kylin.randomhealth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public class RandomAddHealth extends JavaPlugin {

    private static RandomAddHealth plugin;
    public static File dataFolder;

    @Override
    public void onEnable() {
        if (plugin != null) {
            this.getLogger().log(Level.WARNING,"请勿启动两次RandomAddHealth插件");
            return;
        }
        dataFolder = getDataFolder();

        this.getLogger().info("RandomAddHealth插件启动成功！");
    }

    @Override
    public void onDisable() {
        plugin = null;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CmdManager.onCommand(sender, command, label, args);
        return true;
    }
}
