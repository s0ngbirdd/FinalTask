package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WomenPage extends BasePage{

    private static final long DEFAULT_TIMEOUT = 300;

    @FindBy(xpath = "//button[@class='_2syfS2P _1njuHQk otVu6ZN' and @aria-controls='57242f2c-d207-471c-95b1-31d6839df360']")
    private WebElement saleButton;

    @FindBy(xpath = "//ul[@aria-labelledby='93621d77-e6d0-4f3c-be35-0e06a981de22']//a[text()='Biggest deals']")
    private WebElement biggestDealsButton;

    public WomenPage(WebDriver driver) {
        super(driver);
    }

    public void clickBiggestDealsButton() {
        waitVisibilityOfElement(DEFAULT_TIMEOUT, saleButton);
        saleButton.click();
        waitVisibilityOfElement(DEFAULT_TIMEOUT, biggestDealsButton);
        biggestDealsButton.click();
    }
}
