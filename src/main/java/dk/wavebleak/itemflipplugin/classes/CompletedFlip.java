package dk.wavebleak.itemflipplugin.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CompletedFlip {
    private Bet flip;
    private Bet bet;
    private UUID winnerUUID;
}
