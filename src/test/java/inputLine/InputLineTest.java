package inputLine;

import domain.adventurer.*;
import domain.coordinate.*;
import domain.inputLine.*;
import domain.square.*;
import domain.treasureMap.*;
import exceptions.*;
import org.junit.*;

import static org.assertj.core.api.Assertions.*;

public class InputLineTest {

    @Test
    public void should_return_a_map() {
        TreasureMap givenMap = new TreasureMapBuilder().setHorizontalValue(3).setVerticalValue(4).createTreasureMap();

        InputLine line = new InputLineBuilder().setInput("C - 3 - 4").setType(InputLineType.MAP).createInputLine();
        TreasureMap map = line.extractMap();

        assertThat(givenMap).isEqualTo(map);
    }

    @Test
    public void should_return_a_mountain_square() {
        Square givenMountainSquare = new SquareBuilder().setCoordinate(new CoordinateBuilder().setHorizontalValue(3).setVerticalValue((4)).createCoordinate()).setIsMountain((true)).createSquare();

        InputLine line = new InputLineBuilder().setInput("M - 3 - 4").setType(InputLineType.MOUNTAIN).createInputLine();
        Square mountain = line.extractMountain();

        assertThat(givenMountainSquare).isEqualTo(mountain);
    }

    @Test
    public void should_return_a_treasure_square() {
        Square givenMountainSquare = new SquareBuilder().setCoordinate(new CoordinateBuilder().setHorizontalValue(3).setVerticalValue(4).createCoordinate()).setIsTreasure(true).setTreasureNumber(2).createSquare();

        InputLine line = new InputLineBuilder().setInput("T - 3 - 4 - 2").setType(InputLineType.TREASURE).createInputLine();
        Square treasure = line.extractTreasure();

        assertThat(givenMountainSquare).isEqualTo(treasure);
    }

    @Test
    public void should_return_a_adventurer() throws AdventurerUnknownActionException {
        Adventurer givenAdventurer = new AdventurerBuilder().setName("Indiana").setCoordinate(new CoordinateBuilder().setHorizontalValue(1).setVerticalValue(1).createCoordinate())
                .setOrientation(Orientation.valueOfOrDefault("S")).setMoveSet(("AADADA")).createAdventurer();

        InputLine line = new InputLineBuilder().setInput("A - Indiana - 1 - 1 - S - AADADA").setType(InputLineType.ADVENTURER).createInputLine();
        Adventurer adventurer = line.extractAdventurer();

        assertThat((givenAdventurer)).isEqualTo(adventurer);
    }


}
