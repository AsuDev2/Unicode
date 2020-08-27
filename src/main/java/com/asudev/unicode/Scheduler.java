package com.asudev.unicode;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Scheduler {

    // Constants
    private static final Scheduler instance = new Scheduler();

    // Properties
    private Plugin plugin;

    // Constructors

    private Scheduler()
    {

    }

    public static Scheduler getInstance() {
        return instance;
    }

    // Methods

    public void init(Plugin plugin) {
        this.plugin = plugin;
    }

    public void schedule(BukkitRunnable runnable, int delay) {
        //Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, runnable, (long) delay);
        runnable.runTaskLater(this.plugin, (long) delay);
    }

    public void scheduleRepeating(BukkitRunnable runnable, int delay, int howOften) {
        //Bukkit.getScheduler().runTaskTimer(this.plugin, runnable, (long) delay, (long) howOften);
        runnable.runTaskTimer(this.plugin, (long) delay, (long) howOften);
    }

    public void runAsync(BukkitRunnable runnable) {
        runnable.runTaskAsynchronously(this.plugin);
    }

    public void runAsyncRepeating(BukkitRunnable runnable, int delay, int howOften) {
        runnable.runTaskTimerAsynchronously(this.plugin, (long) delay, (long) howOften);
    }

    public void runSync(BukkitRunnable runnable) {
        runnable.runTask(this.plugin);
    }
}
