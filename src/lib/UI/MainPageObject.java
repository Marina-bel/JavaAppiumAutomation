package lib.UI;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by, error_message, 5);
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }
    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize(); //определяем размер нашего девайса из драйвера
        int x = size.width / 2; //начальная переменная по оси х. она одна, т.к. по горизотали не двигаем
        int start_y = (int) (size.height * 0.8); //получаем начальную точку, которая находится в 80% экрана внизу
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform(); //команда perform() отсылает всю последовательность наших действий на выполнение.
        //нужно ввести координаты
    }
    public void swipeUpQuick() { //позволит быстро свайпать вверх
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        driver.findElements(by); //находит все элементы на стр, которые там есть
        driver.findElements(by).size(); //ищет количество элементов

        int already_swiped = 0; //в эту переменную записывается реальное количество свайпов

        while (driver.findElements(by).size() == 0) { //цикл будет работать пока эта функция не находит ни одного элемента

            if(already_swiped > max_swipes){
                waitForElementPresent(by, "Не найден элемент при свайпе. \n" + error_message, 0); //проверяем наличие элемента
                return;
            }
            swipeUpQuick(); //если находим элемент, цикл завершается. если не находим, постоянно свайпаем вверх
            ++already_swiped;
        }
    }
    public void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX(); //запишем самую левую точку нашего элемента по оси х
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

    public int getAmountOfElement(By by) {
        List elements = driver.findElements(by);
        return elements.size(); //возвращаем количество элементов, которые были найдены при помщи driver.findElements(by).
        // затем записываютса в список(List) elements и его размер возвращаем
    }
    public void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElement(by);
        if(amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public WebElement assertElementHasText(By by, String value, String error_message, long timeoutInSecond) {
        WebElement title_element = waitForElementPresent(by, error_message, timeoutInSecond);
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(error_message, value, article_title);
        return title_element;
    }
    public void assertElementPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElement(by);
        if(amount_of_elements == 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
}
