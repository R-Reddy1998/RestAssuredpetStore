package api.utilities;

import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng. ITestContext;
import org.testng. ITestListener;
import org.testng. ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter. ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

 
public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; //populate common info on the report
	public ExtentTest test;// creating test case entries in the report and update status of the test methods
	
	String repName;
	
	public void onStart(ITestContext testContext){
		
		
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//TIME stamp
	repName = "Test-Report" + timeStamp + ".html";
	
	sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);   
	
	sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of report
	sparkReporter.config().setReportName("pet store users API"); // name of the report
	sparkReporter.config().setTheme (Theme.DARK);
	
	extent=new ExtentReports();
	extent.attachReporter(sparkReporter);
	extent.setSystemInfo("Application", "opencart");
	extent.setSystemInfo("Module", "Admin");
	extent.setSystemInfo("Sub Module", "Customers");
	extent.setSystemInfo("User name", System.getProperty("user.name"));
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("user","Reddy");
		}
	
		
	public void onTestSuccess(ITestResult result) 
	{
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS,  "test Passed");
	
	}
	public void onTestFailure(ITestResult result){
			test = extent.createTest(result.getName());
			test.createNode(result.getName());

			test.assignCategory(result.getMethod().getGroups());
			
			test.log(Status.FAIL,"Test failed");
		    test.log(Status.FAIL, result.getThrowable().getMessage());
		    
		}
		
	public void onTestSkipped(ITestResult result){
			test = extent.createTest(result.getName());
			test.createNode(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP, "Test skipped");
		    test.log(Status.SKIP, result.getThrowable().getMessage());
		}
		public void onFinish(ITestContext testContext){
			
		extent.flush();
		
		 
	}
}
	 