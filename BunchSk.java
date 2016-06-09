package com.offline.bunchsk;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

import com.offline.bunchsk.effects.EffBroadcastPerm;
import com.offline.bunchsk.effects.EffSurfaceHigh;
import com.offline.bunchsk.effects.EffSurfaceNear;
import com.offline.bunchsk.effects.EffPickupState;

import com.offline.bunchsk.events.EvtRpackAccept;
import com.offline.bunchsk.events.EvtRpackDecline;
import com.offline.bunchsk.events.EvtRpackFail;
import com.offline.bunchsk.events.EvtRpackLoad;
import com.offline.bunchsk.events.RpackListener;
import com.offline.bunchsk.effects.EffSendRpack;

import com.offline.bunchsk.expressions.ExprBunchVer;
import com.offline.bunchsk.expressions.ExprPluginCheck;
import com.offline.bunchsk.utils.PluginCheck;

import com.offline.bunchsk.conditions.CondCanPickup;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class BunchSk extends JavaPlugin {
            
    @Override
    public void onEnable(){
        Skript.registerAddon(this);
        Classes.registerClass(new ClassInfo<PluginCheck>(PluginCheck.class, "pluginenabled").parser(new Parser<PluginCheck>() {
            @Override
		public String getVariableNamePattern() {
		return ".+";
		}

		@Override
		public PluginCheck parse(String s, ParseContext cont) {
		try {
                    return PluginCheck.valueOf(s.replace(" ", "_").trim().toUpperCase());
		} catch (IllegalArgumentException e) {
                    return null;
		}
		}

		@Override
		public String toString(PluginCheck pc, int i) {
                    return pc.name().replace("_", " ").toLowerCase();
		}

		@Override
		public String toVariableNameString(PluginCheck pc) {
                    return pc.name().replace("_", " ").toLowerCase();
		}
		}));
    
    
        Skript.registerEffect(EffSurfaceHigh.class, "surf[ace] %player% to [the] high[est] [loc[ation]]");
        Skript.registerEffect(EffSurfaceNear.class, "surf[ace] %player% to [the] near[est] [loc[ation]]");
        Skript.registerEffect(EffSendRpack.class, "apply (rp|resource[ ]pack) [from] %string% to %player% with hash %string%");
        Skript.registerEffect(EffPickupState.class, "allow %entity% to pick[ ]up","disallow %entity% to pick[ ]up");
        Skript.registerEffect(EffBroadcastPerm.class, "broad[cast] %string% to [all] players with perm[ission] %string%");
        Skript.registerExpression(ExprBunchVer.class, String.class, ExpressionType.SIMPLE, "bunch[sk] ver[sion]");
        Skript.registerExpression(ExprPluginCheck.class, PluginCheck.class, ExpressionType.SIMPLE, "plugin %string%");
        Bukkit.getServer().getPluginManager().registerEvents(new RpackListener(), this);
        Skript.registerEvent("Resource Decline Event", SimpleEvent.class, EvtRpackDecline.class, "resource[ ][pack] decline");
        EventValues.registerEventValue(EvtRpackDecline.class, Player.class, new Getter<Player, EvtRpackDecline>() 
        {
            @Override
            public Player get(EvtRpackDecline e)
            {
              return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(EvtRpackDecline.class, String.class, new Getter<String, EvtRpackDecline>() 
        {
            @Override
            public String get(EvtRpackDecline e)
            {
              return e.getHash();
            }
        }, 0);
        Skript.registerEvent("Resource Accept Event", SimpleEvent.class, EvtRpackAccept.class, "resource[ ][pack] accept");
        EventValues.registerEventValue(EvtRpackAccept.class, Player.class, new Getter<Player, EvtRpackAccept>() 
        {
            public Player get(EvtRpackAccept e)
            {
              return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(EvtRpackAccept.class, String.class, new Getter<String, EvtRpackAccept>() 
        {
            public String get(EvtRpackAccept e)
            {
              return e.getHash();
            }
        }, 0);
        Skript.registerEvent("Resource Failed Download Event", SimpleEvent.class, EvtRpackFail.class, "resource[ ][pack] (dl|download) fail");
        EventValues.registerEventValue(EvtRpackFail.class, Player.class, new Getter<Player, EvtRpackFail>() 
        {
            public Player get(EvtRpackFail e)
            {
              return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(EvtRpackFail.class, String.class, new Getter<String, EvtRpackFail>() 
        {
            public String get(EvtRpackFail e)
            {
              return e.getHash();
            }
        }, 0);
        Skript.registerEvent("Resource Successful Download Event", SimpleEvent.class, EvtRpackLoad.class, "resource[ ][pack] (dl|download)");
        EventValues.registerEventValue(EvtRpackLoad.class, Player.class, new Getter<Player, EvtRpackLoad>() 
        {
            public Player get(EvtRpackLoad e)
            {
              return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(EvtRpackLoad.class, String.class, new Getter<String, EvtRpackLoad>() 
        {
            public String get(EvtRpackLoad e)
            {
              return e.getHash();
            }
        }, 0);
        Skript.registerCondition(CondCanPickup.class, "%entity% can pick[ ]up", "%entity% can't pick[ ]up");
        if (Bukkit.getServer().getVersion().contains("1.9")) {
            Skript.registerCondition(CondCanPickup.class, "%entity% is collidable", "%entity% isn't collidable");
        }
    }
    
    @Override
    public void onDisable(){
        
    }
}
