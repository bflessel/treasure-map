import domain.InputLine.*;
import domain.Square.*;
import domain.adventurer.*;
import domain.treasureMap.*;
import exceptions.*;
import org.assertj.core.api.*;
import org.junit.*;

import java.util.*;

public class treasureMapTest {
    @Test
    public void should_not_validate_incorrect_square() {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(3).setVerticalValue(3).createTreasureMap();
        Square square = new SquareBuilder().setIsMountain(true).setHorizontalValue(4).setVerticalValue(4).createSquare();
        Assertions.assertThat(map.hasInLimits(square)).isFalse();
    }

    @Test
    public void should_validate_correct_square() {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(3).setVerticalValue(3).createTreasureMap();
        Square square = new SquareBuilder().setIsMountain(true).setHorizontalValue(1).setVerticalValue(1).createSquare();
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
    public void should_return_square_in_limits() throws OutOfMapException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("M - 1 - 1").setType(InputLineType.MOUNTAIN).createInputLine();
        lines.add(line);

        map.populate(lines);
        Assertions.assertThat(line.extractMountain()).isEqualTo(map.getSquare(1, 1));
    }

    @Test
    public void should_return_treasure_square_in_limits() throws OutOfMapException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("T - 1 - 1 - 2").setType(InputLineType.TREASURE).createInputLine();
        lines.add(line);

        map.populate(lines);
        Square square = map.getSquare(1, 1);
        Assertions.assertThat(line.extractTreasure()).isEqualTo(square);
    }

    @Test
    public void should_return_square_with_adventurer() throws OutOfMapException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("A - Indiana - 1 - 1 - S - AADADA").setType(InputLineType.ADVENTURER).createInputLine();
        lines.add(line);
        map.populate(lines);
        Adventurer givenAdventurer = new AdventurerBuilder().setName("Indiana").setHorizontalValue(1).setVerticalValue(1).setOrientation(Orientation.valueOfOrDefault("S")).setMoveSet("AADADA").createAdventurer();

        Assertions.assertThat(givenAdventurer).isEqualTo(map.getSquare(1, 1).getAdventurer());

    }

}
