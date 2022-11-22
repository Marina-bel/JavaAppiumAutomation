import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class MyTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:/Users/Stas/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void assertText(){
        elementClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Элемент 'Search Wikipedia' не найден",
                5
        );

        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search…",
                "Элемент не найден",
                15
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSecond) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement elementClick(By by, String error_message, long timeoutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }
    private WebElement assertElementHasText(By by, String value, String error_message, long timeoutInSecond) {
        WebElement title_element = waitForElementPresent(by, error_message, timeoutInSecond);
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(error_message, value, article_title);
        return title_element;
    }
}

