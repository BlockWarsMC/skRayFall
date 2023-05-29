package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.Skript;
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
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Equipment.EquipmentSlot;

import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

@Name("Citizen Hold")
@Description("This will make a Citizen hold an object in their hand.")
@RequiredPlugins("Citizens")
@Examples({"command /npcholdsword:",
        "\ttrigger:",
        "\t\tmake citizen last created citizen id hold iron sword"})
public class EffCitizenHold extends Effect {

    // make citizen %number% hold %itemstack%

    private Expression<Number> id;
    private Expression<ItemStack> item;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<Number>) exp[0];
        item = (Expression<ItemStack>) exp[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        NPC getter = registry.getById(id.getSingle(evt).intValue());
        if (getter.getEntity().getType().equals(EntityType.PLAYER)
                || getter.getEntity().getType() == EntityType.ENDERMAN
                || getter.getEntity().getType() == EntityType.ZOMBIE
                || getter.getEntity().getType() == EntityType.SKELETON) {
            Equipment equ = getter.getOrAddTrait(Equipment.class);
            equ.set(EquipmentSlot.HAND, item.getSingle(evt));
        } else {
            Skript.error("Entity must be equipable!");
        }

    }

}
