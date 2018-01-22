package pl.put.poznan.scenariomanager.scenario.builder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import pl.put.poznan.scenariomanager.data.dto.scenario.ScenarioDto;
import pl.put.poznan.scenariomanager.data.dto.scenario.ScenarioStepDto;
import pl.put.poznan.scenariomanager.data.dto.scenario.parser.ScenarioDtoParser;
import pl.put.poznan.scenariomanager.data.dto.scenario.parser.ScenarioParseException;
import pl.put.poznan.scenariomanager.data.model.scenario.builder.ScenarioBuilder;
import pl.put.poznan.scenariomanager.data.model.scenario.builder.exception.InvalidScenarioStructureException;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static pl.put.poznan.scenariomanager.data.dto.scenario.ScenarioStepTypeDto.*;

public class ScenarioDtoParserTest {

    private ScenarioDtoParser parser;
    private ScenarioBuilder builder;


    @Before
    public void setUp() {

        builder = mock(ScenarioBuilder.class);
        parser = new ScenarioDtoParser();
    }


    @Test
    public void testStepsParsingOrder() throws ScenarioParseException, InvalidScenarioStructureException {

        ScenarioDto scenario = getTestScenarioDto();
        InOrder order = inOrder(builder);

        parser.parse(scenario, builder);

        order.verify(builder).setActors(anyObject());

        List<ScenarioStepDto> steps = scenario.getSteps();

        order.verify(builder).actorStep(steps.get(0).getText());
        order.verify(builder).simpleStep(steps.get(1).getText());
        order.verify(builder).actorStep(steps.get(2).getText());
        order.verify(builder).ifStep(steps.get(3).getText());
        order.verify(builder).actorStep(steps.get(4).getText());
        order.verify(builder).actorStep(steps.get(5).getText());
        order.verify(builder).forEachStep(steps.get(6).getText());
        order.verify(builder).actorStep(steps.get(7).getText());
        order.verify(builder).actorStep(steps.get(8).getText());
        order.verify(builder).actorStep(steps.get(9).getText());
        order.verify(builder).actorStep(steps.get(10).getText());
        order.verify(builder).elseStep(steps.get(12).getText());
        order.verify(builder).actorStep(steps.get(13).getText());
        order.verify(builder).forEachStep(steps.get(14).getText());
        order.verify(builder).ifStep(steps.get(15).getText());
        order.verify(builder).actorStep(steps.get(16).getText());
        order.verify(builder).elseStep(steps.get(17).getText());
        order.verify(builder).actorStep(steps.get(18).getText());
        order.verify(builder).simpleStep(steps.get(21).getText());
        order.verify(builder).actorStep(steps.get(22).getText());
        order.verify(builder).actorStep(steps.get(24).getText());
        order.verify(builder).actorStep(steps.get(25).getText());
        order.verify(builder).build();
    }


    @Test
    public void testBuiltStepsCount() throws ScenarioParseException, InvalidScenarioStructureException {

        ScenarioDto scenario = getTestScenarioDto();
        parser.parse(scenario, builder);

        verify(builder, atLeastOnce()).setTitle(anyString());
        verify(builder, atLeastOnce()).setActors(any());

        verify(builder, times(14)).actorStep(anyString());
        verify(builder, times(2)).simpleStep(anyString());
        verify(builder, times(2)).forEachStep(anyString());
        verify(builder, times(2)).ifStep(anyString());
        verify(builder, times(2)).elseStep(anyString());
        verify(builder, times(4)).endTag();
    }


    private ScenarioDto getTestScenarioDto() {

        String title = "Procedura dodawania nowej książki";
        List<String> actors = Arrays.asList("system", "bibliotekarz");

        List<ScenarioStepDto> steps = Arrays.asList(
                new ScenarioStepDto("Bibliotekarz wybiera opcje dodania nowej pozycji książkowej", ACTOR),
                new ScenarioStepDto("Wyświetla się formularz.", SIMPLE),
                new ScenarioStepDto("Bibliotekarz podaje dane książki.", ACTOR),
                new ScenarioStepDto("IF: Bibliotekarz pragnie dodać egzemplarze książki", IF),
                new ScenarioStepDto("Bibliotekarz wybiera opcję definiowania egzemplarzy", ACTOR),
                new ScenarioStepDto("System prezentuje zdefiniowane egzemplarze", ACTOR),
                new ScenarioStepDto("FOR EACH egzemplarz:", FOR_EACH),
                new ScenarioStepDto("Bibliotekarz wybiera opcję dodania egzemplarza", ACTOR),
                new ScenarioStepDto("System prosi o podanie danych egzemplarza", ACTOR),
                new ScenarioStepDto("Bibliotekarz podaje dane egzemplarza i zatwierdza.", ACTOR),
                new ScenarioStepDto("System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.", ACTOR),
                new ScenarioStepDto("END", END),
                new ScenarioStepDto("ELSE", ELSE),
                new ScenarioStepDto("Bibliotekarz dostaje szału i demoluje całą bibliotekę.", ACTOR),
                new ScenarioStepDto("FOR EACH: książka w bibliotece", FOR_EACH),
                new ScenarioStepDto("IF: Książka ma beznadziejną okładkę", IF),
                new ScenarioStepDto("Bibliotekarz wywala tę książkę przez okno", ACTOR),
                new ScenarioStepDto("ELSE", ELSE),
                new ScenarioStepDto("Bibliotekarz zabiera książkę, by zdemolować nią resztę biblioteki.", ACTOR),
                new ScenarioStepDto("END", END),
                new ScenarioStepDto("END", END),
                new ScenarioStepDto("Nie zostało już nic do zdemolowania.", SIMPLE),
                new ScenarioStepDto("Bibliotekarz uspokaja się.", ACTOR),
                new ScenarioStepDto("END", END),
                new ScenarioStepDto("Bibliotekarz zatwierdza dodanie książki.", ACTOR),
                new ScenarioStepDto("System informuje o poprawnym dodaniu książki.", ACTOR)
        );

        return new ScenarioDto(title, actors, steps);
    }
}
