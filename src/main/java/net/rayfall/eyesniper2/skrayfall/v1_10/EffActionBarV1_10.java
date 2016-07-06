package net.rayfall.eyesniper2.skrayfall.v1_10;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;

import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffActionBarV1_10 extends Effect {

  // set action bar of %player% to %string%

  private Expression<Player> player;
  private Expression<String> text;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    player = (Expression<Player>) exp[0];
    text = (Expression<String>) exp[1];

    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    for (Player p : player.getAll(evt)) {
      IChatBaseComponent payload =
          ChatSerializer.a("{\"text\": \"" + text.getSingle(evt).replace("\"", "") + "\"}");
      PacketPlayOutChat packet = new PacketPlayOutChat(payload, (byte) 2);
      ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }
  }
}
