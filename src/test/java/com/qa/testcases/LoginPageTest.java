package com.qa.testcases;

import java.io.IOException;

import javax.management.DescriptorKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.extentreports.TestAllureReportListner;
import com.qa.pages.AdminHomePage;
import com.qa.pages.LoginPage;
import com.qa.util.BaseClass;
import com.qa.util.UtilityClass;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@Listeners({TestAllureReportListner.class})
public class LoginPageTest extends BaseClass
{
	 // Logger instance for the class

	    public static Logger log;
		LoginPage login;
		AdminHomePage adminhomeP;
		// Initialize Log4j logs
		
		@BeforeMethod(alwaysRun = true)
		public void setUp() throws Exception
		{
			 log= LogManager.getLogger(LoginPageTest.class);
			 initializeBrowser(UtilityClass.getPFData("BrowserName"));
			// logger.info("Starting valid login test");
			login=new LoginPage();
			adminhomeP=new AdminHomePage();
		    
		}
		
		@Test(groups={"smoke"},priority=1,description="Verify login with valid crendintial",enabled=true)
		@Severity(SeverityLevel.BLOCKER)
		@Story("As a user, I should be able to login with correct credentials")
		public void testloginWithValidInput() throws EncryptedDocumentException, InterruptedException, IOException
		{
			     log.info("Starting  Test:valid login test");
			     login.login(UtilityClass.getTestData(1, 1), UtilityClass.getTestData(1, 2));
			     String actualURL = adminhomeP.getCurrentUrlAdminHomePage();
			     log.info("Actual url "+actualURL);
			     String expectedURL="https://floorfactory.omsdev.in/index";
			      Thread.sleep(1000);
			      Assert.assertEquals(actualURL, expectedURL,"Login failed: User was not redirected to Home Page");
			      log.info("User login and redirected to homepage successfully");
			     
			    
		}
		@Ignore
	    @Test(priority=2,description="Verify user login with Invalid  credentials",enabled=true)
	    @Severity(SeverityLevel.NORMAL)
		public void testloginWithInvalidData() throws InterruptedException, EncryptedDocumentException, IOException
		{
	    	log.info("Starting Test: with Invalid login test");
	    	login.login(UtilityClass.getTestData(2,1),UtilityClass.getTestData(2,2));
		     String actualURL = adminhomeP.getCurrentUrlAdminHomePage();
		     log.info("Actual url="+actualURL);
		     String expectedURL="https://floorfactory.omsdev.in/index";
		 	  // Assert user was not redirected to dashboard
		      Assert.assertNotEquals(actualURL, expectedURL,"user redirected to dashboard");
		      // Assert correct error message is diaplay
		      boolean actualStatus = login.geterrorMessage();
		      Assert.assertTrue( actualStatus,"Error message not displayed");
		      log.info("Login failed as expected and proper error message was shown");
			  
		}
		
	    @Test(priority=3,description="Verify user login with blank username and password")
	    @Severity(SeverityLevel.CRITICAL)
	    public void loginwithEmptyFiled() throws EncryptedDocumentException, InterruptedException, IOException
	    {
	    	log.info("starting test:  login with empty filed");
	    	login.login(UtilityClass.getTestData(3,1), UtilityClass.getTestData(3,2));
	    	
	    	String actualErrorMesg = login.gettErrorMessagetAlert();
	    	log.info("capture error message"+actualErrorMesg);
	    	String expectedErrorMessage="Please fill out this field.";
	    	Assert.assertEquals(actualErrorMesg, expectedErrorMessage," Error message not displayed for empty field");
	    	log.info("User cannot login with empty fields, and proper error is shown");	 
	    }
		
	    @Test(priority=4,description="Veriry  password toggle Functionslity")
	    @Severity(SeverityLevel.NORMAL)
	    public void testTogglePasswordVisibility_WhenUserClicksEyeIcon() throws EncryptedDocumentException, IOException
	    {
	    	log.info("Starting Test=Verify Password toggle show/hide functionality");
	    	login.clickTogglePasswordVissibility(UtilityClass.getTestData(1, 1),UtilityClass.getTestData(1, 2));
	    	String pwdFiledtype = login.getPasswordFiledType();
	    	log.info("Password text"+pwdFiledtype);
	    	Assert.assertEquals(pwdFiledtype, "text","Password field should be visible after toggling");
	    	log.info("Password is visible after clicking toggle");
	    	
	    	
	    }
		
	    @Test(priority=5,description=" Verify logo is visible on the page")
		
	    @Severity(SeverityLevel.NORMAL)
         public void testLogoisDisplay()   
	     {  
	    	log.info("Starting Test: Logo visibility check");
	          boolean actualStatus= login.isdiaplaylogo();	 
	          log.info("Logo displayed status"+actualStatus);
	          Assert.assertTrue(actualStatus,"Logo is not displayed on the page");
	          log.info("Logo is visible on the login page");
	          
	
	      }		
		@AfterMethod(alwaysRun = true)
		public void tearDown() throws Exception
		{
			driver.quit();
		}
		


}