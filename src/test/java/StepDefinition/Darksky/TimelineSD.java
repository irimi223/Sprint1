package StepDefinition.Darksky;

import Drivers.Web;
import Pages.DarkSky.LandingPage;
import cucumber.api.java.ca.Cal;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimelineSD {

    LandingPage landingPage = new LandingPage();
    SimpleDateFormat makeTime = new SimpleDateFormat();

    @Given("^I am on darksky home page$")
    public void launchDarkSky() {
        Web.initDriver("https://darksky.net/");
    }


    @Then("^I verify timeline is displayed with (\\d+) hours increment with (\\d+) data points$")
    public void timelineIncrementVerification (int hrsIncr, int expectedDataPoints) throws ParseException {
        List<WebElement> timelineElements = landingPage.getTimelineElements();
        System.out.println("Number of elements is " + timelineElements.size());
        int actualDataPoints = 2;
        for (int i=hrsIncr; i<timelineElements.size()-1-hrsIncr; i=i+hrsIncr) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hha");
            String timeStr1 = timelineElements.get(i).getText().toUpperCase();
            String timeStr2 = timelineElements.get(i+hrsIncr).getText().toUpperCase();
            Date time1 = simpleDateFormat.parse(timeStr1);
            Date time2 = simpleDateFormat.parse(timeStr2);
            if (time2.getTime() < time1.getTime()) {
                long time2PlusOneDay = time2.getTime() + 24 * 60 * 60 * 1000;
                time2 = new Date(time2PlusOneDay);
            }
            long timeDiff = time2.getTime() - time1.getTime();
            timeDiff = timeDiff / 1000 / 60 / 60;

            Assert.assertTrue(timeDiff == 2, "Timeline is displayed with wrong increment");
            actualDataPoints++;
            }

        Assert.assertEquals(actualDataPoints, expectedDataPoints, "Number of data points is not as expected");
    }



}

