package domain.treasureMap;

import domain.adventurer.*;
import exceptions.*;
import services.*;

import java.util.*;
import java.util.stream.*;

public class Adventurers {
    private final List<Adventurer> adventurers;

    public Adventurers() {
        this.adventurers = new LinkedList<>();
    }

    public int indexOf(Adventurer adventurer) {
        return this.adventurers.indexOf(adventurer);

    }

    public boolean haveActions() {
        int counter = this.adventurers.stream().mapToInt(Adventurer::giveActionNumber).sum();
        return counter > 0;
    }

    public void set(int index, Adventurer adventurer) {
        this.adventurers.set(index, adventurer);

    }

    public void add(Adventurer adventurer) {
        this.adventurers.add(adventurer);
    }

    public void playAll(TreasureMap map) throws AdventurerUnknownActionException {
        for (Adventurer adventurer : adventurers) {
            AdventurerManager.playActions(map, adventurer);
        }

    }

    public String getInput() {
        return adventurers.stream().map(adventurer -> adventurer.getAdventurerInput() + "\n").collect(Collectors.joining());
    }
}
