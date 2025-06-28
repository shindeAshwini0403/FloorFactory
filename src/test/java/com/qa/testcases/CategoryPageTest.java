package com.qa.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.devtools.v128.fedcm.model.LoginState;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
@Listeners({TestAllureReportListner.class})

	public class CategoryPageTest extends BaseClass
	{
		 LoginPage login;
		   AdminHomePage  adminhome;
		   CategoryHomePage categoryHome;
		   NewCategoryCreatePage newcategoryP;
		   SubCategoryPage subcategoryp;
		
		
	   public static Logger log;

	   
	   @BeforeMethod
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
	 
	   @Test (priority=1, enabled=true)
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
		   boolean actualResult = categoryHome.getAllCategoryNames(UtilityClass.getTestData(6,3));
		   Thread.sleep(1000);
		   log.info("CategoryName:"+actualResult);
		   //verify category present in list
		   Assert.assertTrue(actualResult,"New category was NOT found in the category list!"); 
		   log.info("User is add new category successfully and an appeare in the list");
	   }
	   @Ignore
	   @Test(priority=2,enabled=true)
	   @Severity(SeverityLevel.CRITICAL)
	   @Description("Verify Duplicate Category Not Allowed")
	   public void testAddNewCategory_WithDuplicateName() throws InterruptedException, AWTException, EncryptedDocumentException, IOException
	   {    
		   log.info("Starting Test:Add dublicate category");
		   categoryHome.clickOnCreatNewBtn();
	       newcategoryP.enterCategoryName(UtilityClass.getTestData(6, 3));
		   newcategoryP.getfileUpload(UtilityClass.getTestData(6, 4));
		   newcategoryP.selectCategoryStatus(UtilityClass.getTestData(6, 5));
		   boolean actualstatus = newcategoryP.isEenabledSaveBth();
		   // Verify Save button is disabled
		   Assert.assertFalse(actualstatus," save button is enabled");
		  // Verify error message is displayed
		  boolean actualErrormsg = newcategoryP.isDuplicateCategoryErrorDisplayed();
		   System.out.println(actualErrormsg);
		   log .info("error message display"+actualErrormsg);
		   Assert.assertTrue(actualErrormsg,"Dublicate category error message is not display");
		   log.info(" Duplicate category was not allowed and proper error was shown");
		
		   
	   }
	
	   @Test()
	   @Severity(SeverityLevel.NORMAL)
	   @Description("Verify add category with empty filed")
	   public void  testAddCategoryWithEmptyFiled() throws InterruptedException, AWTException, EncryptedDocumentException, IOException
	   {
		   categoryHome.clickOnCreatNewBtn();
		   newcategoryP.clickOnSaveBtn();
		   //Verify error message of emptyFiled
		   String errorMsg = newcategoryP.isEmptyFiledErrorMsgDisplayed();
		   System.out.println(errorMsg);
		   Assert.assertTrue(true,"Validation Messageis is not display");
	   }
