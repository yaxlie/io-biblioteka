package pl.put.poznan.scenariomanager.data.model.scenario.step;

import pl.put.poznan.scenariomanager.data.model.scenario.visitor.ScenarioVisitor;

import java.util.ArrayList;
import java.util.List;

public class CompositeScenarioStep extends AbstractScenarioStep {

    private List<ScenarioStep> childSteps;

    public CompositeScenarioStep(String description) {
        super(description);

        this.childSteps = new ArrayList<>();
    }

    @Override
    public void accept(ScenarioVisitor visitor) {
        super.accept(visitor);

        for (ScenarioStep child : childSteps)
            child.accept(visitor);
    }

    public void addStep(ScenarioStep step) {

        childSteps.add(step);
        step.setParent(this);
    }

    @Override
    public List<ScenarioStep> getSteps() {
        return childSteps;
    }
}
