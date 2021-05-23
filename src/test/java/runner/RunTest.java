package runner;


import courgette.api.testng.TestNGCourgette;
import cucumber.api.CucumberOptions;

import courgette.api.CourgetteOptions;
import courgette.api.CourgetteRunLevel;
import org.testng.annotations.Test;
@Test
@CourgetteOptions(
        runLevel = CourgetteRunLevel.SCENARIO,
        rerunFailedScenarios = true,
        rerunAttempts = 1,
        showTestOutput = true,
        reportTargetDir = "target",
        cucumberOptions = @CucumberOptions(
                features = "src/test/resource/Features/Pledges.feature",
                glue = "stepdefs",
                plugin = {
                        "pretty",
                        "json:target/cucumber-report/cucumber.json",
                        "html:target/cucumber-report/cucumber.html"}
        ))

public class RunTest extends TestNGCourgette {
}
