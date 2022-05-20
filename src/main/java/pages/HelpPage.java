package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HelpPage extends BasePage{

    private static final long DEFAULT_TIMEOUT = 100;
    private int topicCount = 0;

    @FindBy(xpath = "//div[@class='Card_card Card_homepageTopicWrapper']")
    private List<WebElement> faqTopics;

    @FindBy(xpath = "//a[@href='/customer-care/delivery/' and @class='CardListItem_anchor']")
    private WebElement deliveryButton;

    public HelpPage(WebDriver driver) {
        super(driver);
    }

    public String getTopicCount() {
        for (WebElement webElement : faqTopics) {
            waitVisibilityOfElement(DEFAULT_TIMEOUT, webElement);
            topicCount++;
        }
        return String.valueOf(topicCount);
    }

    public void clickDeliveryButton() {
        waitVisibilityOfElement(DEFAULT_TIMEOUT, deliveryButton);
        deliveryButton.click();
    }
}
