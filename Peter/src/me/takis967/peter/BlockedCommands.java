package me.takis967.peter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockedCommands implements CommandExecutor, Listener {
  private final FileConfiguration config;
  
  public BlockedCommands(FileConfiguration config) {
    this.config = config;
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    return true;
  }
  
  @EventHandler
  public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    Player player = event.getPlayer();
    String command = event.getMessage().substring(1).split(" ")[0];
    if (!player.isOp() && !player.hasPermission("blockedcommands.bypass") && 
      isBlockedCommand(command)) {
      event.setCancelled(true);
      String customMessage = this.config.getString("blocked-message");
      if (customMessage != null)
        player.sendMessage(customMessage); 
    } 
  }
  
  private boolean isBlockedCommand(String command) {
    return this.config.getStringList("blocked-commands").contains(command);
  }
}
