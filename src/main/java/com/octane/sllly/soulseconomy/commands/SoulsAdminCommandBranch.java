package com.octane.sllly.soulseconomy.commands;

import com.octane.sllly.soulseconomy.OfflinePlayerArgument;
import com.octane.sllly.soulseconomy.SoulsEconomy;
import com.octane.sllly.soulseconomy.api.SoulsEconomyAPI;
import dev.splityosis.commandsystem.SYSCommand;
import dev.splityosis.commandsystem.SYSCommandBranch;
import dev.splityosis.commandsystem.arguments.IntegerArgument;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class SoulsAdminCommandBranch extends SYSCommandBranch {

    private SoulsEconomyAPI api;

    public SoulsAdminCommandBranch(SoulsEconomyAPI api){
        super("SoulsAdmin", "SoulAdmin");
        this.api = api;
        setPermission("soulseconomy.admin");
        registerCommands();
    }
    private void registerCommands(){
        addCommand(new SYSCommand("set")
                .setArguments(new OfflinePlayerArgument(), new IntegerArgument())
                .setUsage("/soulsadmin set [player] [value]")
                .executes((sender, args) -> {
                    OfflinePlayer player = Bukkit.getOfflinePlayer(SoulsEconomy.playerCache.getUUID(args[0]));
                    int amount = Integer.parseInt(args[1]);
                    api.setSouls(player, amount);
                }));

        addCommand(new SYSCommand("give")
                .setArguments(new OfflinePlayerArgument(), new IntegerArgument())
                .setUsage("/soulsadmin give [player] [value]")
                .executes((sender, args) -> {
                    OfflinePlayer player = Bukkit.getOfflinePlayer(SoulsEconomy.playerCache.getUUID(args[0]));
                    int amount = Integer.parseInt(args[1]);
                    api.depositSouls(player, amount);
                }));

        addCommand(new SYSCommand("take")
                .setArguments(new OfflinePlayerArgument(), new IntegerArgument())
                .setUsage("/soulsadmin take [player] [value]")
                .executes((sender, args) -> {
                    OfflinePlayer player = Bukkit.getOfflinePlayer(SoulsEconomy.playerCache.getUUID(args[0]));
                    int amount = Integer.parseInt(args[1]);
                    api.withdrawSouls(player, amount);
                }));


    }



}
