package net.rayfall.eyesniper2.skrayfall.citizens.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.DocumentationId;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Citizen is spawned")
@Description("Checks if a citizen has been spawned")
@RequiredPlugins("Citizens")
@DocumentationId("CondCitizenIsSpawned")
public class CondIsNpcSpawned extends Condition {

    private Expression<Number> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<Number>) exp[0];
        setNegated((arg3.mark == 1));
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    public boolean check(Event evt) {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        NPC npc = registry.getById(id.getSingle(evt).intValue());
        if (npc != null) {
            return isNegated() == npc.isSpawned();
        }
        return !isNegated();
    }
}
