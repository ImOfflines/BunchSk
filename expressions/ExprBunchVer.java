
package com.offline.bunchsk.expressions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprBunchVer extends SimpleExpression<String>{
    
        public static final String BunchVer = "0.2";
        
	@Override
	public boolean isSingle() {
		return true;
	}
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
		return true;
	}
	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "bunchsk ver";
	}
	@Override
	@Nullable
	protected String[] get(Event e) {
                return new String[]{BunchVer};
	}
}