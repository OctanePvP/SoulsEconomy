package com.octane.sllly.soulseconomy;

import com.octane.sllly.soulseconomy.api.SoulsEconomyAPI;
import dev.splityosis.commandsystem.SYSArgument;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.List;

public class OfflinePlayerArgument extends SYSArgument {
    @Override
    public boolean isValid(String input) {
        return SoulsEconomy.playerCache.hasPlayedBefore(input);
    }

    @Override
    public List<String> getInvalidInputMessage(String input) {
        return Arrays.asList("&cPlayer has never played before.");
    }
}
