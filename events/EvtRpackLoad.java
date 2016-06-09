package com.offline.bunchsk.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.inventivetalent.rpapi.Status;

public class EvtRpackLoad extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private boolean isCancelled;
    private Status status;
    private String hash;
  
  public HandlerList getHandlers() {
    return handlers;
  }
  
  public static HandlerList getHandlerList() {
    return handlers;
  }
  
  public EvtRpackLoad(Status status, Player p, String hash) {
    this.player = p;
    this.status = status;
    this.hash = hash;
    this.isCancelled = false; 
  }
  
  public Player getPlayer() {
    return this.player;
  }
      
  public String getHash() {
      return this.hash;
  }
  
  public boolean isCancelled() {
    return this.isCancelled;
  }
  
  public void setCancelled(boolean b) {
    this.isCancelled = b;
  }
}
