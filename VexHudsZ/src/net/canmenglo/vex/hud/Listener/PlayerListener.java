package net.canmenglo.vex.hud.Listener;

import net.canmenglo.vex.hud.Main;
import net.canmenglo.vex.hud.huds;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener {
    public PlayerListener() {
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        (new BukkitRunnable() {
            public void run() {
                Main.lock.put(e.getPlayer(), false);
                huds.onHuds(e.getPlayer());
                huds.onHudss(e.getPlayer());
            }
        }).runTaskLater(Main.plugin, 120L);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        huds.deleteImageHud(e.getPlayer());
        huds.deleteTextHud(e.getPlayer());
    }
}