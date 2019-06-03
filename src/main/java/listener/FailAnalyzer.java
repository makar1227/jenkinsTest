package listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailAnalyzer implements IRetryAnalyzer {
    private static final Logger logger = LogManager.getLogger(FailAnalyzer.class);
    private static final int MAX_COUNT = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (result.getStatus() != result.SUCCESS && result.getMethod().getCurrentInvocationCount() < MAX_COUNT) {
            logger.log(Level.INFO, "Retrying test " + (result.getMethod().getCurrentInvocationCount() + 1));
            return true;
        }
        return false;
    }
}
