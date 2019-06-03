package util;

import exception.UnsupportedDriverNameException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.apache.logging.log4j.Logger;
import parser.ProjectProperties;

public class DriverFactory {
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);

    public static WebDriver setupDriver() {

        String driverName = ProjectProperties.getProperties().getProperty("browser");
        switch (driverName) {
            case ("chrome"): {
                logger.log(Level.INFO, "Google Chrome webdriver set with thread name = "+Thread.currentThread().getName());
                return new ChromeDriver();
            }
            case ("firefox"): {
                logger.log(Level.INFO, "Firefox webdriver set with thread name = "+Thread.currentThread().getName());
                return new FirefoxDriver();
            }
            case ("ie"): {
                logger.log(Level.INFO, "Internet Explorer webdriver set with thread name = "+Thread.currentThread().getName());
                return new InternetExplorerDriver();
            }
            default: {
                throw new UnsupportedDriverNameException("Can not load driver '" + driverName + "'");
            }
        }
    }

}
