package pl.put.poznan.scenariomanager.data.model.scenario;

import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.ScenarioVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scenario {

    private String title;
    private List<String> actors;

    private List<ScenarioStep> steps;

    public Scenario() {

        this.title = "";
        this.actors = new ArrayList<>();
        this.steps = new ArrayList<>();
    }

    public Scenario(String title, List<String> actors, List<ScenarioStep> steps) {

        this.title = title;
        this.actors = new ArrayList<>(actors);
        this.steps = new ArrayList<>(steps);
    }

    public void inspect(ScenarioVisitor visitor) {

        for (ScenarioStep step : steps)
            step.accept(visitor);
    }

    public void addStep(ScenarioStep step) {
        steps.add(step);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getActors() {
        return Collections.unmodifiableList(actors);
    }

    public void setActors(List<String> actors) {
        this.actors = new ArrayList<>(actors);
    }

    public List<ScenarioStep> getSteps() {
        return Collections.unmodifiableList(steps);
    }
}
