package pomSalesForce.tests;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pomSalesForce.base.BaseAction;
import pomSalesForce.pages.home.HomePage;
import pomSalesForce.pages.login.ForgotPasswordPage;
import pomSalesForce.pages.login.LoginPage;
import pomSalesForce.pages.login.PasswordResetPage;
import pomSalesForce.utility.ExtentReportsUtility;
import pomSalesForce.utility.ReadPropertyFile;

public class StepDefinitionForSalesForce extends BaseAction {
	  public StepDefinitionForSalesForce() {
		    super(); // we will call the constructor for BaseAction
		  }

 @BeforeAll
	    public static void beforeAll() {
	 		logger = LogManager.getLogger(BaseAction.class.getName()); //log4j
	 		extentreport = ExtentReportsUtility.getInstance();
	        extentreport.startExtentReport();
	 		logger.info("From Logger : BeforeALL cucumber"); //log4j     
	        System.out.println("Inside Cucumber BeforeAll");
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.manage().window().maximize();
	    }
	
 @AfterAll
 public static void AfterAll() {
		System.out.println("Inside Cucumber AfterAll");
		extentreport.endReport();
		driver.quit();
	}

@Given("Browser is up and running in loginpage")
public void browser_is_up_and_running_in_loginpage() throws Exception { 
	logger.info("inside Cucumber Given");
	driver.get("https://login.salesforce.com/"); // ok
	Thread.sleep(2000);
}

//TC1
@Test
@When("I enter wrong username")
public void i_enter_wrong_username() throws Exception {
	logger.info("inside TC1 Login Error Message");
	Map<String, String> propFileKeyValue; 
	propFileKeyValue = ReadPropertyFile.getValuesFromPropertyFile();
	LoginPage loginpage = new LoginPage(driver);
	loginpage.enterUsername(propFileKeyValue.get("invaliduserid"));// we enter User@gmail.com
}
@When("I click on clear password field")
public void i_click_on_clear_password_field() {
	LoginPage loginpage = new LoginPage(driver);
	loginpage.clearPassword();// we enter clear password 

}
@When("I click on the login button")
public void i_click_on_the_login_button() throws Exception {
	LoginPage loginpage = new LoginPage(driver);
	loginpage.clickLogin();
	Thread.sleep(4000);

}
@Then("Error Message should be displayed")
public void error_message_should_be_displayed() {
	String expectedMessage = "Please enter your password.";// Expected: Error message "Please enter your password."
	LoginPage loginpage = new LoginPage(driver);
	String actualMessage = loginpage.getErrorMessage();
	if (actualMessage.equalsIgnoreCase(expectedMessage)) {
		System.out.println("Error Message displayed - script passed"); 
	} else {
		System.out.println("Error Message non displayed - script failed");
	}
}
	
//TC2
@Test
@When("I enter correct username")
public void i_enter_correct_username() throws Exception {
	logger.info("inside TC2 Correct Username and Password");
	Map<String, String> propFileKeyValue; 
	propFileKeyValue = ReadPropertyFile.getValuesFromPropertyFile();
	LoginPage loginpage = new LoginPage(driver);
	loginpage.enterUsername(propFileKeyValue.get("userid"));
}
@When("I enter correct password")
public void i_enter_correct_password() throws Exception {
	Map<String, String> propFileKeyValue; 
	propFileKeyValue = ReadPropertyFile.getValuesFromPropertyFile();
	LoginPage loginpage = new LoginPage(driver);
	loginpage.enterPassword(propFileKeyValue.get("password"));
}

@Then("I should get the home page")
public void i_should_get_the_home_page() {
	//String expected = "TEST FAILURE - Home Page ~ Salesforce - Developer Edition";// TESTFAIL >> screenshots are generated in case of failure
	String expected = "Home Page ~ Salesforce - Developer Edition";
	HomePage homepage = new HomePage(driver);
	String actual = homepage.getHomePageTitle(driver);
	Assert.assertEquals(actual, expected, "login passed");
}

//TC3
@Test
@When("I click on logout")
public void i_click_on_logout() throws Exception {
	logger.info("inside TC3 Login with Remember Username");
	HomePage homepage = new HomePage(driver);
	homepage.clickUsernameDropdown();
	Thread.sleep(1000); 
	homepage.clicklogoutOptionDropdown();
	Thread.sleep(3000); 
}
@Then("I should get the Login page with correct username")
public void i_should_get_the_login_page_with_correct_username() throws Exception {
	Map<String, String> propFileKeyValue; 
	propFileKeyValue = ReadPropertyFile.getValuesFromPropertyFile();
	String expectedusername = propFileKeyValue.get("userid");// username should be displayed after logout - same as the one entered
	LoginPage loginpage = new LoginPage(driver);
	String actualusername = loginpage.getIDchecked();// we check again for the login username field that should match expected one / Id changed 
	System.out.println(actualusername);
	if (actualusername.equalsIgnoreCase(expectedusername)) {
		System.out.println("TC3 Remember Me script passed : Username not displayed after logout"); 
	} else {
		System.out.println("TC3 Remember Me script failed : Username not displayed after logout");
}
}

//TC4A

@When("I click on forgot password")
public void i_click_on_forgot_password() throws Exception {
	logger.info("inside TC4A Forgot Password");
	LoginPage loginpage = new LoginPage(driver);
	loginpage.clickForgotPassword();
	Thread.sleep(4000);
}

@Then("Password reset page is displayed")
public void password_reset_page_is_displayed() {
	String expectedForgotPage = "Forgot Your Password | Salesforce";
	ForgotPasswordPage forgotpasswordpage = new ForgotPasswordPage(driver);
	String actualForgotPage = forgotpasswordpage.getForgotPasswordPageTitle(driver);
	if (actualForgotPage.equalsIgnoreCase(expectedForgotPage)) {
	System.out.println("SalesForce forgot password page is displayed - PASS"); 
	} else {
				System.out.println("SalesForce forgot password page is NOT displayed - FAIL");
			}
}

@When("I enter correct username in forgot password page")
public void i_enter_correct_username_in_forgot_password_page() throws Exception {
	Map<String, String> propFileKeyValue; 
	propFileKeyValue = ReadPropertyFile.getValuesFromPropertyFile();
	ForgotPasswordPage forgotpasswordpage = new ForgotPasswordPage(driver);
		forgotpasswordpage.enterUsername((propFileKeyValue.get("userid")));
}
@When("I click on continue button")
public void i_click_on_continue_button() throws Exception {
	ForgotPasswordPage forgotpasswordpage = new ForgotPasswordPage(driver);
	forgotpasswordpage.clickContinueButton();
	Thread.sleep(2000); 
}
	
@Then("SalesForce forgot password page is displayed")
public void sales_force_forgot_password_page_is_displayed() {
	ForgotPasswordPage forgotpasswordpage = new ForgotPasswordPage(driver);
	String expectedForgotPage = "Forgot Your Password | Salesforce";
	String actualForgotPage = forgotpasswordpage.getForgotPasswordPageTitle(driver);
	if (actualForgotPage.equalsIgnoreCase(expectedForgotPage)) {
		System.out.println("SalesForce forgot password page is displayed - PASS"); 
	} else {
		System.out.println("SalesForce forgot password page is NOT displayed - FAIL");
	}
}

// TC4B
@When("I enter wrong password")
public void i_enter_wrong_password() throws Exception {
	logger.info("inside TC4B wrong Username and wrong Password");
	Map<String, String> propFileKeyValue; 
	propFileKeyValue = ReadPropertyFile.getValuesFromPropertyFile();
	LoginPage loginpage = new LoginPage(driver);
	loginpage.enterPassword(propFileKeyValue.get("invalidpassword"));// we enter incorrect password
}

@Then("Login failed Error Message is displayed")
public void login_failed_error_message_is_displayed() {
	String expected = "Your login attempt has failed. The username or password may be incorrect, or your location or login time may be restricted. Please contact the administrator at your company for help";
	LoginPage loginpage = new LoginPage(driver);
	String actual = loginpage.getErrorMessage();
	if (actual.equalsIgnoreCase(expected)) {
		System.out.println("Login script passed"); 
	} else {
		System.out.println("expected message: " + expected);
		System.out.println("actual message: " + actual);
		System.out.println("Login script failed");
	}
}
}

