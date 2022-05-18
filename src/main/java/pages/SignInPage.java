package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


public class SignInPage extends BasePage{

    @FindBy(xpath = "//input[@name='Username']")
    private WebElement emailField;

    @FindBy(xpath = "//span[@id='EmailAddress-error']")
    private WebElement emailErrorMessage;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(final String email) {
        emailField.sendKeys(email, Keys.ENTER);
    }

    public boolean existsEmailErrorMessageElement() {
        try{
            return emailErrorMessage.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement getEmailErrorMessageElement() {
        return emailErrorMessage;
    }

    public String getEmailErrorMessageText() {
        return emailErrorMessage.getText();
    }
}
