package pomSalesForce.utility;
//values which won't change throughout the project
public class Constants {
	public static final String USER_DIR=System.getProperty("user.dir");
	public static final String APPLICATION_PROPERTIES=USER_DIR+"/src/test/resources/testdataPropertyfiles/applicationData.properties";
	public static final String SPARKS_HTML_REPORT_PATH=USER_DIR+"/extentReports/testreport.html"; // works
	public static final String SCREENSHOTS_DIRECTORY_PATH=USER_DIR+"/screenshots/"; // to check
	public static final String useWebDriver = "chrome";
	public static final String TESTDATA_PROPERTIES=USER_DIR+"/src/test/resources/testData.properties";

}
