package dk.wavebleak.itemflipplugin.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dk.wavebleak.itemflipplugin.ItemFlipPlugin;
import dk.wavebleak.itemflipplugin.classes.Bet;
import dk.wavebleak.itemflipplugin.classes.Flip;
import dk.wavebleak.itemflipplugin.extensions.ItemStackExtension;
import dk.wavebleak.itemflipplugin.extensions.StringExtension;
import lombok.experimental.ExtensionMethod;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtensionMethod({StringExtension.class, ItemStackExtension.class})
public class FlipListAdapter extends TypeAdapter<List<Flip>> {

    private final TypeAdapter<Bet> betAdapter = new BetAdapter();

    @Override
    public void write(JsonWriter jsonWriter, List<Flip> flipList) throws IOException {
        jsonWriter.beginArray();
        for(Flip flip : flipList) {
            jsonWriter.beginObject();

            jsonWriter.name("ownerUUID").value(flip.getOwnerUUID().toString());
            jsonWriter.name("items").beginArray();
            for (ItemStack item : flip.getItems()) {
                jsonWriter.value(item.encode());
            }
            jsonWriter.endArray();
            jsonWriter.name("bets").beginArray();
            for (Bet bet : flip.getBets()) {
                betAdapter.write(jsonWriter, bet);
            }
            jsonWriter.endArray();
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
    }

    @Override
    public List<Flip> read(JsonReader jsonReader) throws IOException {
        List<Flip> flips = new ArrayList<>();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            jsonReader.beginObject();

            UUID ownerUUID = UUID.randomUUID();
            List<ItemStack> items = new ArrayList<>();
            List<Bet> bets = new ArrayList<>();
            while(jsonReader.hasNext()) {
                String name = jsonReader.nextName();
                switch (name) {
                    case "ownerUUID":
                        ownerUUID = UUID.fromString(jsonReader.nextString());
                        break;
                    case "items":
                        jsonReader.beginArray();
                        while(jsonReader.hasNext()) {
                            items.add(jsonReader.nextString().decode());
                        }
                        jsonReader.endArray();
                        break;
                    case "bets":
                        jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        bets.add(betAdapter.read(jsonReader));
                    }
                    jsonReader.endArray();
                    break;
                }
            }
            jsonReader.endObject();
            flips.add(new Flip(ownerUUID, items.toArray(new ItemStack[0]), bets.toArray(new Bet[0])));
        }
        jsonReader.endArray();
        return flips;
    }





}
