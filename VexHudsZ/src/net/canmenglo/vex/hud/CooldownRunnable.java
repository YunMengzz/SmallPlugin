package net.canmenglo.vex.hud;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CooldownRunnable{

    /**
     * true为可以切换
     * false为不可切换
     */
    public static HashMap<Player, Boolean> map = new HashMap<>();


    public static void runTask(Player p){
        map.put(p, false);
        final Player player = p;
        Bukkit.getScheduler().runTaskLater(Main.plugin, ()->{
            map.put(player, true);
        }, Main.plugin.getConfig().getInt("cooldown"));
    }


}
