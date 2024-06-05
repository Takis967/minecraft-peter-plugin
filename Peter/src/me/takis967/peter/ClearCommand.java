package me.takis967.peter;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

public class ClearCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission("cc.use")) {
      sender.sendMessage(String.valueOf(ChatColor.RED) + "You don't have permission to use this command.");
      return true;
    } 
    int removedEntitiesCount = clearDroppedItems();
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Removed &a" + removedEntitiesCount + "&6 entities."));
    return true;
  }
  
  private int clearDroppedItems() {
    int removedEntitiesCount = 0;
    for (World world : Bukkit.getWorlds()) {
      for (Entity entity : world.getEntitiesByClass(Item.class)) {
        entity.remove();
        removedEntitiesCount++;
      } 
    } 
    return removedEntitiesCount;
  }
}
