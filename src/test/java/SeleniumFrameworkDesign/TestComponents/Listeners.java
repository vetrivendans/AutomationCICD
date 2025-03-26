package SeleniumFrameworkDesign.TestComponents;

import java.io.IOException;
import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import seleniumFrameworkDesign.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReportsNG.getReportObj();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		// Create a new test node in ExtentReports for every test
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		IRetryAnalyzer retryAnalyzer = result.getMethod().getRetryAnalyzer(result);
		boolean isRetry = retryAnalyzer != null && retryAnalyzer instanceof RetryAnalyzer
				&& ((RetryAnalyzer) retryAnalyzer).getRetryCount() > 0;

		if (isRetry) {
			extentTest.get().log(Status.PASS, "Test Passed on Retry: " + result.getMethod().getMethodName());
		} else {
			extentTest.get().log(Status.PASS, "Test Case Passed: " + result.getMethod().getMethodName());
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
	    // Log every test failure as FAIL
	    extentTest.get().log(Status.FAIL, "Test Case Failed: " + result.getMethod().getMethodName());
	    extentTest.get().fail(result.getThrowable()); // Log exception details
	    captureScreenshot(result); // Optional: Capture screenshot for the failed test
	}

	private void captureScreenshot(ITestResult result) {
		WebDriver driver = null;
		String screenshotPath = null;

		try {
			// Use reflection to retrieve the WebDriver instance from the parent test class
			Class<?> parentClass = result.getTestClass().getRealClass().getSuperclass();
			Field driverField = parentClass.getDeclaredField("driver");
			driverField.setAccessible(true);
			driver = (WebDriver) driverField.get(result.getInstance());
		} catch (NoSuchFieldException | IllegalAccessException e) {
			extentTest.get().fail("Unable to retrieve WebDriver instance: " + e.getMessage());
			e.printStackTrace();
		}

		if (driver != null) {
			try {
				screenshotPath = takeScreenshot(result.getMethod().getMethodName(), driver);
				extentTest.get().addScreenCaptureFromPath(screenshotPath,
						"Screenshot for Failed Test: " + result.getMethod().getMethodName());
			} catch (IOException e) {
				extentTest.get().fail("Failed to capture screenshot: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			extentTest.get().fail("WebDriver instance is null, skipping screenshot.");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test Case Skipped: " + result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		// Write all data to the report
		extent.flush();
	}
}
