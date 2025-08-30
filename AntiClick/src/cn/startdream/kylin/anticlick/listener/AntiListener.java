package cn.startdream.kylin.anticlick.listener;

import cn.startdream.kylin.anticlick.AntiClick;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AntiListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                int id = event.getItem().getType().getId();
                if (AntiClick.items.contains(id)) {
                    event.setCancelled(true);
                    return;
                }
            }
            if (event.getClickedBlock() != null) {
                if (AntiClick.blocks.contains(event.getClickedBlock().getType().getId())) {
                    event.setCancelled(true);
                }
            }

        }
    }

}
