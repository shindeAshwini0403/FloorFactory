package com.qa.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.extentreports.TestAllureReportListner;
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



public class SubCategoryPageTest  extends BaseClass
{      public static Logger log;
	   LoginPage login;
	   AdminHomePage  adminhome;
	   CategoryHomePage categoryHome;
	   NewCategoryCreatePage newcategoryP;
	   SubCategoryPage subcategoryp;
	   WebDriverWait wait;
	  
	   @BeforeMethod(alwaysRun=true)
	   public void  setUp() throws Exception
	   {
		   initializeBrowser("Chrome");
		   log= LogManager.getLogger(CategoryPageTest.class);
		   login=new LoginPage();
		   adminhome=new AdminHomePage();
		   categoryHome=new CategoryHomePage();
		   newcategoryP=new NewCategoryCreatePage();
		   subcategoryp=new SubCategoryPage();
		   login.login(UtilityClass.getTestData(1, 1), UtilityClass.getTestData(1, 2) );
		   adminhome.clickOnCategoryModuleAdminHomePage();
		   
		 
	     }
	 
	   @Test(priority=2,enabled=true) 
	   @Severity(SeverityLevel.NORMAL)
	   @Description("Verify user can update a subcategory with new details")
       public void  testUpdatesupcategoryDetails() throws AWTException, InterruptedException, EncryptedDocumentException, IOException
       {
		   log.info("Start starting test:testUpdateSubCategoryDetails");
		   categoryHome.clickAddSubCategoryOption(UtilityClass.getTestData(6,3));
		  
    	   subcategoryp.clickOnEditOptipn(UtilityClass.getTestData(18,3));
    	
    	   subcategoryp.getUpdateSubcategoryName(UtilityClass.getTestData(24,3)); 
    	 
    	   subcategoryp.getUpdatefileUpload(UtilityClass.getTestData(24,4));
    	   subcategoryp.getUpdateCategoryStatus(UtilityClass.getTestData(24,5));
    	   subcategoryp.clickOnSavechangesBtn();
    	   boolean ActualStatus = subcategoryp.isSubCategoryPresent(UtilityClass.getTestData(24,3));
    	   log.info("Subcategory update status: " + ActualStatus);

    	   Assert.assertTrue(ActualStatus,"Updated SubCategory not found in list"); 
    	   log.info("Updated existing category successfully");

     }
     @Ignore
        @Test (priority=3,enabled=true)
        @Severity(SeverityLevel.CRITICAL)
        @Description("Verify that a subcategory can be deleted successfully")
        public void  deleteSubCategoryTest() throws InterruptedException, EncryptedDocumentException, IOException
        {
        	log.info("Starting test: deleteSubCategoryTest");
        	  categoryHome.clickAddSubCategoryOption(UtilityClass.getTestData(6,3));
           
        	 subcategoryp.clickOnDeleteSubCategory("SDF");
      	     boolean actualResult = subcategoryp.isSubCategoryAbsent("SDF");
      	     log .info("Delete SubCategory Result"+actualResult);
          	 Assert.assertFalse(actualResult," Delete Subcategory still present in the list. ");
          	 log.info("Deleted existing category successfully");
        }

        @Test(priority=1,enabled=true)
        @Severity(SeverityLevel.BLOCKER)
        @Description("Verify that a new subcategory can be added successfully")
 	   public void  addNewSubCategoryTest() throws InterruptedException, AWTException, EncryptedDocumentException, IOException
 	   {
        	 log.info("Starting test: addNewSubCategoryTest");
 		    categoryHome.clickAddSubCategoryOption(UtilityClass.getTestData(6,3));
 		    subcategoryp.enterSubCategoryName(UtilityClass.getTestData(18,3));
 		    subcategoryp.getuploadfile(UtilityClass.getTestData(18,4));
 		    subcategoryp.selectSubCategoryStatus(UtilityClass.getTestData(18,5));
 		    subcategoryp.clickOnSubmitBtn();
 		   
 		      // Verify success message
 		      Assert.assertTrue(subcategoryp.isSubcategoryCreatedMessageDisplayed(),"Success message not displayed");
 		 	log.info("Verifying success message after adding subcategory");
 		      // Verify it Subcategory appears in the list
 		     boolean actualResult = subcategoryp.isSubCategoryPresent(UtilityClass.getTestData(18,3));
 		     Assert.assertTrue(actualResult, "Subcategory not listed after creation");
 			log.info("added successfully and verified in list");

  }
        @Ignore
        @Test
        public  void testSearchFunctionalitywithValidInput()
        {
        	
        	
        }
       
	 
    	@AfterMethod(alwaysRun=true)
        public void tearDown()
        {
          driver.quit();	
        }
       

}
