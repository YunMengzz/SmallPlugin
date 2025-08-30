package cn.kylin.vipbag.data;

import cn.kylin.vipbag.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class PlayerData implements ConfigurationSerializable {

    public static Map<String, PlayerData> datas;

    // inventoryName: VIPBag_playerName_Amount
    public List<Inventory> invs;
    public Player player;
    public Inventory vip;

    public PlayerData(Player player) {
        if (player == null) return;
        this.player = player;
        invs = new ArrayList<>();
        datas.put(player.getName(), this);
    }

    /**
     * open Player Inv Method
     * @param amount  页数 从零开始
     */
    public void openInventory(int amount){
        if (player == null) return;
        if (player.getPlayer() == null) return;
        if (amount < 0 || amount >= invs.size()) return;
        player.getPlayer().openInventory(invs.get(amount));
    }

    @Override
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("playername", player.getName());
        HashMap<String, List<ItemStack>> is = new HashMap<>();
        for (int i = 0; i < invs.size(); i++) {
            Inventory inventory = invs.get(i);
            List<ItemStack> list = new ArrayList<>();
            for (ItemStack stack : inventory) {
                list.add(stack);
            }
            map.put(i + "", list);
        }
        map.put("invs", is);
        List<ItemStack> list = new ArrayList<>();
        for (ItemStack stack : vip) {
            list.add(stack);
        }
        map.put("vip", list);
        return map;
    }


    public static PlayerData deserialize(Map<String, Object> map){
        String playerName = (String) map.get("playername");
        Player player = Bukkit.getPlayer(playerName);
        PlayerData playerData = new PlayerData(player);
        HashMap<String, List<ItemStack>> is = (HashMap<String, List<ItemStack>>) map.get("invs");
        Set<String> keySet = is.keySet();
        int max = 0;
        for (String s : keySet) {
            int i = Integer.parseInt(s);
            if (i > max) max = i;
        }
        for (int i = 0; i <= max; i++) {
            List<ItemStack> list =  is.get(i + "");
            // inventoryName: VIPBag_playerName_Amount
            if (list == null) continue;
            Inventory inventory = Bukkit.createInventory(player, (Main.height + 1) * 9, "VIPBag_" + playerName + "_" + i);
            for (int i1 = 0; i1 < list.size(); i1++) {
                inventory.setItem(i1, list.get(i1));
            }
            playerData.invs.set(i, inventory);
        }
        List<ItemStack> vip = (List<ItemStack>) map.get("vip");
        Inventory inventory = Bukkit.createInventory(player, Main.vipHeight * 9, "VIPBag_" + playerName + "_vip");
        for (int i1 = 0; i1 < vip.size(); i1++) {
            inventory.setItem(i1, vip.get(i1));
        }
        playerData.vip = inventory;
        return playerData;
    }

    public static void createNewPlayerData(Player player){
        PlayerData data = new PlayerData(player);
        int init = Main.initialAmount;
        int height = Main.height;
        List<Inventory> invs = new ArrayList<>();
        int i = 0;

        ItemStack item1 = new ItemStack(Material.SLIME_BALL);
        item1.setAmount(1);
        ItemMeta itemMeta = item1.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "点我到下一页");
        item1.setItemMeta(itemMeta);

        ItemStack item2 = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta meta = item2.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "未解锁本行");

        ItemStack item3 = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta meta1 = item3.getItemMeta();
        meta1.setDisplayName(" ");
        item3.setItemMeta(meta1);


        while(i < Main.totalAmount){
            if (init >= height) {
                init -= height;
                Inventory inv = Bukkit.createInventory(player, (Main.height + 1) * 9, "VIPBag_" + player.getName() + "_" + i);
                inv.setItem((Main.height + 1) * 9 - 1, item1);
                for (int j = Main.height * 9; j < (Main.height + 1) * 9; j++) {
                    inv.setItem(j, item3);
                }
                invs.add(inv);
            } else {
                Inventory inv = Bukkit.createInventory(player, (Main.height + 1) * 9, "VIPBag_" + player.getName() + "_" + i);
                for (int j = init * 9; j < Main.height * 9; j++) {
                    String str;
                    if (Main.getMoney(j, i) == null) {
                        str = "100";
                    } else str = Main.getMoney(j, i).endsWith("d") ? Main.getMoney(j, i).substring(0, Main.getMoney(j, i).length() - 1) + "点券" : Main.getMoney(j, i) + "金币";
                    meta.setLore(Arrays.asList(ChatColor.GOLD + "点击解锁", ChatColor.GOLD + "将花费" + str + "解锁"));
                    item2.setItemMeta(meta);
                    inv.setItem(j, item2);
                }
                inv.setItem((Main.height + 1) * 9 - 1, item1);
                for (int j = Main.height * 9; j < (Main.height + 1) * 9; j++) {
                    inv.setItem(j, item3);
                }
                invs.add(inv);
                init = 0;
            }
            i++;
        }
        data.invs = invs;
        data.vip = Bukkit.createInventory(player, Main.vipHeight * 9, "VIPBag_" + player.getName() + "_vip");

    }

    public void openVip(){
        player.openInventory(vip);
    }


}
