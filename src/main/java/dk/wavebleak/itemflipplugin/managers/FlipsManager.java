package dk.wavebleak.itemflipplugin.managers;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.wavebleak.itemflipplugin.ItemFlipPlugin;
import dk.wavebleak.itemflipplugin.adapters.FlipListAdapter;
import dk.wavebleak.itemflipplugin.classes.Bet;
import dk.wavebleak.itemflipplugin.classes.Flip;
import dk.wavebleak.itemflipplugin.classes.LinkedBet;
import dk.wavebleak.wavespluginlib.database.JsonArrayManager;
import dk.wavebleak.wavespluginlib.database.JsonManagerException;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Flip> getPlayersFlips(Player player) {
        return flips.stream().filter(flip -> flip.getOwnerUUID().equals(player.getUniqueId())).collect(Collectors.toList());
    }

    public List<LinkedBet> getPlayerBets(Player player) {
        List<LinkedBet> bets = new ArrayList<>();
        flips.stream().forEach(flip -> {
            Arrays.stream(flip.getBets()).filter(bet -> bet.getOwnerUUID().equals(player.getUniqueId())).forEach(bet -> {
                bets.add(new LinkedBet(bet, flip));
            });
        });
        return bets;
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
