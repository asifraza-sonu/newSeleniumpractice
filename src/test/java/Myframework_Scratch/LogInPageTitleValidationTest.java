package Myframework_Scratch;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LogInPageTitleValidationTest extends Base {
	public WebDriver driver;
	public static org.apache.logging.log4j.Logger log = LogManager.getLogger(Base.class.getName());

	@BeforeTest
	public void initialise() throws FileNotFoundException, IOException {
		driver = driverInitializer();
		driver.get(prop.getProperty("LogInPagePath"));
		driver.manage().window().maximize();
		log.debug("Driver has been initailised and page has been triggered");
		log.error("error check");
	}

	@Test
	public void validationOfTheWebPageTitle() throws FileNotFoundException, IOException {
		Assert.assertEquals(driver.getTitle(), prop.getProperty("LogInPageTitle"));
		log.debug("Title of the page has been validated");
		log.error("error check1");
		log.error("error check2");
		log.error("error check3");
		log.error("error check4");
	}

	@AfterTest
	public void closingBrowser() {
		driver.close();
	}

}
