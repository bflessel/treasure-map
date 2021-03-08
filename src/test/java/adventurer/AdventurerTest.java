package adventurer;

import domain.InputLine.*;
import domain.adventurer.*;
import domain.treasureMap.*;
import exceptions.*;
import org.assertj.core.api.*;
import org.junit.*;

import java.util.*;

public class AdventurerTest {
    @Test
    public void adventurer_should_move_forward() throws WrongAdventurerPlaceException, OutOfMapException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        lines.add(new InputLineBuilder().setInput("A - Drake - 0 - 0 - E - A").setType(InputLineType.ADVENTURER).createInputLine());
        lines.add(new InputLineBuilder().setInput("A - BlackBeard - 1 - 1 - O - A").setType(InputLineType.ADVENTURER).createInputLine());
        lines.add(new InputLineBuilder().setInput("A - Jones - 2 - 2 - N - A").setType(InputLineType.ADVENTURER).createInputLine());
        lines.add(new InputLineBuilder().setInput("A - Surcouf - 3 - 3 - S - A").setType(InputLineType.ADVENTURER).createInputLine());
        map.populate(lines);
        AdventurerManager adventurerManager = new AdventurerManager();
        for (InputLine line : lines) {
            adventurerManager.moveAdventurerForward(map, line.extractAdventurer());
        }
        Adventurer givenAdventurer = new AdventurerBuilder().setName("Drake").setHorizontalValue(1).setVerticalValue(0).setOrientation(Orientation.valueOfOrDefault("E")).setMoveSet("A").createAdventurer();
        Adventurer givenAdventurer1 = new AdventurerBuilder().setName("BlackBeard").setHorizontalValue(0).setVerticalValue(1).setOrientation(Orientation.valueOfOrDefault("O")).setMoveSet("A").createAdventurer();
        Adventurer givenAdventurer2 = new AdventurerBuilder().setName("Jones").setHorizontalValue(2).setVerticalValue(3).setOrientation(Orientation.valueOfOrDefault("N")).setMoveSet("A").createAdventurer();
        Adventurer givenAdventurer3 = new AdventurerBuilder().setName("Surcouf").setHorizontalValue(3).setVerticalValue(2).setOrientation(Orientation.valueOfOrDefault("S")).setMoveSet("A").createAdventurer();

        Assertions.assertThat(map.getSquare(1, 0).getAdventurer()).isEqualTo(givenAdventurer);
        Assertions.assertThat(map.getSquare(0, 1).getAdventurer()).isEqualTo(givenAdventurer1);
        Assertions.assertThat(map.getSquare(2, 3).getAdventurer()).isEqualTo(givenAdventurer2);
        Assertions.assertThat(map.getSquare(3, 2).getAdventurer()).isEqualTo(givenAdventurer3);


    }
}
