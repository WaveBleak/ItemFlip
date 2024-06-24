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
    private final int maxFlips;

    public ConstantsManager(FileConfiguration config) {
        inventoryName = config.getString("inventory-name");
        maxFlips = config.getInt("max-flips");

        List<String> whitelistItems = config.getStringList("item-whitelist");
        for (String itemKey : whitelistItems) {
            try {
                whitelist.add(Material.valueOf(itemKey));
            } catch (IllegalArgumentException ex) {
                ItemFlipPlugin.getInstance().getLogger().warning("Kunne ikke forstå \"" + itemKey + "\" i item whitelist!");
            }
        }
    }

}
