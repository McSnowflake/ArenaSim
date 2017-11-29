package logic.rule;

import java.util.HashMap;
import java.util.function.Predicate;

public class Rule extends AbstractRule {

    private HashMap<Predicate, RuleElement> ruleSet;

    public Rule(RuleCategory ruleCategory, HashMap<Predicate, RuleElement> ruleSet){
        super(ruleCategory);
        this.ruleSet = ruleSet;
    }

    public HashMap<Predicate, RuleElement> getRuleSet(){
        return this.ruleSet;
    }

    public void setRuleSet(HashMap<Predicate, RuleElement> ruleSet){
        this.ruleSet = ruleSet;
    }
}
