package net.rayfall.eyesniper2.skrayfall.bossbar;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.chat.BungeeConverter;
import ch.njol.skript.util.chat.ChatMessages;
import ch.njol.util.Kleenean;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import java.util.HashSet;

@Name("Create ID based Bossbar")
@Description("Create a ID based Bossbar for a group of people.")
public class EffCreateModernBossBar extends Effect {

    // create (bossbar|boss bar) title[d] %string% and id %string% for %players% [with
    // (value|progress) %number%] [with colors %-bossbarcolors%] [with style %-bossbarstyle%] [with
    // flags %-bossbarflags%]

    private Expression<String> id;
    private Expression<Component> title;
    private Expression<Number> value;
    private Expression<RayFallBarColor> color;
    private Expression<RayFallBarFlag> flag;
    private Expression<RayFallBarStyle> style;
    private Expression<Player> players;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        title = (Expression<Component>) exp[0];
        value = (Expression<Number>) exp[3];
        id = (Expression<String>) exp[1];
        players = (Expression<Player>) exp[2];
        color = (Expression<RayFallBarColor>) exp[4];
        style = (Expression<RayFallBarStyle>) exp[5];
        flag = (Expression<RayFallBarFlag>) exp[6];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        Component titleComp = title.getSingle(evt);

        float progress = 0;
        if (value != null) {
            double vol = value.getSingle(evt).doubleValue();
            if (vol > 100) {
                vol = 100;
            } else if (vol < 0) {
                vol = 0;
            }
            progress = (float)vol;
        }

        BossBar.Overlay barStyle = BossBar.Overlay.PROGRESS;
        BossBar.Color barColor = BossBar.Color.PURPLE;
        BossBar.Flag barFlag;
        if (style != null) {
            barStyle = style.getSingle(evt).getKey();
        }
        if (color != null) {
            barColor = color.getSingle(evt).getKey();
        }

        BossBar bar = BossBar.bossBar(titleComp, progress / 100, barColor, barStyle);
        if (flag != null) {
            barFlag = flag.getSingle(evt).getKey();
            bar.addFlag(barFlag);
        }

        HashSet<Player> playerSet = new HashSet<>();
        for (Player p : players.getAll(evt)) {
            p.showBossBar(bar);
            playerSet.add(p);
        }
        Core.bossbarManager.createBossBar(id.getSingle(evt).replace("\"", ""), bar, playerSet);
    }
}
