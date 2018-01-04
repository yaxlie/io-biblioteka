package pl.put.poznan.scenariomanager.data.model.scenario.builder;

import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;
import pl.put.poznan.scenariomanager.data.model.scenario.builder.exception.InvalidScenarioStructureException;
import pl.put.poznan.scenariomanager.data.model.scenario.step.CompositeScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.ConditionalScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.*;

import java.util.List;

public class ScenarioBuilder {

    private Scenario scenario;
    private CompositeScenarioStep lastStep;
    private StepActorMatcher actorMatcher;

    public ScenarioBuilder() {

        scenario = new Scenario();
        lastStep = new ScenarioStepsWrapper();
        actorMatcher = new StepActorMatcher();
    }

    public void setTitle(String title) {
        scenario.setTitle(title);
    }

    public void setActors(List<String> actors) {

        scenario.setActors(actors);
        actorMatcher = new StepActorMatcher(actors);
    }

    public void simpleStep(String simpleStep) {

        lastStep.addStep(new SimpleScenarioStep(simpleStep));
    }

    public void actorStep(String step) {

        String actor = actorMatcher.matchActor(step);
        lastStep.addStep(new ActorScenarioStep(step, actor));
    }

    public void forEachStep(String step) {

        conditionalStep(new ForEachScenarioStep(step));
    }

    public void ifStep(String step) {

        conditionalStep(new IfScenarioStep(step));
    }

    public void elseStep(String step) throws InvalidScenarioStructureException {

        if (!step.toLowerCase().equals("else"))
            throw new InvalidScenarioStructureException("Else step should not contain any additional characters");

        if (!(lastStep instanceof IfScenarioStep))
            throw new InvalidScenarioStructureException("Before an else step there should be an if step");

        IfScenarioStep ifStep = (IfScenarioStep) lastStep;
        lastStep = (CompositeScenarioStep) lastStep.getParent();

        conditionalStep(new ElseScenarioStep(ifStep));
    }

    private void conditionalStep(ConditionalScenarioStep conditionalStep) {

        lastStep.addStep(conditionalStep);
        lastStep = conditionalStep;
    }

    public void endTag() throws InvalidScenarioStructureException {

        if (lastStep.getParent() == null)
            throw new InvalidScenarioStructureException("Too many END tags");

        lastStep = (CompositeScenarioStep) lastStep.getParent();
    }

    public Scenario build() throws InvalidScenarioStructureException {

        if (lastStep.getParent() != null || !(lastStep instanceof ScenarioStepsWrapper))
            throw new InvalidScenarioStructureException("Not enough END tags");

        ScenarioStepsWrapper stepsWrapper = (ScenarioStepsWrapper) lastStep;

        for (ScenarioStep step : stepsWrapper.getSteps())
            scenario.addStep(step);

        return scenario;
    }
}
