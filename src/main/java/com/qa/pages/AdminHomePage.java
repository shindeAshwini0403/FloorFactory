package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.BaseClass;

public class AdminHomePage extends BaseClass
{
    @FindBy(xpath="//span[text()=\"Category\"]")private WebElement category;
    @FindBy(xpath="//ul/li//span[text()=\"Brands\"]")private WebElement Brand;
    @FindBy(xpath="//ul//li/a//span[text()=\"Units\"]")private WebElement Units;
	
	public AdminHomePage()
	{
		PageFactory.initElements(driver,this);
	}
	public  String  getCurrentUrlAdminHomePage()
	{
		return driver.getCurrentUrl();
	}
	//click on categoryModule
	public void clickOnCategoryModuleAdminHomePage()
	{
		category.click();	
	}
	public  void clickOnBrandModuleAdminHomepage() 
	{
		Brand.click();
		
	}
	
	public  void clickOnUnitsModuleAdminHomepage() 
	{
		Units.click();
		
	}
}

