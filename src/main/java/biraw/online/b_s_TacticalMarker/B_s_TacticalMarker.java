package biraw.online.b_s_TacticalMarker;

import biraw.online.b_s_TacticalMarker.Listeners.PlayerMarkListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class B_s_TacticalMarker extends JavaPlugin {

    private static B_s_TacticalMarker plugin;

    public static B_s_TacticalMarker getPlugin() { return plugin; }

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new PlayerMarkListener(),plugin);


        // Print the motd
        plugin.getLogger().info(" ");
        plugin.getLogger().info("O=============================================================O");
        plugin.getLogger().info("     The B's TacticalMarker plugin has loaded successfully");
        plugin.getLogger().info("  This is B's TacticalMarker for Minecraft 1.20.x and 1.21.x!");
        plugin.getLogger().info("                         Author: BiRaw");
        plugin.getLogger().info("           Discord: https://discord.gg/XwFqu7uahX :>");
        plugin.getLogger().info("O=============================================================O");
        plugin.getLogger().info(" ");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
