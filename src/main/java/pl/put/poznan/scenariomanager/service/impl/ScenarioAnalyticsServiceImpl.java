package pl.put.poznan.scenariomanager.service.impl;

import org.springframework.stereotype.Service;
import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl.SampleScenarioVisitor;
import pl.put.poznan.scenariomanager.service.ScenarioAnalyticsService;

@Service
public class ScenarioAnalyticsServiceImpl implements ScenarioAnalyticsService {

    @Override
    public String getTextSteps(Scenario scenario) {

        SampleScenarioVisitor scenarioVisitor = new SampleScenarioVisitor();
        scenario.inspect(scenarioVisitor);

        return scenarioVisitor.getResult();
    }
}
