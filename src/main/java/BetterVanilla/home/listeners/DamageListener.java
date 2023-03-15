package BetterVanilla.home.listeners;

import BetterVanilla.home.HomeMaster;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    private final HomeMaster master;

    public DamageListener(HomeMaster master){
        this.master = master;
    }

    @EventHandler
    public void onEntityTakeDamage(EntityDamageEvent e){
        if(e instanceof Player){
            Player player = (Player) e;

            if(master.getPlayerIdTask().containsKey(player.getUniqueId().toString())){
                int idTask = master.getPlayerIdTask().get(player.getUniqueId().toString());
                Bukkit.getScheduler().cancelTask(idTask);
                player.sendMessage(ChatColor.YELLOW + "Your home teleport has been cancelled because you take damage");
                master.delPlayerIdTask(player.getUniqueId().toString());
                master.delTeleportingHomeToPlayer(player.getUniqueId().toString());
            }

        }
    }

}
