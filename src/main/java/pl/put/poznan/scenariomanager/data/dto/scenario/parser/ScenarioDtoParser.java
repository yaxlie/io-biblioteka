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

    private static final Logger logger = LoggerFactory.getLogger(ScenarioDtoParser.class);

    public Scenario parse(ScenarioDto scenarioDto) throws ScenarioParseException {

        if (StringUtils.isEmpty(scenarioDto.getTitle()))
            throw new ScenarioParseException("No scenario title is given");

        List<String> actors = scenarioDto.getActors();

        if (actors == null)
            actors = new ArrayList<>();

        try {

            ScenarioBuilder builder = new ScenarioBuilder();

            builder.setTitle(scenarioDto.getTitle());
            builder.setActors(actors);

            for (ScenarioStepDto step : scenarioDto.getSteps()) {

                handleStep(step, builder);
            }

            return builder.build();

        } catch (InvalidScenarioStructureException e) {

            logger.error("Invalid scenario structure");
            e.printStackTrace();

            throw new ScenarioParseException("Invalid scenario structure", e);
        }
    }

    private void handleStep(ScenarioStepDto step, ScenarioBuilder builder) throws InvalidScenarioStructureException {

        switch (step.getType()) {

            case SIMPLE:
                builder.simpleStep(step.getText());
                break;
            case ACTOR:
                builder.actorStep(step.getText());
                break;
            case FOR_EACH:
                builder.forEachStep(step.getText());
                break;
            case IF:
                builder.ifStep(step.getText());
                break;
            case ELSE:
                builder.elseStep(step.getText());
                break;
            case END:
                builder.endTag();
                break;
            default:
                throw new UnsupportedOperationException("Encountered unexpected step type");
        }
    }
}
