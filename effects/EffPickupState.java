package com.offline.bunchsk.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.entity.LivingEntity;

public class EffPickupState extends Effect {
        private Expression<LivingEntity> entity;
        private int patt;
        
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
                this.entity = (Expression<LivingEntity>) expr[0];
                this.patt = matchedPattern;
		return true;
	}
        
	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "set pickup state";
	}
        
	@Override
	protected void execute(Event e) {
            LivingEntity le = entity.getSingle(e);
            if (patt == 0){
                le.setCanPickupItems(true);
            } else {
                le.setCanPickupItems(false);
            }
	}
}