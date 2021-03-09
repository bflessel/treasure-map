package adventurer;

import domain.adventurer.*;
import domain.coordinate.*;
import domain.inputLine.*;
import domain.treasureMap.*;
import exceptions.*;
import org.assertj.core.api.*;
import org.junit.*;
import services.*;

import java.util.*;

public class AdventurerManagerTest {

    @Test
    public void should_get_Move_Forward_Action() throws AdventurerUnknownActionException {

        Actions moveSets = new Actions();
        moveSets.add(Action.MOVE_FORWARD);

        Assertions.assertThat(AdventurerManager.getActions("A")).isEqualTo(moveSets);

    }

    @Test
    public void should_get_turn_left_Action() throws AdventurerUnknownActionException {

        Actions moveSets = new Actions();
        moveSets.add(Action.TURN_LEFT);

        Assertions.assertThat(AdventurerManager.getActions("G")).isEqualTo(moveSets);

    }

    @Test
    public void should_get_turn_right_Action() throws AdventurerUnknownActionException {

        Actions moveSets = new Actions();
        moveSets.add(Action.TURN_RIGHT);

        Assertions.assertThat(AdventurerManager.getActions("D")).isEqualTo(moveSets);


    }

    @Test
    public void should_throw_when_wrong_action() throws AdventurerUnknownActionException {

        Actions moveSets = new Actions();
        moveSets.add(Action.TURN_RIGHT);
        Assertions.assertThatThrownBy(() -> AdventurerManager.getActions("U"))
                .isInstanceOf(AdventurerUnknownActionException.class);
        Assertions.assertThat(AdventurerManager.getActions("D")).isEqualTo(moveSets);

    }


    @Test
    public void should_get_action_list() throws AdventurerUnknownActionException {

        Actions moveSets = new Actions();
        moveSets.add(Action.TURN_RIGHT);
        moveSets.add(Action.TURN_LEFT);
        moveSets.add(Action.MOVE_FORWARD);

        Assertions.assertThat(AdventurerManager.getActions("DGA")).isEqualTo(moveSets);

    }

    @Test
    public void one_adventurer_should_make_one_move() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("A - Indiana - 0 - 0 - E - A").setType(InputLineType.ADVENTURER).createInputLine();
        lines.add(line);
        map.populate(lines);
        AdventurerManager adventurerManager = new AdventurerManager();
        adventurerManager.playActions(map, line.extractAdventurer());

        Adventurer givenAdventurer = new AdventurerBuilder()
                .setName("Indiana")
                .setCoordinate(new CoordinateBuilder().setHorizontalValue(1).setVerticalValue(0).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("A")
                .setActions(new Actions())
                .createAdventurer();

        Assertions.assertThat(map.getSquare(new Coordinate(1, 0)).getAdventurer()).isEqualTo(givenAdventurer);
    }


    @Test
    public void two_adventurers_should_make_one_move() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        lines.add(new InputLineBuilder().setInput("A - Indiana - 0 - 0 - E - A").setType(InputLineType.ADVENTURER).createInputLine());
        lines.add(new InputLineBuilder().setInput("A - Luke - 0 - 2 - E - A").setType(InputLineType.ADVENTURER).createInputLine());
        map.populate(lines);
        AdventurerManager adventurerManager = new AdventurerManager();
        adventurerManager.playAllActions(map);

        Actions actions = new Actions();

        Adventurer givenAdventurer = new AdventurerBuilder()
                .setName("Indiana")
                .setCoordinate(new CoordinateBuilder().setHorizontalValue(1).setVerticalValue(0).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("A")
                .setActions(actions)
                .createAdventurer();
        Adventurer otherGivenAdventurer = new AdventurerBuilder()
                .setName("Luke")
                .setCoordinate(new CoordinateBuilder().setHorizontalValue(1).setVerticalValue(2).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("A")
                .setActions(actions)
                .createAdventurer();

        Assertions.assertThat(map.getSquare(new Coordinate(1, 0)).getAdventurer()).isEqualTo(givenAdventurer);
        Assertions.assertThat(map.getSquare(new Coordinate(1, 2)).getAdventurer()).isEqualTo(otherGivenAdventurer);
    }

    @Test
    public void two_adventurers_should_make_several_move() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        lines.add(new InputLineBuilder().setInput("A - Indiana - 0 - 0 - E - AA").setType(InputLineType.ADVENTURER).createInputLine());
        lines.add(new InputLineBuilder().setInput("A - Luke - 0 - 2 - E - AA").setType(InputLineType.ADVENTURER).createInputLine());
        map.populate(lines);
        AdventurerManager adventurerManager = new AdventurerManager();
        adventurerManager.playAllActions(map);

        Actions actions = new Actions();
        Adventurer givenAdventurer = new AdventurerBuilder()
                .setName("Indiana")
                .setCoordinate(new CoordinateBuilder().setHorizontalValue(2).setVerticalValue(0).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("AA")
                .setActions(actions)
                .createAdventurer();
        Adventurer otherGivenAdventurer = new AdventurerBuilder()
                .setName("Luke")
                .setCoordinate(new CoordinateBuilder().setHorizontalValue(2).setVerticalValue(2).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("AA")
                .setActions(actions)
                .createAdventurer();

        Assertions.assertThat(map.getSquare(new Coordinate(2, 0)).getAdventurer()).isEqualTo(givenAdventurer);
        Assertions.assertThat(map.getSquare(new Coordinate(2, 2)).getAdventurer()).isEqualTo(otherGivenAdventurer);
    }


    @Test
    public void one_adventurers_should_make_several_actions() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        lines.add(new InputLineBuilder().setInput("A - Indiana - 1 - 1 - E - ADAG").setType(InputLineType.ADVENTURER).createInputLine());
        map.populate(lines);
        AdventurerManager adventurerManager = new AdventurerManager();
        adventurerManager.playAllActions(map);

        Actions actions = new Actions();
        Adventurer givenAdventurer = new AdventurerBuilder()
                .setName("Indiana")
                .setCoordinate(new CoordinateBuilder().setHorizontalValue(2).setVerticalValue(2).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("ADAG")
                .setActions(actions)
                .createAdventurer();

        Assertions.assertThat(map.getSquare(new Coordinate(2, 2)).getAdventurer()).isEqualTo(givenAdventurer);
    }

    @Test
    public void two_adventurers_should_make_several_action() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        lines.add(new InputLineBuilder().setInput("A - Indiana - 0 - 2 - E - AA").setType(InputLineType.ADVENTURER).createInputLine());
        lines.add(new InputLineBuilder().setInput("A - Luke - 1 - 2 - E - GD").setType(InputLineType.ADVENTURER).createInputLine());
        map.populate(lines);
        AdventurerManager adventurerManager = new AdventurerManager();
        adventurerManager.playAllActions(map);

        Actions actions = new Actions();
        Adventurer givenAdventurer = new AdventurerBuilder()
                .setName("Indiana")
                .setCoordinate(new CoordinateBuilder().setHorizontalValue(0).setVerticalValue(2).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("AA")
                .setActions(actions)
                .createAdventurer();
        Adventurer otherGivenAdventurer = new AdventurerBuilder()
                .setName("Luke")
                .setCoordinate(new CoordinateBuilder().setHorizontalValue(1).setVerticalValue(2).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("GD")
                .setActions(actions)
                .createAdventurer();

        Assertions.assertThat(map.getSquare(new Coordinate(0, 2)).getAdventurer()).isEqualTo(givenAdventurer);
        Assertions.assertThat(map.getSquare(new Coordinate(1, 2)).getAdventurer()).isEqualTo(otherGivenAdventurer);
    }

}
