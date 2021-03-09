package domain.adventurer;

import java.util.*;

public class Actions {
    private List<Action> actions;

    public Actions(List<Action> actions) {

        this.actions = actions;
    }

    public Actions() {
        this.actions = new LinkedList<>();
    }

    public int giveNumber() {
        return actions.size();
    }

    public Optional<Action> giveAction() {
        return this.actions.stream().findFirst();
    }

    public Actions getNewActionList() {
        LinkedList<Action> newActions = new LinkedList<>(this.actions);
        if (newActions.size() > 0) {
            newActions.removeFirst();
        }
        return new Actions(newActions);

    }

    @Override
    public String toString() {
        return "Actions{" +
                "actions=" + actions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Actions actions1 = (Actions) o;
        return Objects.equals(actions, actions1.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actions);
    }

    public void add(Action action) {
        actions.add(action);
    }
}
