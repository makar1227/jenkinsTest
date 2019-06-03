package util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            WebDriver webDriver = DriverFactory.setupDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
            webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            return webDriver;
        }
    };

    private DriverManager() {
    }

    private static class InnerInit {
        private static final DriverManager instance = new DriverManager();
    }
    public static DriverManager getInstance() {
        return InnerInit.instance;
    }

    public WebDriver getDriver() // call this method to get the driver object and launch the browser
    {
        return driver.get();
    }

    public void removeDriver() // Quits the driver and closes the browser
    {
        logger.log(Level.INFO, "try to quite driver");
        driver.get().quit();
        driver.remove();
    }
}

