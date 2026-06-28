package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.DriverFactory;
import reports.ExtentManager;
import utils.ConfigReader;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    private final ExtentReports extent =
            ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
   
    @Override
    public void onTestStart(ITestResult result) {
        String testName =
                result.getTestClass()
                        .getRealClass()
                        .getSimpleName()
                        + " - "
                        + result.getMethod()
                                .getMethodName();
        ExtentTest test =
                extent.createTest(testName);
        test.assignCategory(
                result.getTestContext()
                        .getName());
        test.info(
                "Browser: "
                        + ConfigReader.getProperty(
                                "browser"));
        extentTest.set(test);
    }
    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = extentTest.get();

        test.fail(result.getThrowable());

        try {
            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(
                            DriverFactory.getDriver(),
                            result.getMethod()
                                    .getMethodName());
            test.addScreenCaptureFromPath(
                    screenshotPath,
                    "Failure Screenshot");
        } catch (Exception e) {
            test.warning(
                    "Screenshot could not be attached: "
                            + e.getMessage());
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().skip(
                result.getThrowable());
    }
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}