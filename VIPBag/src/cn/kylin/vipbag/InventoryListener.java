package cn.kylin.vipbag;

import cn.kylin.vipbag.data.PlayerData;
import cn.kylin.vipbag.data.YmlManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        PlayerData data = YmlManager.getPlayerData(event.getPlayer());
        if (data == null) {
            PlayerData.createNewPlayerData(event.getPlayer());
            YmlManager.insertPlayerData(PlayerData.datas.get(event.getPlayer().getName()));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        YmlManager.insertPlayerData(PlayerData.datas.get(event.getPlayer().getName()));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if (e.getWhoClicked().getOpenInventory().getTitle() != null && e.getWhoClicked().getOpenInventory().getTitle().startsWith("VIPBag")) {
            String[] args = e.getWhoClicked().getOpenInventory().getTitle().split("_");
            String str = args[2];
            if ("vip".equalsIgnoreCase(str)) {
                if(e.getRawSlot()<0 || e.getRawSlot()>=e.getInventory().getSize() || e.getInventory()==null)
                    return;
                Player p = (Player)e.getWhoClicked();

                // vip Bag
                int size = e.getInventory().getSize();
                int ul = 0;
                for (int i = size - 1; i >= 0; i--) {
                    if (p.hasPermission("vipbag.vip." + i)) {
                        ul = i;
                        break;
                    }
                }
                if (e.getRawSlot() > ul) {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "您还未解锁此格子！");
                    return;
                }

            } else {
                int amount = Integer.parseInt(str);
                if(e.getRawSlot()<0 || e.getRawSlot()>=e.getInventory().getSize() || e.getInventory()==null)
                    return;
                Player p = (Player)e.getWhoClicked();
                if (e.getRawSlot() == e.getInventory().getSize() - 1) {
                    e.setCancelled(true);
                    Inventory inv = PlayerData.datas.get(p.getName()).invs.get(amount + 1);
                    if (inv == null) {
                        p.sendMessage(ChatColor.BLUE + "您已在最后一页了");
                        p.closeInventory();
                        return;
                    }
                    p.openInventory(inv);
                } else if (e.getRawSlot() >= e.getInventory().getSize() - 9) {
                    e.setCancelled(true);
                }
                if (e.getInventory().getItem(e.getRawSlot()) == null) return;
                if ((ChatColor.RED + "未解锁本行").equalsIgnoreCase(e.getInventory().getItem(e.getRawSlot()).getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    String money = Main.getMoney(e.getRawSlot(), amount);
                    if (money == null) return;
                    if (money.endsWith("d")) {
                        // 点
                        int i = Integer.parseInt(money.substring(0, money.length() - 1));
                        // delete points
                        String playerPoints = PlaceholderAPI.setPlaceholders(p, "%playerpoints_points%");
                        int m = -1;
                        try{
                            m = Integer.parseInt(playerPoints);
                        } catch (Exception ex) {

                        }
                        if (i > m) {
                            p.sendMessage(ChatColor.RED + "您的点券不足!");
                            return;
                        }

                        String cmd = "points take " + p.getName() + " " + i;
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                        p.sendMessage(ChatColor.GREEN + "解锁成功！ 消耗点券: " + i);
                        int slot = e.getRawSlot();
                        int value = (slot / 9) * 9;
                        for (int j = value; j < value + 9; j++) {
                            e.getInventory().setItem(j, null);
                        }
                    } else{
                        int i = Integer.parseInt(money);
                        String vault_eco_balance = PlaceholderAPI.setPlaceholders(p, "%vault_eco_balance%");
                        int m = -1;
                        try{
                            m = Integer.parseInt(vault_eco_balance);
                        } catch (Exception ex) {

                        }
                        if (i > m) {
                            p.sendMessage(ChatColor.RED + "您的金币不足!");
                            return;
                        }

                        String cmd = "money take " + p.getName() + " " + i;
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                        p.sendMessage(ChatColor.GREEN + "解锁成功！ 消耗金币: " + i);
                        int slot = e.getRawSlot();
                        int value = (slot / 9) * 9;
                        for (int j = value; j < value + 9; j++) {
                            e.getInventory().setItem(j, null);
                        }
                    }
                }
            }
        }
    }

}
