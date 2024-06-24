package dk.wavebleak.itemflipplugin.guis;

import dk.wavebleak.itemflipplugin.ItemFlipPlugin;
import dk.wavebleak.itemflipplugin.classes.Flip;
import dk.wavebleak.wavespluginlib.guihelpers.PlayerGUI;
import dk.wavebleak.wavespluginlib.itemhelpers.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DineFlipsGUI extends PlayerGUI {
    public DineFlipsGUI(Player player) {
        super(player);
    }

    HashMap<Integer, Flip> slotToFlip = new HashMap<>();

    @Override
    protected void init(Player player) {
        List<Flip> flips = ItemFlipPlugin.getFlipsManager().getPlayersFlips(player);
        rows = 5;
        fillSlotsInRange(
                new ItemBuilder()
                        .setMaterial(Material.STAINED_GLASS_PANE)
                        .setData((byte)0)
                        .setName("&6")
                        .build(),
                0,
                8
        );
        fillSlotsInRange(
                new ItemBuilder()
                        .setMaterial(Material.STAINED_GLASS_PANE)
                        .setData((byte)9)
                        .setName("&6")
                        .build(),
                36,
                45
        );


        int slot = 0;
        int index = 0;
        while(flips.size() >= index + 1) {
            if(slots.containsKey(slot)) {
                slot++;
                continue;
            }
            Flip flip = flips.get(index);
            slots.put(slot,
                    new ItemBuilder()
                            .fromExistingItem(flip.getFirstFlipItem())
                            .setName("&aFlip " + (index + 1))
                            .setLore("&7&oBlah")
                            .build()
            );
            slotToFlip.put(slot, flip);
            slot++;
            index++;
        }
    }

    @Override
    protected void onClick(InventoryClickEvent inventoryClickEvent) {
        if(inventoryClickEvent.getInventory().getType().equals(InventoryType.PLAYER)) return;

    }

    @Override
    protected void onClose(InventoryCloseEvent inventoryCloseEvent) {

    }

}
