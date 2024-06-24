package dk.wavebleak.itemflipplugin.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LinkedBet {
    private Bet bet;
    private Flip flip;
}
