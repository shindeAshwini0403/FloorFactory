package com.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

import io.qameta.allure.Step;


	public class SubCategoryPage  extends BaseClass
	{
		@FindBy(xpath="//input[@placeholder='Subcategory Name']")private WebElement subCategoryName;
		@FindBy(xpath="(//input[contains(@type,'file')])[1]")private WebElement subfileuploadImg;
	    @FindBy(xpath="//select[@name=\"status[]\"]")private WebElement  subCategoryStatus;
	    @FindBy(xpath="//a[@id='add-subcategory']")private WebElement   addMoreSubCategoryBtn;
	    @FindBy(xpath="//button[@id='submitBtn']")private WebElement SubmitBtn;
	    @FindBy(xpath="//table[@id=\"DataTables_Table_0\"]//tbody/tr//td[1]")private List<WebElement> subCategoryList;
	    @FindBy(xpath="//div[@class=\"alert alert-success\"]")private WebElement subCategoryaddSucssmessage;
	    @FindBy(xpath="//table[@id=\"DataTables_Table_0\"]//tbody//tr//td[4]//div//a[1]")private  WebElement editSubCategoryOption;
	    @FindBy(xpath="//table[@id=\"DataTables_Table_0\"]//tbody//tr//td[4]//div//a[2]")private WebElement  deleteSubcategoryOption;
	    @FindBy(xpath="//button[@class=\"swal2-confirm swal2-styled\"]")private WebElement popusdeletbtn ;
		@FindBy(xpath="")private WebElement duplicateErrorMsg;
		//EditSubCategory
		@FindBy(id="edit-name")private WebElement  editCategoryNameInput;
		@FindBy(xpath="(//input[contains(@type,'file')])[2]")private WebElement  editimgFileUpload;
		@FindBy(xpath="//select[@id=\"edit-status\"]")private WebElement editCategoryStatus;
		@FindBy(xpath="//button[normalize-space()='Save Changes']")private WebElement  saveChangeBtn;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		@FindBy(xpath="//input[@name='subcategories[]']")private List<WebElement>  subcategoryInputs;
		@FindBy(xpath="input[name='images[]']")private List<WebElement>  fileInputs;
		@FindBy(xpath="//input[@name='status[]']")private List<WebElement>  statusDropdowns;
		

	    public SubCategoryPage()
	    {
	    	PageFactory.initElements(driver,this);
	    }
	    //Upload file
	    public void getuploadfile(String filePath1)  throws AWTException
	    {
	    	JavascriptExecutor js=(JavascriptExecutor)driver;  
	    	js.executeScript("arguments[0].click();",subfileuploadImg);
			//Robat class
	         Robot robot=new Robot();
	         robot.delay(2000);
	    	//Type of filepath
	    	StringSelection filepath=new StringSelection(filePath1);
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
	     @Step("Choose subCategory Status:{0}")
	    public  void selectSubCategoryStatus(String  statusText)
	    {
	    	Select select=new Select(subCategoryStatus);
	    	select.selectByVisibleText(statusText);				
	    }
	    @Step("Click on sumbmit btn")
	    public void clickOnSubmitBtn()
	    {
	    	WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
	    	SubmitBtn.click();
	    }
	   @Step("Add more subcategory btn")
	    public void clickOnAddMoreSubCategoryBtn()
	    {
	    	addMoreSubCategoryBtn.click();
	    }
	   @Step("Verify if subcategory '{0}' is absent from the list")
	    public boolean isSubCategoryAbsent(String categoryName) {
	        for (WebElement sublist : subCategoryList) {
	            String subCategoryText = sublist.getText();
	            if (subCategoryText.contains(categoryName)) {
	                return false; // Match found, so not absent
	            }
	        }
	        return true; // No match found, so it's absent
	    }
	    @Step("Check category list is display or not:{0}")
	    public boolean isSubcategoryCreatedMessageDisplayed()
	    {
	    	return subCategoryaddSucssmessage.isDisplayed() && subCategoryaddSucssmessage.getText().contains("Subcategory added successfully");
	    }
	    
	     @Step("Click on Subcategory edit Option:{0}")//click on  subCategory Editoption
	    public void clickOnEditOptipn(String SubCategoryName)
	    {
	    	 for(WebElement sublist:subCategoryList)
	         {
	       	    String subCategoryList = sublist.getText();
	       	    if(subCategoryList.contains(SubCategoryName))
	       	    {
	       	    	editSubCategoryOption.click();
	       	    	
	       	    	
	       	    }
	         }
	    }
	     
	   @Step(" Click on deletedubcategory btn:{0}")
	    public void clickOnDeleteSubCategory(String SubCategoryName) throws InterruptedException
	    {
	    	wait.until(ExpectedConditions.visibilityOf(deleteSubcategoryOption));
	    	 for(WebElement sublist:subCategoryList)
	         {
	       	    String subCategoryList = sublist.getText();
	       	    if(subCategoryList.contains(SubCategoryName))
	       	    {
	       	    	deleteSubcategoryOption.click();
	       	    	
	       	    	
	       	    }
	         }
	    }
	    
	       
	    @Step("subcategory is present in given list{0}")
	    public boolean isSubCategoryPresent(String categoryName) 
	    {
	    	//wait.until(ExpectedConditions.refreshed( ExpectedConditions.visibilityOfAllElements(subCategoryList)));
	    		   
	        for (WebElement sublist : subCategoryList) {
	            String subCategoryText = sublist.getText();
	            if (subCategoryText.contains(categoryName)) {
	                return true; // Found a match
	            }
	        }
	        return false; // No match found
	    }
	    
	    @Step("Check if dublicate  Subcategory error message is displayed ")
	    public boolean isDuplicateCategoryErrorDisplayed()
	    {
	    	 return duplicateErrorMsg.getText().contains("This category is already used, try another.")&&  duplicateErrorMsg.isDisplayed();
	    	
	    }
	   @Step("Enter Subcategory name{0}")
	   	public void enterSubCategoryName(String categoryName)
		{
	   		
			subCategoryName.sendKeys(categoryName);
			
		}
	    @Step("Get updatfileupload Img:{0}")
		public void  getUpdatefileUpload(String filePath) throws AWTException
		{
	    	JavascriptExecutor js=(JavascriptExecutor)driver;  
	    	js.executeScript("arguments[0].click();",editimgFileUpload);
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
  
				  	
		}
		@Step("Update Subcategory Status:{0}")
		public void getUpdateCategoryStatus(String CategoryStaus)
		{
			Select select=new Select(editCategoryStatus);
			select.selectByVisibleText(CategoryStaus);
		}
		@Step("Click on save changes buttos")
		public void  clickOnSavechangesBtn()
		{
			saveChangeBtn.click();
		}
		@Step("UpdateSubCategoryName;{0}")
		public  void  getUpdateSubcategoryName(String SubCategoryName)
		{
			   wait.until(ExpectedConditions.visibilityOf(editCategoryNameInput));
			editCategoryNameInput.clear();
			editCategoryNameInput.sendKeys(SubCategoryName);
		}
		@Step("confirmPopus subcategory")
		public void confirmDeleteSubcategoryPopus() throws InterruptedException
		{
			Thread.sleep(1000);
			
			popusdeletbtn.click();
		}

	
		
		
		
	
	
		 }
		

		
		
	    
	    
	


