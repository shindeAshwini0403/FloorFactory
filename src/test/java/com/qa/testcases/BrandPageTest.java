package com.qa.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.qa.pages.AdminHomePage;
import com.qa.pages.BrandPage;
import com.qa.pages.LoginPage;
import com.qa.util.BaseClass;
import com.qa.util.UtilityClass;

public class BrandPageTest  extends BaseClass
{
	AdminHomePage admin;
	LoginPage login;
	BrandPage brand;
	@BeforeMethod
   public void setUp() throws IOException, Exception
   {
		initializeBrowser(UtilityClass.getPFData("BrowserName"));
		admin=new AdminHomePage();
		login=new LoginPage();
		brand=new BrandPage();
		login.login("admin@gmail.com", "123456");
		Thread.sleep(1000);
		admin.clickOnBrandModuleAdminHomepage();
		Thread.sleep(1000);
   }
   @Ignore
	@Test
	public void testAddNewBrandWithValidInput() throws AWTException, InterruptedException
	{  
//		brand.clickNewBrandBtn();
//		brand.enterBrandName("Brand1");
//		String filepath="C:\\Users\\sashw\\OneDrive\\Desktop\\FloorFactory\\New folder\\BrandLogo.jpg";
//		brand.getUploadBrandLogoNewBrand(filepath);
//		brand.selectStatus("Active");
//		brand.clickOnSaveBtn();
//		
	}
	  @Ignore
      @Test 
      public void testAddNewBrandWithDuplicateName_ShouldShowValidationError()
      {
    	  
      
	  }
	  @Ignore
      @Test
      public void testAddNewBrandWithEmptyFiled()
      {
    	  
      }
	
      @Test
      public void testEditExistingBrandDetailsSuccessfully() throws InterruptedException, AWTException 
      {
    	  Thread.sleep(1000);
    	 brand.clickonEditOption("Brand1");
    	 Thread.sleep(1000);
         brand.enterUpdateBrandName("Brand2");
         String filePath1="/FloorFactory/src/test/resources/Images/BrandLogo.jpg";
         brand.updateBrandLogo(filePath1);
         brand.updateStatus("Inactive");
    	 
    	  
      }
	  @Ignore
      @Test
      public void testEditBrandDetailsWithoutAnyModification_ShouldSaveSuccessfully()
      {
    	  
      }
	  @Ignore
      @Test
      public void testDeleteExistingBrandSuccessfully()
      {
    	  
      }
  	@Ignore
      @Test
     public void testDeleteBrand_VerifyConfirmationPopupAppears()
     {
    	  
     }
	@Ignore
      @Test
      public void testCancelDeleteAction_ShouldNotRemoveBrand()
      {
    	  
      }
	@Ignore
      @Test
      public void testSearchBrandByValidName()
      {
    	  
      }
  	@Ignore
      @Test
      public void testSearchBrandByPartialName_ShouldReturnMatchingResults()
      {
    	  
      }
	@Ignore
      @Test
      public void testSearchBrandWithInvalidName_ShouldShowNoResultsMessage()
      {
    	  
      }
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
