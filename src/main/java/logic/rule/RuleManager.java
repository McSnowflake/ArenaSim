package logic.rule;

import java.util.HashMap;
import java.util.LinkedList;

public class RuleManager {

    private HashMap<RuleCategory, LinkedList<AbstractRule>> rules;

    private static RuleManager instance;

    public static RuleManager getInstance(){

        if (instance == null){
            return instance = new RuleManager();
        }
        return instance;
    }

    private RuleManager(){
        rules = new HashMap<>();
    }

    public void importRules(HashMap<RuleCategory, LinkedList<AbstractRule>> rules){
        this.rules = rules;
    }

    public LinkedList<AbstractRule> getRulesFromCategory(RuleCategory ruleCategory){
        return rules.get(ruleCategory);
    }
}
