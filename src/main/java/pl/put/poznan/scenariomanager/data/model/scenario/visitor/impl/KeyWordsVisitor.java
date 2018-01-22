package pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl;

import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.ScenarioVisitor;

import java.util.Arrays;
import java.util.List;

public class KeyWordsVisitor implements ScenarioVisitor{

    private final static List keyWordsClasses = Arrays.asList("IfScenarioStep", "ForEachScenarioStep", "elseScenarioStep");
    private int wordsCount;

    public KeyWordsVisitor() {
        wordsCount = 0;
    }

    @Override
    public void visit(ScenarioStep step) {
        String s[] = step.getClass().toString().split("\\.");
        wordsCount = keyWordsClasses.contains(s[s.length - 1])? wordsCount + 1: wordsCount;
    }

    public int getIntResult() {
        return wordsCount;
    }

    public String getResult() {
        return Integer.toString(wordsCount);
    }

}
