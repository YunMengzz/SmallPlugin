package cn.startdream.kylin.itemlock;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class LockListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDropItem(PlayerDropItemEvent event){
        ItemStack item = event.getItemDrop().getItemStack();
        List<String> lore = item.getItemMeta().getLore();
        for (int i = 0; i < lore.size(); i++) {
            String s = lore.get(i);
            if (s.contains(ItemLock.lockLore)) {
                event.getPlayer().sendMessage(ConfigManager.loadItemLockMsg());
                event.setCancelled(true);
                return;
            } else if (s.contains(ItemLock.noTradeLore)) {
                String name = event.getPlayer().getName();
                lore.add("itemlock:" + name);
                //event.getPlayer().sendMessage(ConfigManager.process("&6该物品不可交易！"));
                updateLore(lore, event.getItemDrop());
                return;
            }
        }
    }
    @EventHandler
    public void onPlayerPickUpItem(PlayerPickupItemEvent event){
        ItemStack item = event.getItem().getItemStack();
        List<String> lore = item.getItemMeta().getLore();
        boolean flag = false;
        for (int i = 0; i < lore.size(); i++) {
            String str = lore.get(i);
            if (str.contains(ItemLock.noTradeLore)) {
                flag = true;
            }
            if (str.contains("itemlock:")) {
                String name = str.split(":")[1];
                if (name == null || !flag) {
                    lore.remove(str);
                    updateLore(lore, event.getItem());
                    return;
                }
                if (!name.equals(event.getPlayer().getName())) {
                    event.getPlayer().sendMessage(ConfigManager.process("&6该物品不可交易！"));
                    event.setCancelled(true);
                    event.getItem().setPickupDelay(40);
                    return;
                }
                // 本人捡起 删除标记lore
                lore.remove(str);
                updateLore(lore, event.getItem());
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent event){
        ItemStack item = event.getCurrentItem();
        if (item == null) {
            return;
        }
        List<String> lore = item.getItemMeta().getLore();
        if (lore == null) {
            return;
        }
        for (String s : lore) {
            if (s.contains(ItemLock.lockLore)) {
                event.getWhoClicked().sendMessage(ConfigManager.loadItemLockMsg());
                event.setCancelled(true);
                return;
            }
        }
    }

    private void updateLore(List<String> lore, Item item){
        ItemStack stack = item.getItemStack();
        ItemMeta meta = stack.getItemMeta();
        meta.setLore(lore);
        stack.setItemMeta(meta);
        item.setItemStack(stack);
    }

}
