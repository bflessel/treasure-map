package adventurer;

import domain.Square.*;
import domain.adventurer.*;
import domain.treasureMap.*;
import exceptions.*;

public class AdventurerManager {
    public void moveAdventurerForward(TreasureMap map, Adventurer adventurer) throws AdventureWrongMoveException {
        Square nextSquare = adventurer.getForwaredSquare();
        map.moveAdventurer(adventurer, nextSquare);
    }
}
