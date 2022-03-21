package net.rayfall.eyesniper2.skrayfall.general.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.eclipse.jdt.annotation.Nullable;

@Name("Armor Value")
@Description("Get a players armor value.")
public class ExprArmorValue extends SimplePropertyExpression<Player, Number> {

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    @Nullable
    public Number convert(Player evt) {
        Number armorValue = 0;
        // Check boots
        if (evt.getInventory().getBoots() != null) {
            if (evt.getInventory().getBoots().getType() == Material.LEATHER_BOOTS) {
                armorValue = armorValue.doubleValue() + 0.5;
            } else if (evt.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
                armorValue = armorValue.doubleValue() + 0.5;
            } else if (evt.getInventory().getBoots().getType() == Material.IRON_BOOTS) {
                armorValue = armorValue.doubleValue() + 1;
            } else if (evt.getInventory().getBoots().getType() == Material.GOLDEN_BOOTS) {
                armorValue = armorValue.doubleValue() + 0.5;
            } else if (evt.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS) {
                armorValue = armorValue.doubleValue() + 1.5;
            }
        }
        // Check legs
        if (evt.getInventory().getLeggings() != null) {
            if (evt.getInventory().getLeggings().getType() == Material.LEATHER_LEGGINGS) {
                armorValue = armorValue.doubleValue() + 1;
            } else if (evt.getInventory().getLeggings().getType() == Material.CHAINMAIL_LEGGINGS) {
                armorValue = armorValue.doubleValue() + 2;
            } else if (evt.getInventory().getLeggings().getType() == Material.IRON_LEGGINGS) {
                armorValue = armorValue.doubleValue() + 2.5;
            } else if (evt.getInventory().getLeggings().getType() == Material.GOLDEN_LEGGINGS) {
                armorValue = armorValue.doubleValue() + 1.5;
            } else if (evt.getInventory().getLeggings().getType() == Material.DIAMOND_LEGGINGS) {
                armorValue = armorValue.doubleValue() + 3;
            }
        }
        // Check chest plate
        if (evt.getInventory().getChestplate() != null) {
            if (evt.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE) {
                armorValue = armorValue.doubleValue() + 1.5;
            } else if (evt.getInventory().getChestplate().getType() == Material.CHAINMAIL_CHESTPLATE) {
                armorValue = armorValue.doubleValue() + 2.5;
            } else if (evt.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) {
                armorValue = armorValue.doubleValue() + 3;
            } else if (evt.getInventory().getChestplate().getType() == Material.GOLDEN_CHESTPLATE) {
                armorValue = armorValue.doubleValue() + 2.5;
            } else if (evt.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE) {
                armorValue = armorValue.doubleValue() + 4;
            }
        }
        // Check helm
        if (evt.getInventory().getHelmet() != null) {
            if (evt.getInventory().getHelmet().getType() == Material.LEATHER_HELMET) {
                armorValue = armorValue.doubleValue() + 0.5;
            } else if (evt.getInventory().getHelmet().getType() == Material.CHAINMAIL_HELMET) {
                armorValue = armorValue.doubleValue() + 1;
            } else if (evt.getInventory().getHelmet().getType() == Material.IRON_HELMET) {
                armorValue = armorValue.doubleValue() + 1;
            } else if (evt.getInventory().getHelmet().getType() == Material.GOLDEN_HELMET) {
                armorValue = armorValue.doubleValue() + 1;
            } else if (evt.getInventory().getHelmet().getType() == Material.DIAMOND_HELMET) {
                armorValue = armorValue.doubleValue() + 1.5;
            }
        }
        return armorValue;
    }

    @Override
    protected String getPropertyName() {
        return "Armor value";
    }

}
