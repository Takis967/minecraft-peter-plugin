package me.takis967.peter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrefixCommand implements CommandExecutor {
  private final Main plugin;
  
  public PrefixCommand(Main plugin) {
    this.plugin = plugin;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("prefix")) {
      if (!(sender instanceof Player)) {
        sender.sendMessage(String.valueOf(ChatColor.RED) + "This command can only be used by players.");
        return true;
      } 
      Player player = (Player)sender;
      if (args.length < 2) {
        player.sendMessage(String.valueOf(ChatColor.RED) + "Usage: /prefix <player> <prefix>");
        return true;
      } 
      String playerName = args[0];
      String prefix = args[1];
      this.plugin.getPrefixManager().setPlayerPrefix(playerName, prefix);
      player.sendMessage(String.valueOf(ChatColor.GREEN) + "Prefix set successfully!");
      Player targetPlayer = Bukkit.getPlayer(playerName);
      if (targetPlayer != null)
        this.plugin.getPrefixManager().updatePlayerPrefix(targetPlayer); 
      return true;
    } 
    return false;
  }
}
