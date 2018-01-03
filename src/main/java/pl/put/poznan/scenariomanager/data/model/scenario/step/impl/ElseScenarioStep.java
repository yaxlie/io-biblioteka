package pl.put.poznan.scenariomanager.data.model.scenario.step.impl;

import pl.put.poznan.scenariomanager.data.model.scenario.step.ConditionalScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;

public class ElseScenarioStep extends ConditionalScenarioStep {

    private IfScenarioStep ifStep;

    public ElseScenarioStep(IfScenarioStep ifStep) {
        super("ELSE");

        this.ifStep = ifStep;
    }
}
