package rahulshettyacademy.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import rahulshettyacademy.Resource.ExtentReportNG;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReportNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();


    @Override
    public void onTestStart(ITestResult iTestResult) {
        test = extent.createTest(iTestResult.getMethod().getMethodName());
        extentTest.set(test);

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentTest.get().log(Status.PASS, "Test Passed.");

        try {
            driver = (WebDriver) iTestResult.getTestClass().getRealClass().getField("driver")
                    .get(iTestResult.getInstance());
        } catch (Exception e) {
            e.getMessage();
        }
        String filePath = null;
        try {
            filePath = getScreenShot(iTestResult.getMethod().getMethodName(), driver);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filePath, iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        extentTest.get().fail(iTestResult.getThrowable());
        // test.fail(iTestResult.getThrowable());
        extentTest.get().log(Status.FAIL, "Test Failed.");
        try {
            driver = (WebDriver) iTestResult.getTestClass().getRealClass().getField("driver")
                    .get(iTestResult.getInstance());
        } catch (Exception e) {
            e.getMessage();
        }
        String filePath = null;
        try {
            filePath = getScreenShot(iTestResult.getMethod().getMethodName(), driver);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filePath, iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }
}
