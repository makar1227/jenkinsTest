package listener;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import util.DriverManager;


public class SuiteListener implements ISuiteListener {
    private static final Logger logger = LogManager.getLogger(ISuiteListener.class);

    public void onStart(ISuite iSuite) {
        logger.log(Level.INFO, "\n"+iSuite.getName() + " Suite was started");
        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.get("http://www.mtv.com");
    }

    public void onFinish(ISuite iSuite) {
        logger.log(Level.INFO, iSuite.getName() + " Suite was finished");
        DriverManager.getInstance().removeDriver();
    }
}
