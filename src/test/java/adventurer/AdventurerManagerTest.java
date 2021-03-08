package adventurer;

import domain.inputLine.*;
import domain.adventurer.*;
import domain.treasureMap.*;
import exceptions.*;
import org.assertj.core.api.*;
import org.junit.*;

import java.util.*;

public class AdventurerManagerTest {

    @Test
    public void should_get_Move_Forward_Action() throws AdventurerUnknownActionException {

        List<Action> moveSets = new LinkedList<>();
        moveSets.add(Action.MOVE_FORWARD);

        Assertions.assertThat(AdventurerManager.getActions("A")).isEqualTo(moveSets);

    }

    @Test
    public void should_get_turn_left_Action() throws AdventurerUnknownActionException {

        List<Action> moveSets = new LinkedList<>();
        moveSets.add(Action.TURN_LEFT);

        Assertions.assertThat(AdventurerManager.getActions("G")).isEqualTo(moveSets);

    }

    @Test
    public void should_get_turn_right_Action() throws AdventurerUnknownActionException {

        List<Action> moveSets = new LinkedList<>();
        moveSets.add(Action.TURN_RIGHT);

        Assertions.assertThat(AdventurerManager.getActions("D")).isEqualTo(moveSets);


    }

    @Test
    public void should_throw_when_wrong_action() throws AdventurerUnknownActionException {

        List<Action> moveSets = new LinkedList<>();
        moveSets.add(Action.TURN_RIGHT);
        Assertions.assertThatThrownBy(() -> AdventurerManager.getActions("U"))
                .isInstanceOf(AdventurerUnknownActionException.class);
        Assertions.assertThat(AdventurerManager.getActions("D")).isEqualTo(moveSets);

    }


    @Test
    public void should_get_action_list() throws AdventurerUnknownActionException {

        List<Action> moveSets = new LinkedList<>();
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
                .setHorizontalValue(1)
                .setVerticalValue(0)
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("A")
                .setActions(new LinkedList<>())
                .createAdventurer();

        Assertions.assertThat(map.getSquare(1, 0).getAdventurer()).isEqualTo(givenAdventurer);
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

        List<Action> actions = new LinkedList<>();
        Adventurer givenAdventurer = new AdventurerBuilder()
                .setName("Indiana")
                .setHorizontalValue(1)
                .setVerticalValue(0)
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("A")
                .setActions(actions)
                .createAdventurer();
        Adventurer otherGivenAdventurer = new AdventurerBuilder()
                .setName("Luke")
                .setHorizontalValue(1)
                .setVerticalValue(2)
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("A")
                .setActions(actions)
                .createAdventurer();

        Assertions.assertThat(map.getSquare(1, 0).getAdventurer()).isEqualTo(givenAdventurer);
        Assertions.assertThat(map.getSquare(1, 2).getAdventurer()).isEqualTo(otherGivenAdventurer);
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

        List<Action> actions = new LinkedList<>();
        Adventurer givenAdventurer = new AdventurerBuilder()
                .setName("Indiana")
                .setHorizontalValue(2)
                .setVerticalValue(0)
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("AA")
                .setActions(actions)
                .createAdventurer();
        Adventurer otherGivenAdventurer = new AdventurerBuilder()
                .setName("Luke")
                .setHorizontalValue(2)
                .setVerticalValue(2)
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("AA")
                .setActions(actions)
                .createAdventurer();

        Assertions.assertThat(map.getSquare(2, 0).getAdventurer()).isEqualTo(givenAdventurer);
        Assertions.assertThat(map.getSquare(2, 2).getAdventurer()).isEqualTo(otherGivenAdventurer);
    }


    @Test
    public void one_adventurers_should_make_several_actions() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        lines.add(new InputLineBuilder().setInput("A - Indiana - 1 - 1 - E - ADAG").setType(InputLineType.ADVENTURER).createInputLine());
        map.populate(lines);
        AdventurerManager adventurerManager = new AdventurerManager();
        adventurerManager.playAllActions(map);

        List<Action> actions = new LinkedList<>();
        Adventurer givenAdventurer = new AdventurerBuilder()
                .setName("Indiana")
                .setHorizontalValue(2)
                .setVerticalValue(0)
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("ADAG")
                .setActions(actions)
                .createAdventurer();

        Assertions.assertThat(map.getSquare(2, 0).getAdventurer()).isEqualTo(givenAdventurer);
        }

    @Test
    public void two_adventurers_should_make_several_action() throws WrongAdventurerPlaceException, OutOfMapException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        lines.add(new InputLineBuilder().setInput("A - Indiana - 0 - 0 - N - AA").setType(InputLineType.ADVENTURER).createInputLine());
        lines.add(new InputLineBuilder().setInput("A - Luke - 0 - 2 - E - GD").setType(InputLineType.ADVENTURER).createInputLine());
        map.populate(lines);
        AdventurerManager adventurerManager = new AdventurerManager();
        adventurerManager.playAllActions(map);

        List<Action> actions = new LinkedList<>();
        Adventurer givenAdventurer = new AdventurerBuilder()
                .setName("Indiana")
                .setHorizontalValue(0)
                .setVerticalValue(1)
                .setOrientation(Orientation.valueOfOrDefault("N"))
                .setMoveSet("AA")
                .setActions(actions)
                .createAdventurer();
        Adventurer otherGivenAdventurer = new AdventurerBuilder()
                .setName("Luke")
                .setHorizontalValue(0)
                .setVerticalValue(2)
                .setOrientation(Orientation.valueOfOrDefault("E"))
                .setMoveSet("GD")
                .setActions(actions)
                .createAdventurer();

        Assertions.assertThat(map.getSquare(0, 1).getAdventurer()).isEqualTo(givenAdventurer);
        Assertions.assertThat(map.getSquare(0, 2).getAdventurer()).isEqualTo(otherGivenAdventurer);
    }

}
