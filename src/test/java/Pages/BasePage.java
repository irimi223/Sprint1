package Pages;

import Drivers.Web;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    /**
    public static void openWebPage(String url) {
        Web.initDriver(url);
    }**/

    // generic method to write on web element
    public void type(By locator, String data) {
        findElementUsingFluentWait(locator).sendKeys(data);
    }

    public void typeJS(By locator, String data) {
        WebElement element = findElementUsingFluentWait(locator);
        JavascriptExecutor js = (JavascriptExecutor)Web.getDriver();
        js.executeScript("arguments[0].values='%s';", element, data);
    }

    // generic method to click web element
    public void clickThis(By locator) {
        findElementUsingFluentWait(locator).click();
    }

    public void clickThisJS(By locator) {
        WebElement element = findElementUsingFluentWait(locator);
        JavascriptExecutor js = (JavascriptExecutor)Web.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public void clickThis(WebElement element) { element.click();}

    public void clearField(By locator) {
        findElementUsingFluentWait(locator).clear();
    }

    public void clearField(WebElement element) {
        element.clear();
    }

    public String getWebPageTitle() {
        return Web.getDriver().getTitle();
    }

    public String getCurrentWebUrl() {
        return Web.getDriver().getCurrentUrl();
    }


    // generic method to get text from web element
    public String getTextFromElement(By locator) {
        return findElementUsingFluentWait(locator).getText();
    }

    public String getTextFromElement(WebElement element) {
        return element.getText();
    }

    // generic method to get attribute value from web element
    public String getAttributeValueFromElement(By locator, String attributeName) {
        return findElementUsingFluentWait(locator).getAttribute(attributeName);
    }

    public String getAttributeValueFromElement(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    // generic method to select any value from dropdown using selectByVisibleText
    public void selectFromDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdownElement = findElementUsingFluentWait(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }

    // generic method to get if webElement is enabled
    public boolean isWebElementEnabled(By locator) {
        return findElementUsingFluentWait(locator).isEnabled();
    }

    // generic method to perform mouse hover on any web element
    public void mouseHoverOnElement(WebElement element) {
        Actions act = new Actions(Web.getDriver());
        act.moveToElement(element).build().perform();
    }

    public void mouseHoverOnElement(By locator) {
        WebElement element = findElementUsingFluentWait(locator);
        Actions act = new Actions(Web.getDriver());
        act.moveToElement(element).build().perform();
    }

    public void selectFromAutoSuggestion(By locator, String userSelection) {
        List<WebElement> autoSuggestionElements= findElementsUsingFluentWait(locator);
        for (int i=0 ; i < autoSuggestionElements.size() ; i++) {
            if (autoSuggestionElements.get(i).getText().equalsIgnoreCase(userSelection)) {
                clickThis(autoSuggestionElements.get(i));
                break;
            }
        }
    }

    public void selectFirstFromAutoSuggestion (By locator) {
        List<WebElement> autoSuggestionElements= findElementsUsingFluentWait(locator);
                clickThis(autoSuggestionElements.get(1));
    }


    public void scrollbyPixel(int y) {
        JavascriptExecutor js = (JavascriptExecutor)Web.getDriver();
        js.executeScript("scrollBy(0," + y + ")"); // scrollBy(0,+y+) --> scrollBy(0,y)
    }

    public void scrollUptoWebElement(By locator) {
        WebElement webElement = findElementUsingFluentWait(locator);
        JavascriptExecutor js = (JavascriptExecutor)Web.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public void scrollUptoWebElement(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor)Web.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }
    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor)Web.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void switchWindow(String windowHandle) {
        Web.getDriver().switchTo().window(windowHandle);
    }

    public String getCurrentWindowHandle() {
        return Web.getDriver().getWindowHandle();
    }

    public Set<String> getAllWindowHandles() {
        return Web.getDriver().getWindowHandles();
    }

    public void switchToFrame(String frameId) {
        Web.getDriver().switchTo().frame(frameId);
    }

    public void switchToFrame(By frameLocator) {
        WebElement myFrame = findElementUsingFluentWait(frameLocator);
        Web.getDriver().switchTo().frame(myFrame);
    }

    // generic method to accept an alert
    public void acceptAlert() {
        Web.getDriver().switchTo().alert().accept();
    }

    // generic method to dismiss an alert
    public void dismissAlert() {
        Web.getDriver().switchTo().alert().dismiss();
    }

    // generic method to get text from an alert
    public String getTextFromAlert() {
        return Web.getDriver().switchTo().alert().getText();
    }

    public WebElement findElementUsingFluentWait(final By locator) {
        Wait fWait = new FluentWait<>(Web.getDriver())
                .withTimeout(Duration.ofSeconds(25))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(NoAlertPresentException.class)
                .ignoring(ElementNotInteractableException.class)
                .withMessage("Element is not found within 25 seconds");

        try {
            WebElement ele = (WebElement) fWait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(locator);

                }
            });
            return ele;

        } catch(TimeoutException ex) {
            return null;
        }

    }

    public List<WebElement> findElementsUsingFluentWait(final By locator) {
        Wait fWait = new FluentWait<>(Web.getDriver())
                .withTimeout(Duration.ofSeconds(25))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(NoAlertPresentException.class)
                .ignoring(ElementNotInteractableException.class)
                .withMessage("Elements are not found within 25 seconds");

        List<WebElement> ele = (List<WebElement>) fWait.until(new Function<WebDriver, List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                return driver.findElements(locator);

            }
        });
        return ele;
    }

    public void selectDateFromCalendar(By locator, String userDate) {
        List<WebElement> allDates = findElementsUsingFluentWait(locator);
        for (WebElement date : allDates) {
            if (date.getText().equals(userDate)) {
                clickThis(date);
                break;
            }
        }
    }






}
