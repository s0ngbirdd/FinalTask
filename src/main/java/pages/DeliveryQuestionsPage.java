package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DeliveryQuestionsPage extends BasePage {

    private static final long DEFAULT_TIMEOUT = 100;
    private int questionCount = 0;

    @FindBy(xpath = "//li[contains(@class,'CardListItem_iconRight')]//a[@class='CardListItem_anchor']")
    private List<WebElement> questions;

    public DeliveryQuestionsPage(WebDriver driver) {
        super(driver);
    }

    public String getQuestionsCount() {
        for (WebElement webElement : questions) {
            waitVisibilityOfElement(DEFAULT_TIMEOUT, webElement);
            questionCount++;
        }
        return String.valueOf(questionCount);
    }
}
