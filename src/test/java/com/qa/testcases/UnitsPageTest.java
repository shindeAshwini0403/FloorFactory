package com.qa.testcases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.AdminHomePage;
import com.qa.pages.BrandPage;
import com.qa.pages.LoginPage;
import com.qa.pages.UnitsPage;
import com.qa.util.BaseClass;
import com.qa.util.UtilityClass;

public class UnitsPageTest extends BaseClass
{
	AdminHomePage admin;
	LoginPage login;
	UnitsPage units;
	@BeforeMethod(alwaysRun=true)
   public void setUp() throws IOException, Exception
   {
		initializeBrowser(UtilityClass.getPFData("BrowserName"));
		admin=new AdminHomePage();
		login=new LoginPage();
		units=new UnitsPage();
		login.login("admin@gmail.com", "123456");
		admin.clickOnUnitsModuleAdminHomepage();
		Thread.sleep(2000);
		
   }
	
	@Test(groups= {"smoke"})
	public void addNewUnits() throws InterruptedException
	{
		units.clickOnAddnewUnit();
		units.enterUnitsName("unites");
		units.selectstatus("Active");
		units.clickOnSaveBtn();
		
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}

}
