package pl.put.poznan.scenariomanager.data.model.scenario.step;

import pl.put.poznan.scenariomanager.data.model.scenario.visitor.ScenarioVisitor;

import java.util.List;

public abstract class AbstractScenarioStep implements ScenarioStep {

    private String description;
    private ScenarioStep parent;
    private String index;
    private int nextChildIndex;

    @Override
    public int getNextChildIndex() {
        nextChildIndex=nextChildIndex+1;
        return nextChildIndex;
    }

    @Override
    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String getIndex() {

        return index;
    }

    public AbstractScenarioStep(String description) {

        this.description = description;
        this.parent = null;
        this.index=null;
        this.nextChildIndex=0;
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

    public  List<ScenarioStep> getSteps(){
        return null;
    }
}
