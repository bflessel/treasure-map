package inputLine;

import domain.InputLine.*;
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
}
