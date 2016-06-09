package com.offline.bunchsk.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.inventivetalent.rpapi.ResourcePackAPI;
import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffSendRpack extends Effect {
        private Expression<Player> player;
        private Expression<String> link;
        private Expression<String> hash;
        
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
                link = (Expression<String>) expr[0];
                player = (Expression<Player>) expr[1];
                hash = (Expression<String>) expr[2];
		return true;
	}
        
	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "send resourcepack";
	}
        
	@Override
	protected void execute(Event e) {
                ResourcePackAPI.setResourcepack(player.getSingle(e), link.getSingle(e), hash.getSingle(e));
	}
}