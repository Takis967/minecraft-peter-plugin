package me.takis967.peter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvseeCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (sender instanceof Player) {
      if (args.length == 1) {
        Player target = Bukkit.getPlayer(args[0]);
        if (target != null) {
          ((Player)sender).openInventory((Inventory)target.getInventory());
          sender.sendMessage(String.valueOf(ChatColor.GREEN) + "You are now viewing " + String.valueOf(ChatColor.GREEN) + "'s inventory.");
          return true;
        } 
        sender.sendMessage(String.valueOf(ChatColor.RED) + "That player is not online.");
        return false;
      } 
      sender.sendMessage(String.valueOf(ChatColor.RED) + "Incorrect command usage. Correct usage: /invsee <player>");
      return false;
    } 
    sender.sendMessage(String.valueOf(ChatColor.RED) + "This command can only be used by players.");
    return false;
  }
}
