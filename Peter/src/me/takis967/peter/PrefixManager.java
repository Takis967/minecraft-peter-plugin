package me.takis967.peter;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class PrefixManager {
	  private final Main plugin;
	  private final Map<String, String> playerPrefixes = new HashMap<>();

	  public PrefixManager(Main plugin) {
	    this.plugin = plugin;
	  }

	  public void setPlayerPrefix(String playerName, String prefix) {
	    this.playerPrefixes.put(playerName, ChatColor.translateAlternateColorCodes('&', prefix));
	    this.plugin.savePrefixConfig();
	  }

	  public void updatePlayerPrefix(Player player) {
	    String playerName = player.getName();
	    String prefix = this.playerPrefixes.getOrDefault(playerName, "");
	    String tabListName = prefix + playerName;  // Corrected to include the player's name after the prefix
	    player.setPlayerListName(tabListName);
	  }

	  public void loadPrefixes(FileConfiguration prefixConfig) {
	    this.playerPrefixes.clear();
	    for (String key : prefixConfig.getConfigurationSection("prefixes").getKeys(false)) {
	      String playerName = key;
	      String prefix = prefixConfig.getString("prefixes." + key);
	      this.playerPrefixes.put(playerName, ChatColor.translateAlternateColorCodes('&', prefix));
	    } 
	  }

	  public Map<String, String> getPrefixes() {
	    return this.playerPrefixes;
	  }
	}
