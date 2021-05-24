package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.support.PageFactory;
import pages.Common;
import cucumber.api.Scenario;


public class CommonStepDefs extends Common implements En {
    public CommonStepDefs() {

        Given("^I open the browser then navigate to the website$", () -> {
            Common common = PageFactory.initElements(driver, Common.class);
            common.navigateToWebsite();
        });
    }

    @cucumber.api.java.Before
    public void openBrowser() {
        setWebDriver();
    }

    @cucumber.api.java.After
    public void closeBrowser(Scenario scenario) {
        end(scenario);
    }
}
