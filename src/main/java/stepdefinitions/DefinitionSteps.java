package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.SignInPage;
import pages.WishlistPage;

import java.util.NoSuchElementException;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class DefinitionSteps {

    private static final long DEFAULT_TIMEOUT = 300;

    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    WishlistPage wishlistPage;
    SignInPage signInPage;

    PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @And("User opens {string} page")
    public void userOpensHomepagePage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @And("User makes search by keyword {string}")
    public void userMakesSearchByKeywordKeyword(final String keyword) {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.enterTextToSearchField(keyword);
    }

    @And("User clicks search button")
    public void userClicksSearchButton() {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getSearchButton());
        homePage.clickSearchButton();
    }

    @And("User adds all products on page to wishlist")
    public void userAddsAllProductsOnPageToWishlist() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        searchResultsPage.addAllItemsToWishlist();
    }

    @And("User clicks on wishlist page")
    public void userClicksOnWishlistPage() {
        homePage.clickWishlistButton();
    }

    @And("User checks that amount of products in wishlist are {string}")
    public void userChecksThatAmountOfProductsInWishlistAreAmountOfProducts(final String amount) {
        wishlistPage = pageFactoryManager.getWishlistPage();
        wishlistPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        wishlistPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        wishlistPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, wishlistPage.getWishlistAmountElement());
        Assert.assertEquals(wishlistPage.getWishlistAmount(), amount);
    }

    @And("User deletes all products from wishlist")
    public void userDeletesAllProductsFromWishlist() {
        wishlistPage = pageFactoryManager.getWishlistPage();
        wishlistPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        wishlistPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        wishlistPage.deleteAllProductsFromWishlist();
    }

    @And("User checks that {string} message is displayed")
    public void userChecksThatTextMessageMessageIsDisplayed(final String message) {
        wishlistPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, wishlistPage.getNoSavedItemsElement());
        Assert.assertEquals(wishlistPage.getNoSavedItemsText(), message);
    }

    @And("User clicks on the sign in button")
    public void userClicksOnTheSignInButton() {
        homePage.clikSignInButton();
    }

    @And("User enter {string} into email field")
    public void userEnterEmailIntoEmailField(final String email) {
        signInPage = pageFactoryManager.getSignInPage();
        signInPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        signInPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        signInPage.enterEmail(email);
    }

    @And("User can see the {string} message")
    public void userCanSeeTheErrorMessageMessage(final String message) {
        signInPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, signInPage.getEmailErrorMessageElement());
        Assert.assertEquals(signInPage.getEmailErrorMessageText(), message);
    }

    @And("User can not see the {string} message")
    public void userCanNotSeeTheErrorMessageMessage() {
        Assert.assertFalse(signInPage.existsEmailErrorMessageElement());
    }
}
