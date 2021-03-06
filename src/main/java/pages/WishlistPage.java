package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class WishlistPage extends BasePage {

    private static final long DEFAULT_TIMEOUT = 200;
    private int cartItems = 0;
    private float totalAmount = 0;

    @FindBy(xpath = "//div[@class='itemCount_ftSVY']")
    private WebElement wishlistAmount;

    @FindBy(xpath = "//button[@aria-label='Delete']")
    private List<WebElement> deleteFromWishlistButtons;

    @FindBy(xpath = "//h2[text()='You have no Saved Items']")
    private WebElement noSavedItemsText;

    @FindBy(xpath = "//button[text()='Move to bag']")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//button[@data-testid='minibag-close-btn']")
    private WebElement closeCartPopup;

    @FindBy(xpath = "//span[@class='price_kdre2 discounted_XPEtP']//span[@class='noWrap_FJw5Z']")
    private List<WebElement> prices;

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
        waitVisibilityOfElement(DEFAULT_TIMEOUT, wishlistAmount);
        for (int i = 0; i < Integer.valueOf(getWishlistAmount()); i++) {
            waitVisibilityOfElement(DEFAULT_TIMEOUT, deleteFromWishlistButtons.get(i));
            deleteFromWishlistButtons.get(i).click();
        }
    }

    public WebElement getNoSavedItemsElement() {
        return noSavedItemsText;
    }

    public String getNoSavedItemsText() {
        return noSavedItemsText.getText();
    }

    public void addAllItemsToCart() throws InterruptedException {
        waitVisibilityOfElement(DEFAULT_TIMEOUT, wishlistAmount);
        for (int i = 0; i < Integer.valueOf(getWishlistAmount()); i++) {
            waitVisibilityOfElement(DEFAULT_TIMEOUT, addToCartButtons.get(i));
            if (addToCartButtons.get(i).isEnabled()) {
                addToCartButtons.get(i).click();
                cartItems++;
                waitVisibilityOfElement(DEFAULT_TIMEOUT, closeCartPopup);
                closeCartPopup.click();
                addToTotalAmount(prices.get(i).getText());
                //implicitWait(300);
                Thread.sleep(300);
            }
        }
    }

    public String getCartItemsNumber() {
        return String.valueOf(cartItems);
    }

    public void addToTotalAmount(String add) {
        char[] chars = add.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isDigit(chars[i]) && chars[i] != '.') {
                chars[i] = ' ';
            }
        }
        totalAmount += Float.parseFloat(String.valueOf(chars).strip());
    }

    public String getTotalAmount() {
        return String.valueOf(totalAmount);
    }
}
