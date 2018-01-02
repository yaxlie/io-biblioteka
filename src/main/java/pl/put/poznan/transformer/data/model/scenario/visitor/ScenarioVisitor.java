package pl.put.poznan.transformer.data.model.scenario.visitor;

import pl.put.poznan.transformer.data.model.scenario.step.ScenarioStep;

/**
 * Represents a visitor which, when passed to a scenario, visits and processes the steps one by one
 */
public interface ScenarioVisitor {

    /**
     * This method is invoked on each step in the scenario step hierarchy
     *
     * @param step Scenario step which is currently being visited
     */
    void visit(ScenarioStep step);
}
