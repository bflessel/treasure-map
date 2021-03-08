package inputLine;

import domain.InputLine.*;
import domain.Square.*;
import domain.treasureMap.*;
import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;

public class InputLineTest {

    @Test
    public void should_return_a_map(){
        TreasureMap givenMap = new TreasureMapBuilder().setHorizontalValue(3).setVerticalValue(4).createTreasureMap();

        InputLine line = new InputLineBuilder().setInput("C - 3 - 4").setType(InputLineType.MAP).createInputLine();
        TreasureMap map = line.extractMap();

        assertThat(givenMap).isEqualTo(map);
    }

    @Test
    public void should_return_a_mountain_square(){
        Square givenMountainSquare = new SquareBuilder().setHorizontalValue((3)).setVerticalValue((4)).setIsMountain((true)).createSquare();

        InputLine line = new InputLineBuilder().setInput("M - 3 - 4").setType(InputLineType.MOUNTAIN).createInputLine();
        Square mountain = line.extractMountain();

        assertThat(givenMountainSquare).isEqualTo(mountain);
    }

}
