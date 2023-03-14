package BetterVanilla.home.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import BetterVanilla.plugin.BetterVanilla;

import java.util.Set;

public class Homes implements CommandExecutor {

    private final BetterVanilla plugin;

    private Set<String> homes;

    public Homes(BetterVanilla plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            String mex = ChatColor.AQUA + "These are your homes: " ;
            homes = plugin.getConfig().getConfigurationSection(p.getUniqueId().toString()).getKeys(true);
            for (String home : homes) {
                mex += ChatColor.GREEN + home + ", ";
            }
            mex = mex.substring(0,mex.length()-2);
            p.sendMessage(mex);
        }

        return true;
    }

}
