package pl.put.poznan.scenariomanager.scenario.builder;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;
import pl.put.poznan.scenariomanager.data.model.scenario.builder.ScenarioBuilder;
import pl.put.poznan.scenariomanager.data.model.scenario.builder.exception.InvalidScenarioStructureException;
import pl.put.poznan.scenariomanager.data.model.scenario.step.CompositeScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ScenarioBuilderTest {

    private ScenarioBuilder scenarioBuilder;

    @Before
    public void setUp() {

        scenarioBuilder = new ScenarioBuilder();
    }

    @Test
    public void testValidScenarioCreation() throws InvalidScenarioStructureException {

        scenarioBuilder.setTitle("Procedura dodawania nowej książki");

        List<String> actors = Arrays.asList("bibliotekarz", "system");

        scenarioBuilder.setActors(actors);

        buildValidSteps();

        Scenario scenario = scenarioBuilder.build();

        assertBuiltValidScenario(scenario);
    }

    private void buildValidSteps() throws InvalidScenarioStructureException {

        scenarioBuilder.actorStep("Bibliotekarz wybiera opcje dodania nowej pozycji książkowej");
        scenarioBuilder.simpleStep("Wyświetla się formularz.");
        scenarioBuilder.actorStep("Bibliotekarz podaje dane książki.");
        scenarioBuilder.ifStep("IF: Bibliotekarz pragnie dodać egzemplarze książki");
            scenarioBuilder.actorStep("Bibliotekarz wybiera opcję definiowania egzemplarzy");
            scenarioBuilder.actorStep("System prezentuje zdefiniowane egzemplarze");
            scenarioBuilder.forEachStep("FOR EACH egzemplarz:");
                scenarioBuilder.actorStep("Bibliotekarz wybiera opcję dodania egzemplarza");
                scenarioBuilder.actorStep("System prosi o podanie danych egzemplarza");
                scenarioBuilder.actorStep("Bibliotekarz podaje dane egzemplarza i zatwierdza.");
                scenarioBuilder.actorStep("System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.");
            scenarioBuilder.endTag();
        scenarioBuilder.elseStep("ELSE");
            scenarioBuilder.actorStep("Bibliotekarz dostaje szału i demoluje całą bibliotekę.");
            scenarioBuilder.forEachStep("FOR EACH: książka w bibliotece");
                scenarioBuilder.ifStep("IF: Książka ma beznadziejną okładkę");
                    scenarioBuilder.actorStep("Bibliotekarz wywala tę książkę przez okno");
                scenarioBuilder.elseStep("ELSE");
                    scenarioBuilder.actorStep("Bibliotekarz zabiera książkę, by zdemolować nią resztę biblioteki.");
                scenarioBuilder.endTag();
            scenarioBuilder.endTag();
            scenarioBuilder.simpleStep("Nie zostało już nic do zdemolowania.");
            scenarioBuilder.actorStep("Bibliotekarz uspokaja się.");
        scenarioBuilder.endTag();
        scenarioBuilder.actorStep("Bibliotekarz zatwierdza dodanie książki.");
        scenarioBuilder.actorStep("System informuje o poprawnym dodaniu książki.");
    }

    private void assertBuiltValidScenario(Scenario scenario) {

        List<ScenarioStep> steps = scenario.getSteps();

        assertEquals(7, steps.size());

        CompositeScenarioStep step3 = ((CompositeScenarioStep) steps.get(3));
        assertEquals(3, step3.getSteps().size());

        CompositeScenarioStep step32 = ((CompositeScenarioStep) step3.getSteps().get(2));
        assertEquals(4, step32.getSteps().size());

        CompositeScenarioStep step4 = ((CompositeScenarioStep) steps.get(4));
        assertEquals(4, step4.getSteps().size());

        CompositeScenarioStep step41 = ((CompositeScenarioStep) step4.getSteps().get(1));
        assertEquals(2, step41.getSteps().size());

        CompositeScenarioStep step410 = ((CompositeScenarioStep) step41.getSteps().get(0));
        assertEquals(1, step410.getSteps().size());

        CompositeScenarioStep step411 = ((CompositeScenarioStep) step41.getSteps().get(1));
        assertEquals(1, step411.getSteps().size());
    }

    @Test(expected = InvalidScenarioStructureException.class)
    public void testTooManyEndTagsScenario() throws InvalidScenarioStructureException {

        scenarioBuilder.setTitle("Test title");
        scenarioBuilder.setActors(Arrays.asList("system"));

        scenarioBuilder.ifStep("IF: test ma się powieść");
            scenarioBuilder.simpleStep("To musi mieć nie za dużą liczbę tagów END");
            scenarioBuilder.endTag();
        scenarioBuilder.endTag();

        scenarioBuilder.build();
    }

    @Test(expected = InvalidScenarioStructureException.class)
    public void testTooLessEndTagsScenario() throws InvalidScenarioStructureException {

        scenarioBuilder.setTitle("Test title");
        scenarioBuilder.setActors(Arrays.asList("system"));

        scenarioBuilder.ifStep("IF: test ma się powieść");
            scenarioBuilder.simpleStep("To musi mieć nie za małą liczbę tagów END");

        scenarioBuilder.build();
    }
}
