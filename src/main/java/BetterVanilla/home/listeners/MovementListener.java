package BetterVanilla.home.listeners;

import BetterVanilla.home.HomeMaster;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementListener implements Listener {

    private final HomeMaster master;

    public MovementListener(HomeMaster master){
        this.master = master;
    }

    @EventHandler
    public void onPlayerMovment(PlayerMoveEvent e){
        Player player = e.getPlayer();

        if(master.getPlayerIdTask().containsKey(player.getUniqueId().toString())){
            int idTask = master.getPlayerIdTask().get(player.getUniqueId().toString());
            Bukkit.getScheduler().cancelTask(idTask);
            player.sendMessage(ChatColor.YELLOW + "Your home teleport has been cancelled because you moved");
            master.delPlayerIdTask(player.getUniqueId().toString());
            master.delTeleportingHomeToPlayer(player.getUniqueId().toString());
        }
    }

}
