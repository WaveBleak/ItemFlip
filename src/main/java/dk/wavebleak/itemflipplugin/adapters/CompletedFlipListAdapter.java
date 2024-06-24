package dk.wavebleak.itemflipplugin.adapters;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dk.wavebleak.itemflipplugin.classes.Bet;
import dk.wavebleak.itemflipplugin.classes.CompletedFlip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CompletedFlipListAdapter extends TypeAdapter<List<CompletedFlip>> {

    private final TypeAdapter<Bet> betAdapter;
    public CompletedFlipListAdapter() {
        this.betAdapter = new Gson().getAdapter(Bet.class);
    }

    @Override
    public void write(JsonWriter jsonWriter, List<CompletedFlip> completedFlips) throws IOException {
        jsonWriter.beginArray();

        for(CompletedFlip flip : completedFlips) {
            jsonWriter.beginObject();
            jsonWriter.name("winnerUUID").value(flip.getWinnerUUID().toString());
            jsonWriter.name("flip");
            betAdapter.write(jsonWriter, flip.getFlip());
            jsonWriter.name("bet");
            betAdapter.write(jsonWriter, flip.getBet());
            jsonWriter.endObject();
        }

        jsonWriter.endArray();
    }

    @Override
    public List<CompletedFlip> read(JsonReader jsonReader) throws IOException {
        List<CompletedFlip> completedFlips = new ArrayList<>();

        jsonReader.beginArray();

        while(jsonReader.hasNext()) {
            UUID winnerUUID = UUID.randomUUID();
            Bet flip = null;
            Bet bet = null;
            jsonReader.beginObject();
            while(jsonReader.hasNext()) {
                String name = jsonReader.nextName();
                switch (name) {
                    case "winnerUUID":
                        winnerUUID = UUID.fromString(jsonReader.nextString());
                        break;
                    case "flip":
                        flip = betAdapter.read(jsonReader);
                        break;
                    case "bet":
                        bet = betAdapter.read(jsonReader);
                        break;
                }
            }
            jsonReader.endObject();
            completedFlips.add(new CompletedFlip(flip, bet, winnerUUID));
        }

        jsonReader.endArray();
        return completedFlips;
    }
}
