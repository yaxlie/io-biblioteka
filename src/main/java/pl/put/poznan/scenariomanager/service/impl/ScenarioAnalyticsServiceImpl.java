package pl.put.poznan.scenariomanager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl.IndexScenarioVisitor;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl.KeyWordsVisitor;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl.StepCounter;
import pl.put.poznan.scenariomanager.service.ScenarioAnalyticsService;

@Service
public class ScenarioAnalyticsServiceImpl implements ScenarioAnalyticsService {

    private static final Logger log = LoggerFactory.getLogger(ScenarioAnalyticsServiceImpl.class);

    @Override
    public int countAllSteps(Scenario scenario) {

        StepCounter visitor = new StepCounter();

        log.debug("Created step counter visitor.");

        scenario.inspect(visitor);

        return visitor.getWynik();
    }

    @Override
    public int countConditionalSteps(Scenario scenario) {

        KeyWordsVisitor visitor = new KeyWordsVisitor();

        log.debug("Created scenario conditional steps visitor.");

        scenario.inspect(visitor);

        return visitor.getIntResult();
    }

    @Override
    public String getNumberedSteps(Scenario scenario) {

        IndexScenarioVisitor visitor = new IndexScenarioVisitor();

        log.debug("Created index scenario visitor.");

        scenario.inspect(visitor);

        return visitor.getResult();
    }
}
