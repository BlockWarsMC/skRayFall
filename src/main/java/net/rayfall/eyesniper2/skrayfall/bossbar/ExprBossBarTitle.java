package net.rayfall.eyesniper2.skrayfall.bossbar;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Title/Name of BossBar")
@Description("Get the name of an ID based Bossbar.")
public class ExprBossBarTitle extends SimpleExpression<Component> {

    private Expression<String> id;

    @Override
    public Class<? extends Component> getReturnType() {
        return Component.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<String>) exp[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    @Nullable
    protected Component[] get(Event evt) {
        Component barTitle = Core.bossbarManager.getBarTitle(id.getSingle(evt).replace("\"", ""));
        if (barTitle == null) return new Component[]{};

        return new Component[]{ barTitle };
    }
}
