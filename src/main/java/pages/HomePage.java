package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    private static final long DEFAULT_TIMEOUT = 300;
    private String placeholderFrance = "Rechercher des articles et des marques";

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

    //@FindBy(xpath = "//button[@class='_2syfS2P _1njuHQk otVu6ZN' and @aria-controls='57242f2c-d207-471c-95b1-31d6839df360']")
    //private WebElement saleButton;

    //@FindBy(xpath = "//ul[@aria-labelledby='93621d77-e6d0-4f3c-be35-0e06a981de22']//a[text()='Biggest deals']")
    //private WebElement biggestDealsButton;

    @FindBy(xpath = "//span[@class='_1z5n7CN']")
    private WebElement cartButton;

    @FindBy(xpath = "//a[@class='_1TlOB9h _2tvh469 _12h15d-']")
    private WebElement viewBagButton;

    @FindBy(xpath = "//a[@id='women-floor']")
    private WebElement womenPage;

    @FindBy(xpath = "//span[@data-test-id='miniBagItemCount']")
    private WebElement cartItemsNumber;

    @FindBy(xpath = "//div[@class='_25L--Pi']//button[@data-testid='country-selector-btn']")
    private WebElement countrySelectorButton;

    @FindBy(xpath = "//select[@id='country']")
    private WebElement countrySelectorDropdown;

    @FindBy(xpath = "//option[@value='FR']")
    private WebElement optionFrance;

    @FindBy(xpath = "//button[@class='_3jBV0Hh _2h9sodS']")
    private WebElement submitCountryButton;

    @FindBy(xpath = "//a[@data-testid='help']")
    private WebElement helpButton;

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

    public void cliсkSignInButton() {
        accountButton.click();
        waitVisibilityOfElement(DEFAULT_TIMEOUT, signInButton);
        signInButton.click();
    }

    /*public void clickBiggestDealsButton() {
        //a[@id='women-floor']
        waitVisibilityOfElement(DEFAULT_TIMEOUT, saleButton);
        saleButton.click();
        waitVisibilityOfElement(DEFAULT_TIMEOUT, biggestDealsButton);
        biggestDealsButton.click();
    }*/

    public WebElement getCartButton() {
        return cartButton;
    }

    public void clickCartButton() {
        ((JavascriptExecutor)driver).executeScript("scroll(0,400)");
        cartButton.click();
    }

    public WebElement getViewBagButton() {
        return viewBagButton;
    }

    public void clickViewBagButton() {
        ((JavascriptExecutor)driver).executeScript("scroll(0,400)");
        cartButton.click();
        waitVisibilityOfElement(DEFAULT_TIMEOUT, viewBagButton);
        viewBagButton.click();
    }

    public void clickWomenPage() {
        womenPage.click();
    }

    public WebElement getCartItemsNumberElement() {
        return cartItemsNumber;
    }

    public String getCartItemsNumberText() {
        char[] chars = cartItemsNumber.getText().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isDigit(chars[i])) {
                chars[i] = ' ';
            }
        }
        return String.valueOf(chars).strip();
    }

    public void changeCountry() {
        countrySelectorButton.click();
        waitVisibilityOfElement(DEFAULT_TIMEOUT, countrySelectorDropdown);
        countrySelectorDropdown.click();
        optionFrance.click();
        waitVisibilityOfElement(DEFAULT_TIMEOUT, submitCountryButton);
        submitCountryButton.click();
    }

    public String getHelpButtonText() {
        return helpButton.getText();
    }

    public void clickHelpButton() {
        waitVisibilityOfElement(DEFAULT_TIMEOUT, helpButton);
        helpButton.click();
    }
}
