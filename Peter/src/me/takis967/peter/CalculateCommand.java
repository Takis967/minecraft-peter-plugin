package me.takis967.peter;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CalculateCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command can only be executed by players.");
      return true;
    } 
    Player player = (Player)sender;
    if (player.getWorld().getEnvironment() == World.Environment.NORMAL) {
      if (args.length >= 2) {
        int overworldX = Integer.parseInt(args[0]);
        int overworldZ = Integer.parseInt(args[1]);
        int netherX = overworldX / 8;
        int netherZ = overworldZ / 8;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Nether Coordinates: &3X&6: &b" + netherX + "&6, &3Z&6: &b" + netherZ));
      } else {
        int overworldX = player.getLocation().getBlockX();
        int overworldZ = player.getLocation().getBlockZ();
        int netherX = overworldX / 8;
        int netherZ = overworldZ / 8;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Nether Coordinates: &3X&6: &b" + netherX + "&6, &3Z&6: &b" + netherZ));
      } 
    } else if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
      if (args.length >= 2) {
        int netherX = Integer.parseInt(args[0]);
        int netherZ = Integer.parseInt(args[1]);
        int overworldX = netherX * 8;
        int overworldZ = netherZ * 8;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aOverworld Coordinates: &3X&a: &b" + overworldX + "&a, &3Z&a: &b" + overworldZ));
      } else {
        int netherX = player.getLocation().getBlockX();
        int netherZ = player.getLocation().getBlockZ();
        int overworldX = netherX * 8;
        int overworldZ = netherZ * 8;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aOverworld Coordinates: &3X&a: &b" + overworldX + "&a, &3Z&a: &b" + overworldZ));
      } 
    } else if (player.getWorld().getEnvironment() == World.Environment.THE_END) {
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou can't execute this command in this dimension."));
    } 
    return true;
  }
}
