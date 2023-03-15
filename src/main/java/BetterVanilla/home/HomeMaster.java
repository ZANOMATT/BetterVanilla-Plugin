package BetterVanilla.home;

import BetterVanilla.home.commands.DelHome;
import BetterVanilla.home.commands.Home;
import BetterVanilla.home.commands.Homes;
import BetterVanilla.home.commands.SetHome;
import BetterVanilla.home.listeners.DamageListener;
import BetterVanilla.home.listeners.MovementListener;
import BetterVanilla.plugin.BetterVanilla;
import org.bukkit.scheduler.BukkitTask;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class HomeMaster {

    private final BetterVanilla plugin;

    private final HashMap<String, Set<String>> playerHomes = new HashMap<>();
    private final HashMap<String, Integer> playerHomesNum = new HashMap<>();

    private final HashMap<String, Integer> playerIdTask = new HashMap<>();
    private final HashMap<String, String> playerHomeTeleporting = new HashMap<>();
    private Set<String> players;



    public HomeMaster(BetterVanilla plugin){
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("setHome")).setExecutor(new SetHome(plugin, this));
        Objects.requireNonNull(plugin.getCommand("home")).setExecutor(new Home(plugin, this));
        Objects.requireNonNull(plugin.getCommand("homes")).setExecutor(new Homes(plugin));
        Objects.requireNonNull(plugin.getCommand("delHome")).setExecutor(new DelHome(plugin, this));
        plugin.getServer().getPluginManager().registerEvents(new DamageListener(this), plugin);
        plugin.getServer().getPluginManager().registerEvents(new MovementListener(this), plugin);

        countHomes();
    }

    public void countHomes(){
        players = plugin.getConfig().getKeys(false);
        Set<String> homes ;
        for(String player : players){
            homes = Objects.requireNonNull(plugin.getConfig().getConfigurationSection(player)).getKeys(true);
                playerHomes.put(player, homes);
                playerHomesNum.put(player, homes.size());
        }

    }



    public void countHomes(String pUUID){
        players = plugin.getConfig().getKeys(false);
        boolean isAlreadyThere = false;

        for(String p : players){
            if(p.equals(pUUID)){
                isAlreadyThere = true;
                break;
            }
        }
        if(isAlreadyThere) {
            playerHomes.put(pUUID, Objects.requireNonNull(plugin.getConfig().getConfigurationSection(pUUID)).getKeys(false));
            int homeNum = playerHomes.get(pUUID).size();
            playerHomesNum.put(pUUID, homeNum);

        } else{
            playerHomesNum.put(pUUID, 0);
        }

    }

    public int getHomesNum(String pUUID){
        return playerHomesNum.get(pUUID);
    }

    public void addTaskToPlayer(String pUUID, int taskID){
        if(!playerIdTask.containsKey(pUUID))
            playerIdTask.put(pUUID, taskID);
    }

    public void delPlayerIdTask(String pUUID){
        if(playerIdTask.containsKey(pUUID))
            playerIdTask.remove(pUUID);
    }

    public int addTeleportingHomeToPlayer(String pUUID, String home){
        if(playerHomeTeleporting.containsKey(pUUID))
            return -1;
        else {
            playerHomeTeleporting.put(pUUID, home);
            return 0;
        }
    }

    public void delTeleportingHomeToPlayer(String pUUID){
        if(playerHomeTeleporting.containsKey(pUUID))
            playerHomeTeleporting.remove(pUUID);
    }

    public HashMap<String, Set<String>> getPlayerHomes() {
        return playerHomes;
    }

    public HashMap<String, Integer> getPlayerHomesNum() {
        return playerHomesNum;
    }

    public HashMap<String, Integer> getPlayerIdTask() {
        return playerIdTask;
    }

    public HashMap<String, String> getPlayerTeleportingHome() {
        return playerHomeTeleporting;
    }

}
