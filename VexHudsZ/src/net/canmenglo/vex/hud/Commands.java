package net.canmenglo.vex.hud;

import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.components.VexImage;
import lk.vexview.hud.VexImageShow;
import lk.vexview.hud.VexShow;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("后台无法执行此插件指令");
            return true;
        } else {
            Player p = (Player)sender;
            if (args == null || args.length == 0) {
                if (cmd.getName().equalsIgnoreCase("vhuds") && p.isOp()) {
                    p.sendMessage("§6§m-----------------------------------------");
                    p.sendMessage("§7/vhuds reload §8- §6重载插件");
                    p.sendMessage("§6§m-----------------------------------------");
                } else {
                    p.sendMessage("§c你无法使用这个指令,原因你太丑!");
                }
            } else if (cmd.getName().equalsIgnoreCase("vhuds") && "reload".equalsIgnoreCase(args[0]) && p.isOp()) {
                Main.plugin.reloadConfig();
                for (Player zxp : Bukkit.getOnlinePlayers()) {
                    huds.onHuds(zxp);
                    huds.onHudss(zxp);
                }
                sender.sendMessage("§7配置已重载");
                return false;
            } else if ("hide".equalsIgnoreCase(args[0])) {
                Boolean b = Main.lock.get(p);
                if (CooldownRunnable.map.get(p) != null && (!CooldownRunnable.map.get(p))) {
                    p.sendMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "切换按钮正在冷却中...");
                    return true;
                }
                if (b){
                    huds.deleteImageHud(p);
                    huds.onHuds(p);
                    huds.onHudss(p);
                    Main.lock.put(p, false);
                    p.sendMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "界面提示按钮 " + ChatColor.YELLOW + "已显示");
                } else {
                    huds.deleteImageHud(p);
                    huds.deleteTextHud(p);
                    String lock = Main.plugin.getConfig().getString("lockHudId");
                    String url = Main.plugin.getConfig().getString("Huds.Image." + lock + ".Url");
                    int id = Main.plugin.getConfig().getInt("Huds.Image." + lock + ".Id");
                    int x = Main.plugin.getConfig().getInt("Huds.Image." + lock + ".x");
                    int y = Main.plugin.getConfig().getInt("Huds.Image." + lock + ".y");
                    int w = Main.plugin.getConfig().getInt("Huds.Image." + lock + ".w");
                    int h = Main.plugin.getConfig().getInt("Huds.Image." + lock + ".h");
                    int xs = Main.plugin.getConfig().getInt("Huds.Image." + lock + ".xs");
                    int ys = Main.plugin.getConfig().getInt("Huds.Image." + lock + ".ys");
                    VexImage im = new VexImage(url, x, y, w, h, xs, ys);
                    VexShow huds = new VexImageShow(id, im, 0);
                    VexViewAPI.sendHUD(p, huds);
                    Main.lock.put(p, true);
                    p.sendMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "界面提示按钮 " + ChatColor.YELLOW + "已隐藏");
                }
                CooldownRunnable.runTask(p);
            }

            return true;
        }
    }
}