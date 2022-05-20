package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    private static final long DEFAULT_TIMEOUT = 300;
    private int countItemsOnPage = 0;

    @FindBy(xpath = "//button[@aria-label='Save for later']")
    List<WebElement> wishlistButtons;

    @FindBy(xpath = "//h2[contains(text(),'NOTHING MATCHES YOUR SEARCH')]")
    private WebElement nothingMatchesText;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void addAllItemsToWishlist() {
        for (WebElement webElement : wishlistButtons) {
            waitVisibilityOfElement(DEFAULT_TIMEOUT, webElement);
            webElement.click();
        }
    }

    public WebElement getNothingMatchesTextElement() {
        return nothingMatchesText;
    }

    public String getNothingMatchesText() {
        return nothingMatchesText.getText().strip();
    }

    public String getCountItemsOnPage() {
        for (WebElement webElement : wishlistButtons) {
            waitVisibilityOfElement(DEFAULT_TIMEOUT, webElement);
            countItemsOnPage++;
        }
        return String.valueOf(countItemsOnPage);
    }
}
