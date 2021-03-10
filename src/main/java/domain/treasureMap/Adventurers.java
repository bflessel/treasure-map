package domain.treasuremap;

import domain.adventurer.*;
import exceptions.*;
import services.*;

import java.util.*;
import java.util.stream.*;

public class Adventurers {
    public static final String CARIAGE_RETURN = "\n";
    private final List<Adventurer> adventurerList;

    public Adventurers() {
        this.adventurerList = new LinkedList<>();
    }

    public int indexOf(Adventurer adventurer) {
        return this.adventurerList.indexOf(adventurer);
    }

    public boolean haveActions() {
        int counter = this.adventurerList.stream().mapToInt(Adventurer::giveActionNumber).sum();
        return counter > 0;
    }

    public void set(int index, Adventurer adventurer) {
        this.adventurerList.set(index, adventurer);
    }

    public void add(Adventurer adventurer) {
        this.adventurerList.add(adventurer);
    }

    public void playAll(TreasureMap map) throws AdventurerUnknownActionException {
        for (Adventurer adventurer : adventurerList) {
            AdventurerManager.playActions(map, adventurer);
        }

    }

    public String getInput() {
        return adventurerList.stream().map(adventurer -> adventurer.getAdventurerInput() + CARIAGE_RETURN).collect(Collectors.joining());
    }
}
