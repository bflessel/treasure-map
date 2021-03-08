package adventurer;

import domain.Square.*;
import domain.adventurer.*;
import domain.treasureMap.*;

public class AdventurerManager {
    public void moveAdventurerForward(TreasureMap map, Adventurer adventurer) {
        Square nextSquare = adventurer.getForwaredSquare();
        map.moveAdventurer(adventurer, nextSquare);
    }
}
