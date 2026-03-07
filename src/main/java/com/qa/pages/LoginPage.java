package com.qa.pages;


import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.BaseClass;

import io.qameta.allure.Step;

public class LoginPage extends BaseClass
{
	@FindBy(xpath="//input[@name=\"email\"]") private  WebElement emailAddress;
	@FindBy(xpath="//input[@name=\"password\"]")private WebElement Pwd;
	@FindBy(xpath="//button[@type=\"submit\"]")private WebElement singBtn;
	@FindBy(xpath="//div[@class='alert alert-danger']")private WebElement errorMessageAler;
	@FindBy(xpath="//div[@class=\"login-logo logo-normal\"]")private WebElement logo;
	@FindBy(xpath="//span[@class='fas toggle-password fa-eye-slash']") private WebElement togglePasswordBtn;
	
	//constructor
	public LoginPage()
	{
		PageFactory.initElements(driver,this);
	}
	  @Step
    public AdminHomePage login(String userNamr,String password) throws InterruptedException
    {     
    	emailAddress.clear();
    	emailAddress.sendKeys(userNamr);
    	Pwd.clear();
    	Pwd.sendKeys(password);
    	singBtn.click();
    	return new  AdminHomePage();
           	
    }
    @Step(" verify logo is display")
    public boolean isdiaplaylogo()
    {
    	return logo.isDisplayed();
    	
    }
    @Step("Getting error message")
    public boolean  geterrorMessage()
    {
    	 return errorMessageAler.isDisplayed()&& errorMessageAler.getText().equalsIgnoreCase("Invalid Credentials, Login Failed");

    }
    @Step("Getting error message alert for email address")
    public String gettErrorMessagetAlert()
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		String message = (String) js.executeScript("return arguments[0].validationMessage;", emailAddress);
    	System.out.println("Browser validation message: " + message);
		return message;
    	
    }
    @Step("Click password visibility toggle icon")
    public void clickTogglePasswordVissibility(String usernamr,String password)
    {
    	emailAddress.sendKeys(usernamr);
    	Pwd.sendKeys(password);
    	
    	togglePasswordBtn.click();
    	
    }
    @Step("Getting password filed type text")
    public String getPasswordFiledType()
    {
    	return Pwd.getAttribute("type");
    	
    }
    
    
    
	
	

	
}