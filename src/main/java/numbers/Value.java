package numbers;

import javax.json.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Value {

    private int value = 0;
    private List<Die> dice = new ArrayList<>();

    public Value(int value) {
        this.value = value;
    }

    public Value(Die die) {
        this.dice.add(die);
    }

    public Value(Die die, int value) {
        this.dice.add(die);
        this.value = value;
    }

    public Value(JsonObject valueJSON) {
        value = valueJSON.getInt(jsonKeys.value.name());
        JsonArray diceJSON = valueJSON.getJsonArray(jsonKeys.dice.name());
        dice = diceJSON.stream().map(jsonValue -> Die.valueOf(((JsonString) jsonValue).getString())).collect(Collectors.toList());
    }

    public int get() {
        return value + dice.stream().mapToInt(Die::roll).sum();
    }

    public Value addToBase(int value) {
        this.value += value;
        return this;
    }

    public void addDie(Die die) {
        this.dice.add(die);
    }

    public void removeDie(Die die) {
        this.dice.remove(die);
    }

    public int getBase() {
        return value;
    }

    public JsonObject toJSON() {
        JsonObjectBuilder valueJSON = Json.createObjectBuilder();
        valueJSON.add(jsonKeys.value.name(), value);
        JsonArrayBuilder diceJSON = Json.createArrayBuilder();
        dice.forEach(die -> diceJSON.add(die.name()));
        valueJSON.add(jsonKeys.dice.name(), diceJSON);
        return valueJSON.build();
    }

    public List<Die> getDice() {
        return dice;
    }

    public void addToDice(List<Die> dice) {
        this.dice.addAll(dice);
    }

    @Override
    public boolean equals(Object value) {
        return (value instanceof Value) && (this.value == ((Value) value).value) && (this.dice.equals(((Value) value).dice));
    }

    @Override public int hashCode() {
        return value;
    }

    public String print() {
        return value + (dice.size() > 0 ? " + dice -> " + dice.stream().map(die -> die.max + "").collect(Collectors.joining(", ")) : "");
    }

    private enum jsonKeys {
        value,
        dice
    }
}
