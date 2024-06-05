package me.takis967.peter;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinAndLeaveHandler implements Listener {
  private final Main plugin;
  
  public JoinAndLeaveHandler(Main plugin) {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    event.setJoinMessage(null);
    String customJoinMessage = ChatColor.translateAlternateColorCodes('&', "&b" + event.getPlayer().getName() + "&7 (&a+&7)");
    this.plugin.getServer().broadcastMessage(customJoinMessage);
  }
  
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    event.setQuitMessage(null);
    String customQuitMessage = ChatColor.translateAlternateColorCodes('&', "&b" + event.getPlayer().getName() + "&7 (&c-&7)");
    this.plugin.getServer().broadcastMessage(customQuitMessage);
  }
}
