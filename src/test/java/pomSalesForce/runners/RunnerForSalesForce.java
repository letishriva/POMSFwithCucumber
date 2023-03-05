package pomSalesForce.runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(
		features= {"src/test/resources/salesforcefeature.feature"},
		glue= {"pomSalesForce.tests"},
		monochrome=true,
		plugin= {"pretty","html:target/cucumber.html","json:target/cucumber.json"}
		)
		
public class RunnerForSalesForce extends AbstractTestNGCucumberTests {
	
}


