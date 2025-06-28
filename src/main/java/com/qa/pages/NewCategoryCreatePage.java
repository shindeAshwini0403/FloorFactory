package com.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.util.BaseClass;

import io.qameta.allure.Step;

	public class NewCategoryCreatePage extends BaseClass 
	{
	   @FindBy(id="category")public WebElement categoryNamInpute; 
	   @FindBy(xpath="(//input[contains(@type,'file')])[1]")private WebElement categoryUploadImg;
	   @FindBy(xpath=" //select[@class=\"select select2-hidden-accessible\"]")private WebElement CategoryStatus;
	   @FindBy(xpath="(//select[@class=\"select select2-hidden-accessible\"])[3]")private WebElement categoryoption;
	   @FindBy(id="submitBtn")private WebElement saveBtn;
	   @FindBy(xpath="(//button[@class=\"btn btn-cancel me-2\"])[1]")private WebElement  cancelBtn;
	   @FindBy(id="UpdateBtn")private WebElement saveChangesBtn;
	   @FindBy(xpath="//p[@id='message']")private WebElement duplicateErrorMsg;
	 
	 //select[@class="select select2-hidden-accessible"]
	    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		//constructor
		public NewCategoryCreatePage()
		{
			 PageFactory.initElements(driver,this);
		}
	
	    @Step("Enter Categoryname {0}")
	    public void enterCategoryName(String categoryName) throws InterruptedException
	    {
			categoryNamInpute.sendKeys(categoryName);
	    }
	  
		@Step("fileUpload{0}")
		public void getfileUpload(String file) throws AWTException
		{
			JavascriptExecutor js=(JavascriptExecutor)driver;  
	    	js.executeScript("arguments[0].click();",categoryUploadImg);
			//Robat class
	         Robot robot=new Robot();
	         robot.delay(2000);
	    	//Type of filepath
	    	StringSelection filepath=new StringSelection(file);
	    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath, filepath);
	    	//press control v
	    	robot.keyPress(KeyEvent.VK_CONTROL);
	    	robot.keyPress(KeyEvent.VK_V);
	    	robot.keyRelease(KeyEvent.VK_V);
	    	robot.keyRelease(KeyEvent.VK_CONTROL);
	    	//pressEnter
	    	robot.keyPress(KeyEvent.VK_ENTER);
	    	robot.keyRelease(KeyEvent.VK_ENTER);
  
			 	  	
		}
		
	   @Step("Click Status option")
	    public void clickonstatusCategory()
	    {
	    	CategoryStatus.click();
	    }
	   
	   @Step("select category status{0}")
	    public void selectCategoryStatus(String CategoryStatus)
	    { 
	    	 Select select=new Select(categoryoption);
	    	select.selectByVisibleText(CategoryStatus);
	    }
	    
	    @Step("Click Save buttons")
	    public void  clickOnSaveBtn()
	    {
	    	saveBtn .click(); 
	    }
	    
	    @Step("Click cancel btn")
	    public void  clickOnCancelBtn()
	    {
	    	cancelBtn.click();
	    }
	    
	    @Step("Dublicate category error message")
	    public boolean isDuplicateCategoryErrorDisplayed()
	    {
	    	
	    	 return duplicateErrorMsg.getText().contains("This category is already used, try another.");
	    	
	    }
	    @Step
	    public boolean isEenabledSaveBth()
		  {
			return saveBtn.isEnabled() ;
			  
		  }
	    @Step
	    public   String isEmptyFiledErrorMsgDisplayed()
	    { 
	    	  try {
	    	        JavascriptExecutor js = (JavascriptExecutor) driver;
	    	        return (String) js.executeScript("return arguments[0].validationMessage;",CategoryStatus );
	    	    } catch (Exception e) {
	    	        return "Validation message not retrievable via JS.";
	    	    }
	    	
	    }
		 
	
	    
	  ////span[@id='select2-status-9x-container']
	    
	    

		

	}



