package pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;
import pl.put.poznan.scenariomanager.data.model.scenario.builder.ScenarioBuilder;
import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.*;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl.KeyWordsVisitor;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marcin on 09.01.2018.
 */
public class KeyWordsCounterTest {

    private KeyWordsVisitor keyWordsVisitor;

    @Before
    public void setUp() {
        keyWordsVisitor = new KeyWordsVisitor();
    }

    @Test
    public void testCounter1(){
        Scenario scenario = scenario1(1);
        scenario.inspect(keyWordsVisitor);
        assertEquals(keyWordsVisitor.getIntResult(), 2);
    }

    @Test
    public void testCounter2(){
        Scenario scenario = scenario1(2);
        scenario.inspect(keyWordsVisitor);
        assertEquals(keyWordsVisitor.getIntResult(), 4);
    }

    @Test
    public void testCounter3(){
        Scenario scenario = scenario1(3);
        scenario.inspect(keyWordsVisitor);
        assertEquals(keyWordsVisitor.getIntResult(), 0);
    }

    private Scenario scenario1(int i) {

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

        ElseScenarioStep step7 = new ElseScenarioStep(step4);

        List<ScenarioStep> steps;

        step4.addStep(step41);
        step4.addStep(step42);
        step4.addStep(step43);
        step43.addStep(step431);
        step43.addStep(step432);
        step43.addStep(step433);
        step43.addStep(step434);

        switch (i){
            case 2:
                steps = Arrays.asList(step1, step2, step3, step4, step5, step6, step7, step4);
                break;
            case 3:
                steps = Arrays.asList(step1);
                break;
            default:
                steps = Arrays.asList(step1, step2, step3, step4, step5, step6);
                break;
        }
        return new Scenario(title, actors, steps);
    }

}
