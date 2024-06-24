package dk.wavebleak.itemflipplugin.managers;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.wavebleak.itemflipplugin.ItemFlipPlugin;
import dk.wavebleak.itemflipplugin.adapters.FlipListAdapter;
import dk.wavebleak.itemflipplugin.classes.Flip;
import dk.wavebleak.wavespluginlib.database.JsonArrayManager;
import dk.wavebleak.wavespluginlib.database.JsonManagerException;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FlipsManager extends JsonArrayManager<Flip> {
    @Getter
    private List<Flip> flips = new ArrayList<>();

    public FlipsManager() {
        super(Flip.class);
        setInstance(ItemFlipPlugin.getInstance());
        setFile("Flips");
        setGson(new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(new TypeToken<List<Flip>>() {}.getType(), new FlipListAdapter())
                .create());
    }


    @Override
    public void init() throws JsonManagerException {
        super.init();
        flips = loadData(new TypeToken<List<Flip>>() {}.getType());
    }

    @Override
    public void saveData(List<Flip> data) {
        super.saveData(flips);
    }


    @Override
    public List<Flip> refreshData(Type type) {
        List<Flip> data = super.refreshData(type);
        flips = data;
        return data;
    }

    @Override
    public List<Flip> loadData(Type type) {
        List<Flip> data = super.loadData(type);
        this.flips = data;
        return data;
    }
}
