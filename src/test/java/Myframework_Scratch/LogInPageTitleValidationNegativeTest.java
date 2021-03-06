package Myframework_Scratch;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LogInPageTitleValidationNegativeTest extends Base {
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
		log.error("Fetched Webpage title :" + driver.getTitle()
				+ " is not matching with Expected title Hence validation failed ");
		Assert.assertEquals(driver.getTitle(), "negative testing paramater");
		log.debug("Title of the page has been validated");

	}

	@AfterTest
	public void closingBrowser() {
		driver.close();
	}

}
