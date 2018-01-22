package pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl;

import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.ActorScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.ScenarioVisitor;

import java.util.ArrayList;

/**
 * ScenarioVisitor, that search for Steps which don't begin with an actor.
 */
public class NotActorVisitor implements ScenarioVisitor {


    /**
     * Array to collect matching Scenario steps
     */
    private ArrayList<ScenarioStep> noActorSteps;

    /**
     * Create new @ArrayList of ScenarioSteps on initialization
     */
    public NotActorVisitor() {
        noActorSteps = new ArrayList<>();
    }

    /**
     * This method is invoked on each step in the scenario step hierarchy
     *
     * @param step Scenario step which is currently being visited
     */
    @Override
    public void visit(ScenarioStep step) {
        if(step.getClass() != ActorScenarioStep.class)
            noActorSteps.add(step);
    }

    /**
     * Get @ArrayList of matching steps
     * @return
     */
    public ArrayList<ScenarioStep> getNoActorSteps(){
        return  noActorSteps;
    }
}
