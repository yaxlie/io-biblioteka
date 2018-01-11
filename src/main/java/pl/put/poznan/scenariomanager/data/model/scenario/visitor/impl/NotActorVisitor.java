package pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl;

import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.ActorScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.ScenarioVisitor;

import java.util.ArrayList;

/**
 * Created by Marcin on 11.01.2018.
 */
public class NotActorVisitor implements ScenarioVisitor {

    private ArrayList<ScenarioStep> noActorSteps;

    public NotActorVisitor() {
        noActorSteps = new ArrayList<>();
    }

    @Override
    public void visit(ScenarioStep step) {
        if(step.getClass() != ActorScenarioStep.class)
            noActorSteps.add(step);
    }

    public ArrayList<ScenarioStep> getNoActorSteps(){
        return  noActorSteps;
    }
}
