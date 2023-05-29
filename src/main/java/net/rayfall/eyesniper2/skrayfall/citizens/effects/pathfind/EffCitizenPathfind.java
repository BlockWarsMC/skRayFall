package net.rayfall.eyesniper2.skrayfall.citizens.effects.pathfind;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Make Citizen Pathfind")
@Description({"Make citizens pathfind to the specified location"})
@RequiredPlugins("Citizens")
@Examples({"command /here <integer>:",
        "\ttrigger:",
        "\t\tmake citizen arg 1 pathfind to location of player"
})
public class EffCitizenPathfind extends Effect {

    private Expression<Number> id;
    private Expression<Location> targetLocation;
    private Expression<Number> speed;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<Number>) exp[0];
        targetLocation = (Expression<Location>) exp[1];
        if (exp.length > 2)
            speed = (Expression<Number>) exp[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event evt, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        NPC npc = registry.getById(id.getSingle(evt).intValue());
        if (npc != null) {
            npc.getNavigator().getDefaultParameters().useNewPathfinder(true);
            npc.getNavigator().getDefaultParameters().avoidWater(true);
            npc.getNavigator().setTarget(targetLocation.getSingle(evt));
            if (speed != null) {
                Number single = speed.getSingle(evt);
                if (single != null) {
                    npc.getNavigator().getLocalParameters().baseSpeed(single.floatValue());
                }
            }
        }
    }

}
