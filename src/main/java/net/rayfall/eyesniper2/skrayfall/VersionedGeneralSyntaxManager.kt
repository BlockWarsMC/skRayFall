package net.rayfall.eyesniper2.skrayfall

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.util.SimpleEvent
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import ch.njol.skript.util.Version
import net.kyori.adventure.text.Component
import net.rayfall.eyesniper2.skrayfall.bossbar.*
import net.rayfall.eyesniper2.skrayfall.general.conditions.CondIsGlowing
import net.rayfall.eyesniper2.skrayfall.general.effects.EffMakePlayerGlow
import net.rayfall.eyesniper2.skrayfall.general.effects.EffUnglowPlayer
import net.rayfall.eyesniper2.skrayfall.general.events.ArmorStandDamageEvent
import net.rayfall.eyesniper2.skrayfall.general.events.ArmorStandListener
import net.rayfall.eyesniper2.skrayfall.general.expressions.ExprNoNbt
import net.rayfall.eyesniper2.skrayfall.general.expressions.ExprRayfallOffhand
import net.rayfall.eyesniper2.skrayfall.general.expressions.ExprShinyItem
import net.rayfall.eyesniper2.skrayfall.v1_17.EffActionBarV1_17
import net.rayfall.eyesniper2.skrayfall.v1_17.EffParticlesV1_17
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.eclipse.jdt.annotation.Nullable

class VersionedGeneralSyntaxManager(val plugin: Plugin) : SyntaxManagerInterface {

    val bossbarManager: BossBarManager = BossBarManager()

    override fun registerSyntax() {
        if (Skript.isRunningMinecraft(1, 8)) {
            plugin.logger.info("Enabling general 1.8+ bacon!")
            Skript.registerExpression(ExprShinyItem::class.java, ItemStack::class.java, ExpressionType.PROPERTY,
                    "shiny %itemstacks%")
            Skript.registerExpression(ExprNoNbt::class.java, ItemStack::class.java, ExpressionType.PROPERTY,
                    "%itemstacks% with no nbt")
            ArmorStandListener(plugin)
            Skript.registerEvent("armorstand damage", SimpleEvent::class.java, ArmorStandDamageEvent::class.java,
                    "armo[u]r stand damage")
            EventValues.registerEventValue(ArmorStandDamageEvent::class.java, Entity::class.java,
                    object : Getter<Entity, ArmorStandDamageEvent>() {
                        @Nullable
                        override fun get(evt: ArmorStandDamageEvent): Entity {
                            return evt.armorStand
                        }
                    }, 0)
            EventValues.registerEventValue(ArmorStandDamageEvent::class.java, Player::class.java,
                    object : Getter<Player, ArmorStandDamageEvent>() {
                        @Nullable
                        override fun get(evt: ArmorStandDamageEvent): Player {
                            return evt.damager
                        }
                    }, 0)
        }
        if (Skript.isRunningMinecraft(1, 9)) {
            plugin.logger.info("Getting the general 1.9+ bacon!")
            // New bossbar content
            Skript.registerEffect(EffCreateModernBossBar::class.java,
                    "create (bossbar|boss bar) title[d] %component% and id %string% for %players% "
                            + "[with (value|progress) %number%] [with colors %-raybossbarcolor%] "
                            + "[with style %-raybossbarstyle%] [with flags %-raybossbarflag%]")
            Skript.registerEffect(EffDeleteModernBossBar::class.java, "(remove|destroy) bossbar %string%")
            Skript.registerEffect(EffRemovePlayerFromBossBar::class.java,
                    "remove %players% [from] bossbar %string%")
            Skript.registerEffect(EffSetBossBar::class.java,
                    "(add|set) bossbar %string% for %player% ",
                    "(add|give) %players% [to] bossbar %string%")
            Skript.registerEffect(EffChangeBossBarValue::class.java,
                    "(set|edit) bossbar %string% (value|progress) to %number%")
            Skript.registerEffect(EffChangeBossBarColor::class.java,
                    "(set|edit) bossbar %string% colo[u]r to %raybossbarcolor%")
            Skript.registerEffect(EffChangeBossBarTitle::class.java,
                    "(set|edit) bossbar %string% (title|name) to %component%")
            Skript.registerEffect(EffChangeBossBarStyle::class.java,
                    "(set|edit) bossbar %string% style to %raybossbarstyle%")
            Skript.registerEffect(EffBossBarAddFlag::class.java,
                    "(add|set) [a] [the] flag %raybossbarflag% to [the] bossbar %string%")
            Skript.registerEffect(EffBossBarRemoveFlag::class.java,
                    "(remove|delete) [a] [the] flag %raybossbarflag% [to] [from] [the] bossbar %string%")
            Skript.registerEffect(EffBossBarShow::class.java, "(show|display|unhide) bossbar %string%")
            Skript.registerEffect(EffBossBarHide::class.java, "hide bossbar %string%")
            Skript.registerExpression(ExprBossBarTitle::class.java, Component::class.java, ExpressionType.SIMPLE,
                    "(title|name) of bossbar %component%")
            Skript.registerExpression(ExprBossBarValue::class.java, Number::class.java, ExpressionType.SIMPLE,
                    "(value|progress) of bossbar %string%")
            // Glowing API
            Skript.registerEffect(EffMakePlayerGlow::class.java, "make %player% glow")
            Skript.registerEffect(EffUnglowPlayer::class.java, "make %player% (unglow|stop glowing)")
            Skript.registerCondition(CondIsGlowing::class.java,"%player% glowing")
            // 1.9.2 Team stuff
            // Skript.registerEffect(Eff1_9MessageOnDeathRule.class,
            // "(show|display) death message[s] %teamoptionstatus% for team %string%");
            // Skript.registerEffect(Eff1_9NameTagVisibility.class,
            // "(show|display) (name tags|nametags) %teamoptionstatus% for team %string%");
            // Skript.registerEffect(Eff1_9TeamCollisionRule.class,
            // "(set|define) team collision [rule] as %teamoptionstatus% for team %string%");
            Skript.registerExpression(ExprRayfallOffhand::class.java, ItemStack::class.java, ExpressionType.SIMPLE,
                    "%player%['s] offhand", "item in %player%['s] offhand")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 17, 0)) {
            plugin.logger.info("Getting the extra special 1.17+ bacon!")
            Skript.registerEffect(EffParticlesV1_17::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(
                EffActionBarV1_17::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
        }
    }
}