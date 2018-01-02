package pl.put.poznan.transformer.data.model.scenario.step;

import pl.put.poznan.transformer.data.model.scenario.visitor.ScenarioVisitor;

public interface ScenarioStep {

    String getDescription();

    void setParent(ScenarioStep parentStep);
    ScenarioStep getParent();

    int getNestingLevel();

    void accept(ScenarioVisitor visitor);
}
