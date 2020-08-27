package com.asudev.unicode;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Unicode extends JavaPlugin {

    private static JavaPlugin plugin;

    private File customConfigFile;
    private FileConfiguration customConfig;

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "players.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("players.yml", false);
        }
        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        plugin = this;
        Scheduler.getInstance().init(this);
        HealthBar.getInstance().init(this);
        HealthBar.getInstance().startCustomInterfaces();
        getServer().getPluginManager().registerEvents(new Events(), this);
        try {
            Commands commands = new Commands();
            getCommand(commands.resolution).setExecutor(commands);
        } catch (Exception e) {
            e.printStackTrace();
        }
        createCustomConfig();
    }

    @Override
    public void onDisable() {
    }
}
