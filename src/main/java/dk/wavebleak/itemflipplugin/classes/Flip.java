package dk.wavebleak.itemflipplugin.classes;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Flip {
    private UUID ownerUUID;
    private ItemStack[] items;
    private Bet[] bets;


    public Flip(UUID ownerUUID, ItemStack[] items, Bet[] bets) {
        this.ownerUUID = ownerUUID;
        this.bets = (bets == null ? new Bet[] {} : bets);
        if(items == null) {
            this.items = new ItemStack[]{null, null, null, null, null, null, null, null, null};
        } else {
            List<ItemStack> itemList = new ArrayList<>();
            for(int i = 0; i < 9; i++) {
                if(items.length >= i + 1) {
                    itemList.add(items[i]);
                }
                itemList.add(null);
            }
            this.items = itemList.toArray(new ItemStack[0]);
        }
    }

    public Bet toBet() {
        return new Bet(ownerUUID, items);
    }

    public void addItem(ItemStack item) throws IndexOutOfBoundsException {
        for(int i = 0; i < 9; i++) {
            if (items[i] == null) {
                items[i] = item;
                return;
            }
        }
        throw new IndexOutOfBoundsException("The item list is full");
    }
}
