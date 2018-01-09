package pl.put.poznan.scenariomanager.service.impl;

import org.springframework.stereotype.Service;
import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl.IndexScenarioVisitor;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl.KeyWordsVisitor;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl.SampleScenarioVisitor;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl.StepCounter;
import pl.put.poznan.scenariomanager.service.ScenarioAnalyticsService;

@Service
public class ScenarioAnalyticsServiceImpl implements ScenarioAnalyticsService {

    @Override
    public int countAllSteps(Scenario scenario) {

        StepCounter visitor = new StepCounter();

        scenario.inspect(visitor);

        return visitor.getWynik();
    }

    @Override
    public int countConditionalSteps(Scenario scenario) {

        KeyWordsVisitor visitor = new KeyWordsVisitor();

        scenario.inspect(visitor);

        return visitor.getIntResult();
    }

    @Override
    public String getNumberedSteps(Scenario scenario) {

        IndexScenarioVisitor visitor = new IndexScenarioVisitor();

        scenario.inspect(visitor);

        return visitor.getResult();
    }
}
