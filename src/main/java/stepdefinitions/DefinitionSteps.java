package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.util.NoSuchElementException;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class DefinitionSteps {

    private static final long DEFAULT_TIMEOUT = 300;

    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    WishlistPage wishlistPage;
    SignInPage signInPage;
    WomenPage womenPage;
    CartPage cartPage;
    HelpPage helpPage;
    DeliveryQuestionsPage deliveryQuestionsPage;

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
        homePage.cliсkSignInButton();
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

    @And("User click on Women page")
    public void userClickOnWomenPage() {
        homePage.clickWomenPage();
    }

    @And("User clicks on biggest deals button")
    public void userClicksOnBiggestDealsButton() {
        womenPage = pageFactoryManager.getWomenPage();
        womenPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        womenPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        womenPage.clickBiggestDealsButton();
    }

    @And("User adds all products to cart")
    public void userAddsAllProductsToCart() {
        try {
            wishlistPage = pageFactoryManager.getWishlistPage();
            wishlistPage.addAllItemsToCart();
        } catch (InterruptedException e) {
        }
    }

    @And("Clicks on cart button")
    public void clicksOnCartButton() {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getCartButton());
        homePage.clickCartButton();
    }

    @And("User can see total products number")
    public void userCanSeeTotalProductsNumberOnTheCartIcon() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getCartItemsNumberElement());
        Assert.assertEquals(homePage.getCartItemsNumberText(), wishlistPage.getCartItemsNumber());
    }




    @And("Clicks on view bag button")
    public void clicksOnViewBagButton() {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getCartButton());
        homePage.clickViewBagButton();
    }

    @And("User can see total products price")
    public void userCanSeeTotalProductsPrice() {
        cartPage = pageFactoryManager.getCartPage();
        cartPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        cartPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        cartPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, cartPage.getSubTotalElement());
        Assert.assertEquals(cartPage.getSubTotalText(), wishlistPage.getTotalAmount());
    }




    @And("User can see {string} message")
    public void userCanSeeTextMessageMessage(final String message) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getNothingMatchesTextElement());
        Assert.assertEquals(searchResultsPage.getNothingMatchesText(), message);
    }

    @And("User change country to France")
    public void userChangeCountryToFrance() {
        homePage.changeCountry();
    }

    /*@And("User can see placeholder {string} text on the search field")
    public void userCanSeePlaceholderTextMessageTextOnTheSearchField(final String message) {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        Assert.assertEquals(homePage.getSearchPlaceholderText(), message);
    }*/

    /*@And("User can see France placeholder text on the search field")
    public void userCanSeeFrancePlaceholderTextOnTheSearchField() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        //Assert.assertEquals(homePage.getSearchPlaceholderText(), homePage.getPlaceholderFrance());
        //Assert.assertEquals(homePage.getSearchPlaceholderText(), homePage.getPlaceholderFrance());
        Assert.assertEquals(homePage.getHelpButtonText(), "Aide et FAQ");
    }*/

    @And("User can see the {string} text on the Help & FAQs button")
    public void userCanSeeTheTextTextOnTheHelpFAQsButton(final String text) {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        Assert.assertEquals(homePage.getHelpButtonText(), text);
    }

    @And("User can see {string} number of products on the page")
    public void userCanSeeNumberNumberOfProductsOnThePage(final String number) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        Assert.assertEquals(searchResultsPage.getCountItemsOnPage(), number);
    }

    @And("User clicks Help & FAQs button")
    public void userClicksHelpFAQsButton() {
        homePage.clickHelpButton();
    }

    @And("User can see {string} topics")
    public void userCanSeeTopicNumberTopics(final String number) {
        helpPage = pageFactoryManager.getHelpPage();
        helpPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        helpPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        Assert.assertEquals(helpPage.getTopicCount(), number);
    }

    @And("user clicks on Delivery topic page")
    public void userClicksOnDeliveryTopicPage() {
        helpPage = pageFactoryManager.getHelpPage();
        helpPage.clickDeliveryButton();
    }

    @And("User can see {string} on page")
    public void userCanSeeQuestionNumberOnPage(final String number) {
        deliveryQuestionsPage = pageFactoryManager.getDeliveryQuestionsPage();
        deliveryQuestionsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        deliveryQuestionsPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        Assert.assertEquals(deliveryQuestionsPage.getQuestionsCount(), number);
    }
}
