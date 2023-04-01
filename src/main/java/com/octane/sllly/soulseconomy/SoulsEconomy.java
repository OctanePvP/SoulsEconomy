package com.octane.sllly.soulseconomy;

import com.octane.sllly.soulseconomy.api.SoulsEconomyAPI;
import com.octane.sllly.soulseconomy.commands.SoulsAdminCommandBranch;
import com.octane.sllly.soulseconomy.commands.SoulsCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

public final class SoulsEconomy extends JavaPlugin implements SoulsEconomyAPI {

    public static PlayerCache playerCache;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        DataFile.setup(this);
        playerCache = new PlayerCache(getDataFolder(), "cache", this);
        playerCache.initialize();
        new SoulsAdminCommandBranch(this).registerCommandBranch(this);
        new SoulsCommand(this, this).registerCommand(this);
        new Placeholder(this).register();

    }

    @Override
    public void onDisable() {
        DataFile.save();
    }

    @Override
    public int getPlayerSouls(OfflinePlayer target) {
        return DataFile.get().getInt(target.getUniqueId().toString());
    }

    @Override
    public void depositSouls(OfflinePlayer target, int amount) {
        int souls = getPlayerSouls(target) + amount;
        DataFile.get().set(target.getUniqueId().toString(), souls);
    }

    @Override
    public void withdrawSouls(OfflinePlayer target, int amount) {
        int souls = getPlayerSouls(target) - amount;
        DataFile.get().set(target.getUniqueId().toString(), souls);
    }

    @Override
    public void setSouls(OfflinePlayer target, int amount) {
        DataFile.get().set(target.getUniqueId().toString(), amount);
    }

    @Override
    public void forceSaveData() {
        DataFile.save();
    }
}
