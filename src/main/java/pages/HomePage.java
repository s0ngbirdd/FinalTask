package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    private static final long DEFAULT_TIMEOUT = 300;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//a[@aria-label='Saved Items']")
    private WebElement wishlistButton;

    @FindBy(xpath = "//button[@aria-label='My Account']")
    private WebElement accountButton;

    @FindBy(xpath = "//a[text()='Sign In']")
    private WebElement signInButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void enterTextToSearchField(final String searchText) {
        searchField.clear();
        searchField.sendKeys(searchText);
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickWishlistButton() {
        ((JavascriptExecutor)driver).executeScript("scroll(0,400)");
        wishlistButton.click();
    }

    public void clikSignInButton() {
        accountButton.click();
        waitVisibilityOfElement(DEFAULT_TIMEOUT, signInButton);
        signInButton.click();
    }
}
