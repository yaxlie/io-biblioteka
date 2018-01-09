package pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl;

import pl.put.poznan.scenariomanager.data.model.scenario.step.ScenarioStep;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.ScenarioVisitor;
public class StepCounter implements  ScenarioVisitor{
	private int wynik;
	public StepCounter() {
		wynik = 0;
	}
	@Override
	public void visit(ScenarioStep step) {
		this.Increment();
	}
	private void Increment() {
		wynik = wynik + 1;
	}
	public int getWynik(){
		return wynik;
	}
	
}
