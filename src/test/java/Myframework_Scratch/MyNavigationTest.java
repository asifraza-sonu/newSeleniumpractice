package Myframework_Scratch;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjetcs.LogInpracticePage;

public class MyNavigationTest extends Base {
	public static Logger log = LogManager.getLogger(Base.class.getName());
	public WebDriver driver;

	@BeforeTest
	public void initialisingDriver() throws FileNotFoundException, IOException {
		driver = driverInitializer();
		LogInPageUrl = prop.getProperty("LogInPagePath");// instead of getting from parent class method got directly
															// from properties file
		driver.get(LogInPageUrl);
		driver.manage().window().maximize();
		log.debug("LogInpage has been Loaded");
		log.info("The LogIn page Url is " + LogInPageUrl);
		log.error("error check");

	}

	@Test(dataProvider = "getUserDataForLogin")
	public void testLogInPage(String userName, String password) throws FileNotFoundException, IOException {
		LogInpracticePage loginpage = new LogInpracticePage(driver);// trying to access page object pages methods
		loginpage.getUserName().sendKeys(userName);
		loginpage.getPassword().sendKeys(password);
		loginpage.getSignInButton().click();
		log.debug("SignIn attempt has been made");
		log.error("error check");

	}

	// Data provider is used here For Testing the test case with multiple sets of
	// data and can be extended as in when required.
	@DataProvider
	public Object[][] getUserDataForLogin() {

		Object[][] data = new Object[1][2];
		data[0][0] = "rahulshettyacademy";
		data[0][1] = "learning";
		return data;
	}

	@AfterTest
	public void browserClosure() {
		driver.close();
	}
}
