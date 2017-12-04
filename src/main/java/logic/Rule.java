package logic;

import enums.Attribute;

import java.util.Map;
import java.util.stream.Stream;

public class Rule {
    private Map<Attribute, Integer> requirements;
    private Map<Attribute, Integer> bonus;

    public Stream<Map.Entry<Attribute, Integer>> getRequirements() {
        return requirements.entrySet().stream();
    }

    public Stream<Map.Entry<Attribute, Integer>> getBonus() {
        return bonus.entrySet().stream();
    }
}
