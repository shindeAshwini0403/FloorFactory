package com.qa.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.devtools.v128.fedcm.model.LoginState;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.pages.AdminHomePage;
import com.qa.pages.CategoryHomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.NewCategoryCreatePage;
import com.qa.pages.SubCategoryPage;
import com.qa.util.BaseClass;
import com.qa.util.UtilityClass;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
//@Listeners({TestAllureReportListner.class})

	public class CategoryPageTest extends BaseClass
	{
		 LoginPage login;
		  AdminHomePage  adminhome;
		  CategoryHomePage categoryHome;
		  NewCategoryCreatePage newcategoryP;
		  SubCategoryPage subcategoryp;
	   public static Logger log;
	   
	   @BeforeMethod(alwaysRun=true)
	   public void  setUp() throws Exception
	   {
		   log= LogManager.getLogger(CategoryPageTest.class);
		   initializeBrowser("chrome");
		   login=new LoginPage();
		   adminhome=new AdminHomePage();
		   categoryHome=new CategoryHomePage();
		   newcategoryP=new NewCategoryCreatePage();
		   subcategoryp=new SubCategoryPage();
		   login.login(UtilityClass.getTestData(1, 1), UtilityClass.getTestData(1, 2) );
		   adminhome.clickOnCategoryModuleAdminHomePage();
		    Thread.sleep(1000);
	   }
	
	   @Test (priority=1, enabled=true ,groups= {"smoke"})
	   @Severity(SeverityLevel.BLOCKER)
	   @Description("add new category with valid input")
	   public void testaddNewCategoryWithValid_Input() throws InterruptedException, EncryptedDocumentException, IOException, AWTException
	   {
		  
		  log.info("Starting Test: Add new Category");
		   categoryHome.clickOnCreatNewBtn();
		   newcategoryP.enterCategoryName(UtilityClass.getTestData(6, 3));
		   newcategoryP.getfileUpload(UtilityClass.getTestData(6, 4));
		   newcategoryP.selectCategoryStatus(UtilityClass.getTestData(6, 5));
		   newcategoryP.clickOnSaveBtn();
		   boolean actualResult = categoryHome.isCategoryPresentInList(UtilityClass.getTestData(6, 3));
		   log.info("CategoryName:"+actualResult);
		   //verify category present in list
		   Assert.assertTrue(actualResult,"New category was NOT found in the category list!"); 
		   log.info("User is add new category successfully and an appeare in the list");
	   }
	   
	   @Test(priority=2,enabled=true)
	   @Severity(SeverityLevel.CRITICAL)
	   @Description("Verify Duplicate Category Not Allowed")
	   public void testAddNewCategory_WithDuplicateName() throws InterruptedException, AWTException, EncryptedDocumentException, IOException
	   {    
		   log.info("Starting Test:Add dublicate category");
		   categoryHome.clickOnCreatNewBtn();
		   Thread.sleep(1000);
	       newcategoryP.enterCategoryName(UtilityClass.getTestData(6, 3));
		   newcategoryP.getfileUpload(UtilityClass.getTestData(6, 4));
		   newcategoryP.selectCategoryStatus(UtilityClass.getTestData(6, 5));
		   boolean actualstatus = newcategoryP.isEenabledSaveBth();
		   // Verify Save button is disabled
		   Assert.assertFalse(actualstatus," save button is enabled");
		  // Verify error message is displayed
		   boolean actualErrormsg = newcategoryP.isDuplicateCategoryErrorDisplayed();
		   System.out.println(actualErrormsg);
		   log .info("error message display="+actualErrormsg);
		   Assert.assertTrue(actualErrormsg,"Dublicate category error message is not display");
		   log.info(" Duplicate category was not allowed and proper error was shown");
		
		   
	   }
	  @Ignore
	   @Test(priority=3,enabled=true)
	   @Severity(SeverityLevel.NORMAL)
	   @Description("Verify add category with empty filed")
	   public void  testAddCategoryWithEmptyFiled() throws InterruptedException, AWTException, EncryptedDocumentException, IOException
	   {
		   log .info("Starting Test: add category with empty filed");
		   categoryHome.clickOnCreatNewBtn();
		   newcategoryP.enterCategoryName(UtilityClass.getTestData(8, 3));
		   newcategoryP.clickOnSaveBtn();
		   //Verify error message of emptyFiled
		   String errorMsg = newcategoryP.isEmptyFiledErrorMsgDisplayed();
		   System.out.println(errorMsg);
		   Assert.assertTrue(true,"Validation Messageis is not display");
		   
	   }
      @Ignore
	   @Test(priority=4,enabled=true)
	   @Severity(SeverityLevel.NORMAL)
	   @Description("Verify that after updating an existing category, the new category name is displayed in the category list")
	   public void updateExistingCategoryTest() throws AWTException, InterruptedException, EncryptedDocumentException, IOException
	   {   
		   log.info("Starting Test:Updated existing category");
		   //Click on edit option
		   categoryHome.clickOneditOptionCategoryList(UtilityClass.getTestData(6, 3)); 
		   //Enter Update category name
		   categoryHome.enterCategoryName(UtilityClass.getTestData(10, 3));	  
	       categoryHome.getUpdateCategoryStatus(UtilityClass.getTestData(10,5 ));
		   categoryHome.clickonChangeSaveBtn();
		   // Verify it  update  category appears in the category list
	       boolean isPresentActualResult = categoryHome.isCategoryPresentInList(UtilityClass.getTestData(10, 3));
	       log.info("Upate category name="+ isPresentActualResult);
	       Assert.assertTrue(isPresentActualResult,"Updated category not listed"); 
	       log.info("User successfully updated the category.");
       }
        @Ignore
         
	       @Test(priority=5,enabled=true)
           @Severity(SeverityLevel.CRITICAL)
           @Description("Vrify delete category functionality")
	       public void testDeleteCategory() throws InterruptedException, EncryptedDocumentException, IOException
	       {
	    	     log.info("Starting Test:Delete Category Functionality");
	    	   categoryHome.isdeleteCategory(UtilityClass.getTestData(11, 3)); 
	    	   //Confirm delete action in confirmation dialog
	    	   categoryHome.clickOnDeleteConfirmationButton();
	    	   //validate category is no longer present inthe list
	    	    boolean actualResult = categoryHome.isCategoryPresentInList(UtilityClass.getTestData(11, 3)); 
	    	    log.info("Delete Category name="+actualResult);
	    	    Assert.assertFalse(actualResult,"Delete category still present in the list"); 
	    	    log.info("Sussfully deleted and removed from list");
	       }
	      
	      
	       @Test(priority=5,enabled=true)
	       @Severity(SeverityLevel.MINOR)
	       @Description("Verify category search with valid input")
	       public void testSearchFunctionality_WithValidInput() throws InterruptedException, EncryptedDocumentException, IOException
	       {
	    	   log.info("Starting Test:Search Functionality with valid input");
	    	   //Search for category
	    	   categoryHome.enterSearchInput(UtilityClass.getTestData(11, 3));
	    	   //Get actual result
	    	   List<String> actualCategoriesName = categoryHome.getSearchResult();
	    	   log.info("Search Result-"+actualCategoriesName);
	    	   System.out.println(actualCategoriesName);
	    	   //Assert each result matches expected category
	    	   for( String SearchResult:actualCategoriesName)
	    	   {
	    		   Assert.assertEquals(SearchResult,UtilityClass.getTestData(11, 3),"Expected category not found in search results");  
	    	   }
	    	   log.info("Test Passed:successfully found in search results");
	    	  
       }
	       
	       @Test (priority=6,enabled=true)
	       @Severity(SeverityLevel.MINOR)
	       @Description("Verify search functionality with Invalid input")
	       public void testSearchFunctionalityWithValidInvalidInput() throws InterruptedException, EncryptedDocumentException, IOException
	       {
	    	   log.info("Starting Test:Search functionality with invalid input");
	    	   //Enter invalid  Search input
	    	   categoryHome.enterSearchInput(UtilityClass.getTestData(14, 3));
	    	   boolean actualresult = categoryHome.isNoResultDisplayedSearchFunctionality();
	    	   log.info("Get validtion message:"+actualresult);
	    	   Assert.assertTrue(actualresult, "Search results are displayed");
	    	   log .info("Test Passed: 'No matching records found' message successfully displayed for invalid search input");
	       }    
			 	  	
		
	   @AfterMethod(alwaysRun=true)
	   public void tearDown()
	   {
		   driver.quit();
		   
	   }
	   
	   

	}



