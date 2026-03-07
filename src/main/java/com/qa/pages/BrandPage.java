package com.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.util.BaseClass;

public class BrandPage extends BaseClass 
{
////a[normalize-space()='Add New Brand']	//a[contains(@class,'btn btn-added')]
	@FindBy(xpath="//a[normalize-space()='Add New Brand']")private WebElement addnewbrandBtn;
	@FindBy(css="tbody tr:nth-child(1) td:nth-child(1) div:nth-child(1) a:nth-child(1)") private  WebElement editBrandOption;
	@FindBy(xpath="//table[@id=\"DataTables_Table_0\"]//tbody//tr//div/a[2]")private WebElement  deleteBrandOption;
	//searchInput
	@FindBy(xpath="//input[@class=\"form-control form-control-sm\"]")private WebElement  searchInputBoxBrand;
	@FindBy(xpath="//table//tbody//tr/td[text()=\"No matching records found\"]")private WebElement noResultsMsg;
	//NewBrandName
	@FindBy(id="brand")private WebElement newBrandName;
	@FindBy(xpath="(//div//input[contains(@type,'file')])[1]")private WebElement uploadnewBrandLogo;
	@FindBy(id="submitBtn")private WebElement  saveBtnnewBrand; 
	@FindBy(xpath="(//select[@class=\"form-control\"])[1]")private WebElement brandStatus;
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Cancel'])[2]")private WebElement cancelBtnNewBrand;
	//UpdateBrandName 
	@FindBy(xpath="//input[@id='edit-name']")private WebElement updateBrandName;
	@FindBy(css="#edit-logo")private WebElement  updatelogoInput;
	@FindBy(xpath="//select[@id='edit-status']")private WebElement  updateBrandstatus;
	@FindBy(xpath="//button[text()=\"Save Changes\"]")private WebElement  saveChangesBtn;
	@FindBy(xpath="(//button[contains(@class, 'btn btn-cancel me-2')])[2]")private WebElement  editCancelBtn;
	//BrandList
	@FindBy(xpath="//div[@id='DataTables_Table_0_wrapper']//tbody//tr[@class=\"odd\"]//td[2]")private List<WebElement> brandNameList;
	@FindBy(xpath="//small[text()=\"This brand is already registered!\"]")private WebElement duplicateBrandError;
	// Confirmation popup - Confirm button
	@FindBy(xpath="//button[text()=\"Yes, delete it!\"]")private WebElement DeleteconfirmPopusButton;
	@FindBy(xpath="//button[@class=\"swal2-cancel swal2-styled\"]")private WebElement cancelBtnPopus;
	// Success message after deletion (optional)
	@FindBy(xpath="//div[text()=\"Brand has been deleted.\"]")private WebElement deleteConfirmatiomsg;
	@FindBy(xpath="//div[@class=\"alert alert-success\"]")private  WebElement updateSuccessMsg;
	  WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
	
