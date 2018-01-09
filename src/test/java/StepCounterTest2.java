import static org.junit.Assert.*;

import org.junit.Test;

import pl.put.poznan.stepCounter.StepCounter;

public class StepCounterTest2 {

	StepCounter dd = new StepCounter();
	@Test
	public void test1() {
		assertEquals(0, dd.getWynik());
	}

}
