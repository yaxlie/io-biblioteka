package pl.put.poznan.transformer.data.model.scenario.visitor;

import pl.put.poznan.transformer.data.model.scenario.step.ScenarioStep;

public interface ScenarioVisitor {

    void visit(ScenarioStep step);
}
