import lib.CoreTestCase;
import lib.UI.MainPageObject;
import lib.UI.SearchPageObject;
import org.junit.Test;

public class MyTestCancelSearch extends CoreTestCase {

    private lib.UI.MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Android");
        SearchPageObject.getAmountOfFoundArticles();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForEmptyResultsLabel();

    }

}
