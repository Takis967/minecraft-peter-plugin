package me.takis967.peter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(String.valueOf(ChatColor.RED) + "Only players can use this command.");
      return true;
    } 
    Player player = (Player)sender;
    if (!player.hasPermission("fly.use")) {
      player.sendMessage(String.valueOf(ChatColor.RED) + "You do not have permission to fly.");
      return true;
    } 
    if (player.getAllowFlight()) {
      player.setAllowFlight(false);
      player.sendMessage(String.valueOf(ChatColor.RED) + "Flying disabled.");
    } else {
      player.setAllowFlight(true);
      player.sendMessage(String.valueOf(ChatColor.GREEN) + "Flying enabled.");
    } 
    return true;
  }
}
