package me.takis967.peter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AnnounceCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 0) {
      sender.sendMessage(String.valueOf(ChatColor.RED) + "Usage: /announce <message>");
      return true;
    } 
    String message = ChatColor.translateAlternateColorCodes('&', String.join(" ", (CharSequence[])args));
    String formattedMessage = ChatColor.translateAlternateColorCodes('&', "&dPeter's Private Server&7: &r" + message);
    Bukkit.broadcastMessage(formattedMessage);
    return true;
  }
}
