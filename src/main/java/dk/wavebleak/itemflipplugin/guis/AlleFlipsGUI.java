package dk.wavebleak.itemflipplugin.guis;

import dk.wavebleak.itemflipplugin.ItemFlipPlugin;
import dk.wavebleak.itemflipplugin.classes.Flip;
import dk.wavebleak.wavespluginlib.guihelpers.GUI;
import dk.wavebleak.wavespluginlib.guihelpers.GUIAnchorHorizontal;
import dk.wavebleak.wavespluginlib.guihelpers.GUIAnchorVertical;
import dk.wavebleak.wavespluginlib.itemhelpers.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.HashMap;
import java.util.List;

public class AlleFlipsGUI extends GUI {

    int page;
    int next;
    int previous;
    HashMap<Integer, Flip> slotToFlip = new HashMap<>();
    public AlleFlipsGUI(int page) {
        this.page = page;
        init();
    }

    @Override
    protected void init() {
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
        List<Flip> flips = ItemFlipPlugin.getFlipsManager().getFlips();
        next = getSlot(GUIAnchorHorizontal.RIGHT, GUIAnchorVertical.BOTTOM) - 1;
        previous = getSlot(GUIAnchorHorizontal.LEFT, GUIAnchorVertical.BOTTOM) + 1;
        slots.put(next,
                new ItemBuilder()
                        .setMaterial(Material.REDSTONE_BLOCK)
                        .setName("&cNæste side")
                        .build());
        slots.put(previous,
                new ItemBuilder()
                        .setMaterial(Material.REDSTONE_BLOCK)
                        .setName("&cTilbage")
                        .build());

        int index = ((page - 1) * 27) + 9;
        int slot = 0;
        int endIndex = Math.min(index + 27, flips.size());
        while(endIndex >= index + 1) {
            if(slots.containsKey(slot)) {
                slot++;
                continue;
            }
            Flip flip = flips.get(index);
            slots.put(slot,
                    new ItemBuilder()
                            .fromExistingItem(flip.getFirstFlipItem())
                            .setName("&aFlip " + (index + 1))
                            .setLore("&7&oBlahh")
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
        int slot = inventoryClickEvent.getSlot();
        Player player = Bukkit.getPlayer(inventoryClickEvent.getWhoClicked().getUniqueId());

        if(slot == next) {
            new AlleFlipsGUI(page + 1)
                    .open(player);
        }
        if(slot == previous) {
            new AlleFlipsGUI(page - 1)
                    .open(player);
        }

    }

    @Override
    protected void onClose(InventoryCloseEvent inventoryCloseEvent) {

    }


}
