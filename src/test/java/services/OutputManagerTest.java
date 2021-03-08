package services;

import domain.inputLine.*;
import org.junit.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputManagerTest {

    @Test
    public void should_return_map_line(){
        List<InputLine> givenInputList = new ArrayList<>();
        givenInputList.add(new InputLineBuilder().setInput("C - 3 - 4").setType(InputLineType.MAP).createInputLine());
        givenInputList.add(new InputLineBuilder().setInput("M - 1 - 1").setType(InputLineType.MOUNTAIN).createInputLine());
        givenInputList.add(new InputLineBuilder().setInput("T - 1 - 3 - 1").setType(InputLineType.TREASURE).createInputLine());
        givenInputList.add(new InputLineBuilder().setInput("A - Indiana - 1 - 1 - S - AADADA").setType(InputLineType.ADVENTURER).createInputLine());

        String outputLine = OutputManager.getMapOutputLine(givenInputList);
        String  givenInputLine= "C - 3 - 4";
        assertThat(givenInputLine).isEqualTo( outputLine);

    }
}
