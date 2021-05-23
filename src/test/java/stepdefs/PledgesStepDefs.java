package stepdefs;

import pages.PledgesPage;
import cucumber.api.Scenario;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class PledgesStepDefs extends PledgesPage implements En {
    public PledgesStepDefs() {

        Given("^I open the browser then navigate to the website$", () -> {
            PledgesPage pledgespages = PageFactory.initElements(driver, PledgesPage.class);
            pledgespages.navigateToWebsite();
        });

        When("I validate there are '(.*?)' pledges displayed under Our Business Leaderboard", (String value) -> {
            PledgesPage pledgespages = PageFactory.initElements(driver, PledgesPage.class);
            pledgespages.validatePledgesDisplayed(value);
        });

        And("I validate the pledges are displayed in highest to lowest dollar amount", () -> {
            PledgesPage pledgespages = PageFactory.initElements(driver, PledgesPage.class);
            pledgespages.validatePledgesDisplayedByAmount();
        });

        And("I validate if decimals are displayed or not for the pledges below:", (DataTable dataTable) -> {
            PledgesPage pledgespages = PageFactory.initElements(driver, PledgesPage.class);
            pledgespages.validateDecimalPlacesPledges(dataTable);
        });

        Then("I validate '(.*?)' pledge is displayed after clicking on Show more", (String value) -> {
            PledgesPage pledgespages = PageFactory.initElements(driver, PledgesPage.class);
            pledgespages.validateNumOfPledgesDisplayedShowMore(value);
        });

    }

    @cucumber.api.java.Before
    public void openBrowser () {
        System.setProperty("webdriver.chrome.driver", "src/test/resource/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @cucumber.api.java.After
    public void closeBrowser (Scenario scenario){
        byte[] screenshot = (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
        driver.quit();
    }
}
