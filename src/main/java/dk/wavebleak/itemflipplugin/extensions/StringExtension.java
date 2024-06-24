package dk.wavebleak.itemflipplugin.extensions;

import dk.wavebleak.itemflipplugin.ItemFlipPlugin;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.util.logging.Level;

public class StringExtension {

    public static ItemStack decode(String base64) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decode(base64));
             BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream)) {
            return (ItemStack) dataInput.readObject();
        } catch (Exception exception) {
            ItemFlipPlugin.getInstance().getLogger().log(Level.SEVERE, "Failed to decode Base64 to ItemStack", exception);
        }
        return new ItemStack(Material.AIR);
    }
}
