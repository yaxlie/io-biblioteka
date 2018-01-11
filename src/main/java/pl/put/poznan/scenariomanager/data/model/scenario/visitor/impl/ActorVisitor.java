package pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl;

import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.ScenarioVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Marcin on 11.01.2018.
 */
public class ActorVisitor implements ScenarioVisitor {

    private ArrayList<ScenarioStep> noActorSteps;

    public ActorVisitor() {
        noActorSteps = new ArrayList<>();
    }

    @Override
    public void visit(ScenarioStep step) {
        String s[] = step.getClass().toString().split("\\.");
        if(s[s.length - 1].equals("ActorScenarioStep"))
            noActorSteps.add(step);
    }

    public ArrayList<ScenarioStep> getNoActorSteps(){
        return  noActorSteps;
    }
}
