package logic;

import enums.Attribute;
import enums.Dice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Rule {
    private Map<Attribute, Integer> requirements;
    private Map<Attribute, Integer> bonus;
    private Dice changeDice;

    public Rule(){
        this.requirements = new HashMap<>();
        this.bonus = new HashMap<>();
    }

    public Rule(HashMap<Attribute, Integer> requirements, HashMap<Attribute, Integer> bonus, Dice changedDice){
        this.requirements = requirements;
        this.bonus = bonus;
        this.changeDice = changedDice;
    }

    public Stream<Map.Entry<Attribute, Integer>> getRequirements() {
        return requirements.entrySet().stream();
    }

    public Stream<Map.Entry<Attribute, Integer>> getBonus() {
        return bonus.entrySet().stream();
    }

    public Dice getChangedDice(){
        return this.changeDice;
    }
}
