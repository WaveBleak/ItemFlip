package dk.wavebleak.itemflipplugin.guis;

import dk.wavebleak.itemflipplugin.ItemFlipPlugin;
import dk.wavebleak.itemflipplugin.classes.LinkedBet;
import dk.wavebleak.wavespluginlib.guihelpers.PlayerGUI;
import dk.wavebleak.wavespluginlib.itemhelpers.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.List;

public class DineBudGUI extends PlayerGUI {
    public DineBudGUI(Player player) {
        super(player);
    }

    @Override
    protected void init(Player player) {
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
        List<LinkedBet> bets = ItemFlipPlugin.getFlipsManager().getPlayerBets(player);





    }

    @Override
    protected void onClick(InventoryClickEvent inventoryClickEvent) {
        if(inventoryClickEvent.getInventory().getType().equals(InventoryType.PLAYER)) return;

    }

    @Override
    protected void onClose(InventoryCloseEvent inventoryCloseEvent) {

    }
}
