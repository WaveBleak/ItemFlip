package dk.wavebleak.itemflipplugin.guis;

import dk.wavebleak.wavespluginlib.guihelpers.GUI;
import dk.wavebleak.wavespluginlib.guihelpers.GUIAnchorHorizontal;
import dk.wavebleak.wavespluginlib.guihelpers.GUIAnchorVertical;
import dk.wavebleak.wavespluginlib.guihelpers.PlayerGUI;
import dk.wavebleak.wavespluginlib.itemhelpers.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

public class ViewFlipGUI extends PlayerGUI {
    GUI previousGUI;
    public ViewFlipGUI(Player player, GUI previous) {
        super(player);
        this.previousGUI = previous;
    }
    int back;

    @Override
    protected void init(Player player) {
        back = getSlot(GUIAnchorHorizontal.LEFT, GUIAnchorVertical.BOTTOM);
        slots.put(back,
                new ItemBuilder()
                        .setMaterial(Material.REDSTONE_BLOCK)
                        .setName("&cTilbage")
                        .build());
    }

    @Override
    protected void onClick(InventoryClickEvent inventoryClickEvent) {
        if(inventoryClickEvent.getInventory().getType().equals(InventoryType.PLAYER)) return;
        int slot = inventoryClickEvent.getSlot();
        if(slot == back) {
            previousGUI.open(player);
            return;
        }
    }

    @Override
    protected void onClose(InventoryCloseEvent inventoryCloseEvent) {

    }
}
