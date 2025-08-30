package cn.kylin.onlinegive.cmd;

import cn.kylin.onlinegive.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdExe implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args != null && args.length == 1 && "reload".equalsIgnoreCase(args[0])) {
            if (sender.isOp()) {
                Main.reload(sender);
            }
            return true;
        }
        sender.sendMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "OnlineGive > 输入/og reload 来重载插件");
        return true;
    }
}
