package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

import com.aventstack.extentreports.*;

import base.BaseTest;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getReport();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed ✅");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail("Test Failed ❌");

        String testName = result.getName();

        // take screenshot
        ScreenshotUtil.takeScreenshot(BaseTest.driver, testName);

        // attach screenshot in report
        test.addScreenCaptureFromPath(
            System.getProperty("user.dir") + "/screenshots/" + testName + ".png"
        );
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}