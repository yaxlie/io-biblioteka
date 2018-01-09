import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.ActorScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.SimpleScenarioStep;
import pl.put.poznan.stepCounter.StepCounter;

public class StepCounterTest1 {
	StepCounter dd = new StepCounter();
	
	@Before
	public  void beforeTest() {
		
		ActorScenarioStep step1 = new ActorScenarioStep("Bibliotekarz wybiera opcje dodania nowej pozycji książkowej.", "Bibliotekarz");
        SimpleScenarioStep step2 = new SimpleScenarioStep("Wyświetla się formularz.");
		dd.visit(step1);
		dd.visit(step2);
	}

	@Test
	public void test2() {

		assertEquals(2, dd.getWynik());
	}

}
