package me.takis967.peter;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
  private BlockedCommands blockedCommands;
  
  private InfoCommand infoCommand;
  
  private PrefixManager prefixManager;
  
  private FileConfiguration prefixConfig;
  
  public void onEnable() {
    saveDefaultConfig();
    FileConfiguration config = getConfig();
    this.blockedCommands = new BlockedCommands(config);
    getServer().getPluginManager().registerEvents(this.blockedCommands, (Plugin)this);
    getLogger().info("Peter's Private Plugin has been enabled!");
    getCommand("fly").setExecutor(new FlyCommand());
    this.infoCommand = new InfoCommand();
    getCommand("info").setExecutor(this.infoCommand);
    getServer().getPluginManager().registerEvents(this.infoCommand, (Plugin)this);
    getCommand("heal").setExecutor(new HealCommand());
    getCommand("help").setExecutor(new HelpCommand());
    getCommand("calculate").setExecutor(new CalculateCommand());
    getCommand("invsee").setExecutor(new InvseeCommand());
    getCommand("announce").setExecutor(new AnnounceCommand());
    getCommand("kick").setExecutor(new KickCommand());
    getCommand("bin").setExecutor(new BinCommand());
    getCommand("coinflip").setExecutor(new CoinFlipCommand());
    getCommand("cc").setExecutor(new ClearCommand());
    getCommand("pvp").setExecutor(new PvPCommand());
    getCommand("spawn").setExecutor(new SpawnCommand(this));
    getServer().getPluginManager().registerEvents(new OnChatListener(), (Plugin)this);
    getServer().getPluginManager().registerEvents(new JoinAndLeaveHandler(this), (Plugin)this);
    this.prefixManager = new PrefixManager(this);
    loadPrefixConfig();
    Bukkit.getPluginManager().registerEvents(new PrefixListener(this), (Plugin)this);
    getCommand("prefix").setExecutor(new PrefixCommand(this));
  }
  
  public void onDisable() {
    saveConfig();
    savePrefixConfig();
    getLogger().info("Peter's Private Plugin has been disabled!");
  }
  
  public PrefixManager getPrefixManager() {
    return this.prefixManager;
  }
  
  private void loadPrefixConfig() {
    this.prefixConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(new File(getDataFolder(), "prefixes.yml"));
    if (!this.prefixConfig.contains("prefixes"))
      this.prefixConfig.createSection("prefixes"); 
    this.prefixManager.loadPrefixes(this.prefixConfig);
  }
  
  public void savePrefixConfig() {
    this.prefixConfig.createSection("prefixes", this.prefixManager.getPrefixes());
    try {
      this.prefixConfig.save(new File(getDataFolder(), "prefixes.yml"));
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
