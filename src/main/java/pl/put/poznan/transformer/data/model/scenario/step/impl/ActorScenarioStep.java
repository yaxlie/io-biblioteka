package pl.put.poznan.transformer.data.model.scenario.step.impl;

import pl.put.poznan.transformer.data.model.scenario.step.AbstractScenarioStep;
import pl.put.poznan.transformer.data.model.scenario.step.ScenarioStep;

public class ActorScenarioStep extends AbstractScenarioStep {

    private String actor;

    public ActorScenarioStep(String description, String actor) {
        super(description);

        this.actor = actor;
    }

    public String getActor() {
        return actor;
    }
}
