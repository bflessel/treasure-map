package services;

import domain.inputLine.*;
import domain.treasureMap.*;
import exceptions.*;
import org.junit.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class OutputManagerTest {

    @Test
    public void should_return_map_line() {
        List<InputLine> givenInputList = new ArrayList<>();
        givenInputList.add(new InputLineBuilder().setInput("C - 3 - 4").setType(InputLineType.MAP).createInputLine());
        givenInputList.add(new InputLineBuilder().setInput("M - 1 - 1").setType(InputLineType.MOUNTAIN).createInputLine());
        givenInputList.add(new InputLineBuilder().setInput("T - 1 - 3 - 1").setType(InputLineType.TREASURE).createInputLine());
        givenInputList.add(new InputLineBuilder().setInput("A - Indiana - 1 - 1 - S - AADADA").setType(InputLineType.ADVENTURER).createInputLine());

        String outputLine = OutputManager.getMapOutputLine(givenInputList);
        String givenInputLine = "C - 3 - 4\n";
        assertThat(givenInputLine).isEqualTo(outputLine);

    }

    @Test
    public void should_return_mountain_line() {
        List<InputLine> givenInputList = new ArrayList<>();
        givenInputList.add(new InputLineBuilder().setInput("C - 3 - 4").setType(InputLineType.MAP).createInputLine());
        givenInputList.add(new InputLineBuilder().setInput("M - 1 - 1").setType(InputLineType.MOUNTAIN).createInputLine());
        givenInputList.add(new InputLineBuilder().setInput("T - 1 - 3 - 1").setType(InputLineType.TREASURE).createInputLine());
        givenInputList.add(new InputLineBuilder().setInput("A - Indiana - 1 - 1 - S - AADADA").setType(InputLineType.ADVENTURER).createInputLine());

        String outputLine = OutputManager.getMountains(givenInputList);
        String givenInputLine = "M - 1 - 1\n";
        assertThat(givenInputLine).isEqualTo(outputLine);

    }

    @Test
    public void should_return_treasures_line() throws AdventurerUnknownActionException, WrongAdventurerPlaceException, OutOfMapException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("A - Drake - 0 - 0 - E - A").setType(InputLineType.ADVENTURER).createInputLine();
        lines.add(line);
        lines.add(new InputLineBuilder().setInput("T - 1 - 0 - 2").setType(InputLineType.TREASURE).createInputLine());
        map.populate(lines);
        AdventurerManager adventurerManager = new AdventurerManager();

        adventurerManager.moveAdventurerForward(map, line.extractAdventurer());

        String outputLine = OutputManager.getTreasures(map, lines);
        String givenInputLine = "T - 1 - 0 - 1\n";
        assertThat(givenInputLine).isEqualTo(outputLine);

    }

    @Test
    public void should_return_adventurer_line() throws AdventurerUnknownActionException, WrongAdventurerPlaceException, OutOfMapException {
        TreasureMap map = new TreasureMapBuilder().setHorizontalValue(4).setVerticalValue(4).createTreasureMap();
        List<InputLine> lines = new ArrayList<>();
        InputLine line = new InputLineBuilder().setInput("A - Drake - 0 - 0 - E - A").setType(InputLineType.ADVENTURER).createInputLine();
        lines.add(line);
        lines.add(new InputLineBuilder().setInput("T - 1 - 0 - 2").setType(InputLineType.TREASURE).createInputLine());
        map.populate(lines);
        AdventurerManager adventurerManager = new AdventurerManager();

        adventurerManager.moveAdventurerForward(map, line.extractAdventurer());

        String outputLine = OutputManager.getAdventurers(map, lines);
        String givenInputLine = "A - Drake - 1 - 0 - E - 1\n";
        assertThat(outputLine).isEqualTo(givenInputLine);

    }
}
