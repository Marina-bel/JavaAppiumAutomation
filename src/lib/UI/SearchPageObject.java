package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text,'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']",
            SEARCH_RESULT_ELEMENT_DZ = "//*[@resource-id = 'org.wikipedia:id/search_results_list']",
            TITLE = "org.wikipedia:id/view_page_title_text",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text = 'No results found']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }
    //TEMPLATES METHODS
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    //TEMPLATES METHODS

    public void initSearchInput() {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Элемент 'Search Wikipedia' не найден", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Кнопка не найдена", 5);
    }
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Кнопка присутствует", 5);
    }
    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Элемент не найден", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Элемент не найден", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Результат не найден " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Не кликаетс и Результат не найден " + substring, 10);
    }

//    public int getAmountOfFoundArticles() {
//
//        this.waitForElementPresent(
//                By.xpath(SEARCH_RESULT_ELEMENT),
//                "Не найден результат поиска",
//                15
//        );
//        return this.getAmountOfElement(By.xpath(SEARCH_RESULT_ELEMENT));
//    }

    public int getAmountOfFoundArticles() {

        this.waitForElementPresent(
                By.id(SEARCH_RESULT_ELEMENT_DZ),
                "Не найден результат поиска",
                15
        );
        return this.getAmountOfElement(By.id(SEARCH_RESULT_ELEMENT_DZ));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Не могу найти результат элемента", 15);
    }

//    public void assertThereIsNoResultOfSearch() {
//        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Мы не должны были найти результат");
//    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.id(SEARCH_RESULT_ELEMENT_DZ), "Мы не должны были найти результат");
    }
    public void assertThereIsResultOfSearch() {
        this.assertElementPresent(By.id(TITLE), "Заголовок статьи не найден");
    }
}
