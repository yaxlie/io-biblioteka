package pl.put.poznan.scenariomanager.data.model.scenario.step.impl;

import pl.put.poznan.scenariomanager.data.model.scenario.step.ConditionalScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;

public class ForEachScenarioStep extends ConditionalScenarioStep {

    public ForEachScenarioStep(String description) {
        super(description);
    }
}
