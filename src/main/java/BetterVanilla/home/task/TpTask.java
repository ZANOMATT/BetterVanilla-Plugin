package BetterVanilla.home.task;

import BetterVanilla.home.HomeMaster;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TpTask extends BukkitRunnable {

    private final Player player;
    private final Location location;

    private final HomeMaster master;

    public TpTask(HomeMaster master ,Player player, Location location){
        this.master = master;
        this.player = player;
        this.location = location;
    }

    @Override
    public void run() {
        player.teleport(location);
        master.delPlayerIdTask(player.getUniqueId().toString());
        master.delTeleportingHomeToPlayer(player.getUniqueId().toString());
    }
}
