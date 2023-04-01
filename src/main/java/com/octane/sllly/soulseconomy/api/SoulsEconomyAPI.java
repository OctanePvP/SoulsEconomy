package com.octane.sllly.soulseconomy.api;

import org.bukkit.OfflinePlayer;

public interface SoulsEconomyAPI {

    public int getPlayerSouls(OfflinePlayer target);

    public void depositSouls(OfflinePlayer target, int amount);

    public void withdrawSouls(OfflinePlayer target, int amount);

    public void setSouls(OfflinePlayer target, int amount);

    public void forceSaveData();
}
