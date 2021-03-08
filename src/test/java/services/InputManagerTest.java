package services;


import domain.InputLine.*;
import org.assertj.core.api.*;
import org.junit.*;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class InputManagerTest {
    public static final String path = "src/test/java/files";
    public static final String fileName = "firstTest.txt";
    public static final String inputTest = """
            C - 3 - 4
            M - 1 - 1
            T - 1 - 3 - 1
            A - Indiana - 1 - 1 - S - AADADA""";


    @Test
    public void should_return_inputString_from_input_file() throws IOException {
        String content = InputManager.getInputString(path, fileName);
        Assertions.assertThat(inputTest).isEqualTo(content);
    }

    @Test
    public void should_not_return_attributeLine_with_wrong_input_line(){
        String inputTest = "H - 3 - 4";
        Assertions.assertThat(InputManager.getInputLine(inputTest)).isNull();
    }

    @Test
    public void shoud_return_map_input_line()  {
        String inputTest = "C - 3 - 4";
        InputLine givenInputLine = new InputLineBuilder().setInput("C - 3 - 4").setType(InputLineType.MAP).createInputLine();
        InputLine mapSize = InputManager.getInputLine(inputTest);
        assertThat(givenInputLine).isEqualTo(mapSize);
    }

    @Test
    public void shoud_return_mountain_input_line()  {
        String inputTest = "M - 3 - 4";
        InputLine givenInputLine = new InputLineBuilder().setInput("M - 3 - 4").setType(InputLineType.MOUNTAIN).createInputLine();
        InputLine mapSize = InputManager.getInputLine(inputTest);
        assertThat(givenInputLine).isEqualTo(mapSize);
    }

    @Test
    public void shoud_return_treasure_input_line()  {
        String inputTest = "T - 3 - 4";
        InputLine givenInputLine = new InputLineBuilder().setInput("T - 3 - 4").setType(InputLineType.TREASURE).createInputLine();
        InputLine mapSize = InputManager.getInputLine(inputTest);
        assertThat(givenInputLine).isEqualTo(mapSize);
    }

    @Test
    public void shoud_return_adventure_input_line()  {
        String inputTest = "A - 3 - 4";
        InputLine givenInputLine = new InputLineBuilder().setInput("A - 3 - 4").setType(InputLineType.ADVENTURER).createInputLine();
        InputLine mapSize = InputManager.getInputLine(inputTest);
        assertThat(givenInputLine).isEqualTo( mapSize);
    }

    @Test
    public void shoud_return_nothing_if_comment()  {
        String inputTest = "# A - 3 - 4";
        assertThat(InputManager.getInputLine(inputTest)).isNull();
    }

}
