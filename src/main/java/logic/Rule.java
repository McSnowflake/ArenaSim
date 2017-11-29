package logic;

import enums.Attribute;

import java.util.List;
import java.util.stream.Stream;

public class Rule {
    private List<Attribute> requirements;
    private List<Attribute> bonus;



    public Stream<Attribute> getRequirements() {
        return requirements.stream();
    }


    public Stream<Attribute> getBonus() {
        return bonus.stream();
    }
}
