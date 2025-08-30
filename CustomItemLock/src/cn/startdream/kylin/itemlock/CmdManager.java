package cn.startdream.kylin.itemlock;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CmdManager {

    public static void onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (args != null && args.length >= 1) {
            if ("reload".equalsIgnoreCase(args[1])) {
                ItemLock.getPlugin().reload();
            }
        }
        if (!(sender instanceof Player)) {
            return;
        }
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null) {
            return;
        }
        List<String> lore = item.getItemMeta().getLore();
        String str = null;
        if (lore != null) {
            for (int i = 0; i < lore.size(); i++) {
                String s = lore.get(i);
                if (s.contains(ItemLock.lockLore)) {
                    str = s;
                }
            }
            if (str != null) {
                lore.remove(str);
            } else {
                // 无 已锁定
                lore.add(ItemLock.color + ItemLock.lockLore);
                p.sendMessage(ConfigManager.loadItemLockMsg());
            }
        } else {
            lore = new ArrayList<>();
            lore.add(ItemLock.color + ItemLock.lockLore);
            p.sendMessage(ConfigManager.loadItemLockMsg());
        }

        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
        p.getInventory().setItemInMainHand(item);
    }

}
