package me.takis967.peter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PvPCommand implements CommandExecutor {
  private static boolean pvpEnabled = true;
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission("pvp.use")) {
      sender.sendMessage(String.valueOf(ChatColor.RED) + "You don't have permission to use this command.");
      return true;
    } 
    pvpEnabled = !pvpEnabled;
    if (pvpEnabled) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lPvP&6: &a✔"));
    } else {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lPvP&6: &c✖"));
    } 
    for (Player player : Bukkit.getOnlinePlayers())
      player.setInvulnerable(!pvpEnabled); 
    return true;
  }
  
  public static boolean isPvPEnabled() {
    return pvpEnabled;
  }
}
