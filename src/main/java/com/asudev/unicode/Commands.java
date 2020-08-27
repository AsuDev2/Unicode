package com.asudev.unicode;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

public class Commands implements Listener, CommandExecutor {

    private Unicode plugin = Unicode.getPlugin(Unicode.class);

    private HealthBar healthBar = HealthBar.getInstance();

    public String resolution = "resolution";

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase(resolution)) {
                Player player = (Player) sender;
                String uuid = player.getUniqueId().toString();
                if (args.length == 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: &c/resolution <1440,1080,1050>"));
                } else {
                    File config = new File(plugin.getDataFolder(), "players.yml");
                    FileConfiguration players = new YamlConfiguration();
                    try {
                        players.load(config);
                    } catch (IOException | InvalidConfigurationException e) {
                        e.printStackTrace();
                    }
                    switch (args[0]) {
                        case "1440":
                            players.set("Players." + uuid, "2560x1440p");
                            healthBar.getCachedResolutions().put(player, "2560x1440p");
                            try {
                                players.save(config);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You selected &b2560x1440p &7as your resolution."));
                            healthBar.sendResourcePackPlayer(player);
                            break;
                        case "1080":
                            players.set("Players." + uuid, "1920x1080p");
                            healthBar.getCachedResolutions().put(player, "1920x1080p");
                            try {
                                players.save(config);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You selected &b1920x1080p &7as your resolution."));
                            healthBar.sendResourcePackPlayer(player);
                            break;
                        case "1050":
                            players.set("Players." + uuid, "1680x1050p");
                            healthBar.getCachedResolutions().put(player, "1680x1050p");
                            try {
                                players.save(config);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You selected &b1680x1050p &7as your resolution."));
                            healthBar.sendResourcePackPlayer(player);
                            break;
                        default:
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You must select a valid resolution!"));
                    }
                    player.setHealth(player.getHealth() - 1);
                    Scheduler.getInstance().schedule(new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.setHealth(player.getHealth() + 1);
                        }
                    }, 2);
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
     return false;
    }
}
