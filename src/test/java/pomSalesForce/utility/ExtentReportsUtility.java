package pomSalesForce.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pomSalesForce.base.BaseAction;

public class ExtentReportsUtility extends BaseAction {
	public static ExtentReports report;
	public static ExtentSparkReporter spartReporter;
	public static ExtentTest logger;
	private static ExtentReportsUtility extentObject;
	  public ExtentReportsUtility() { //
		    super(); // we will call the constructor for BaseAction
		  }//
	
	public static ExtentReportsUtility getInstance() {
		if(extentObject==null) { 
			System.out.println("creating textent utility object");
			extentObject=new ExtentReportsUtility();
		}
		return extentObject;
	}
	
	public void startExtentReport() {
		spartReporter=new ExtentSparkReporter(Constants.SPARKS_HTML_REPORT_PATH);
		report=new ExtentReports();

		
		report.attachReporter(spartReporter);
		report.attachReporter(spartReporter);
		report.setSystemInfo("Host Name", "SalesForce");
		report.setSystemInfo("Environment", "Automation Testing");
		report.setSystemInfo("User Name", "Leti");

		spartReporter.config().setDocumentTitle("Test Execution Report");
		spartReporter.config().setReportName("SalesForce Automation tests");
		spartReporter.config().setTheme(Theme.DARK);
	}
	
	
	public void startSingleTestReport(String testScript_Name) {
		logger=report.createTest(testScript_Name);
	}
	
	public void logTestInfo(String text) {
		logger.info(" info from logger regarding Test " + text);
	}
	public void logTestpassed(String testcaseName) {
		logger.pass(MarkupHelper.createLabel(testcaseName + "is passTest", ExtentColor.GREEN));
	}
	
	public void logTestFailed(String testcaseName) {
		logger.fail(MarkupHelper.createLabel(testcaseName + "is failed", ExtentColor.RED));
	}
	
	public void logTestFailedWithException(Exception e) {
		logger.log(Status.FAIL,e);
		}
	public void logTestScreenshot(String path) {
		logger.addScreenCaptureFromPath(path);
		}
	public void endReport() {
		report.flush();
	}

	
}
