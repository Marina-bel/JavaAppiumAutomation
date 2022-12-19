import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SaveArticles extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSaveFirstArticleToMyList() {

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.initArticleInput();
        ArticlePageObject.clickCancelArticleMore1();
        ArticlePageObject.clickByArticleWithSubstring("Add to reading list");
        ArticlePageObject.clickByArticleWithSubstring("GOT IT");
        ArticlePageObject.elementAndClear();
        ArticlePageObject.typeArticleLine("Test");
        ArticlePageObject.clickByArticleWithSubstring("OK");
        ArticlePageObject.clickCancelArticleMore2();
        ArticlePageObject.clickByArticleWithSubstring("Add to reading list");
        ArticlePageObject.clickCancelArticleItemImage();
        ArticlePageObject.clickCancelArticleImageButton();
        ArticlePageObject.clickCancelArticleSave();
        ArticlePageObject.clickCancelArticleItemImage();
        ArticlePageObject.swipeElementToLeftArticle("2022 Ürümqi fire");
        ArticlePageObject.waitForEmptyResultsLabel();
        ArticlePageObject.clickByArticleWithSubstring("2022 COVID-19 protests in China");
        String article_title = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Мы видим неожиданное название",
                "2022 COVID-19 protests in China",
                article_title
        );

    }
}
