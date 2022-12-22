package pages;

import driver.InitAppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;
import java.util.List;

public class BasePage extends InitAppiumDriver {

    public void setText(By locator, String text) {
        appiumDriver.findElement(locator).sendKeys(text);
    }

    public void clickElement(By locator) {
        appiumDriver.findElement(locator).click();
    }

    public String getText(By locator) {
        return appiumDriver.findElement(locator).getText();
    }

    public Boolean verifyElementIsPresent(By locator) {
        List<MobileElement> elements = appiumDriver.findElements(locator);
        return elements != null && elements.size() > 0;
    }

    public Boolean verifyElementNotPresent(By locator) {
        return appiumDriver.findElements(locator).size() == 0;
    }

    public void waitUntil(ExpectedCondition<WebElement> expectedCondition) {
        webDriverWait.until(expectedCondition);
    }

    public void swipeUp(int swipe) {
        try {
            Thread.sleep(2000);
            Dimension size = appiumDriver.manage().window().getSize();
            int startX = (int) (size.height * 0.5);
            int startY = (int) (size.height * 0.8);
            int endX = (int) (size.height * 0.2);
            int endY = (int) (size.height * 0.2);

            for (int i = 1; i <= swipe; i++) {
                TouchAction<?> touch = new TouchAction<>(appiumDriver);
                touch.press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(PointOption.point(endX, endY)).release().perform();
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void swipeScreen(String direction) {
        final int PRESS_TIME = 200;
        final int edgeBorder = 10;

        PointOption<?> pointOptionStart, pointOptionEnd;
        Dimension dims = appiumDriver.manage().window().getSize();
        pointOptionStart = PointOption.point(dims.width/2, dims.height/2);

        pointOptionEnd = switch (direction) {
            case "Down" -> PointOption.point(dims.width/2, dims.height-edgeBorder);
            case "Left" -> PointOption.point(edgeBorder, dims.height/2);
            case "Right" -> PointOption.point(dims.width-edgeBorder, dims.height/2);
            default -> throw new IllegalArgumentException("SwipeScreen(): dir: '" + direction + "' not supported");
        };

        try {
            new TouchAction<>(appiumDriver)
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}