package com.qa.testcases;

import java.awt.AWTException;
import java.time.Duration;


import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.util.BaseClass;
import com.qa.util.UtilityClass;

//public class SubCategoryPageTest  extends BaseClass
//{
//	   LoginPage login;
//	   AdminHomePage  adminhome;
//	   CategoryHomePage categoryHome;
//	   NewCategoryCreatePage newcategoryP;
//	   SubCategoryPage subcategoryp;
//	  
//	   @BeforeMethod
//	   public void  setUp() throws Exception
//	   {
//		   initializeBrowser("Edge");
//		   login=new LoginPage();
//		   adminhome=new AdminHomePage();
//		   categoryHome=new CategoryHomePage();
//		   newcategoryP=new NewCategoryCreatePage();
//		   subcategoryp=new SubCategoryPage();
//		   login.login(UtilityClass.getTestData(1, 1), UtilityClass.getTestData(1, 2) );
//		   adminhome.clickOnCategoryModuleAdminHomePage();
//		 
//	     }
//	   @Test(priority=5,enabled=false) 
//       public void  updateSubcategoryTest() throws AWTException, InterruptedException
//       {
//    	   categoryHome.clickAddSubCategoryOption("Leathrs");;
//    	   subcategoryp.clickOnEditOptipn("leather sofa");
//    	   subcategoryp.enterSubcategoryName("LeatherSofasss"); 
//    	   Thread.sleep(1000);
//    	   subcategoryp.getupdatefileUpload("C:\\Users\\sashw\\OneDrive\\Desktop\\FloorFactory\\Curtains.jpg");
//    	   Thread.sleep(1000);
//    	   subcategoryp.categoryStatus("Inactive");
//    	   Thread.sleep(1000);
//    	   subcategoryp.clickOnSavechangesBtn();
//    	   boolean ActualStatus = subcategoryp.isSubCategoryPresent("LeatherSofasss");
//    	   Assert.assertTrue(ActualStatus,"Updated SubCategory not found in list"); 
//    	   
//       }
//        @Test (priority=6,enabled=false)
//        public void  deleteSubCategoryTest() throws InterruptedException
//        {
//            categoryHome.clickAddSubCategoryOption("Leathrs");
//            Thread.sleep(1000);
//        	subcategoryp.clickOnDeleteSubCategory("leather sofa");
//      	boolean actualResult = subcategoryp.isSubCategoryAbsent("leather sofa");
//       	Assert.assertTrue(actualResult," Delete Subcategory still present in the list. ");
//        }
//        @Test(priority=4,enabled=false)
// 	   public void  addNewSubCategoryTest() throws InterruptedException, AWTException
// 	   {
// 		   categoryHome.clickAddSubCategoryOption("Leathrs");;
// 		   Thread.sleep(1000);
// 		   subcategoryP.enterSubcategoryName("LeatherSofa");
// 		   Thread.sleep(1000);
// 		   subcategoryP.getuploadfile("C:\\Users\\sashw\\OneDrive\\Desktop\\FloorFactory\\sofa.jpg");
// 		   Thread.sleep(1000);
// 		   subcategoryP.selectSubCategoryStatus("Active");
// 		   Thread.sleep(1000);
// 		   subcategoryP.clickOnSubmitBtn();
// 		   
// 		    // Verify success message
// 		    Assert.assertTrue(subcategoryP.isSubcategoryCreatedMessageDisplayed(),"Success message not displayed");
// 		    
// 		    // Verify it Subcategory appears in the list
// 		     boolean actualResult = subcategoryP.isSubCategoryPresent("LeatherSofa");
// 		     Assert.assertTrue(actualResult, "Subcategory not listed after creation");
// 		  
// 	   }
//        
//       
//
//}
