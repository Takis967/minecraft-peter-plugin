package me.takis967.peter;

import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinFlipCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      String result = (new Random()).nextBoolean() ? "&3heads" : "&btails";
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYou flipped " + result + "&e!"));
    } else {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cOnly players can use this command."));
    } 
    return true;
  }
}
