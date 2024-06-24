package dk.wavebleak.itemflipplugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.wavebleak.itemflipplugin.adapters.BetAdapter;
import dk.wavebleak.itemflipplugin.adapters.CompletedFlipListAdapter;
import dk.wavebleak.itemflipplugin.classes.Bet;
import dk.wavebleak.itemflipplugin.managers.CompletedFlipsManager;
import dk.wavebleak.itemflipplugin.managers.ConstantsManager;
import dk.wavebleak.itemflipplugin.managers.FlipsManager;
import dk.wavebleak.wavespluginlib.database.JsonManagerException;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Level;

public final class ItemFlipPlugin extends JavaPlugin {
    @Getter
    private static ItemFlipPlugin instance;
    @Getter
    private static FlipsManager flipsManager;
    @Getter
    private static CompletedFlipsManager completedFlipsManager;
    @Getter
    private static ConstantsManager constants;

    public void initDB() {
        saveDefaultConfig();
        flipsManager = new FlipsManager();
        completedFlipsManager = new CompletedFlipsManager();
        constants = new ConstantsManager(getConfig());

        try {
            flipsManager.init();
            completedFlipsManager.init();
        }catch (JsonManagerException ex) {
            getLogger().log(Level.SEVERE, "Kunne ikke oprette forbindelse til databasen", ex);
        }
    }

    @Override
    public void onEnable() {
        instance = this;

        initDB();
    }

    public void load() {
        initDB();
    }
    public void save() {
        completedFlipsManager.saveData(new ArrayList<>());
        flipsManager.saveData(new ArrayList<>());
    }
    public void reload() {
        save();
        load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