	public BrandPage()
	{
		PageFactory.initElements(driver, this);
		
	}
	//click on cancel Btn
	public void ClickOnCancelBtnNewBrand()
	{
		cancelBtnNewBrand.click();
		
	}
	public String validateSaveBtnColornNewBrand()
	{
		 return saveBtnnewBrand.getCssValue("background");
	}
	//validate error message newbrand 
	public String getDublicateBrandErrorMessageNewBrand()
	{
		 return duplicateBrandError.getText();
	}
	//Clickon AddnewbrandOption
	public void clickNewBrandBtn()
	{
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addnewbrandBtn));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
	}
    public void enterBrandName(String brandName)
    {
    	wait.until(ExpectedConditions.visibilityOf(newBrandName));
    	newBrandName.sendKeys(brandName);
    }
	//FileUpload NewBrand
	public void getUploadBrandLogoNewBrand(String brandLogoFile) throws AWTException, InterruptedException
	{
		 Thread.sleep(1000);
		JavascriptExecutor js=(JavascriptExecutor)driver;  
    	js.executeScript("arguments[0].click();",uploadnewBrandLogo);
		//Robat class
         Robot robot=new Robot();
         robot.delay(2000);
    	//Type of filepath
    	StringSelection filepath=new StringSelection(brandLogoFile);
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
	//select statusNewBrandpage
	public void selectStatus(String status)
	{
		
		Select select = new Select(brandStatus);
		select.selectByVisibleText(status);
		
		
	}
//	//addNew Brand
////	public void addnewBrand(String brandsName,String brandLogo,String brandStatuss) throws AWTException
////	{
////		//enter BrandName
////		brandName.clear();
////		brandName.sendKeys(brandsName);
////		//upload logo file
////		getUploadBrandLogoNewBrand(brandLogo);
////    	//choose status
////		
////		selectStatus(brandStatuss);
////		//clickOn saveBtn
////		wait.until(ExpectedConditions.elementToBeClickable(saveBtnnewBrand)).click();
////		
////		
////	}
	//GetBrandList
	public boolean isBrandInList(String BrandName)
	{
		for(WebElement list:brandNameList)
		{
			if(list.getText().equalsIgnoreCase(BrandName))
			{
			return true;
			}
		}
		return false;
	}
	//click on editOption
	public void clickonEditOption(String brandName) throws InterruptedException
	{
		for(WebElement listname:brandNameList)
    	{
    		String brandList = listname.getText();
    		System.out.println(brandList);
    		if(brandList.contains(brandList))
    		{
    			Thread.sleep(1000);
    			editBrandOption.click();
    		}
    	}	
		
	}
	//updateBrandLogo
	 public void  updateBrandLogo(String filePath) throws AWTException, InterruptedException 
	    {
	    	
			JavascriptExecutor js=(JavascriptExecutor)driver;  
	    	js.executeScript("arguments[0].click();",updatelogoInput);
			//Robat class
	         Robot robot=new Robot();
	         robot.delay(2000);
	    	//Type of filepath
	    	StringSelection filepath=new StringSelection(filePath);
	    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath, filepath);
	    	//press control v
	    	robot.keyPress(KeyEvent.VK_CONTROL);
	    	robot.keyPress(KeyEvent.VK_V);
	    	robot.keyRelease(KeyEvent.VK_V);
	    	robot.keyRelease(KeyEvent.VK_CONTROL);
	    	//pressEnter
	    	robot.keyPress(KeyEvent.VK_ENTER);
	    	robot.keyRelease(KeyEvent.VK_ENTER);
	    	Thread.sleep(1000);
	    	
	    }
	 
	    //Update brand details
//         public void editnewBrand(String brandName,String brandlogoFile, String status) throws AWTException, InterruptedException
//        {
//    	
//     	//enter brandName
//    	updateBrandName.clear();
//    	updateBrandName.sendKeys(brandName);
//    	//Update BrandLogo
//	     Thread.sleep(1000);    	
//	     updateBrandLogo( brandlogoFile);
//    	//updatebrandStatus   	
//   	    Select select=new Select(updateBrandstatus);
//    	select.selectByVisibleText(status);
//    	//clickonSaveBtn
//     	//wait.until(ExpectedConditions.elementToBeClickable(saveChangesBtn)).click();	
//    }
    
    //Delete Brand 
    public void deleteBrand(String brandName)
    {
    	for(WebElement listname:brandNameList)
    	{
    		String brandList = listname.getText();
    		if(brandList.contains(brandList))
    		{
    			deleteBrandOption.click();
    			//wait.until(ExpectedConditions.elementToBeClickable(DeleteconfirmPopusButton)).click();	
    		}
    	}	
    }
    //updateBrandsussfullyMsg
    public boolean isupdateBrandSucssesMsg()
    {
    	try {
    	        

    	        return updateSuccessMsg.isDisplayed()
    	            && updateSuccessMsg.getText().trim().contains("Brand updated successfully");
    	    } 
    	    catch ( NoSuchElementException | StaleElementReferenceException e) 
    	       {
    	        return false;
    	     }
    
      }
    //Search brand
    public void searchForBrand(String brandName)
    {
    	 //wait.until(ExpectedConditions.visibilityOf(searchInputBoxBrand)).clear();
    	 searchInputBoxBrand.sendKeys(brandName,Keys.ENTER);
    		
    }
    //NoResultsearchMeg
    public boolean isNoResultsDisplayed()
    {
		return false;
    	//return wait.until(ExpectedConditions.visibilityOf(noResultsMsg)).isDisplayed();
    }
    //click on save btn
    public void clickOnSaveBtn()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(saveBtnnewBrand)).click();	
    }
    //EditBrandName
    public void enterUpdateBrandName(String editBrandName)
    {
    	updateBrandName.clear();
    	updateBrandName.sendKeys(editBrandName);
    	
    }
    //UpdateStatus
     public  void updateStatus(String status)
     {
    	 Select select=new Select(updateBrandstatus);
    	 select.selectByVisibleText(status);	 
     }
     //click on save changes btn
     public void clickOnSaveChangesBtn()
     {
    	 saveChangesBtn.click();
   
     
     }
    
    
    
  
}

