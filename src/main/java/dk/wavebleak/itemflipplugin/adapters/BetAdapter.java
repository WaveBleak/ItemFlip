package dk.wavebleak.itemflipplugin.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dk.wavebleak.itemflipplugin.classes.Bet;
import dk.wavebleak.itemflipplugin.extensions.ItemStackExtension;
import dk.wavebleak.itemflipplugin.extensions.StringExtension;
import lombok.experimental.ExtensionMethod;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtensionMethod({StringExtension.class, ItemStackExtension.class})
public class BetAdapter extends TypeAdapter<Bet> {
    @Override
    public void write(JsonWriter jsonWriter, Bet bet) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("ownerUUID").value(bet.getOwnerUUID().toString());
        jsonWriter.name("items").beginArray();
        for (ItemStack item : bet.getItems()) {
            jsonWriter.value(item.encode());
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public Bet read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        UUID ownerUUID = UUID.randomUUID();
        List<ItemStack> itemList = new ArrayList<>();
        while(jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            switch (name) {
                case "ownerUUID":
                    ownerUUID = UUID.fromString(jsonReader.nextString());
                    break;
                case "items":
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        itemList.add(jsonReader.nextString().decode());
                    }
                    jsonReader.endArray();
                    break;
            }
        }
        jsonReader.endObject();
        return new Bet(ownerUUID, itemList.toArray(new ItemStack[0]));
    }
}
