package me.takis967.peter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class BinCommand implements CommandExecutor, Listener {
  private final Map<UUID, Inventory> binInventories = new HashMap<>();
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command can only be executed by players.");
      return true;
    } 
    Player player = (Player)sender;
    openbinInventory(player);
    return true;
  }
  
  private void openbinInventory(Player player) {
    Inventory inventory = Bukkit.createInventory((InventoryHolder)player, 54, "Trash Bin");
    this.binInventories.put(player.getUniqueId(), inventory);
    player.openInventory(inventory);
  }
  
  @EventHandler
  public void onInventoryClose(InventoryCloseEvent event) {
    Player player = (Player)event.getPlayer();
    Inventory inventory = event.getInventory();
    if (this.binInventories.containsKey(player.getUniqueId()) && inventory.equals(this.binInventories.get(player.getUniqueId()))) {
      deletebinItems(inventory);
      this.binInventories.remove(player.getUniqueId());
    } 
  }
  
  private void deletebinItems(Inventory inventory) {
    byte b;
    int i;
    ItemStack[] arrayOfItemStack;
    for (i = (arrayOfItemStack = inventory.getContents()).length, b = 0; b < i; ) {
      ItemStack item = arrayOfItemStack[b];
      if (item != null)
        item.setAmount(0); 
      b++;
    } 
  }
}
