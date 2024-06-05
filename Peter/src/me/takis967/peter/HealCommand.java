package me.takis967.peter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HealCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (!player.hasPermission("heal.use")) {
        player.sendMessage(String.valueOf(ChatColor.RED) + "You do not have permission to heal.");
        return true;
      } 
      if (cmd.getName().equalsIgnoreCase("heal")) {
        for (PotionEffect effect : player.getActivePotionEffects())
          player.removePotionEffect(effect.getType()); 
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 15, 254));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 15, 254));
        player.sendMessage(String.valueOf(ChatColor.GOLD) + "You are now cured!");
        return true;
      } 
    } 
    return false;
  }
}
