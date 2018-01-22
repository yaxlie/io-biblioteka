package pl.put.poznan.scenariomanager.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.scenariomanager.data.dto.scenario.ScenarioDto;
import pl.put.poznan.scenariomanager.data.dto.scenario.parser.ScenarioDtoParser;
import pl.put.poznan.scenariomanager.data.dto.scenario.parser.ScenarioParseException;
import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;
import pl.put.poznan.scenariomanager.service.ScenarioAnalyticsService;

import java.util.List;


@RestController
@RequestMapping("/scenario")
public class ScenarioAnalysisController {

    private static final Logger log = LoggerFactory.getLogger(ScenarioAnalysisController.class);

    private ScenarioAnalyticsService scenarioAnalyticsService;

    public ScenarioAnalysisController(ScenarioAnalyticsService scenarioAnalyticsService) {
        this.scenarioAnalyticsService = scenarioAnalyticsService;
    }

    @RequestMapping(value = "/steps", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Integer> countStepsRequest(@RequestBody ScenarioDto scenario) {

        log.info("Received 'count all steps' request...");

        try
        {
            Scenario scenarioEntity = parseScenario(scenario);
            int stepsCount = scenarioAnalyticsService.countAllSteps(scenarioEntity);

            log.info("Successfully count scenario steps.");
            log.debug(String.format("Number of count steps: %d", stepsCount));

            return new ResponseEntity<>(stepsCount, HttpStatus.OK);

        } catch (ScenarioParseException e) {

            log.error("Failed to parse scenario.\n\t" + e.getMessage());
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            log.error("Unexpected error occurred during requested operation");
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/conditions", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Integer> countConditionsRequest(@RequestBody ScenarioDto scenario) {

        log.info("Received 'count conditional steps' request...");

        try
        {
            Scenario scenarioEntity = parseScenario(scenario);
            int conditionsCount = scenarioAnalyticsService.countConditionalSteps(scenarioEntity);

            log.info("Successfully count conditional steps.");
            log.debug(String.format("Number of conditional steps is: %d", conditionsCount));

            return new ResponseEntity<>(conditionsCount, HttpStatus.OK);

        } catch (ScenarioParseException e) {

            log.error("Failed to parse scenario. " + e.getMessage());
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            log.error("Unexpected error occurred during requested operation.");
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/numbered", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> numberedStepsRequest(@RequestBody ScenarioDto scenario) {

        log.info("Received 'number steps' request...");

        try {

            Scenario scenarioEntity = parseScenario(scenario);
            String numberedSteps = scenarioAnalyticsService.getNumberedSteps(scenarioEntity);

            log.info("Successfully numbered steps.");
            log.info(String.format("Numbered scenario:\n%s", numberedSteps));

            return new ResponseEntity<>(numberedSteps, HttpStatus.OK);

        } catch (ScenarioParseException e) {

            log.error("Failed to parse scenario during 'number steps' request.\n\t" + e.getMessage());
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            log.error("Unexpected error occurred during requested operation");
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/nonactor", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<String>> nonActorStepsRequest(@RequestBody ScenarioDto scenario) {

        log.info("Received collect non actor steps request...");

        try
        {
            Scenario parsedScenario = parseScenario(scenario);
            List<String> nonActorSteps = scenarioAnalyticsService.getNonActorSteps(parsedScenario);

            log.info("Successfully collected non actor steps.");

            return new ResponseEntity<>(nonActorSteps, HttpStatus.OK);

        } catch (ScenarioParseException e) {

            log.error("Failed to parse scenario. " + e.getMessage());
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            log.error("Unexpected error occurred during scenario parse operation");
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private Scenario parseScenario(ScenarioDto scenarioDto) throws Exception {

        log.debug("Received given scenario in the request:\n" + scenarioDto.toString());

        ScenarioDtoParser scenarioParser = new ScenarioDtoParser();
        return scenarioParser.parse(scenarioDto);
    }
}


