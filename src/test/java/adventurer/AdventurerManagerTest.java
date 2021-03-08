package adventurer;

import domain.adventurer.*;
import org.assertj.core.api.*;
import org.junit.*;

import java.util.*;

public class AdventurerManagerTest {

    @Test
    public void should_get_Move_Forward_Action(){

        List<Action> moveSets = new LinkedList<>();
        moveSets.add(Action.MOVE_FORWARD);

        Assertions.assertThat(AdventurerManager.getActions("A")).isEqualTo(moveSets);

    }


}
