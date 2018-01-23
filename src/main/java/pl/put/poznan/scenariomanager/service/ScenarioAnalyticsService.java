package pl.put.poznan.scenariomanager.service;

import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;

import java.util.List;

/**
 * Contains scenario analysis operations
 */
public interface ScenarioAnalyticsService {

    /**
     * Counts the number of all steps in the given scenario
     *
     * @param scenario Analysed scenario
     * @return Number of all scenario steps
     */
    int countAllSteps(Scenario scenario);

    /**
     * Counts the number of conditional steps only in the given scenario
     *
     * @param scenario Analysed scenario
     * @return Number of conditional steps
     */
    int countConditionalSteps(Scenario scenario);

    /**
     * Formats scenario steps - adds indexing to the steps e.g. 1.2.3., 1.2.4., 1.3.
     *
     * @param scenario
     * @return Formatted scenario
     */
    String getNumberedSteps(Scenario scenario);

    /**
     * Find and gets all non actor steps in the scenario
     *
     * @param scenario Analysed scenario
     * @return List of scenario steps in text format
     */
    List<String> getNonActorSteps(Scenario scenario);

    /**
     * Gets numbered scenario steps, which nesting level is not greater that the specified nesting level
     * In case of level equal to 1, only root steps are fetched
     * If level is 2, also children steps of root steps are also collected, and so on...
     *
     * @param scenario Analysed scenario
     * @param level Specified scenario nesting level
     * @return Numbered scenario steps,
     */
    String getStepsWithLevelLimit(Scenario scenario, int level);
}
