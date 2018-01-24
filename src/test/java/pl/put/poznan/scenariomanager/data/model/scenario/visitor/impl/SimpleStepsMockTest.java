package pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl;
import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;
import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.*;

import java.util.ArrayList;
import static org.mockito.Mockito.*;


/**
 * Created by Marcin on 21.01.2018.
 */
public class SimpleStepsMockTest {

    private NotActorVisitor mockVisitor;
    private Scenario mockScenario;
    private ScenarioStep mockStep;

    @Before
    public void setUp() {
        mockVisitor = mock(NotActorVisitor.class);
        mockScenario = mock(Scenario.class);
        mockStep = mock(ScenarioStep.class);
    }

    @Test
    public void testVisitorInit(){
        mockVisitor.getNoActorSteps();
        when(mockVisitor.getNoActorSteps()).thenReturn(null);
        mockVisitor.getNoActorSteps();
        when(mockVisitor.getNoActorSteps()).thenReturn(new ArrayList<ScenarioStep>());
        verify(mockVisitor, times(2)).getNoActorSteps();
    }

    @Test
    public void testVisitorProcess(){

        ActorScenarioStep a =  new ActorScenarioStep("blablabla", "Hugh Jackman");
        mockVisitor.visit(a);
        when(mockVisitor.getNoActorSteps()).thenReturn(new ArrayList<ScenarioStep>());
        verify(mockVisitor, times(1)).visit(a);
    }


}
