package me.takis967.peter;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnCommand implements CommandExecutor {
  private final JavaPlugin plugin;
  
  public SpawnCommand(JavaPlugin plugin) {
    this.plugin = plugin;
  }
  
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command can only be run by a player.");
      return true;
    } 
    Player player = (Player)sender;
    if (player.isOp() || player.hasPermission("spawn.use")) {
      teleportToSpawn(player);
    } else {
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYou will be teleported to spawn in &c5 &bseconds."));
      countdownTeleport(player, 5);
    } 
    return true;
  }
  
  private void countdownTeleport(final Player player, int seconds) {
    new BukkitRunnable() {
        int count = seconds;

        @Override
        public void run() {
          if (count > 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bTeleporting in &c" + count + " &b..."));
            SpawnCommand.this.playTickSound(player);
            count--;
          } else {
            SpawnCommand.this.teleportToSpawn(player);
            cancel();
          } 
        }
      }.runTaskTimer(plugin, 0L, 20L);
  }
  
  private void playTickSound(Player player) {
    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1.0F, 1.0F);
  }
  
  private void teleportToSpawn(Player player) {
    player.teleport(player.getWorld().getSpawnLocation());
    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYou have been teleported to spawn."));
  }
}
