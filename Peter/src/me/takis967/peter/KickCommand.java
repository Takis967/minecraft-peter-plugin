package me.takis967.peter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (args.length < 1) {
      sender.sendMessage(String.valueOf(ChatColor.RED) + "Usage: /kick <player> <reason>");
      return true;
    } 
    Player target = Bukkit.getPlayerExact(args[0]);
    if (target == null) {
      sender.sendMessage(String.valueOf(ChatColor.RED) + "Player not found!");
      return true;
    } 
    String playerName = target.getName();
    String daSender = sender.getName();
    String reason = (args.length > 1) ? ChatColor.translateAlternateColorCodes('&', String.join(" ", (CharSequence[])args).substring(args[0].length() + 1)) : "No reason specified.";
    String formattedReason = ChatColor.translateAlternateColorCodes('&', "&c&d&lPeter's Private Server &c\n \n &7You were kicked from the game! \n \n \n &c&lReason&7&l: &r" + reason);
    target.kickPlayer(formattedReason);
    String kickMessage = ChatColor.translateAlternateColorCodes('&', "&5Peter's Private Server&7: &6" + playerName + "&7 has been kicked by &c" + daSender + "&7. &eReason&7: &r" + reason);
    for (Player player : Bukkit.getOnlinePlayers()) {
      if (player.hasPermission("kick.use") || player.isOp())
        player.sendMessage(kickMessage); 
    } 
    return true;
  }
}
