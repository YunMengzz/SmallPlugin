package cn.startdream.kylin.randomhealth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/*
ah 血量(数值) [几率]
 */
public class CmdManager {
    public static void onCommand(CommandSender sender, Command cmd, String label, String[] args){
        try {
            if (args != null && args.length != 0) {
                int health = Integer.parseInt(args[0]);
                
            }
        }catch (Exception e) {
        }

        sender.sendMessage(ConfigManager.process("&6使用方法： ah 血量(数值) [几率]"));
    }
}
