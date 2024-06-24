package dk.wavebleak.itemflipplugin.managers;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import dk.wavebleak.itemflipplugin.ItemFlipPlugin;
import dk.wavebleak.itemflipplugin.adapters.FlipListAdapter;
import dk.wavebleak.itemflipplugin.classes.CompletedFlip;
import dk.wavebleak.itemflipplugin.classes.Flip;
import dk.wavebleak.wavespluginlib.database.JsonArrayManager;
import dk.wavebleak.wavespluginlib.database.JsonManagerException;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CompletedFlipsManager extends JsonArrayManager<CompletedFlip> {
    @Getter
    private List<CompletedFlip> completedFlips = new ArrayList<>();

    public CompletedFlipsManager() {
        super(CompletedFlip.class);
        setInstance(ItemFlipPlugin.getInstance());
        setFile("CompletedFlips");
        setGson(new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(new TypeToken<List<Flip>>() {}.getType(), new FlipListAdapter())
                .create());
    }


    @Override
    public void init() throws JsonManagerException {
        super.init();
        completedFlips = loadData(new TypeToken<List<Flip>>() {}.getType());
    }

    @Override
    public void saveData(List<CompletedFlip> data) {
        super.saveData(completedFlips);
    }


    @Override
    public List<CompletedFlip> refreshData(Type type) {
        List<CompletedFlip> data = super.refreshData(type);
        completedFlips = data;
        return data;
    }

    @Override
    public List<CompletedFlip> loadData(Type type) {
        List<CompletedFlip> data = super.loadData(type);
        this.completedFlips = data;
        return data;
    }
}
