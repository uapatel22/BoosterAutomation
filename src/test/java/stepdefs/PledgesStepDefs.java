package stepdefs;

import pages.PledgesPage;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.support.PageFactory;


public class PledgesStepDefs extends PledgesPage implements En {
    public PledgesStepDefs() {

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
}
