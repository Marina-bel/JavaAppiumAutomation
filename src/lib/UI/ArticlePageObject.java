package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            ARTICLE = "//*[contains(@text,'A deadly fire in Ürümqi')]",
            MORE_OPTIONS = "(//android.widget.ImageView[@content-desc='More options'])[1]",
            MORE_OPTIONS_2 = "(//android.widget.ImageView[@content-desc='More options'])[2]",
            ARTICLE_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']",
            ENTRY_FIELD = "org.wikipedia:id/text_input",
            ITEM_IMAGE = "org.wikipedia:id/item_image_container",
            IMAGE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            SAVE = "//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView",
            EMPTY_RESULT_ELEMENT = "org.wikipedia:id/item_container",
            FOOTER_ELEMENT = "//*[@text = 'View page in browser']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getResultArticleElement(String substring) {
        return ARTICLE_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE), "Не найден заголовок статьи", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Не могу найти конец статьи",
                20
        );
    }

    public void initArticleInput() {
        this.waitForElementPresent(By.xpath(ARTICLE), "Статья не найдена");
        this.waitForElementAndClick(By.xpath(ARTICLE), "Не удалось кликнуть по статье", 5);
    }

    public void clickCancelArticleMore1() {
        this.waitForElementAndClick(By.xpath(MORE_OPTIONS), "Элемент 'More options [1]' не найден", 5);
    }

    public void waitForArticleResult(String substring) {
        String search_result_xpath = getResultArticleElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Результат не найден " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultArticleElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Не кликаетс и Результат не найден " + substring, 10);
    }
    public void elementAndClear() {
        this.waitForElementAndClear(By.id(ENTRY_FIELD), "Не удалось очистить поле",5);
    }
    public void typeArticleLine(String search_line) {
        this.waitForElementAndSendKeys(By.id(ENTRY_FIELD), search_line, "Элемент не найден", 5);
    }
    public void clickCancelArticleMore2() {
        this.waitForElementAndClick(By.xpath(MORE_OPTIONS_2), "Элемент 'More options [2]' не найден", 5);
    }
    public void clickCancelArticleItemImage() {
        this.waitForElementAndClick(By.id(ITEM_IMAGE), "Элемент с сохраненными статьями не найден", 5);
    }
    public void clickCancelArticleImageButton() {
        this.waitForElementAndClick(By.xpath(IMAGE_BUTTON), "мент 'Стрелка' не найден", 5);
    }
    public void clickCancelArticleSave() {
        this.waitForElementAndClick(By.xpath(SAVE), "Элемент save не найден", 5);
    }

    public void swipeElementToLeftArticle(String substring) {
        String element_to_left_xpath = getResultArticleElement(substring);
        this.swipeElementToLeft(By.xpath(element_to_left_xpath),"Сохраненный элемент не найден");
    }
    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.id(EMPTY_RESULT_ELEMENT), "Не могу найти результат элемента", 15);
    }
}
