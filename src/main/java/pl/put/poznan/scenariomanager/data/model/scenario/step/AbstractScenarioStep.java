package pl.put.poznan.scenariomanager.data.model.scenario.step;

import pl.put.poznan.scenariomanager.data.model.scenario.visitor.ScenarioVisitor;

public abstract class AbstractScenarioStep implements ScenarioStep {

    private String description;
    private ScenarioStep parent;

    public AbstractScenarioStep(String description) {

        this.description = description;
        this.parent = null;
    }

    @Override
    public void accept(ScenarioVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public ScenarioStep getParent() {
        return parent;
    }

    @Override
    public void setParent(ScenarioStep parentStep) {
        this.parent = parentStep;
    }

    @Override
    public int getNestingLevel() {

        if (getParent() == null)
            return 1;

        return getParent().getNestingLevel() + 1;
    }

    public String getDescription() {
        return description;
    }
}
