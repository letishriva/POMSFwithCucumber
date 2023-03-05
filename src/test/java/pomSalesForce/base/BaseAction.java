package pomSalesForce.base;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.awt.dnd.DragGestureEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import io.github.bonigarcia.wdm.WebDriverManager;
import pomSalesForce.utility.*;

public class BaseAction {
	public static Logger logger;
	public static ExtentReportsUtility extentreport;
	protected static WebDriver driver; // we have to create a singleton = only one instance of BaseAction for the same Driver throughout
	
// Singleton 
	protected BaseAction() { // not to get other instantiation from somewhere else
	}
	public static WebDriver getDriver() {
	return driver;
	}

	protected static BaseAction getInstance() {
		if(driver == null) {
		driver = (WebDriver) new BaseAction();
		}
		return (BaseAction) driver;
	}

	@BeforeTest
	public void setUpBeforeTest() {
        System.out.println("Base Action class - Before Test called - Test is starting");
        System.out.println("Class name " + BaseAction.class.getName());
 	}
	
	@BeforeMethod
	public void setUpBeforeMethod(Method method) {
		System.out.println("Before Method script executing");
        logger = LogManager.getLogger(BaseAction.class.getName());
        logger.info("method started");
        extentreport.startSingleTestReport("Single Test Report " + logger.getName());
	}
	
	@AfterMethod
	public void closeDriver() {
		extentreport.logTestInfo("After Method script executing");
	}
	
		
	@AfterTest
	public void tearDown() {
		System.out.println("Base Action class - After Test called - Test is finished");
		extentreport.endReport();
		driver.quit();
	}

	public WebDriver getWebDriver(String browserName) {
		WebDriver lDriver; //Local within method
		System.out.println("Driver is defined in BaseAction Class");
		switch(browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			lDriver = new ChromeDriver();
			return lDriver;			
		case "firefox":
			WebDriverManager.firefoxdriver().setup(); 
			lDriver = new FirefoxDriver();
			return lDriver;
		case "edge":
			WebDriverManager.edgedriver().setup();
			lDriver =  new EdgeDriver();	 
			return lDriver;
		default:
			WebDriverManager.chromedriver().setup();
			lDriver = new ChromeDriver();
			return lDriver;
		}
	}

	public  void closeBrowser(WebDriver dr) {
		dr.close();
	}

	public static String getScreenShotOfThePage(WebDriver dr) throws Exception  {

		File screenshot = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE); //Code to take screen shot
		File pathOfScreenShotFile = new File(Constants.SCREENSHOTS_DIRECTORY_PATH + System.currentTimeMillis()+ ".png");	// Code to get File and File path of screen shot	
		FileUtils.copyFile(screenshot, pathOfScreenShotFile); // Copies a file to a new location preserving the file date	
		String ssFilePath = pathOfScreenShotFile.getPath();//we get the path of the screenshot to return it in our ExtentReport		
		System.out.println("Screen shot file path :- " + ssFilePath);
		return ssFilePath;//we get the path of the screenshot to return it in our ExtentReport
	}
	
	public static Map<String, String> mapWithKeyValue;

	public static Map<String, String> getValuesFromPropertyFile() throws IOException {
			//String abc = null;
			mapWithKeyValue = new HashMap<>();		
			Properties testDataProp = new Properties();
			FileInputStream TDfileInputStream = new FileInputStream(Constants.TESTDATA_PROPERTIES);	
			testDataProp.load(TDfileInputStream);
			testDataProp.forEach((k, v) -> mapWithKeyValue.put(k.toString(), v.toString()));
			//System.out.println(" value of UID is  - " + mapWithKeyValue.get("userid"));		
			return mapWithKeyValue;
	}
	}
	

