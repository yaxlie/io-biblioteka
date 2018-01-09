package pl.put.poznan.scenariomanager.data.model.scenario.step;

import pl.put.poznan.scenariomanager.data.model.scenario.visitor.ScenarioVisitor;

import java.util.List;

/**
 * Represents a step in a scenario step hierarchy
 * It can be a single step or a step composed of a condition step and multiple substeps
 */
public interface ScenarioStep {

    /**
     * Gets text form of the step in the scenario
     *
     * @return Scenario step text
     */
    String getDescription();

    /**
     * Sets parent step (e.g. 'if' or 'for each' or another composite step) in the scenario hierarchy
     * WARNING! This method should not be used during scenario step hierarchy creation, it should be invoked by a parent step object
     *
     * @param parentStep Parent ScenarioStep object
     */
    void setParent(ScenarioStep parentStep);

    /**
     * Gets parent step (e.g. 'if' or 'for each' or another composite step) in the scenario hierarchy
     *
     * @return Parent ScenarioStep object
     */
    ScenarioStep getParent();

    /**
     * Gets level of nesting of a step in scenario step hierarchy (tree)
     * In case a step is one of the 'root' steps in scenario then its level is 1
     * Else it is equal to the number of parents + 1
     *
     * @return Int specifying nesting level
     */
    int getNestingLevel();

    /**
     * Accepts a visitor object and passes itself to the visitor's visit method
     * In case the step has sub-steps, it lets the visitor to visit them either
     *
     * @param visitor A visitor object that inspects a scenario step hierarchy
     */
    void accept(ScenarioVisitor visitor);

    /**
     * Sets index of a step (e.g 4.2.2)
     * @param index String representing index of a step
     */
    void setIndex(String index);

    /** Gets index of a step
     * @return String representing index of a step
     */
    String getIndex();

    /**
     * Gets next index of a child step of a specific step
     * @return Int specifying index
     */
    int getNextChildIndex();


    /**
     * Gets list of child steps of specific step
     * @return List of Child steps
     */
    List<ScenarioStep> getSteps();

}
