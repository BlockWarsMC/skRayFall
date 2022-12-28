package net.rayfall.eyesniper2.skrayfall.bossbar;

import ch.njol.skript.Skript;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BossBarManager {

    private HashMap<String, BossBar> barMap = new HashMap<>();
    private HashMap<String, Set<Player>> bossBarPlayers = new HashMap<>();

    public BossBarManager() {

    }

    /**
     * Saves a bossbar in the BossBarManager for future use.
     *
     * @param id      The ID text for the bossbar, duplicate ID's will be over written.
     * @param bossbar The bossbar object to be stored.
     */
    void createBossBar(String id, BossBar bossbar, Set<Player> players) {
        if (barMap.containsKey(id)) {
            removeBar(id);
        }
        bossBarPlayers.put(id, players);
        barMap.put(id, bossbar);
    }

    /**
     * Add a flag to a bossbar in the BossBarManager through the stored ID.
     *
     * @param id   The ID text for the bossbar.
     * @param flag The BarFlag to be added.
     */
    void addFlag(String id, BossBar.Flag flag) {
        BossBar bar = barMap.get(id);
        if (bar != null) {
            bar.addFlag(flag);
            barMap.put(id, bar);
        }
    }

    /**
     * Remove a flag from a bossbar in the BossBarManager through the stored ID.
     *
     * @param id   The ID text for the bossbar.
     * @param flag The BarFlag to be added.
     */
    void removeFlag(String id, BossBar.Flag flag) {
        BossBar bar = barMap.get(id);
        if (bar != null) {
            bar.removeFlag(flag);
            barMap.put(id, bar);
        }
    }

    /**
     * Add an array of players to a bossbar in the BossBarManager through the stored ID.
     *
     * @param id      The ID text for the bossbar.
     * @param players Array of players to be added to the bossbar
     */
    void addPlayers(String id, Player[] players) {
        BossBar bar = barMap.get(id);
        Set<Player> playerSet = bossBarPlayers.get(id);
        if (bar != null && playerSet != null) {
            for (Player p : players) {
                playerSet.add(p);
                p.showBossBar(bar);
            }
            bossBarPlayers.put(id, playerSet);
            barMap.put(id, bar);
        }
    }

    /**
     * Remove an array of players from a bossbar in the BossBarManager through the stored ID.
     *
     * @param id      The ID text for the bossbar.
     * @param players Array of players to be removed from the bossbar
     */
    void removePlayers(String id, Player[] players) {
        BossBar bar = barMap.get(id);
        Set<Player> playerSet = bossBarPlayers.get(id);
        if (bar != null && playerSet != null) {
            for (Player p : players) {
                playerSet.remove(p);
                p.hideBossBar(bar);
            }
            bossBarPlayers.put(id, playerSet);
            barMap.put(id, bar);
        }
    }

    /**
     * Remove a bossbar from the BossBarManager and remove it from all players who were assigned it.
     *
     * @param id The ID text for the bossbar.
     */
    void removeBar(String id) {
        BossBar bar = barMap.get(id);
        Set<Player> playerSet = bossBarPlayers.get(id);
        if (bar != null) {
            for (Player p : playerSet) {
                p.hideBossBar(bar);
            }
            bossBarPlayers.remove(id);
            barMap.remove(id);
        }
    }

    /**
     * Changed the title of a bossbar from the BossBarManager through the stored ID.
     *
     * @param id    The ID text for the bossbar.
     * @param title The new title for the bossbar with color codes.
     */
    void changeTitle(String id, Component title) {
        BossBar bar = barMap.get(id);
        if (bar != null) {
            bar.name(title);
            barMap.put(id, bar);
        }
    }

    /**
     * Changed the progress or fill of a bossbar from the BossBarManager through the stored ID.
     *
     * @param id       The ID text for the bossbar.
     * @param progress The progress or fill to be set from 0 - 100.
     */
    void changeValue(String id, double progress) {
        BossBar bar = barMap.get(id);
        if (bar != null) {
            if (progress > 100) {
                progress = 100;
            } else if (progress < 0) {
                progress = 0;
            }
            bar.progress((float) (progress / 100));
            barMap.put(id, bar);
        }
    }

    /**
     * Changed the color of a bossbar from the BossBarManager through the stored ID.
     *
     * @param id    The ID text for the bossbar.
     * @param color The BarColor to be used.
     */
    void changeColor(String id, BossBar.Color color) {
        BossBar bar = barMap.get(id);
        if (bar != null) {
            bar.color(color);
            barMap.put(id, bar);
        }
    }

    /**
     * Changed the style of a bossbar from the BossBarManager through the stored ID.
     *
     * @param id    The ID text for the bossbar.
     * @param style The BarStyle to be used.
     */
    void changeStyle(String id, BossBar.Overlay style) {
        BossBar bar = barMap.get(id);
        if (bar != null) {
            bar.overlay(style);
            barMap.put(id, bar);
        }
    }

    /**
     * Hide a bossbar from the BossBarManager through the stored ID for all players
     * who were assigned it.
     *
     * @param id The ID text for the bossbar.
     */
    void hideBar(String id) {
        BossBar bar = barMap.get(id);
        Set<Player> playerSet = bossBarPlayers.get(id);
        if (bar != null) {
            for (Player player : playerSet) {
                player.hideBossBar(bar);
            }
            barMap.put(id, bar);
        }
    }

    /**
     * Show a bossbar from the BossBarManager through the stored ID for all players
     * who were assigned it.
     *
     * @param id The ID text for the bossbar.
     */
    void showBar(String id) {
        BossBar bar = barMap.get(id);
        Set<Player> playerSet = bossBarPlayers.get(id);
        if (bar != null) {
            for (Player player : playerSet) {
                player.hideBossBar(bar);
            }
            barMap.put(id, bar);
        }
    }

    /**
     * Get the title of a stored bossbar from the BossBarManager through the ID.
     *
     * @param id The ID text for the bossbar.
     * @return Title of the bossbar
     */
    Component getBarTitle(String id) {
        BossBar bar = barMap.get(id);
        if (bar != null) {
            return bar.name();
        }
        return null;
    }

    /**
     * Get the progress or fill of a stored bossbar from the BossBarManager through the ID.
     *
     * @param id The ID text for the bossbar.
     * @return The progress or fill of the bossbar from 0 - 100.
     */
    Float getBarProgress(String id) {
        BossBar bar = barMap.get(id);
        if (bar != null) {
            return bar.progress();
        }
        return null;
    }

    public void dumpData() {
        barMap.clear();
    }

}
