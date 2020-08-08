package StepDefinition.Hotels;

import Drivers.Web;
import Pages.Hotels.LandingPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ChildrenDropdown {

    LandingPage landingPage = new LandingPage();

    @Given("^I am on hotels landing page$")
    public void launchHotels() {
        Web.initDriver("https://hotels.com/");
    }



    @When("^I select (\\d+) in children dropdown$")
    public void selectNumOfChildren (Integer childrenCount) {
        landingPage.selectChildrenDropdown(childrenCount.toString());
    }

    @Then("^I see (\\d+) child age dropdowns$")
    public void isChildAgeDropdownsPresent (int expectedChildrenCount) {

        if (expectedChildrenCount == 0) {
            WebElement element = landingPage.findChildNDropdownElement();
            Assert.assertEquals(null, element);
        } else {
            for (int n=0; n<=expectedChildrenCount; n++) {
                landingPage.scrollbyPixel(60);
                //landingPage.scrollUptoWebElement(landingPage.searchButton());
                WebElement element1 = landingPage.findChildNDropdownElement();
                boolean isChildNPresent = landingPage.findChildNDropdownElement().isDisplayed();
                 Assert.assertTrue(isChildNPresent, "Number of children dropdowns is not as expected");
                 try {
                    Thread.sleep(2000);
                 } catch (InterruptedException e) {
                    e.printStackTrace();
                 }
             }
        }
    }
}
