package runner;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
		features={"src/test/resources/features/CheapestFlight.feature"},
		glue="steps",
		tags = {"@flightSearch"}
		
		)
public class CheapestFlightRunner
{

	
	@Test
	public void runCukes() {
		
		new TestNGCucumberRunner(getClass()).runCukes();
		
	}
}
