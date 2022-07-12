package Myframework_Scratch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("currenttime", dateFormat.format(new Date()));

	}

	public static Logger log = LogManager.getLogger(Base.class.getName());
	public WebDriver driver; // Defining Driver object reference at global level so that it can be re used
	String LogInPageUrl;// rather than creating object multiple times
	public Properties prop;
	/*
	 * based on the global variable browser set by us in the properties data file
	 * the driverIntializer will create and initialize the the respective browser
	 * and return the particular driver upon calling the base class. method into any
	 * of their methods
	 */

	public WebDriver driverInitializer() throws FileNotFoundException, IOException {
		prop = new Properties();
		/*FileInputStream fis = new FileInputStream(
				"D:\\selenium workspace\\Myframework_Scratch\\src\\main\\java\\resources\\MyGlobalData.properties");    */
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
				"\\src\\main\\java\\resources\\MyGlobalData.properties");
		
		/* Frame Work Optimisation  -- never hardcode any local paths or any driver paths try maximum to parameterise and make it useful when other 
		 * implement your framework copy till root folder i.e src by using System.getProperty("user.dir")*/
		prop.load(fis);
		//String givenBrowser = prop.getProperty("browser"); instead of properties lets try sending from maven commands
		
		/*Instead of getting the Browsername and other variables,parameters from properties file,we can also 
		 * get it from the maven commands and later we can control and integrate the same with jenkins.in this way someone
		 * like non technical team member alos can control and change the broswer or environment values from maven while triggering from CMD prompt
		 *  or jenkins while triggering the build It also lessens the work of checking out the code-->commit them-->checkIn-->Build them again
		 * Maven command to pass variables is mvn test -Dbrowser=chrome i.e. mvn test -Drequiredfield=value
		 */
		// System.out.println(givenBrowser);
		String givenBrowser = System.getProperty("browser");
		log.debug("The browser retrieved  from data.properties file is : " + "   " + givenBrowser);
		log.error("error check");
		if (givenBrowser.contains("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "D:\\chrome driver\\chromedriver_win32\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver_win32\\chromedriver.exe");
			
			//Now we can give inteliignce to this programme on normal chrome and chrome headless(i.e. execution without opening browser)
			//ChromeOptions class can be used to give arguments such as headless to chromdriver  and then initialise the driver
			ChromeOptions chromeOptions = new ChromeOptions();
			if(givenBrowser.contains("headless"))
			{
				chromeOptions.addArguments("headless");
			}
			driver = new ChromeDriver(chromeOptions);//based on our given browser it will initialise either normal or headless chrome 
		} else if (givenBrowser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"");// path not defined
			driver = new FirefoxDriver();
		} else if (givenBrowser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+ "");// path not defined
			driver = new InternetExplorerDriver();
		} else {
			log.error(
					"The retrieved Browser varaiable from  data.properties file can only chrome or firefox or internet explorer");
		}
		// Setting implicit time outs once the driver has been initialized
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}

	public String getLogInpageUrl() throws FileNotFoundException, IOException {
		/*
		 * Properties prop = new Properties(); FileInputStream fis = new
		 * FileInputStream(
		 * "D:\\selenium workspace\\Myframework_Scratch\\src\\main\\java\\resources\\MyGlobalData.properties"
		 * ); prop.load(fis);
		 */
		LogInPageUrl = prop.getProperty("LogInPagePath");
		log.debug("getLogInpageUrl method has been accessed");
		log.info("The login page UrL is:" + "  " + LogInPageUrl);
		return LogInPageUrl;
	}

	public String getScreenshot(String testcasename, WebDriver driver) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		String ScreenShotpathname = System.getProperty("user.dir") + "\\reports\\" + "_" + testcasename
				+ System.getProperty("currenttime") + ".png";
		FileUtils.copyFile(source, new File(ScreenShotpathname));
		
		return ScreenShotpathname;
	}
}
