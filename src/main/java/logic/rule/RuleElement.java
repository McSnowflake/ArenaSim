package logic.rule;

import logic.Attribute;

import java.util.HashMap;

public class RuleElement {

    private HashMap<Attribute, Integer> effectiveRules;

    public RuleElement(HashMap<Attribute, Integer> effectiveRules){
        this.effectiveRules = effectiveRules;
    }

    public HashMap<Attribute, Integer> getEffectiveRules(){
        return this.effectiveRules;
    }
}
