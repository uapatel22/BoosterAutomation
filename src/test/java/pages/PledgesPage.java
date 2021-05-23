package pages;


import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.cucumber.datatable.DataTable;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class PledgesPage implements En  {
    private static final Logger logger = LoggerFactory.getLogger(PledgesPage.class);
    public static WebDriver driver;

    @FindBy(xpath = "//span[@class='col-lg-3 mb-1 text-18 text-success fw-500']")
    List<WebElement> pledgeAmount;

    @FindBy(xpath = "//div[@class='business-entry']")
    List<WebElement> pledges;

    @FindBy(xpath = "//div[@id='more-businesses']//div[@class='business-entry']")
    List<WebElement> pledgesCollapsed;

    @FindBy(xpath = "//span[@class='collapsed']")
    WebElement showMoreBusinessPledges;


    public void navigateToWebsite(){
        String url = new ReadPropertyFile().getProperty(new File("testconfig.properties"), "url");
        driver.get(url);
    }

    /*
   Getting the number of pledges displayed below Show more button
   If the count is more than 0 then that amount is subtracted from the total pledges count
   Otherwise if the count is 0 then it verifies the number of pledges
*/
    public void validatePledgesDisplayed(String value) throws Exception {
        int totalPledgesCount = pledges.size();
        int totalPledgesCollapsedCount = pledgesCollapsed.size();
        int totalPledgeCountNotCollapsed;
        logger.info("Total number of pledges on the page is " + " " + String.valueOf(totalPledgesCount));
        logger.info("Total number of pledges that are collapsed is " + " " + String.valueOf(totalPledgesCollapsedCount));

        if (totalPledgesCollapsedCount > 0) {
            totalPledgeCountNotCollapsed = totalPledgesCount - totalPledgesCollapsedCount;
            Assert.assertEquals(String.valueOf(totalPledgeCountNotCollapsed), value, "The number of pledges does not match");
        } else {
            Assert.assertEquals(String.valueOf(totalPledgesCount), value, "The number of pledges does not match");
        }
    }

    /*
       Converting the pledge amount list to a sortable list then storing in a list where it's not sorted and sorting the other list
       As we are expecting the values to be from highest to lowest, the sorted list is reversed
       Then we are validating if the unsorted list equals the sorted list
     */
    public void validatePledgesDisplayedByAmount() throws Exception {
        List<String> formattedPledgeAmount = new ArrayList<>(formatPledgeValueAmount());
        List<Float> sortablePledgeAmount = new ArrayList<>();
        for (String element : formattedPledgeAmount) {
            sortablePledgeAmount.add(Float.valueOf(element));
        }
        List<Float> actualPledgeAmount = new ArrayList<>(sortablePledgeAmount);
        Collections.sort(sortablePledgeAmount, Collections.reverseOrder());
        logger.info("The value of the pledge amount list that is sorted " + " " + sortablePledgeAmount.toString());
        logger.info("The value of the pledge amount list that is unsorted " + " " + actualPledgeAmount.toString());
        Assert.assertEquals(true, sortablePledgeAmount.equals(actualPledgeAmount), "The pledge amount is not displayed from greatest to lowest amount");
    }

    /*
       Getting the pledge amount value then removing the dollar sign and if there is an value that contains "to" then getting the
       value to the right of the "to" as the assumption is the value will be higher on the right
    */
    public List formatPledgeValueAmount() throws Exception {
        ArrayList<String> formattedPledgeAmount = new ArrayList<>();
        for (WebElement ele : pledgeAmount) {
            formattedPledgeAmount.add(ele.getAttribute("innerText"));
        }
        if (formattedPledgeAmount.toString().contains("to")) {
            for (String element : formattedPledgeAmount) {
                if (element.contains("to")) {
                    String s2 = element;
                    String s1 = element.substring(element.indexOf("to") + 2);
                    s1.trim();
                    Collections.replaceAll(formattedPledgeAmount, s2, s1);
                }
            }
        }
        if (formattedPledgeAmount.toString().contains("$")) {
            for (String element : formattedPledgeAmount) {
                if (element.contains("$")) {
                    String s2 = element;
                    String s1 = element.replaceAll("\\$", "");
                    Collections.replaceAll(formattedPledgeAmount, s2, s1);
                }
            }
        }
        logger.info("Pledge amount after it is formatted " + " " + formattedPledgeAmount.toString());
        return formattedPledgeAmount;
    }

    public void validateNumOfPledgesDisplayedShowMore(String value) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", showMoreBusinessPledges);
        showMoreBusinessPledges.click();
        int totalPledgesCollapsedCount = pledgesCollapsed.size();
        Assert.assertEquals(String.valueOf(totalPledgesCollapsedCount), value, "The number of pledges displayed after Show more is not correct");
    }

    /*
        Getting the pledge amount value by the pledge name
        If the verification is for decimal places to be displayed then the value(s) before decimal is removed, we check if there are two numerical values
        If the verification is for decimals places not be displayed then we check the amount value does not contain a period
    */
    public void validateDecimalPlacesPledges(DataTable dataTable) {
        List<List<String>> tempDataTable = dataTable.asLists();
        int rowsCount = tempDataTable.size();
        String pledgeName, status, amountValue;

        for (int i = 0; i < rowsCount; i++) {
            pledgeName = tempDataTable.get(i).get(0);
            status = tempDataTable.get(i).get(1);
            WebElement elm = driver.findElement(By.xpath("//u[contains(text(), '" + pledgeName + "')]//following::span[@class='col-lg-3 mb-1 text-18 text-success fw-500'][1]"));
            amountValue = elm.getAttribute("innerText");
            if (status.equalsIgnoreCase("isDisplayed")) {
                String decimalValues = amountValue.substring(amountValue.indexOf(".") + 1);
                logger.info("The value of the decimal place is " +  " " + decimalValues);
                Assert.assertEquals(true, decimalValues.matches("([0-9]{2})"), "There is not 2 decimal values displayed for " + pledgeName);
            } else {
                logger.info("The value of the whole pledge amount is " + " " + amountValue);
                Assert.assertEquals(false, amountValue.contains("."), "There is a decimal value displayed for " + pledgeName);
            }
        }
    }


}
