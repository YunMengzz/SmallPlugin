package cn.kylin.llw;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdExe implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args == null || args.length == 0) {
            sender.sendMessage(ChatColor.BLUE + "输入/llw reload  重载LimitLevelWorld插件！");
        } else if ("reload".equalsIgnoreCase(args[0])) {
            if (sender.isOp()) {
                ConfigLoader.load();
                sender.sendMessage(ChatColor.GREEN + "LimitLevelWorld > 重载成功！");
            }
        }

        return true;
    }
}
