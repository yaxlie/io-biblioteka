package pl.put.poznan.transformer.data.model.scenario.step;

import pl.put.poznan.transformer.data.model.scenario.visitor.ScenarioVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class ConditionalScenarioStep extends AbstractScenarioStep {

    private List<ScenarioStep> childSteps;

    public ConditionalScenarioStep(String description) {
        super(description);

        this.childSteps = new ArrayList<>();
    }

    @Override
    public void accept(ScenarioVisitor visitor) {
        super.accept(visitor);

        for (ScenarioStep child : childSteps)
            child.accept(visitor);
    }

    public void addSubStep(ScenarioStep step) {

        childSteps.add(step);
        step.setParent(this);
    }
}
