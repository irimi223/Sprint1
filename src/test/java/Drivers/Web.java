package Drivers;

import Utils.ScreenShotHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Web {

    private static WebDriver driver;


    public static void initDriver(String url) {
        System.setProperty("webdriver.chrome.driver", "DriverExecFiles/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // NoSuchElementException
        driver.get(url);
    }

    //@Before
    public static void initDriver() {
        System.setProperty("webdriver.chrome.driver", "DriverExecFiles/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.hotels.com");
    }


    public static void closeDriver() {
        driver.close();
    }

    public static WebDriver getDriver() {
        return driver;
    }


    @After
    public static void quitDriver(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenShotHelper.takeWebScreenShot(scenario.getName());
        }
        driver.quit();
    }



}
