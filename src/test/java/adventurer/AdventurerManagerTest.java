package adventurer;

import domain.adventurer.*;
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
        moveSets.add(Action.TURN_RIGH);

        Assertions.assertThat(AdventurerManager.getActions("D")).isEqualTo(moveSets);


    }

    @Test
    public void should_throw_when_wrong_action() throws AdventurerUnknownActionException {

        List<Action> moveSets = new LinkedList<>();
        moveSets.add(Action.TURN_RIGH);
        Assertions.assertThatThrownBy(() -> AdventurerManager.getActions("U"))
                .isInstanceOf(AdventurerUnknownActionException.class);
        Assertions.assertThat(AdventurerManager.getActions("D")).isEqualTo(moveSets);

    }



}
