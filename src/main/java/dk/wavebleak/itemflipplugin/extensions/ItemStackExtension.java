package dk.wavebleak.itemflipplugin.extensions;

import dk.wavebleak.itemflipplugin.ItemFlipPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayOutputStream;
import java.util.logging.Level;

public class ItemStackExtension {

    public static String encode(ItemStack item) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {
            dataOutput.writeObject(item);
            return new String(Base64Coder.encode(outputStream.toByteArray()));
        } catch (Exception exception) {
            ItemFlipPlugin.getInstance().getLogger().log(Level.SEVERE, "Failed to encode ItemStack to Base64", exception);
        }
        return "";
    }

}
