package dk.wavebleak.itemflipplugin.guis;

import dk.wavebleak.wavespluginlib.guihelpers.GUI;
import dk.wavebleak.wavespluginlib.guihelpers.GUIAnchorHorizontal;
import dk.wavebleak.wavespluginlib.guihelpers.GUIAnchorVertical;
import dk.wavebleak.wavespluginlib.itemhelpers.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

public class MainGUI extends GUI {



    int dineBud;
    int dineFlips;
    int alleFlips;
    int createFlip;
    int claim;
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


        int middle = getSlot(GUIAnchorHorizontal.MIDDLE, GUIAnchorVertical.MIDDLE);

        dineBud = middle - 3;
        dineFlips = middle - 1;
        alleFlips = middle + 1;
        createFlip = middle + 3;

        claim = getSlot(GUIAnchorHorizontal.RIGHT, GUIAnchorVertical.BOTTOM) - 1;



        slots.put(dineBud, new ItemBuilder()
                .setMaterial(Material.DIAMOND)
                .setName("&aDine Bud")
                .setLore("&7Se alle dine bud")
                .build());

        slots.put(dineFlips, new ItemBuilder()
                .setMaterial(Material.PAPER)
                .setName("&aDine Flips")
                .setLore("&7Se alle dine flips")
                .build());

        slots.put(alleFlips, new ItemBuilder()
                .setMaterial(Material.BOOK)
                .setName("&aAlle Flips")
                .setLore("&7Se alle flips")
                .build());

        slots.put(createFlip, new ItemBuilder()
                .setMaterial(Material.EMERALD)
                .setName("&aOpret Flip")
                .setLore("&7Opret et nyt flip")
                .build());

        slots.put(claim, new ItemBuilder()
                .setMaterial(Material.GOLD_INGOT)
                .setName("&aClaim")
                .setLore("&7Claim dine gevinster")
                .build());





    }

    @Override
    protected void onClick(InventoryClickEvent event) {
        if(event.getInventory().getType().equals(InventoryType.PLAYER)) return;

    }

    @Override
    protected void onClose(InventoryCloseEvent event) {

    }
}
