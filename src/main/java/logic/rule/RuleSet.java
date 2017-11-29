package logic.rule;

import java.util.LinkedList;
import java.util.List;

public class RuleSet extends AbstractRule{

    private LinkedList<AbstractRule> rules;

    public RuleSet(RuleCategory ruleCategory){
        super(ruleCategory);
        rules = new LinkedList<>();
    }

    public RuleSet(RuleCategory ruleCategory, List<AbstractRule> rules){
        super(ruleCategory);
        rules.addAll(rules);
    }

    public List<AbstractRule> getRuleChildren(){
        return rules;
    }
}
