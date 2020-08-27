package com.asudev.unicode;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

public class Events implements Listener {

    private Unicode plugin = Unicode.getPlugin(Unicode.class);

    private HealthBar healthBar = HealthBar.getInstance();

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        healthBar.clear(player);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        File config = new File(plugin.getDataFolder(), "players.yml");
        FileConfiguration players = new YamlConfiguration();
        try {
            players.load(config);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        String resolution;
        if (players.getString("Players." + uuid) == null) {
            players.set("Players." + uuid, "1920x1080p");
            resolution = "1920x1080p";
        } else {
            resolution = players.getString("Players." + uuid);
        }
        try {
            players.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        healthBar.getCachedResolutions().put(player, resolution);
        Scheduler.getInstance().schedule(new BukkitRunnable() {
            @Override
            public void run() {
                healthBar.sendResourcePackPlayer(player);
            }
        }, 5);
    }

}
