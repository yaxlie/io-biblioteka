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


@RestController
@RequestMapping("/scenario")
public class ScenarioAnalysisController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioAnalysisController.class);

    private ScenarioAnalyticsService scenarioAnalyticsService;

    public ScenarioAnalysisController(ScenarioAnalyticsService scenarioAnalyticsService) {
        this.scenarioAnalyticsService = scenarioAnalyticsService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> exampleScenarioPost(@RequestBody ScenarioDto scenario) {

        logger.debug("\n" + scenario.toString());

        ScenarioDtoParser scenarioParser = new ScenarioDtoParser();
        Scenario parsedScenario;

        try
        {
            parsedScenario = scenarioParser.parse(scenario);

        } catch (ScenarioParseException e) {

            logger.error("Failed to parse scenario. " + e.getMessage());
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            logger.error("Unexpected error occurred during scenario parse operation");
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(scenarioAnalyticsService.getTextSteps(parsedScenario), HttpStatus.OK);
    }
}


