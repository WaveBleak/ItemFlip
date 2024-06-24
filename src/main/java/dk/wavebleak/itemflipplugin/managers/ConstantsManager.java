package dk.wavebleak.itemflipplugin.managers;

import dk.wavebleak.itemflipplugin.ItemFlipPlugin;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ConstantsManager {

    private final String inventoryName;
    private final List<Material> whitelist = new ArrayList<>();

    public ConstantsManager(FileConfiguration config) {
        inventoryName = config.getString("inventory-name");

        ConfigurationSection whitelistSection = config.getConfigurationSection("item-whitelist");

        whitelistSection.getKeys(false).forEach(key -> {
            try {
                whitelist.add(Material.valueOf(key));
            }catch (IllegalArgumentException ex) {
                ItemFlipPlugin.getInstance().getLogger().warning("Kunne ikke forstå \"" + key + "\" i item whitelist!");
            }
        });
    }

}
