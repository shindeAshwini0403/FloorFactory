package com.qa.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.util.BaseClass;

public class UnitsPage  extends BaseClass
{
  @FindBy(xpath="//a[normalize-space()='Add New Unit']")private WebElement abbnewUnitBtn;
  @FindBy(xpath="//input[@id='unit']")private WebElement unitInput;
  @FindBy(xpath="(//select[@name='status'])[1]")private WebElement Status;
  @FindBy(xpath="//button[@id='submitBtn']")private WebElement saveBtn;
  @FindBy(xpath="//a[@class=\"me-2 p-2\"]")private List<WebElement> editButton;
  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  public UnitsPage()
	{
		PageFactory.initElements(driver, this);
		
	}
   public void clickOnAddnewUnit()
   {
	  
	WebElement button = wait.until(ExpectedConditions.elementToBeClickable(abbnewUnitBtn));
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
   }
	   
   public void enterUnitsName(String unitsName)
   {
	  wait.until(ExpectedConditions.visibilityOf(unitInput));
	  unitInput.sendKeys(unitsName);
   }
   public void selectstatus(String status)
   {
	   Select select=new Select(Status);
	   select.selectByVisibleText(status);
   }
   public void clickOnSaveBtn()
   {
		WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
	   save.click();
   }
   public void  ClickonEditOption(String unitName)
   {
	 for(WebElement list:editButton)  
	 {
		 if(unitName.equals(unitName))
		 {
			 list.click();
		 }
	 }
   }
   
   
}
