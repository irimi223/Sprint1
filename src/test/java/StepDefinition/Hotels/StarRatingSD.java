package StepDefinition.Hotels;

import Drivers.Web;
import Pages.Hotels.LandingPage;
import Pages.Hotels.SearchResultPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class StarRatingSD {

    LandingPage landingPage = new LandingPage();
    SearchResultPage searchResultPage = new SearchResultPage();


    @Given("^I am on default locations search result screen$")
    public void defaultSearch() {
        Web.initDriver("https://hotels.com/");
        landingPage.clickSearchBox();
        landingPage.selectFirstElemFromAutoSuggestion();
        landingPage.clickSearchButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("^I click on (\\d+) star$")
    public void ClickOnStarRating(int star) {
            landingPage.scrollbyPixel(500);
            searchResultPage.clickStarRating(star);
    }

    @Then("^I verify hotels are (\\d+) star rating$")
    public void iVerifyHotelsAreStarRating(int expectedStR) {

            List<WebElement> starRatings = searchResultPage.getStarRetings();
            if (starRatings.size()>0) {
                for (int i = 0; i < starRatings.size(); i++) {

                    String actualStarRating = "";
                    actualStarRating = searchResultPage.getTextFromElement(starRatings.get(i)).substring(0, 1);
                    Assert.assertEquals(actualStarRating, Integer.toString(expectedStR), "Star Rating is not as expected");
                }
            } else {
                System.out.println("There are no hotels with " + expectedStR + " star rating in this search");
            }
    }


}
