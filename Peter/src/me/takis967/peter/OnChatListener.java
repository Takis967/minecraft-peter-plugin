package me.takis967.peter;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnChatListener implements Listener {
  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    Player player = event.getPlayer();
    String playerName = player.getDisplayName();
    String message = event.getMessage();
    if (player.isOp()) {
      String formattedMessage = ChatColor.translateAlternateColorCodes('&', "&7" + playerName + "&7: &r" + message);
      event.setFormat(formattedMessage);
    } else {
      String formattedMessage = ChatColor.GRAY + playerName + ChatColor.GRAY + ": " + ChatColor.WHITE + message;
      event.setFormat(formattedMessage);
    } 
  }
}
