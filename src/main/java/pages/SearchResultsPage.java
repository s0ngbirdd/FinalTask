package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    private static final long DEFAULT_TIMEOUT = 300;

    @FindBy(xpath = "//button[@aria-label='Save for later']")
    List<WebElement> wishlistButtons;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void addAllItemsToWishlist() {
        for (WebElement webElement : wishlistButtons) {
            waitVisibilityOfElement(DEFAULT_TIMEOUT, webElement);
            webElement.click();
        }
    }
}
