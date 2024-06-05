package me.takis967.peter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (cmd.getName().equalsIgnoreCase("help")) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f-&7=&f[&a&k.&f]&7=&f-&7[ &5&lPeter's Private Server &7]&f-&7=&f[&a&k.&f]&7=&f-"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r                 &c"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&ecalculate(&6Nether Portal Calculator.&e)"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r              &cusage: &e/calculate &7(&bX &7& &bZ &7optional.)"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&ebin(&6Throw away your trash.&e)"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r              &cusage: &e/bin"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&ecoinflip(&6Flip a coin to decide who goes first.&e)"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r              &cusage: &e/coinflip"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aIf you need help, please contact the server manager on discord:"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&oDiscord Name: &b@takis967&d&o."));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&oDiscord Link: &bhttps://discord.gg/eHz2qyDZn7&d&o."));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f-&7=&f[&a&k.&f]&7=&f-&7[ &5&lPeter's Private Server &7]&f-&7=&f[&a&k.&f]&7=&f-"));
        return true;
      } 
    } 
    return false;
  }
}
