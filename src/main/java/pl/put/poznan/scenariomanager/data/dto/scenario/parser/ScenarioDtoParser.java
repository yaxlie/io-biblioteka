package pl.put.poznan.scenariomanager.data.dto.scenario.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import pl.put.poznan.scenariomanager.data.dto.scenario.ScenarioDto;
import pl.put.poznan.scenariomanager.data.dto.scenario.ScenarioStepDto;
import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;
import pl.put.poznan.scenariomanager.data.model.scenario.builder.exception.InvalidScenarioStructureException;
import pl.put.poznan.scenariomanager.data.model.scenario.builder.ScenarioBuilder;

import java.util.ArrayList;
import java.util.List;

public class ScenarioDtoParser {

    private static final Logger log = LoggerFactory.getLogger(ScenarioDtoParser.class);

    public Scenario parse(ScenarioDto scenarioDto) throws ScenarioParseException {

        return parse(scenarioDto, new ScenarioBuilder());
    }

    public Scenario parse(ScenarioDto scenarioDto, ScenarioBuilder builder) throws ScenarioParseException {

        log.info("Parsing scenario data transfer object...");

        if (StringUtils.isEmpty(scenarioDto.getTitle()))
            throw new ScenarioParseException("No scenario title is given");

        List<String> actors = scenarioDto.getActors();

        if (actors == null) {

            log.warn("Received no scenario actors list. Assuming that actors list is empty.");
            actors = new ArrayList<>();
        }

        try {

            log.trace("Setting scenario title and actors...");
            log.debug(String.format("Title: %s\nActors:\n\t%s", scenarioDto.getTitle(),
                    String.join("\n\t", scenarioDto.getActors())));

            builder.setTitle(scenarioDto.getTitle());
            builder.setActors(actors);

            log.trace("Parsing scenario steps...");

            for (ScenarioStepDto step : scenarioDto.getSteps()) {

                log.debug(String.format("Processing step (%s): %s ", step.getType().toString(), step.getText()));
                handleStep(step, builder);
            }

            log.trace("Steps parsed. Building scenario...");

            Scenario scenario = builder.build();

            log.trace("Scenario built.");

            return scenario;

        } catch (InvalidScenarioStructureException e) {

            log.error("Invalid scenario structure.");
            e.printStackTrace();

            throw new ScenarioParseException("Invalid scenario structure", e);
        }
    }

    private void handleStep(ScenarioStepDto step, ScenarioBuilder builder) throws InvalidScenarioStructureException {

        String stepText = step.getText().trim();

        switch (step.getType()) {

            case SIMPLE:
                builder.simpleStep(stepText);
                break;
            case ACTOR:
                builder.actorStep(stepText);
                break;
            case FOR_EACH:
                builder.forEachStep(stepText);
                break;
            case IF:
                builder.ifStep(stepText);
                break;
            case ELSE:
                builder.elseStep(stepText);
                break;
            case END:
                builder.endTag();
                break;
            default:
                throw new UnsupportedOperationException(String.format("Encountered unexpected step type (%s)", step.getType().toString()));
        }
    }
}
