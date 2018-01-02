package pl.put.poznan.transformer.data.model.scenario.visitor.impl;

import pl.put.poznan.transformer.data.model.scenario.Scenario;
import pl.put.poznan.transformer.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.transformer.data.model.scenario.step.impl.ActorScenarioStep;
import pl.put.poznan.transformer.data.model.scenario.step.impl.ForEachScenarioStep;
import pl.put.poznan.transformer.data.model.scenario.step.impl.IfScenarioStep;
import pl.put.poznan.transformer.data.model.scenario.step.impl.SimpleScenarioStep;
import pl.put.poznan.transformer.data.model.scenario.visitor.ScenarioVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleScenarioVisitor implements ScenarioVisitor {

    @Override
    public void visit(ScenarioStep step) {

        System.out.println("Visiting step with description: \"" + step.getDescription() + "\"");
        System.out.println("\tNesting level is equal to: " + Integer.toString(step.getNestingLevel()));
    }

    public static void exampleUsage() {

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

        step4.addSubStep(step41);
        step4.addSubStep(step42);
        step4.addSubStep(step43);

        step43.addSubStep(step431);
        step43.addSubStep(step432);
        step43.addSubStep(step433);
        step43.addSubStep(step434);

        List<ScenarioStep> steps = Arrays.asList(step1, step2, step3, step4, step5, step6);

        Scenario scenario = new Scenario(title, actors, steps);

        SampleScenarioVisitor sampleVisitor = new SampleScenarioVisitor();
        scenario.inspect(sampleVisitor);
    }
}
