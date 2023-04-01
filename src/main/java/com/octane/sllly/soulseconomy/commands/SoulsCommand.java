package com.octane.sllly.soulseconomy.commands;

import com.octane.sllly.soulseconomy.OfflinePlayerArgument;
import com.octane.sllly.soulseconomy.SoulsEconomy;
import com.octane.sllly.soulseconomy.Utils;
import com.octane.sllly.soulseconomy.api.SoulsEconomyAPI;
import dev.splityosis.commandsystem.SYSCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class SoulsCommand extends SYSCommand {

    public SoulsCommand(SoulsEconomyAPI api, SoulsEconomy plugin){
        super("souls");
        String soulscommandoutput = plugin.getConfig().getString("soulscommandoutput");
        String soulsotherscommandoutput = plugin.getConfig().getString("soulsotherscommandoutput");
        setPermission("soulseconomy.souls");
        executes((sender, args) -> {
            if(args.length==0){
                sender.sendMessage(soulscommandoutput + api.getPlayerSouls((OfflinePlayer) sender));
                return;
            }
            if(!sender.hasPermission("soulseconomy.souls.others")){
                sender.sendMessage(soulscommandoutput + api.getPlayerSouls((OfflinePlayer) sender));
                return;
            }
            OfflinePlayerArgument arg = new OfflinePlayerArgument();
            if (!arg.isValid(args[0])){
                Utils.sendMessage(sender, arg.getInvalidInputMessage(args[0]));
                return;
            }
            OfflinePlayer player = Bukkit.getOfflinePlayer(SoulsEconomy.playerCache.getUUID(args[0]));
            sender.sendMessage(player.getName() + soulsotherscommandoutput +api.getPlayerSouls(player));
        });
    }
}
