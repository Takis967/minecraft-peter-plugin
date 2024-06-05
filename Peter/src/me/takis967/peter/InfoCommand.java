package me.takis967.peter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class InfoCommand implements CommandExecutor, Listener {
  private File playerDataFile = new File(Bukkit.getServer().getPluginManager().getPlugin("Peter").getDataFolder(), "player_data.yml");
  
  private FileConfiguration playerDataConfig;
  
  public InfoCommand() {
    if (!this.playerDataFile.exists())
      try {
        this.playerDataFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }  
    this.playerDataConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.playerDataFile);
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    updatePlayerIP(player);
    updatePlayerCoordinates(player);
  }
  
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    updatePlayerCoordinates(player);
  }
  
  private void updatePlayerIP(Player player) {
    String ipAddress = player.getAddress().getAddress().getHostAddress();
    this.playerDataConfig.set(player.getUniqueId().toString() + ".ipAddress", ipAddress);
    try {
      this.playerDataConfig.save(this.playerDataFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  private void updatePlayerCoordinates(Player player) {
    int x = player.getLocation().getBlockX();
    int y = player.getLocation().getBlockY();
    int z = player.getLocation().getBlockZ();
    this.playerDataConfig.set(player.getUniqueId().toString() + ".coordinates", "&eX: &6" + x + "&e, Y: &6" + y + "&e, Z: &6" + z);
    try {
      this.playerDataConfig.save(this.playerDataFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    String coordinatesMessage;
    if (!(sender instanceof Player)) {
      sender.sendMessage(String.valueOf(ChatColor.RED) + "Only players can use this command.");
      return true;
    } 
    Player player = (Player)sender;
    if (args.length != 1) {
      player.sendMessage(String.valueOf(ChatColor.RED) + "Usage: /info <player>");
      return true;
    } 
    String targetName = args[0];
    OfflinePlayer targetPlayer = null;
    byte b;
    int i;
    OfflinePlayer[] arrayOfOfflinePlayer;
    for (i = (arrayOfOfflinePlayer = Bukkit.getOfflinePlayers()).length, b = 0; b < i; ) {
      OfflinePlayer offlinePlayer = arrayOfOfflinePlayer[b];
      if (offlinePlayer.getName() != null && offlinePlayer.getName().equalsIgnoreCase(targetName)) {
        targetPlayer = offlinePlayer;
        break;
      } 
      b++;
    } 
    if (targetPlayer == null) {
      player.sendMessage(String.valueOf(ChatColor.RED) + "Player not found or has never played on this server.");
      return true;
    } 
    UUID targetUUID = targetPlayer.getUniqueId();
    String targetIPAddress = getLastIPAddress(targetUUID);
    Date lastPlayed = new Date(targetPlayer.getLastPlayed());
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
    String lastPlayedFormatted = sdf.format(lastPlayed);
    if (targetPlayer.isOnline()) {
      Player onlinePlayer = targetPlayer.getPlayer();
      int x = onlinePlayer.getLocation().getBlockX();
      int y = onlinePlayer.getLocation().getBlockY();
      int z = onlinePlayer.getLocation().getBlockZ();
      coordinatesMessage = ChatColor.translateAlternateColorCodes('&', "&eCurrent Coordinates: X: &6" + x + "&e, Y: &6" + y + "&e, Z: &6" + z);
    } else {
      String lastCoordinates = getLastCoordinates(targetUUID);
      coordinatesMessage = ChatColor.translateAlternateColorCodes('&', "&eLast Known Coordinates: " + lastCoordinates);
    } 
    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&ePlayer Information for &b" + targetName + "&e:"));
    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eUUID: &b" + String.valueOf(targetUUID)));
    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eIP Address: &b" + targetIPAddress));
    player.sendMessage(coordinatesMessage);
    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eLast DC : &b" + lastPlayedFormatted));
    return true;
  }
  
  private String getLastCoordinates(UUID playerUUID) {
    String lastCoordinates = this.playerDataConfig.getString(playerUUID.toString() + ".coordinates");
    return (lastCoordinates != null) ? lastCoordinates : "&cUnknown";
  }
  
  private String getLastIPAddress(UUID playerUUID) {
    String lastIPAddress = this.playerDataConfig.getString(playerUUID.toString() + ".ipAddress");
    return (lastIPAddress != null) ? lastIPAddress : "&cUnknown";
  }
}
