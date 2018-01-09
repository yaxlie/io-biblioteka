package pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.ActorScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.step.impl.SimpleScenarioStep;

public class StepCounterTest {

	private StepCounter dd;
	
	@Before
	public  void setUp() {
		
		dd = new StepCounter();
	}

	@Test
	public void test1() {
		assertEquals(0, dd.getWynik());
	}

	@Test
	public void test2() {

		ActorScenarioStep step1 = new ActorScenarioStep("Bibliotekarz wybiera opcje dodania nowej pozycji książkowej.", "Bibliotekarz");
		SimpleScenarioStep step2 = new SimpleScenarioStep("Wyświetla się formularz.");
		dd.visit(step1);
		dd.visit(step2);

		assertEquals(2, dd.getWynik());
	}

}
