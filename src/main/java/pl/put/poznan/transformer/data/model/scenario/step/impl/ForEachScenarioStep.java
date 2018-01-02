package pl.put.poznan.transformer.data.model.scenario.step.impl;

import pl.put.poznan.transformer.data.model.scenario.step.ConditionalScenarioStep;
import pl.put.poznan.transformer.data.model.scenario.step.ScenarioStep;

public class ForEachScenarioStep extends ConditionalScenarioStep {

    public ForEachScenarioStep(String description) {
        super(description);
    }
}
