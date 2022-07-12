package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	 public static ExtentSparkReporter reporter ;
	 public static ExtentReports extent;
	public static ExtentReports getExtentReport()
	{
		//Creation and setting up the folder path location where we want to see the Extent Report in our Project Location
		String path = System.getProperty("user.dir") +"\\reports\\"+"ExtentReport.html";
		//Configuring the report by setting up names and location using EventSparkreporter and laster on we can attach this to the extent report
		reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Myframework_Scratch_Automation_Extent_Report");
		reporter.config().setReportName("Myframework_Scratch_Automation_Results");
	    //Creation of the Extent Report and attaching the above configured eventSparkReporter to It
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		//After Attaching the report no we can specify some customised  attributes using setSystemInfor  we want to see in the HTML report like tester name and etc
		extent.setSystemInfo("TesterName","RockStarAsif");
		/*after report configuration and attaching it to the report extent object we can return this extent object to all test methods using
		  Listenrsclass*/
		 
		return extent;
		
	}

}
