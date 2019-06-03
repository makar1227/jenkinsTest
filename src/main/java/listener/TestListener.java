package listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    public void onTestStart(ITestResult iTestResult) {
        logger.log(Level.INFO, iTestResult.getTestClass().getName() + " " + iTestResult.getMethod().getMethodName() + " started");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        logger.log(Level.INFO, iTestResult.getTestClass().getName() + " " + iTestResult.getMethod().getMethodName() + " finished with success");
    }

    public void onTestFailure(ITestResult iTestResult) {
        logger.log(Level.INFO, iTestResult.getTestClass().getName() + " " + iTestResult.getMethod().getMethodName() + " finished with fail");
    }

    public void onTestSkipped(ITestResult iTestResult) {
        logger.log(Level.INFO, iTestResult.getName() + " onTestSkipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.log(Level.INFO, iTestResult.getName() + " onTestFailedButWithinSuccessPercentage");
    }

    public void onStart(ITestContext iTestContext) {
        logger.log(Level.INFO, iTestContext.getName() + " onStart");
    }

    public void onFinish(ITestContext iTestContext) {
        logger.log(Level.INFO, iTestContext.getName() + " onFinish\n");
    }
}
