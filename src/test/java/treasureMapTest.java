import domain.Square.*;
import domain.treasureMap.*;
import org.assertj.core.api.*;
import org.junit.*;

public class treasureMapTest {
    @Test
    public void should_not_validate_incorrect_square() {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(3).setVerticalValue(3).createTreasureMap();
        Square square = new SquareBuilder().setIsMountain(true).setHorizontalValue(4).setVerticalValue(4).createSquare();
        Assertions.assertThat(map.hasInLimits(square)).isFalse();
    }

}
