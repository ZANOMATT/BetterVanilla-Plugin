package BetterVanilla.home.commands;
import BetterVanilla.home.HomeMaster;
import BetterVanilla.home.task.TpTask;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import BetterVanilla.plugin.BetterVanilla;
import org.bukkit.scheduler.BukkitTask;

public class Home implements CommandExecutor {

    private final BetterVanilla plugin;

    private final HomeMaster master;
    //private final BukkitTask tpTask;

    public Home(BetterVanilla plugin, HomeMaster master){
        this.plugin = plugin;
        this.master = master;
        //this.tpTask = tpTask;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(!master.getPlayerIdTask().containsKey(p.getUniqueId().toString())) {
                if (args.length == 1) {
                    Location location = plugin.getConfig().getLocation(p.getUniqueId() + "." + args[0]);
                    p.sendMessage(ChatColor.AQUA + "You will be teleported to this home: " + ChatColor.GREEN + args[0] + ChatColor.AQUA + " in 3 seconds");

                    BukkitTask tpTask = new TpTask(master, p, location).runTaskLater(plugin, 60L);
                    master.addTaskToPlayer(p.getUniqueId().toString(), tpTask.getTaskId());
                    master.addTeleportingHomeToPlayer(p.getUniqueId().toString(), args[0]);
                } else
                    p.sendMessage(ChatColor.RED + "Add the name of the home ex: /home <home name>");
            }
            else
                p.sendMessage(ChatColor.RED + "You are already teleporting to: " + master.getPlayerTeleportingHome().get(p.getUniqueId().toString()));
        }
        return true;
    }
}