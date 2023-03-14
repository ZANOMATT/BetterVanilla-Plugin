package BetterVanilla.home.commands;

import BetterVanilla.home.HomeMaster;
import BetterVanilla.plugin.BetterVanilla;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHome implements CommandExecutor {

    private final BetterVanilla plugin;

    private final HomeMaster master;

    public DelHome(BetterVanilla plugin, HomeMaster master){
        this.plugin = plugin;
        this.master = master;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if(args.length == 1) {
                plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).set(args[0], null);
                plugin.saveConfig();

                master.countHomes(p.getUniqueId().toString());

                p.sendMessage(ChatColor.AQUA + "The home " + ChatColor.GREEN + args[0] + ChatColor.AQUA + " has been deleted");

            } else
                p.sendMessage(ChatColor.RED + "Add the name of the home ex: /delhome <home name>");
        }

        return true;
    }

}
