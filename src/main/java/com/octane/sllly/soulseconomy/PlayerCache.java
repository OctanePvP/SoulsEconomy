package com.octane.sllly.soulseconomy;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerCache implements Listener {
    private File file;
    private FileConfiguration config;

    public PlayerCache(File parentDir, String fileName, JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        file = new File(parentDir, fileName+".yml");
    }

    public void initialize(){
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void save(){
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName(UUID uuid){
        String lowName = config.getString("uuid-to-lower-name."+uuid);
        if (lowName == null) return null;
        return config.getString("lower-name-to-proper-name."+lowName);
    }

    public UUID getUUID(String name){
        String uuidStr = config.getString("lower-name-to-uuid."+name.toLowerCase());
        if (uuidStr == null) return null;
        return UUID.fromString(uuidStr);
    }

    public boolean hasPlayedBefore(String name){
        return getUUID(name) != null;
    }

    public boolean hasPlayedBefore(UUID uuid){
        return getName(uuid) != null;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        config.set("lower-name-to-uuid."+e.getPlayer().getName().toLowerCase(), e.getPlayer().getUniqueId().toString());
        config.set("uuid-to-lower-name."+e.getPlayer().getUniqueId(), e.getPlayer().getName().toLowerCase());
        config.set("lower-name-to-proper-name."+e.getPlayer().getName().toLowerCase(), e.getPlayer().getName());
    }
}
