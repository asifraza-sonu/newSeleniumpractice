package pageObjetcs;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogInpracticePage {

	WebDriver driver;
	String LogInpagepath;

	// Defined Constructor to make use of the driver which got initialised with our
	// utility and pass it in this class object whenever been created

	public LogInpracticePage(WebDriver driver) throws FileNotFoundException, IOException {
		this.driver = driver;
	}
/* Achieved encapsulation by making the pageobject variables of page Locators private and can be accessed with the public methods of this class
	upon invokation by other class*/
	private By username = new By.ById("username");
	private By password = new By.ByCssSelector("input[name='password']");
	private By signInButton = new By.ByCssSelector("input#signInBtn");

	public WebElement getUserName() {
		return driver.findElement(username);
	}

	public WebElement getPassword() {
		return driver.findElement(password);
	}

	public WebElement getSignInButton() {
		return driver.findElement(signInButton);
	}

}
