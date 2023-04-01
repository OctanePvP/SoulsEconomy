package com.octane.sllly.soulseconomy;

import com.octane.sllly.soulseconomy.api.SoulsEconomyAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Placeholder extends PlaceholderExpansion {

    private SoulsEconomyAPI api;

    public Placeholder(SoulsEconomyAPI api) {
        this.api = api;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "soulseconomy-balance";
    }

    @Override
    public @NotNull String getAuthor() {
        return "sllly";
    }

    @Override
    public @NotNull String getVersion() {
        return "1";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        return String.valueOf(api.getPlayerSouls(player));
    }
}
