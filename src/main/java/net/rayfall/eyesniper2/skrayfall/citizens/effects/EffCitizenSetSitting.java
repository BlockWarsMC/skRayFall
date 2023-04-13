package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.denizenscript.denizen.npc.traits.SittingTrait;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Sittable;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Make Citizen Sit/Stand")
@Description("Make a NPC/Citizen Sit or Stand")
@RequiredPlugins({"Citizens", "Denizen"})
public class EffCitizenSetSitting extends Effect {

    // make citizen %number% sit [at location %-location%]
    // make citizen %number% stand

    private boolean shouldSit;
    private Expression<Number> id;
    private Expression<Location> locationExpr;


    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int ind, Kleenean arg2, ParseResult arg3) {
        shouldSit = arg3.mark == 0;
        id = (Expression<Number>) exp[0];
        if (exp.length > 1)
            locationExpr = (Expression<Location>) exp[1];
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

        Entity entity = npc.getEntity();
        if (entity instanceof Sittable sittable) {
            sittable.setSitting(shouldSit);
        } else {
            if (shouldSit) {
                SittingTrait trait = npc.getOrAddTrait(SittingTrait.class);

                Location loc = null;
                if (locationExpr != null) loc = locationExpr.getSingle(evt);
                if (loc == null) trait.sit();
                else trait.sit(loc);
            } else {
                if (npc.hasTrait(SittingTrait.class)) {
                    SittingTrait trait = npc.getOrAddTrait(SittingTrait.class);
                    trait.stand();
                    npc.removeTrait(SittingTrait.class);
                }
            }
        }
    }

}
