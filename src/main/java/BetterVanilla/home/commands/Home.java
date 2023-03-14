package BetterVanilla.home.commands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import BetterVanilla.plugin.BetterVanilla;

public class Home implements CommandExecutor {

    private final BetterVanilla plugin;

    public Home(BetterVanilla plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if(args.length == 1) {
                Location location = plugin.getConfig().getLocation(p.getUniqueId() + "." + args[0]);
                p.sendMessage(ChatColor.AQUA + "You will be teleported to this home: "+ ChatColor.GREEN + args[0] + ChatColor.AQUA + " in 3 seconds");
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> p.teleport(location), 60L);

            } else
                p.sendMessage(ChatColor.RED + "Add the name of the home ex: /home <home name>");
        }
        return true;
    }
}