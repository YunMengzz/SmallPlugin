package net.canmenglo.vex.hud;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.components.VexImage;
import lk.vexview.gui.components.VexText;
import lk.vexview.hud.VexImageShow;
import lk.vexview.hud.VexShow;
import lk.vexview.hud.VexTextShow;
import org.bukkit.entity.Player;

public class huds {
    public huds() {
    }

    public static void onHuds(Player e) {
        Set set2 = Main.plugin.getConfig().getConfigurationSection("Huds.Image").getKeys(false);
        Iterator localIterator = set2.iterator();

        while(localIterator.hasNext()) {
            String s = (String)localIterator.next();
            String url = Main.plugin.getConfig().getString("Huds.Image." + s + ".Url");
            int id = Main.plugin.getConfig().getInt("Huds.Image." + s + ".Id");
            int x = Main.plugin.getConfig().getInt("Huds.Image." + s + ".x");
            int y = Main.plugin.getConfig().getInt("Huds.Image." + s + ".y");
            int w = Main.plugin.getConfig().getInt("Huds.Image." + s + ".w");
            int h = Main.plugin.getConfig().getInt("Huds.Image." + s + ".h");
            int xs = Main.plugin.getConfig().getInt("Huds.Image." + s + ".xs");
            int ys = Main.plugin.getConfig().getInt("Huds.Image." + s + ".ys");
            VexImage im = new VexImage(url, x, y, w, h, xs, ys);
            VexShow huds = new VexImageShow(id, im, 0);
            VexViewAPI.sendHUD(e, huds);
        }

    }

    public static void onHudss(Player e) {
        Set set2 = Main.plugin.getConfig().getConfigurationSection("Huds.Text").getKeys(false);
        Iterator localIterator = set2.iterator();

        while(localIterator.hasNext()) {
            String s = (String)localIterator.next();
            int ids = Main.plugin.getConfig().getInt("Huds.Text." + s + ".Id");
            List<String> text = Main.plugin.getConfig().getStringList("Huds.Text." + s + ".text".replace("&", "§").replace("%player%", e.getPlayer().getName()));
            int xss = Main.plugin.getConfig().getInt("Huds.Text." + s + ".x");
            int yss = Main.plugin.getConfig().getInt("Huds.Text." + s + ".y");
            VexText im = new VexText(xss, yss, text);
            VexShow huds = new VexTextShow(ids, im, 0);
            VexViewAPI.sendHUD(e, huds);
        }

    }

    public static void deleteImageHud(Player e) {
        Set set2 = Main.plugin.getConfig().getConfigurationSection("Huds.Image").getKeys(false);
        Iterator localIterator = set2.iterator();

        while(localIterator.hasNext()) {
            String s = (String)localIterator.next();
            int id = Main.plugin.getConfig().getInt("Huds.Image." + s + ".Id");
            VexViewAPI.removeHUD(e, id);
        }

    }

    public static void deleteTextHud(Player e) {
        Set set2 = Main.plugin.getConfig().getConfigurationSection("Huds.Text").getKeys(false);
        Iterator localIterator = set2.iterator();

        while(localIterator.hasNext()) {
            String s = (String)localIterator.next();
            int ids = Main.plugin.getConfig().getInt("Huds.Text." + s + ".Id");
            VexViewAPI.removeHUD(e, ids);
        }

    }

    public static void addHud(Player e){}
}