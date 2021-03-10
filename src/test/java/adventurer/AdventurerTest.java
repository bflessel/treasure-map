package adventurer;

import domain.adventurer.*;
import domain.coordinate.*;
import domain.inputline.*;
import domain.treasuremap.*;
import exceptions.*;
import org.assertj.core.api.*;
import org.junit.*;
import services.*;

import java.util.*;

public class AdventurerTest {
    @Test
    public void adventurer_should_move_forward() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        lines.add(new InputLineBuilder().setInput("A - Drake - 0 - 0 - E - A").setType(InputLineType.ADVENTURER).createInputLine());
        lines.add(new InputLineBuilder().setInput("A - BlackBeard - 1 - 1 - O - A").setType(InputLineType.ADVENTURER).createInputLine());
        lines.add(new InputLineBuilder().setInput("A - Jones - 2 - 2 - N - A").setType(InputLineType.ADVENTURER).createInputLine());
        lines.add(new InputLineBuilder().setInput("A - Surcouf - 3 - 3 - S - A").setType(InputLineType.ADVENTURER).createInputLine());
        map.populate(lines);
        for (InputLine line : lines) {
            AdventurerManager.moveAdventurerForward(map, line.extractAdventurer());
        }
        Adventurer givenAdventurer = new AdventurerBuilder().setName("Drake").setCoordinate(new CoordinateBuilder().setHorizontalValue(1).setVerticalValue(0).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("E")).setMoveSet("A").setActions(new Actions()).createAdventurer();
        Adventurer givenAdventurer1 = new AdventurerBuilder().setName("BlackBeard").setCoordinate(new CoordinateBuilder().setHorizontalValue(0).setVerticalValue(1).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("O")).setMoveSet("A").setActions(new Actions()).createAdventurer();
        Adventurer givenAdventurer2 = new AdventurerBuilder().setName("Jones").setCoordinate(new CoordinateBuilder().setHorizontalValue(2).setVerticalValue(1).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("N")).setMoveSet("A").setActions(new Actions()).createAdventurer();
        Adventurer givenAdventurer3 = new AdventurerBuilder().setName("Surcouf").setCoordinate(new CoordinateBuilder().setHorizontalValue(3).setVerticalValue(3).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("S")).setMoveSet("A").setActions(new Actions()).createAdventurer();

        Assertions.assertThat(map.getSquare(new Coordinate(1, 0)).getAdventurer()).isEqualTo(givenAdventurer);
        Assertions.assertThat(map.getSquare(new Coordinate(0, 1)).getAdventurer()).isEqualTo(givenAdventurer1);
        Assertions.assertThat(map.getSquare(new Coordinate(2, 1)).getAdventurer()).isEqualTo(givenAdventurer2);
        Assertions.assertThat(map.getSquare(new Coordinate(3, 3)).getAdventurer()).isEqualTo(givenAdventurer3);


    }

    @Test
    public void adventurer_should_not_climb_mountains() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("A - Drake - 0 - 0 - E - A").setType(InputLineType.ADVENTURER).createInputLine();
        lines.add(line);
        lines.add(new InputLineBuilder().setInput("M - 1 - 0").setType(InputLineType.MOUNTAIN).createInputLine());
        map.populate(lines);
        AdventurerManager.moveAdventurerForward(map, line.extractAdventurer());
        Assertions.assertThat(map.getSquare(new Coordinate(0, 0)).getAdventurer()).isEqualTo(line.extractAdventurer().missTurn());
        Assertions.assertThat(map.getSquare(new Coordinate(1, 0)).getAdventurer()).isNotEqualTo(line.extractAdventurer().missTurn());

    }


    @Test
    public void adventurer_should_not_move_outside_map() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(1).setVerticalValue(1).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("A - Drake - 0 - 0 - E - A").setType(InputLineType.ADVENTURER).createInputLine();
        lines.add(line);
        map.populate(lines);
        AdventurerManager.moveAdventurerForward(map, line.extractAdventurer());
        Assertions.assertThat(map.getSquare(new Coordinate(0, 0)).getAdventurer()).isEqualTo(line.extractAdventurer().missTurn());
    }

    @Test
    public void adventurer_should_not_climb_other_adventurers() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("A - Drake - 0 - 0 - E - A").setType(InputLineType.ADVENTURER).createInputLine();
        lines.add(line);
        lines.add(new InputLineBuilder().setInput("A - BlackBeard - 1 - 0 - E - A").setType(InputLineType.ADVENTURER).createInputLine());
        map.populate(lines);
        AdventurerManager.moveAdventurerForward(map, line.extractAdventurer());

        Assertions.assertThat(map.getSquare(new Coordinate(0, 0)).getAdventurer()).isEqualTo(line.extractAdventurer().missTurn());

    }


    @Test
    public void adventurer_should_pick_up_treasures() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("A - Drake - 0 - 0 - E - A").setType(InputLineType.ADVENTURER).createInputLine();
        lines.add(line);
        lines.add(new InputLineBuilder().setInput("T - 1 - 0 - 2").setType(InputLineType.TREASURE).createInputLine());
        map.populate(lines);
        AdventurerManager.moveAdventurerForward(map, line.extractAdventurer());
        Assertions.assertThat(map.getSquare(new Coordinate(1, 0)).getTreasureNumber()).isEqualTo(1);
        Assertions.assertThat(map.getSquare(new Coordinate(1, 0)).getAdventurer().getTreasureNumber()).isEqualTo(1);

    }

    @Test
    public void adventurer_should_turn_left() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("A - Indiana - 0 - 0 - E - G").setType(InputLineType.ADVENTURER).createInputLine();
        lines.add(line);
        map.populate(lines);
        AdventurerManager.turnLeft(map, line.extractAdventurer());
        Adventurer givenAdventurer = new AdventurerBuilder()
                .setName("Indiana")
                .setCoordinate(new CoordinateBuilder().setHorizontalValue(0).setVerticalValue(0).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("N"))
                .setMoveSet("G")
                .setActions(new Actions())
                .createAdventurer();

        Assertions.assertThat(map.getSquare(new Coordinate(0, 0)).getAdventurer()).isEqualTo(givenAdventurer);
    }

    @Test
    public void adventurer_should_turn_right() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("A - Indiana - 0 - 0 - E - D").setType(InputLineType.ADVENTURER).createInputLine();
        lines.add(line);
        map.populate(lines);
        AdventurerManager.turnRight(map, line.extractAdventurer());
        Adventurer givenAdventurer = new AdventurerBuilder()
                .setName("Indiana")
                .setCoordinate(new CoordinateBuilder().setHorizontalValue(0).setVerticalValue(0).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("S"))
                .setMoveSet("D")
                .setActions(new Actions())
                .createAdventurer();

        Assertions.assertThat(map.getSquare(new Coordinate(0, 0)).getAdventurer()).isEqualTo(givenAdventurer);
    }

}
