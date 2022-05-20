package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(xpath = "//div[@class='bag-total-holder bag-title-holder bag-title-holder--total']//span[@class='bag-total-price bag-total-price--subtotal']")
    private WebElement subTotal;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSubTotalElement() {
        return subTotal;
    }

    public String getSubTotalText() {
        char[] chars = subTotal.getText().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isDigit(chars[i]) && chars[i] != '.') {
                chars[i] = ' ';
            }
        }
        return String.valueOf(chars).strip();
    }
}
