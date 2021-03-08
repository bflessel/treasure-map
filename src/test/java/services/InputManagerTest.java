package services;


import org.assertj.core.api.*;
import org.junit.*;

import java.io.*;

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

}
