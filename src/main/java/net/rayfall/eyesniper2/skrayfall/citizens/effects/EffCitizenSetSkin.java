package net.rayfall.eyesniper2.skrayfall.citizens.effects;

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

import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Change Citizen Skin")
@Description({"Change the skin of a citizen by:",
        "* ID",
        "* Minecraft Username",
        "This will change the skin of an NPC to a given Minecraft username"})
@RequiredPlugins("Citizens")
@Examples({"command /npcskin:",
        "\ttrigger:",
        "\t\tcreate a citizen named \"notch\" at location of player",
        "\t\tchange citizen last created citizen skin to \"eyesniper2\""})
public class EffCitizenSetSkin extends Effect {

    private Expression<Number> id;
    private Expression<String> toSkin;
    private Expression<String> exprSignature;
    private int pattern;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        pattern = arg1;
        id = (Expression<Number>) exp[0];
        toSkin = (Expression<String>) exp[1];
        if (arg1 == 1) {
            exprSignature = (Expression<String>) exp[2];
        }
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
        if (pattern == 0) {
            npc.data().setPersistent(NPC.Metadata.PLAYER_SKIN_UUID,
                    toSkin.getSingle(evt).replace("\"", ""));
        } else {
            SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
            skinTrait.setSkinPersistent(npc.getName(), exprSignature.getSingle(evt), toSkin.getSingle(evt));
        }

        if (npc.getEntity() == null) return;
        Location respawnloc = npc.getEntity().getLocation();
        npc.despawn();
        npc.spawn(respawnloc);
    }


}
