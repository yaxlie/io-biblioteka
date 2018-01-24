package pl.put.poznan.scenariomanager.data.model.scenario.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;
import pl.put.poznan.scenariomanager.data.model.scenario.builder.exception.InvalidScenarioStructureException;
import pl.put.poznan.scenariomanager.data.model.scenario.step.CompositeScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.ConditionalScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.*;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl.DebugScenarioVisitor;

import java.util.List;

public class ScenarioBuilder {

    private static final Logger log = LoggerFactory.getLogger(ScenarioBuilder.class);

    private Scenario scenario;
    private CompositeScenarioStep lastStep;
    private StepActorMatcher actorMatcher;


    public ScenarioBuilder() {

        scenario = new Scenario();
        lastStep = new ScenarioStepsWrapper();
        actorMatcher = new StepActorMatcher();

        log.trace(String.format("Created scenario builder with empty scenario.\nInitial nesting level is: %d",
                lastStep.getNestingLevel()));
    }


    public void setTitle(String title) {
        scenario.setTitle(title);
    }


    public void setActors(List<String> actors) {

        scenario.setActors(actors);
        actorMatcher = new StepActorMatcher(actors);
    }


    public void simpleStep(String simpleStep) {

        log.debug(String.format("Adding simple step (%s)", simpleStep));
        lastStep.addStep(new SimpleScenarioStep(simpleStep));
    }


    public void actorStep(String step) {

        log.debug(String.format("Adding actor step (%s)", step));

        String actor = actorMatcher.matchActor(step);
        log.debug(String.format("Actor matched: %s", actor));

        lastStep.addStep(new ActorScenarioStep(step, actor));
    }


    public void forEachStep(String step) {

        log.debug(String.format("Adding for each loop step (%s)", step));
        conditionalStep(new ForEachScenarioStep(step));
    }


    public void ifStep(String step) {

        log.debug(String.format("Adding if step (%s)", step));
        conditionalStep(new IfScenarioStep(step));
    }


    public void elseStep(String step) throws InvalidScenarioStructureException {

        log.debug(String.format("Adding else step (%s)", step));

        if (!step.toLowerCase().equals("else"))
            throw new InvalidScenarioStructureException("Else step should not contain any additional characters");

        if (!(lastStep instanceof IfScenarioStep))
            throw new InvalidScenarioStructureException("Before an else step there should be an if step");

        IfScenarioStep ifStep = (IfScenarioStep) lastStep;
        lastStep = (CompositeScenarioStep) lastStep.getParent();

        conditionalStep(new ElseScenarioStep(ifStep));
    }


    private void conditionalStep(ConditionalScenarioStep conditionalStep) {

        log.debug(String.format("Adding conditional step - creating new nesting level.\nNew nesting level is: %d",
                lastStep.getNestingLevel()));

        lastStep.addStep(conditionalStep);
        lastStep = conditionalStep;
    }


    public void endTag() throws InvalidScenarioStructureException {

        if (lastStep.getParent() == null) {
            throw new InvalidScenarioStructureException("Too many END tags");
        }

        log.debug(String.format("Adding end tag - decreasing nesting level.\nNew nesting level is: %d",
                lastStep.getParent().getNestingLevel()));

        lastStep = (CompositeScenarioStep) lastStep.getParent();
    }


    public Scenario build() throws InvalidScenarioStructureException {

        if (lastStep.getParent() != null || !(lastStep instanceof ScenarioStepsWrapper))
            throw new InvalidScenarioStructureException("Not enough END tags");

        ScenarioStepsWrapper stepsWrapper = (ScenarioStepsWrapper) lastStep;

        log.trace("Adding root steps to the scenario...");

        for (ScenarioStep step : stepsWrapper.getSteps())
            scenario.addStep(step);

        log.trace("Steps added root steps.");

        logBuiltScenario(scenario);

        return scenario;
    }


    private void logBuiltScenario(Scenario scenario) {

        DebugScenarioVisitor debugVisitor = new DebugScenarioVisitor();

        try {
            scenario.inspect(debugVisitor);

            log.debug(String.format("Built scenario:\n%s", debugVisitor.getResult()));

        } catch (Exception e) {
            log.warn("Failed to log built scenario.");
            e.printStackTrace();
        }
    }
}
