package pages;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;
import cucumber.api.Scenario;

public class Common  {

    public static WebDriver driver;

    public void setWebDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resource/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public void end(Scenario scenario) {
        byte[] screenshot = (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
        driver.quit();
    }

    public void navigateToWebsite() {
        String url = new ReadPropertyFile().getProperty(new File("testconfig.properties"), "url");
        driver.get(url);

    }
}
