package pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;
import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.ActorScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.ForEachScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.IfScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.SimpleScenarioStep;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
public class IndexScenarioVisitorTest {

    private IndexScenarioVisitor indexScenarioVisitor;
    private Scenario scenario;

    @Before
    public void setUp() {
        indexScenarioVisitor=new IndexScenarioVisitor();
        String title = "Dodanie nowej książki";
        List<String> actors = Arrays.asList("System", "Bibliotekarz");

        ActorScenarioStep step1 = new ActorScenarioStep("Bibliotekarz wybiera opcje dodania nowej pozycji książkowej.", "Bibliotekarz");
        SimpleScenarioStep step2 = new SimpleScenarioStep("Wyświetla się formularz.");
        ActorScenarioStep step3 = new ActorScenarioStep("Bibliotekarz podaje dane książki.", "Bibliotekarz");
        IfScenarioStep step4 = new IfScenarioStep("IF: Bibliotekarz pragnie dodać egzemplarze książki");
        ActorScenarioStep step41 = new ActorScenarioStep("Bibliotekarz wybiera opcję definiowania egzemplarzy", "Bibliotekarz");
        ActorScenarioStep step42 = new ActorScenarioStep("System prosi o podanie danych egzemplarza", "System");
        ForEachScenarioStep step43 = new ForEachScenarioStep("FOR EACH egzemplarz:");
        ActorScenarioStep step431 = new ActorScenarioStep("Bibliotekarz wybiera opcję dodania egzemplarza", "Bibliotekarz");
        ActorScenarioStep step432 = new ActorScenarioStep("System prosi o podanie danych egzemplarza", "System");
        ActorScenarioStep step433 = new ActorScenarioStep("Bibliotekarz podaje dane egzemplarza i zatwierdza.","Bibliotekarz");
        ActorScenarioStep step434 = new ActorScenarioStep("System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.", "System");
        ActorScenarioStep step5 = new ActorScenarioStep("Bibliotekarz zatwierdza dodanie książki.", "Bibliotekarz");
        ActorScenarioStep step6 = new ActorScenarioStep("System informuje o poprawnym dodaniu książki.", "System");

        step4.addStep(step41);
        step4.addStep(step42);
        step4.addStep(step43);

        step43.addStep(step431);
        step43.addStep(step432);
        step43.addStep(step433);
        step43.addStep(step434);

        List<ScenarioStep> steps = Arrays.asList(step1, step2, step3, step4, step5, step6);

        scenario = new Scenario(title, actors, steps);
        indexScenarioVisitor.IndexScenario(scenario);

    }



    @Test
    public void testIndexScenario() throws Exception {


        List<ScenarioStep> stepList =scenario.getSteps();
        assertEquals(stepList.get(3).getSteps().get(2).getSteps().get(2).getIndex(),"4.3.3");

    }

    @Test
    public void testChildIndexScenario() throws Exception {

        List<ScenarioStep> stepList =scenario.getSteps();
        assertEquals(stepList.get(3).getSteps().get(2).getNextChildIndex(),5);

    }

}