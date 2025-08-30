package cn.kylin.vipbag.cmd;

import cn.kylin.vipbag.data.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (args == null || args.length == 0) return true;
        if (sender instanceof Player) {
            Player p = (Player) sender;
            PlayerData data = PlayerData.datas.get(p.getName());
            if ("open".equalsIgnoreCase(args[0])) {
                data.openInventory(0);
            } else if ("vip".equalsIgnoreCase(args[0])) {
                data.openVip();
            }
        }
        return true;
    }
}
