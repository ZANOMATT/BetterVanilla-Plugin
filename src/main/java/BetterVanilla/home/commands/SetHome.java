package BetterVanilla.home.commands;


import BetterVanilla.home.HomeMaster;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import BetterVanilla.plugin.BetterVanilla;



public class SetHome implements CommandExecutor {



    private final BetterVanilla plugin;

    private final HomeMaster master;

    public SetHome(BetterVanilla plugin, HomeMaster master){
        this.plugin = plugin;
        this.master = master;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;

            if(args.length == 1) {
                master.countHomes(p.getUniqueId().toString());
                if(master.getHomesNum(p.getUniqueId().toString()) < 3) {
                    Location location = p.getLocation();


                    plugin.getConfig().set(p.getUniqueId() + "." + args[0], location);
                    plugin.saveConfig();

                    p.sendMessage(ChatColor.AQUA + "The home " + args[0] + " as been setted to x: " +
                            ChatColor.GREEN + "" + ChatColor.BOLD + location.getBlockX() +
                            ChatColor.RESET + "" + ChatColor.AQUA + " y: " +
                            ChatColor.GREEN + "" + ChatColor.BOLD + location.getBlockY() +
                            ChatColor.RESET + "" + ChatColor.AQUA + " z: " +
                            ChatColor.GREEN + "" + ChatColor.BOLD + location.getBlockZ());
                }else{
                    p.sendMessage(ChatColor.YELLOW + "You have reached the max number of homes");
                }
            } else
                p.sendMessage(ChatColor.RED + "Add the name of the home ex: /sethome <home name>");
        }
        return true;
    }
}
