package pages;

import config.OsConfig;
import driver.InitWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage extends InitWebDriver {

    public void clearText(By by) {
        OsConfig.getOperatingSystems(
                () -> driver.findElement(by).sendKeys(Keys.chord(Keys.COMMAND, "a"), Keys.chord(Keys.DELETE)),
                () -> driver.findElement(by).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.chord(Keys.BACK_SPACE)),
                () -> driver.findElement(by).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.chord(Keys.BACK_SPACE))
        );
    }

    public void setText(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        clearText(locator);
        element.sendKeys(text);
    }

    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public Boolean verifyElementIsPresent(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements != null && elements.size() > 0;
    }

    public Boolean verifyElementNotPresent(By locator) {
        return driver.findElements(locator).size() == 0;
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public void waitUntil(ExpectedCondition<WebElement> expectedCondition) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        webDriverWait.until(expectedCondition);
    }

    public void scrollToElement(By by) {
        try {
            JavascriptExecutor js = driver;
            js.executeScript("arguments[0].scrollIntoView();", driver.findElement(by));
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void clickElementByOffset(By locator) {
        WebElement element = driver.findElement(locator);
        int width = element.getSize().getWidth();
        Actions actions = new Actions(driver);
        actions.moveToElement(element).moveByOffset((width/2)-2, 0).click().perform();
    }
}