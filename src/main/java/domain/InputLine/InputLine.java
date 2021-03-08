package domain.InputLine;

import java.util.*;

public class InputLine {
    private String input;
    private InputLineType inputLineType;

    public InputLine(String input, InputLineType inputLineType) {
        this.input = input;
        this.inputLineType = inputLineType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InputLine inputLine = (InputLine) o;
        return Objects.equals(input, inputLine.input) && inputLineType == inputLine.inputLineType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(input, inputLineType);
    }
}
