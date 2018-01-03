package pl.put.poznan.scenariomanager.data.model.scenario.step;

import pl.put.poznan.scenariomanager.data.model.scenario.visitor.ScenarioVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class ConditionalScenarioStep extends CompositeScenarioStep {

    public ConditionalScenarioStep(String description) {
        super(description);
    }
}
