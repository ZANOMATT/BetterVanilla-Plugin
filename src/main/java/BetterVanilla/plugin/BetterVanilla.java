package BetterVanilla.plugin;

import BetterVanilla.home.HomeMaster;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterVanilla extends JavaPlugin {

    private HomeMaster home;
    @Override
    public void onEnable() {
        System.out.println("BetterVanilla Plugin has started!");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        home = new HomeMaster(this);

    }
}
