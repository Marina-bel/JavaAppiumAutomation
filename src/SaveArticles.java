import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
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

public class SaveArticles {

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
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void saveFirstArticleToMyList() {

        String article = "//*[contains(@text,'A deadly fire in Ürümqi')]";
        waitForElementAndClick(
                By.xpath(article),
                "Статья не найдена",
                5
        );
        waitForElementAndClick(
                By.xpath("(//android.widget.ImageView[@content-desc='More options'])[1]"),
                "Элемент 'More options [1]' не найден",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "Элемент 'Add to reading list' не найден",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'GOT IT']"),
                "Элемент 'GOT IT' не найден",
                5
        );
        String entry_field = "org.wikipedia:id/text_input";
        waitForElementAndClear(
                By.id(entry_field),
                "Не удалось очистить поле",
                5
        );
        String test = "Test";
        waitForElementAndSendKeys(
                By.id(entry_field),
                test,
                "Не удалось ввести текст",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Элемент 'OK' не найден",
                5
        );
        waitForElementAndClick(
                By.xpath("(//android.widget.ImageView[@content-desc='More options'])[2]"),
                "Элемент 'More options [2]' не найден",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "Элемент 'Add to reading list' не найден",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_image_container"),
                "Элемент с сохраненными статьями не найден",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Элемент 'Стрелка' не найден",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView"),
                "Элемент save не найден",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_image_container"),
                "Элемент с сохраненными статьями не найден",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='2022 Ürümqi fire']"),
                "Сохраненный элемент не найден");

        waitForElementPresent(
                By.id("org.wikipedia:id/item_container"),
                "Статья не найдена",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='2022 COVID-19 protests in China']"),
                "Не удалось перейти на статью",
                5
        );
        assertElementHasText(
                By.id("org.wikipedia:id/view_page_title_text"),
                "2022 COVID-19 protests in China",
                "Элемент не найден",
                15
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 20);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getWidth();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }

    private WebElement assertElementHasText(By by, String value, String error_message, long timeoutInSecond) {
        WebElement title_element = waitForElementPresent(by, error_message, timeoutInSecond);
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(error_message, value, article_title);
        return title_element;
    }
}
