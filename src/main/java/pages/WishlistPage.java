package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class WishlistPage extends BasePage {

    private static final long DEFAULT_TIMEOUT = 300;
    public boolean readyCheck = false;

    @FindBy(xpath = "//div[@class='itemCount_ftSVY']")
    private WebElement wishlistAmount;

    @FindBy(xpath = "//button[@aria-label='Delete']")
    List<WebElement> deleteFromWishlistButtons;

    @FindBy(xpath = "//h2[text()='You have no Saved Items']")
    private WebElement noSavedItemsText;

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getWishlistAmountElement() {
        return wishlistAmount;
    }

    public String getWishlistAmount() {
        char[] chars = wishlistAmount.getText().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isDigit(chars[i])) {
                chars[i] = ' ';
            }
        }
        return String.valueOf(chars).strip();
    }

    public void deleteAllProductsFromWishlist() {
        /*waitForPageLoadComplete(DEFAULT_TIMEOUT);
        waitForAjaxToComplete(DEFAULT_TIMEOUT);
        for (WebElement webElement : deleteFromWishlistButtons) {
            waitVisibilityOfElement(DEFAULT_TIMEOUT, webElement);
            //waitForAjaxToComplete(DEFAULT_TIMEOUT);
            webElement.click();
            waitForAjaxToComplete(DEFAULT_TIMEOUT);
        }
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        waitForAjaxToComplete(DEFAULT_TIMEOUT);*/

        waitVisibilityOfElement(DEFAULT_TIMEOUT, wishlistAmount);
        for (int i = 0; i < Integer.valueOf(getWishlistAmount()); i++) {
            waitVisibilityOfElement(DEFAULT_TIMEOUT, deleteFromWishlistButtons.get(i));
            deleteFromWishlistButtons.get(i).click();
        }
    }

    public WebElement getNoSavedItemsElement() {
        //((JavascriptExecutor)driver).executeScript("scroll(0,400)");
        return noSavedItemsText;
    }

    public String getNoSavedItemsText() {
        //((JavascriptExecutor)driver).executeScript("scroll(0,400)");
        return noSavedItemsText.getText();
    }
}
