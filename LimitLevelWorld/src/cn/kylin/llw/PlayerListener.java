package cn.kylin.llw;

import cn.kylin.dreamlevel.api.DlApi;
import cn.kylin.dreamlevel.api.data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerToWorld(PlayerTeleportEvent event){
        if (ConfigLoader.containsWorld(event.getTo().getWorld().getName())) {
            LimitData data = ConfigLoader.getData(event.getTo().getWorld().getName());
            Player p = event.getPlayer();
            if (data.getLevel() <= 0) return;
            if (p == null) return;
            PlayerData playerData = DlApi.getPlayerData(p.getName(), data.getLevelName());
            if (playerData == null) return;
            if (!p.isOp() && playerData.getCurrentLevel() < data.getLevel()) {
                event.setCancelled(true);
                String _msg = Main.plugin.getConfig().getString("language.limitMsg");
                String msg = ChatColor.translateAlternateColorCodes('&', _msg).replaceAll("<limitLevel>", data.getLevel() + "");
                p.sendMessage(msg);
            }
        }

    }


}
