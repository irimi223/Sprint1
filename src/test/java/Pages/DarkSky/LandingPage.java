package Pages.DarkSky;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPage extends BasePage {
    By feelsLikeTemp = By.xpath("//span[@class='feels-like-text'");
    String summaryTempLocatorStarts = "//span[contains(text(),'";
    String summaryTempLocatorEnds = "')]/following-sibling::span";
    By timelineLocator = By.xpath("//span[contains(@class, 'hour')]");

    public String getFeelsLikeTemp() {
        return getTextFromElement(By.xpath(summaryTempLocatorStarts+"Feels Like"+summaryTempLocatorEnds));
    }

    public String getLowTemp() {
        return getTextFromElement(By.xpath(summaryTempLocatorStarts+"Low"+summaryTempLocatorEnds));
    }

    public String getHighTemp() {
        return getTextFromElement(By.xpath(summaryTempLocatorStarts+"High"+summaryTempLocatorEnds));
    }

    public List<WebElement> getTimelineElements() {
        return findElementsUsingFluentWait(timelineLocator);
    }


}
