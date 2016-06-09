package com.offline.bunchsk.events;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptParser;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.inventivetalent.rpapi.ResourcePackStatusEvent;
import org.inventivetalent.rpapi.Status;


public class RpackListener implements Listener {

  public String toString(Event arg0, boolean arg1) {
    return null;
  }
  
  @EventHandler
  public void RpackCheck(ResourcePackStatusEvent evt) {
        if (((ResourcePackStatusEvent)evt).getStatus().equals(Status.DECLINED)){
            EvtRpackDecline event = new EvtRpackDecline(evt.getStatus(),evt.getPlayer(),evt.getHash());
            Bukkit.getPluginManager().callEvent(event);
        } else if (((ResourcePackStatusEvent)evt).getStatus().equals(Status.ACCEPTED)) {
            EvtRpackAccept event = new EvtRpackAccept(evt.getStatus(),evt.getPlayer(),evt.getHash());
            Bukkit.getPluginManager().callEvent(event); 
        } else if (((ResourcePackStatusEvent)evt).getStatus().equals(Status.FAILED_DOWNLOAD)) {
            EvtRpackFail event = new EvtRpackFail(evt.getStatus(),evt.getPlayer(),evt.getHash());
            Bukkit.getPluginManager().callEvent(event); 
        } else
        {
            if (((ResourcePackStatusEvent)evt).getStatus().equals(Status.SUCCESSFULLY_LOADED)) {
                EvtRpackLoad event = new EvtRpackLoad(evt.getStatus(),evt.getPlayer(),evt.getHash());
                Bukkit.getPluginManager().callEvent(event); }
            }
        }
        
        public boolean init(Literal<?>[] e, int arg1, SkriptParser.ParseResult arg2) {
          return true;
        }
}
