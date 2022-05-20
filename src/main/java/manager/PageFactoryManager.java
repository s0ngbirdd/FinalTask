package manager;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public SearchResultsPage getSearchResultsPage() {
        return new SearchResultsPage(driver);
    }

    public WishlistPage getWishlistPage() {
        return new WishlistPage(driver);
    }

    public SignInPage getSignInPage() {
        return new SignInPage(driver);
    }

    public WomenPage getWomenPage() {
        return new WomenPage(driver);
    }

    public CartPage getCartPage() {
        return new CartPage(driver);
    }

    public HelpPage getHelpPage() {
        return new HelpPage(driver);
    }

    public DeliveryQuestionsPage getDeliveryQuestionsPage() {
        return new DeliveryQuestionsPage(driver);
    }
}
