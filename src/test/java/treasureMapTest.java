import domain.adventurer.*;
import domain.coordinate.*;
import domain.inputLine.*;
import domain.square.*;
import domain.treasureMap.*;
import exceptions.*;
import org.assertj.core.api.*;
import org.junit.*;

import java.util.*;

public class treasureMapTest {
    @Test
    public void should_not_validate_incorrect_square() {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(3).setVerticalValue(3).createTreasureMap();
        Square square = new SquareBuilder().setIsMountain(true).setCoordinate(new CoordinateBuilder().setHorizontalValue(4).setVerticalValue(4).createCoordinate()).createSquare();
        Assertions.assertThat(map.hasInLimits(square)).isFalse();
    }

    @Test
    public void should_validate_correct_square() {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(3).setVerticalValue(3).createTreasureMap();
        Square square = new SquareBuilder().setIsMountain(true).setCoordinate(new CoordinateBuilder().setHorizontalValue(1).setVerticalValue(1).createCoordinate()).createSquare();
        Assertions.assertThat(map.hasInLimits(square)).isTrue();
    }

    @Test
    public void should_not_return_square_if_out_limits() {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("M - 4 - 1").setType(InputLineType.MOUNTAIN).createInputLine();
        lines.add(line);

        Assertions.assertThatThrownBy(() -> map.populate(lines))
                .isInstanceOf(OutOfMapException.class);
    }


    @Test
    public void should_return_square_in_limits() throws OutOfMapException, WrongAdventurerPlaceException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("M - 1 - 1").setType(InputLineType.MOUNTAIN).createInputLine();
        lines.add(line);

        map.populate(lines);
        Assertions.assertThat(line.extractMountain()).isEqualTo(map.getSquare(new Coordinate(1, 1)));
    }

    @Test
    public void should_return_treasure_square_in_limits() throws OutOfMapException, WrongAdventurerPlaceException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("T - 1 - 1 - 2").setType(InputLineType.TREASURE).createInputLine();
        lines.add(line);

        map.populate(lines);
        Square square = map.getSquare(new Coordinate(1, 1));
        Assertions.assertThat(line.extractTreasure()).isEqualTo(square);
    }

    @Test
    public void should_return_square_with_adventurer() throws OutOfMapException, WrongAdventurerPlaceException, AdventurerUnknownActionException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("A - Indiana - 1 - 1 - S - AADADA").setType(InputLineType.ADVENTURER).createInputLine();
        lines.add(line);
        map.populate(lines);
        Adventurer givenAdventurer = new AdventurerBuilder().setName("Indiana").setCoordinate(new CoordinateBuilder().setHorizontalValue(1).setVerticalValue(1).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("S")).setMoveSet("AADADA").createAdventurer();

        Assertions.assertThat(givenAdventurer).isEqualTo(map.getSquare(new Coordinate(1, 1)).getAdventurer());

    }

    @Test
    public void should_throw_when_setting_adventurer_in_mountain_square() {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        lines.add(new InputLineBuilder().setInput("M - 1 - 1").setType(InputLineType.MOUNTAIN).createInputLine());
        lines.add(new InputLineBuilder().setInput("A - Indiana - 1 - 1 - S - AADADA").setType(InputLineType.ADVENTURER).createInputLine());
        Assertions.assertThatThrownBy(() -> map.populate(lines))
                .isInstanceOf(WrongAdventurerPlaceException.class);
    }

    @Test
    public void should_throw_when_setting_adventurer_in_occupied_square() {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        lines.add(new InputLineBuilder().setInput("A - Indiana - 1 - 1 - S - AADADA").setType(InputLineType.ADVENTURER).createInputLine());
        lines.add(new InputLineBuilder().setInput("A - Arnold - 1 - 1 - S - AADADA").setType(InputLineType.ADVENTURER).createInputLine());
        Assertions.assertThatThrownBy(() -> map.populate(lines))
                .isInstanceOf(WrongAdventurerPlaceException.class);


    }


}
