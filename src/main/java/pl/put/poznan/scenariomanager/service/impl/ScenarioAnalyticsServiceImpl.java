package pl.put.poznan.scenariomanager.service.impl;

import org.springframework.stereotype.Service;
import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;
import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl.*;
import pl.put.poznan.scenariomanager.service.ScenarioAnalyticsService;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<String> getNonActorSteps(Scenario scenario) {

        NotActorVisitor visitor = new NotActorVisitor();
        scenario.inspect(visitor);

        List<ScenarioStep> steps = visitor.getNoActorSteps();

        return steps.stream().map(ScenarioStep::getDescription).collect(Collectors.toList());
    }
}
