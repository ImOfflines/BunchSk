package com.offline.bunchsk.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import javax.annotation.Nullable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffBroadcastPerm extends Effect {
        private Expression<String> text;
        private Expression<String> perm;
        
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
		text = (Expression<String>) expr[0];
                perm = (Expression<String>) expr[1];
		return true;
	}
        
	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "broadcast to with perms";
	}
        
	@Override
	protected void execute(Event e) {
                for (Player p: Bukkit.getServer().getOnlinePlayers()){
                    if (p.hasPermission(perm.getSingle(e))){
                        p.sendMessage(text.getSingle(e));
                    }
                }
	}
}