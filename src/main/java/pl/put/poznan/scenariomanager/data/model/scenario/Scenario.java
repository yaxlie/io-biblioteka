package pl.put.poznan.scenariomanager.data.model.scenario;

import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.ScenarioVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a use case scenario
 */
public class Scenario {

    /**
     * Holds title of a scenario
     */
    private String title;

    /**
     * Holds actor names that can occur in the scenario
     */
    private List<String> actors;

    /**
     * Holds scenario root steps
     * Nested steps are children (direct or indirect) of these steps
     */
    private List<ScenarioStep> steps;

    /**
     * Constructs an empty scenario
     */
    public Scenario() {

        this.title = "";
        this.actors = new ArrayList<>();
        this.steps = new ArrayList<>();
    }

    /**
     * Constructs an empty scenario and initializes it with a title, actors list and root steps
     *
     * @param title Title of the scenario
     * @param actors Actors that can occur in the scenario
     * @param steps Root steps of the scenario
     */
    public Scenario(String title, List<String> actors, List<ScenarioStep> steps) {

        this.title = title;
        this.actors = new ArrayList<>(actors);
        this.steps = new ArrayList<>(steps);
    }

    /**
     * Accepts a scenario visitor, which traverses through the whole steps tree
     *
     * @param visitor Scenario visitor
     */
    public void inspect(ScenarioVisitor visitor) {

        for (ScenarioStep step : steps)
            step.accept(visitor);
    }

    /**
     * Adds next root step to the scenario
     * The new step is appended to the end of the scenario
     *
     * @param step Root step
     */
    public void addStep(ScenarioStep step) {
        steps.add(step);
        step.setParent(null);
    }

    /**
     * Gets scenario title
     *
     * @return Title string
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets scenario's title
     *
     * @param title Scenarios new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets scenario's actors list
     *
     * @return Actors' names list
     */
    public List<String> getActors() {
        return Collections.unmodifiableList(actors);
    }

    /**
     * Sets scenario's actors
     *
     * @param actors Actors' names list
     */
    public void setActors(List<String> actors) {
        this.actors = new ArrayList<>(actors);
    }

    /**
     * Gets scenario's root steps
     *
     * @return List of scenario steps
     */
    public List<ScenarioStep> getSteps() {
        return Collections.unmodifiableList(steps);
    }
}
