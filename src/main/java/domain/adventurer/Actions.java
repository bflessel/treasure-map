package domain.adventurer;

import java.util.*;

public class Actions {
    private final List<Action> actionList;

    public Actions(List<Action> actions) {

        this.actionList = actions;
    }

    public Actions() {
        this.actionList = new LinkedList<>();
    }

    public int giveNumber() {
        return actionList.size();
    }

    public Optional<Action> giveAction() {
        return this.actionList.stream().findFirst();
    }

    public Actions getNewActionList() {
        LinkedList<Action> newActions = new LinkedList<>(this.actionList);
        if (!newActions.isEmpty()) {
            newActions.removeFirst();
        }
        return new Actions(newActions);

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
        return Objects.equals(actionList, actions1.actionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionList);
    }

    public void add(Action action) {
        actionList.add(action);
    }
}
