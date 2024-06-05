package me.takis967.peter;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PrefixListener implements Listener {
  private final Main plugin;
  
  public PrefixListener(Main plugin) {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    this.plugin.getPrefixManager().updatePlayerPrefix(event.getPlayer());
  }
}
