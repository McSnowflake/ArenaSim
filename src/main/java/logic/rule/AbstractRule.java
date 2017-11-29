package logic.rule;

public class AbstractRule {

    private RuleCategory ruleCategory;

    public AbstractRule(RuleCategory ruleCategory){

        this.ruleCategory = ruleCategory;

    }

    public RuleCategory getRuleCategory(){
        return this.ruleCategory;
    }
}
