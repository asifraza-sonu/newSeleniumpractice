package Myframework_Scratch;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;

import resources.ExtentReporterNG;

public class Listeners extends Base implements ITestListener {

	// get the Extent report object from your resources to here in order to access
	// and utilise for different stages of the listener

	ExtentReports extent = ExtentReporterNG.getExtentReport();

	ExtentTest test;
	String testname;
	String failureScreenshotPathname;
	
	ThreadLocal<ExtentTest> threadSafeTest = new ThreadLocal<ExtentTest>() ;

	public void onTestStart(ITestResult result) {
		// For every test we need tell extent objetc to create an entry for it ,using
		// the ExtentTest and using the result object for fetching the current test case
		// name
		testname = result.getMethod().getMethodName();
		test = extent.createTest(testname);
		threadSafeTest.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		// On Suceess we shud log it using test.log and test.fail

		threadSafeTest.get().log(Status.PASS, "Test Case:" + testname + "is passed");

	}

	public void onTestFailure(ITestResult result) {

		// extend or inherit parent class to access the get screenshot method

		// String testname = result.getMethod().getMethodName();
		// String testname = result.getTestName();
		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			failureScreenshotPathname = getScreenshot(testname, driver);//added failureScreenshotPathname for getting it to Extent reports as well
			// for recording it as failed status and with the exception that casued failure
			threadSafeTest.get().fail(result.getThrowable());
			//for adding the scree capture upon failure in the extent report as well
			threadSafeTest.get().addScreenCaptureFromPath(failureScreenshotPathname, testname);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// will fecth the exception/error details about the current result instance
		// along with marking the test as fail
		
		

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// we can give extent.flush to flush and write the HTML reports to the
		// configured reporter
		extent.flush();

	}

}
