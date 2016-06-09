package com.offline.bunchsk.expressions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.Bukkit;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import com.offline.bunchsk.utils.PluginCheck;

public class ExprPluginCheck extends SimpleExpression<PluginCheck>{
        
        private Expression<String> plugin;
    
	@Override
	public boolean isSingle() {
		return true;
	}
	@Override
	public Class<? extends PluginCheck> getReturnType() {
		return PluginCheck.class;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
                plugin = (Expression<String>) expr[0];
		return true;
	}
	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "bunchsk ver";
	}
        
	@Override
	@Nullable
	protected PluginCheck[] get(Event e) {
            if (Bukkit.getServer().getPluginManager().isPluginEnabled(plugin.getSingle(e))){
                return new PluginCheck[]{PluginCheck.ENABLED};
            } else {
                return new PluginCheck[]{PluginCheck.DISABLED};
            } 
            }
}