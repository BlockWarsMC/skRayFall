package net.rayfall.eyesniper2.skrayfall.bossbar;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.chat.BungeeConverter;
import ch.njol.skript.util.chat.ChatMessages;
import ch.njol.util.Kleenean;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Set Bossbar Title/Name")
@Description("Set the name of an ID based Bossbar.")
public class EffChangeBossBarTitle extends Effect {

    // (set|edit) bossbar %string% (title|name) to %string%

    private Expression<String> id;
    private Expression<String> title;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<String>) exp[0];
        title = (Expression<String>) exp[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        String titleText = title.getSingle(evt).replace("\"", "");
        Component titleComp = BungeeComponentSerializer.get().deserialize(BungeeConverter.convert(ChatMessages.parse(titleText)));

        Core.bossbarManager.changeTitle(id.getSingle(evt).replace("\"", ""),
                titleComp);
    }
}
