package cn.kylin.onlinegive.listener;

import cn.kylin.onlinegive.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Main.playerTime.put(event.getPlayer().getName(), 0);
    }


    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Main.playerTime.put(event.getPlayer().getName(), 0);
    }


}
