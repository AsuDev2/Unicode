package com.asudev.unicode;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class HealthBar {

    private JavaPlugin plugin;
    private static HealthBar instance = new HealthBar();

    public static HashMap<Double, String> healthUnicode = new HashMap<>();
    public static HashMap<String, String> healthUnicode2 = new HashMap<>();

    private HashMap<Player, BossBar> bossbars = new HashMap<>();
    private HashMap<Player, BossBar> bossbars2 = new HashMap<>();
    private HashMap<Player, Integer> cachedHP = new HashMap<>();
    private HashMap<Player, Integer> cachedMaxHP = new HashMap<>();

    private static HashMap<Player, String> cachedResolutions = new HashMap<>();
    private static HashMap<String, String> resolutions = new HashMap<>();

    static {
        healthUnicode.put(1.0, "\\u1531");
        healthUnicode.put(0.99, "\\u1532");
        healthUnicode.put(0.98, "\\u1533");
        healthUnicode.put(0.97, "\\u1534");
        healthUnicode.put(0.96, "\\u1535");
        healthUnicode.put(0.95, "\\u1536");
        healthUnicode.put(0.94, "\\u1537");
        healthUnicode.put(0.93, "\\u1538");
        healthUnicode.put(0.92, "\\u1539");
        healthUnicode.put(0.91, "\\u153A");
        healthUnicode.put(0.90, "\\u153B");
        healthUnicode.put(0.89, "\\u153C");
        healthUnicode.put(0.88, "\\u153D");
        healthUnicode.put(0.87, "\\u153E");
        healthUnicode.put(0.86, "\\u153F");
        healthUnicode.put(0.85, "\\u1540");
        healthUnicode.put(0.84, "\\u1541");
        healthUnicode.put(0.83, "\\u1542");
        healthUnicode.put(0.82, "\\u1543");
        healthUnicode.put(0.81, "\\u1546");
        healthUnicode.put(0.80, "\\u1547");
        healthUnicode.put(0.79, "\\u1548");
        healthUnicode.put(0.78, "\\u1549");
        healthUnicode.put(0.77, "\\u154A");
        healthUnicode.put(0.76, "\\u154B");
        healthUnicode.put(0.75, "\\u154C");
        healthUnicode.put(0.74, "\\u154D");
        healthUnicode.put(0.73, "\\u154E");
        healthUnicode.put(0.72, "\\u154F");
        healthUnicode.put(0.71, "\\u1550");
        healthUnicode.put(0.70, "\\u1551");
        healthUnicode.put(0.69, "\\u1552");
        healthUnicode.put(0.68, "\\u1553");
        healthUnicode.put(0.67, "\\u1554");
        healthUnicode.put(0.66, "\\u1555");
        healthUnicode.put(0.65, "\\u1556");
        healthUnicode.put(0.64, "\\u1557");
        healthUnicode.put(0.63, "\\u1558");
        healthUnicode.put(0.62, "\\u1559");
        healthUnicode.put(0.61, "\\u155A");
        healthUnicode.put(0.60, "\\u155B");
        healthUnicode.put(0.59, "\\u155C");
        healthUnicode.put(0.58, "\\u155D");
        healthUnicode.put(0.57, "\\u155E");
        healthUnicode.put(0.56, "\\u155F");
        healthUnicode.put(0.55, "\\u1560");
        healthUnicode.put(0.54, "\\u1561");
        healthUnicode.put(0.53, "\\u1562");
        healthUnicode.put(0.52, "\\u1563");
        healthUnicode.put(0.51, "\\u1564");
        healthUnicode.put(0.50, "\\u1565");
        healthUnicode.put(0.49, "\\u1566");
        healthUnicode.put(0.48, "\\u1567");
        healthUnicode.put(0.47, "\\u1568");
        healthUnicode.put(0.46, "\\u1569");
        healthUnicode.put(0.45, "\\u156A");
        healthUnicode.put(0.44, "\\u156B");
        healthUnicode.put(0.43, "\\u156C");
        healthUnicode.put(0.42, "\\u156D");
        healthUnicode.put(0.41, "\\u156E");
        healthUnicode.put(0.40, "\\u156F");
        healthUnicode.put(0.39, "\\u1570");
        healthUnicode.put(0.38, "\\u1571");
        healthUnicode.put(0.37, "\\u1572");
        healthUnicode.put(0.36, "\\u1573");
        healthUnicode.put(0.35, "\\u1574");
        healthUnicode.put(0.34, "\\u1575");
        healthUnicode.put(0.33, "\\u1576");
        healthUnicode.put(0.32, "\\u1577");
        healthUnicode.put(0.31, "\\u1578");
        healthUnicode.put(0.30, "\\u1579");
        healthUnicode.put(0.29, "\\u157A");
        healthUnicode.put(0.28, "\\u157B");
        healthUnicode.put(0.27, "\\u157C");
        healthUnicode.put(0.26, "\\u157D");
        healthUnicode.put(0.25, "\\u157E");
        healthUnicode.put(0.24, "\\u157F");
        healthUnicode.put(0.23, "\\u1580");
        healthUnicode.put(0.22, "\\u1581");
        healthUnicode.put(0.21, "\\u1582");
        healthUnicode.put(0.20, "\\u1583");
        healthUnicode.put(0.19, "\\u1584");
        healthUnicode.put(0.18, "\\u1585");
        healthUnicode.put(0.17, "\\u1586");
        healthUnicode.put(0.16, "\\u1587");
        healthUnicode.put(0.15, "\\u1588");
        healthUnicode.put(0.14, "\\u1589");
        healthUnicode.put(0.13, "\\u158A");
        healthUnicode.put(0.12, "\\u158B");
        healthUnicode.put(0.11, "\\u158C");
        healthUnicode.put(0.10, "\\u158D");
        healthUnicode.put(0.09, "\\u158E");
        healthUnicode.put(0.08, "\\u158F");
        healthUnicode.put(0.07, "\\u1590");
        healthUnicode.put(0.06, "\\u1591");
        healthUnicode.put(0.05, "\\u1592");
        healthUnicode.put(0.04, "\\u1593");
        healthUnicode.put(0.03, "\\u1594");
        healthUnicode.put(0.02, "\\u1595");
        healthUnicode.put(0.01, "\\u1596");

        healthUnicode2.put("0", "\\u06A9");
        healthUnicode2.put("1", "\\u06AA");
        healthUnicode2.put("2", "\\u06AB");
        healthUnicode2.put("3", "\\u06AC");
        healthUnicode2.put("4", "\\u06AD");
        healthUnicode2.put("5", "\\u06AE");
        healthUnicode2.put("6", "\\u06AF");
        healthUnicode2.put("7", "\\u06B0");
        healthUnicode2.put("8", "\\u06B1");
        healthUnicode2.put("9", "\\u06B2");
        healthUnicode2.put("10", "\\u06B3");
        healthUnicode2.put("11", "\\u06B4");
        healthUnicode2.put("12", "\\u06B5");

        resolutions.put("2560x1440p", "\uF808\uF808\uF808\uF808\uF808\uF80A\uF80A\uF809\uF80A\uF809\uF80E");
        resolutions.put("1920x1080p", "\uF80E\uF826");
        resolutions.put("1680x1050p", "\uF80D\uF80C\uF80A");

    }

    public HashMap<Player, String> getCachedResolutions() {
        return cachedResolutions;
    }

    public static HealthBar getInstance() {
        return instance;
    }

    public void init(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void clear(Player player) {
        this.cachedHP.remove(player);
        this.cachedMaxHP.remove(player);
        this.bossbars.remove(player);
        this.bossbars2.remove(player);
        this.cachedResolutions.remove(player);
    }

    public void sendResourcePackPlayer(Player player) {
        String resolution = cachedResolutions.get(player);
        if (resolution.equalsIgnoreCase("2560x1440p")) {
            player.setResourcePack("https://www.dropbox.com/s/iashcpc13wbyw13/Pack_1440p_GuiScale3.zip?dl=1");
        } else if (resolution.equalsIgnoreCase("1920x1080p")) {
            player.setResourcePack("https://www.dropbox.com/s/snndb86ltsy1hy8/Pack_1080p_GuiScale3.zip?dl=1");
        } else if (resolution.equalsIgnoreCase("1680x1050p")) {
            player.setResourcePack("https://www.dropbox.com/s/cj3bbi9pdxsmmen/Pack_1050p_GuiScale3.zip?dl=1");
        }
    }

    public void updateHealthBar(Player p) {
        double doubleHealth = p.getHealth();
        int currentHealth = (int)Math.round(doubleHealth);
        AttributeInstance maxHealthAttribute = p.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        double doubleMaxHealth = 0;
        if (maxHealthAttribute != null) {
            doubleMaxHealth = maxHealthAttribute.getValue();
        }
        int maxHealth = (int)Math.round(doubleMaxHealth);
        double cachedHP = this.cachedHP.getOrDefault(p, -1);
        double cachedMaxHP = this.cachedMaxHP.getOrDefault(p, -1);
        if (currentHealth != cachedHP || maxHealth != cachedMaxHP) {
            this.cachedHP.put(p, currentHealth);
            this.cachedMaxHP.put(p, maxHealth);
            double ratio = (double) currentHealth / (double) maxHealth;
            ratio = Math.round(ratio * 100.0) / 100.0;
            String healthBarModel = healthUnicode.get(ratio);
            char healthBarModelChar = (char)Integer.parseInt(healthBarModel.substring(2), 16);
            String numberFormat = currentHealth + "ڳ" + maxHealth;
            String[] strArray = numberFormat.split("");
            int counter = 0;
            for (int i = 0; i < strArray.length; i++) {
                String c = strArray[i];
                String d;
                if (healthUnicode2.get(c) != null) {
                    d = healthUnicode2.get(c);
                    char v = (char)Integer.parseInt(d.substring(2), 16);
                    strArray[i] = String.valueOf(v);
                }
                counter++;
            }
            StringBuilder numbers = new StringBuilder();
            counter = counter - 3;
            if (counter > 0) {
                for (int z = 0; z < counter; z++) {
                    numbers.append(" ");
                }
            }
            numbers.append(String.join("", strArray));
            Scheduler.getInstance().runSync(new BukkitRunnable() {
                @Override
                public void run() {
                    if (bossbars.get(p) != null) {
                        BossBar bar = bossbars.get(p);
                        String resolution = cachedResolutions.get(p);
                        bar.setTitle(resolutions.get(resolution) + healthBarModelChar);
                    } else {
                        String resolution = cachedResolutions.get(p);
                        BossBar bar = Bukkit.createBossBar(resolutions.get(resolution) + healthBarModelChar, BarColor.GREEN, BarStyle.SOLID);
                        bar.addPlayer(p);
                        bar.setVisible(true);
                        bossbars.put(p, bar);
                    }
                    if (bossbars2.get(p) != null) {
                        BossBar bar2 = bossbars2.get(p);
                        bar2.setTitle(numbers.toString());
                    } else {
                        BossBar bar2 = Bukkit.createBossBar(numbers.toString(), BarColor.BLUE, BarStyle.SOLID);
                        bar2.addPlayer(p);
                        bar2.setVisible(true);
                        bossbars2.put(p, bar2);
                    }
                }
            });
        }
    }

    public void startCustomInterfaces() {
        Scheduler.getInstance().runAsyncRepeating(new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    updateHealthBar(p);
                }
            }
        }, 0, 1);

    }

}