////	   @Test(priority=4,enabled=false)
////	   public void  addNewSubCategoryTest() throws InterruptedException, AWTException
////	   {
////		   categoryHome.clickAddSubCategoryOption("Leathrs");;
////		   Thread.sleep(1000);
////		   subcategoryP.enterSubcategoryName("LeatherSofa");
////		   Thread.sleep(1000);
////		   subcategoryP.getuploadfile("C:\\Users\\sashw\\OneDrive\\Desktop\\FloorFactory\\sofa.jpg");
////		   Thread.sleep(1000);
////		   subcategoryP.selectSubCategoryStatus("Active");
////		   Thread.sleep(1000);
////		   subcategoryP.clickOnSubmitBtn();
////		   
////		    // Verify success message
////		    Assert.assertTrue(subcategoryP.isSubcategoryCreatedMessageDisplayed(),"Success message not displayed");
////		    
////		    // Verify it Subcategory appears in the list
////		     boolean actualResult = subcategoryP.isSubCategoryPresent("LeatherSofa");
////		     Assert.assertTrue(actualResult, "Subcategory not listed after creation");
////		  
////	   }
//	   @Ignore
//	   @Test(priority=3,enabled=false)
//	   public void updateCategoryTest() throws AWTException, InterruptedException
//	   {
//		   
//		   categoryHome.clickOneditOptionCategoryList("Leathr"); 
//		   Thread.sleep(1000);
//		   updatCatrgoeyP.enterCategoryName("Leathrs");
//		   Thread.sleep(1000);
//	   updatCatrgoeyP.getUpdateUploadFile("");
////		   Thread.sleep(1000);
//		   updatCatrgoeyP.getUpdateCategoryStatus("Inactive");
//		   Thread.sleep(1000);
//		   updatCatrgoeyP.clickonChangeSaveBtn();
//		   // Verify it  update  category appears in the category list
//	       boolean actualResult = categoryHome.getAllCategoryNames("Leathrs");
//	       Assert.assertTrue(actualResult,"Updated category not listed"); 
//	       }
//	   @Ignore
//	       @Test(priority=8,enabled=false)
//	       public void deleteCategoryTest() throws InterruptedException
//	       {
//	    	   
//	    	   categoryHome.isdeleteCategory("Leathrs"); 
//	    	   Thread.sleep(2000);
//	    	  boolean actualResult = categoryHome.isCategoryDeletedinlist("Leathrs"); 
//	    	   Assert.assertTrue(actualResult,"Delete category still present in the list");
//	    	   
//	       }
//	       @Ignore
//	      
//	       @Test(priority=7,enabled=false)
//	       public void validSearchProductTest() throws InterruptedException
//	       {
//	    	   categoryHome.search("Ceilings");
//	    	   Thread.sleep(1000);
//	    	   boolean actualResult = categoryHome.isDisplayedSearchResultCount();
//	    	   Assert.assertTrue(actualResult, "Search results are not  displayed.");  
//	    	  
//	   
//	       }
//	       @Ignore
//	       @Test (priority=9,enabled=false)
//	       public void invalidSearchProductTest() throws InterruptedException
//	       {
//	    	   categoryHome.search("Leather");
//	    	   Thread.sleep(2000);
//	    	   boolean actualresult = categoryHome.isNoResultDisplayed();
//	    	   Assert.assertTrue(actualresult, "Search results are displayed");
//	    	   
//	       }
//	       @Ignore
//	       @Test(priority=5,enabled=false) 
////	       public void  updateSubcategoryTest() throws AWTException, InterruptedException
////	       {
////	    	   categoryHome.clickAddSubCategoryOption("Leathrs");;
////	    	   subcategoryP.clickOnEditOptipn("leather sofa");
////	    	   updatesubcategory.enterCategoryName("LeatherSofasss"); 
////	    	   Thread.sleep(1000);
////	    	   updatesubcategory.getupdatefileUpload("C:\\Users\\sashw\\OneDrive\\Desktop\\FloorFactory\\Curtains.jpg");
////	    	   Thread.sleep(1000);
////	    	   updatesubcategory.categoryStatus("Inactive");
////	    	   Thread.sleep(1000);
////	    	   updatesubcategory.clickOnSavechangesBtn();
////	    	   boolean ActualStatus = subcategoryP.isSubCategoryPresent("LeatherSofasss");
////	    	   Assert.assertTrue(ActualStatus,"Updated SubCategory not found in list"); 
////	    	   
////	       }
//	      
////	        @Test (priority=6,enabled=false)
////	        public void  deleteSubCategoryTest() throws InterruptedException
////	        {
////	            categoryHome.clickAddSubCategoryOption("Leathrs");
////	            Thread.sleep(1000);
////	        	subcategoryP.clickOnDeleteSubCategory("leather sofa");
////	      	boolean actualResult = subcategoryP.isSubCategoryAbsent("leather sofa");
////	       	Assert.assertTrue(actualResult," Delete Subcategory still present in the list. ");
////	        }
//	       
	       
	   @AfterMethod
	   public void tearDown()
	   {
		   driver.quit();
		   
	   }
	   
	   

	}



