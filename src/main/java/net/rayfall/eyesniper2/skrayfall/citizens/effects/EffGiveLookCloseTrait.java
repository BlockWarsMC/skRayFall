package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.trait.LookClose;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Citizens Look Close Trait")
@Description("Allow citizens to look at the closest player automatically. " +
        "This effect is toggleable, so to disable this trait for a citizen run this effect again.")
@RequiredPlugins("Citizens")
public class EffGiveLookCloseTrait extends Effect {

    // give npc %number% the look close trait

    private Expression<Number> id;
    private Expression<Number> range;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<Number>) exp[0];
        if (exp.length > 1)
            range = (Expression<Number>) exp[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        NPC npc = registry.getById(id.getSingle(evt).intValue());
        npc.addTrait(LookClose.class);
        if (range != null) {
            Number exprRange = range.getSingle(evt);
            if (exprRange != null)
                npc.getTrait(LookClose.class).setRange(exprRange.doubleValue());
        }
        npc.getTrait(LookClose.class).toggle();
    }

}
