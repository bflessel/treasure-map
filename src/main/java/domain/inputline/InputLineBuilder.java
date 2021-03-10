package domain.inputline;

public class InputLineBuilder {
    private String input;
    private InputLineType type;

    public InputLineBuilder setInput(String input) {
        this.input = input;
        return this;
    }

    public InputLineBuilder setType(InputLineType type) {
        this.type = type;
        return this;
    }

    public InputLine createInputLine() {
        return new InputLine(input, type);
    }
}