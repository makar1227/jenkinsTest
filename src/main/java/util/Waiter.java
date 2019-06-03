package util;

import exception.UnsupportedDriverNameException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Waiter {
    private static final Logger logger = LogManager.getLogger(Waiter.class);
    private static WebDriverWait webDriverWait;
    private static WebDriverWait longWebDriverWait;

    static {
        int timeOut = 15;
        int longTimeOut = 30;
        try {
            webDriverWait = new WebDriverWait(DriverManager.getInstance().getDriver(), timeOut);
            longWebDriverWait = new WebDriverWait(DriverManager.getInstance().getDriver(), longTimeOut);
        } catch (UnsupportedDriverNameException e) {
            logger.log(Level.FATAL, e.getMessage());
        }
    }

    public static void waitForElementVisible(WebElement element) {
        logger.log(Level.INFO, "Wait for presence of element: " + element.toString());
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void waitForElementVisible(List<WebElement> elements) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
    public static void waitForPageLoad() {
        logger.log(Level.INFO, "Wait for page to be loaded");
        webDriverWait.until(e -> (JavascriptExecutor) e).executeScript("return document.readyState").equals("complete");
    }

}

