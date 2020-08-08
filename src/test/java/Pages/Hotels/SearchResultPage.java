package Pages.Hotels;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultPage extends BasePage {

    By destinationHeaderLocator = By.xpath("//h1[@class='destination-title']");
    By destinationPropertyLandmarkText = By.xpath("//label[text()='Destination, property, or landmark']/following-sibling::input[@id='q-destination']");
    //By starRatingLocator = By.xpath("//label[contains(@id, 'star-rating-"+ n +"')]");
    By hotelsStarLocator = By.xpath("//span[contains(@class, 'star-rating-text')]");


    public String getDestinationHeader() {
        return getTextFromElement(destinationHeaderLocator);
    }

    public String getDestinationPropertyLandmarkText() {
        return getAttributeValueFromElement(destinationPropertyLandmarkText, "value");
    }

    public void clickStarRating (int n) {
        By starRatingLocator = By.xpath("//label[contains(@id, 'star-rating-"+ n +"')]");
        clickThis(starRatingLocator);
    }

    public List<WebElement> getStarRetings() {
       return findElementsUsingFluentWait(hotelsStarLocator);
    }



}
