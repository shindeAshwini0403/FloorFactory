package com.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.util.BaseClass;


	public class SubCategoryPage  extends BaseClass
	{
		@FindBy(xpath="//input[@name=\"subcategories[]\"]")private WebElement subCategoryName;
		@FindBy(xpath="(//input[contains(@type,'file')])[1]")private WebElement subfileuploadImg;
	    @FindBy(xpath="//select[@name=\"status[]\"]")private WebElement  subCategoryStatus;
	    @FindBy(id="add-subcategory")private WebElement   addSubCategoryBtn;
	    @FindBy(id="submitBtn")private WebElement SubmitBtn;
	    @FindBy(xpath="//table[@id=\"DataTables_Table_0\"]//tbody/tr")private List<WebElement> subCategoryList;
	    @FindBy(xpath="//div[@class=\"alert alert-success\"]")private WebElement subCategoryaddSucssmessage;
	    @FindBy(xpath="//table[@id=\"DataTables_Table_0\"]//tbody//tr//td[4]//div//a[1]")private  WebElement editSubCategoryOption;
	    @FindBy(xpath="//table[@id=\"DataTables_Table_0\"]//tbody//tr//td[4]//div//a[2]")private WebElement  deleteSubcategoryOption;
	    @FindBy(xpath="//button[@class=\"swal2-confirm swal2-styled\"]")private WebElement popusdeletbtn ;
		@FindBy(xpath="")private WebElement duplicateErrorMsg;
		//EditSubCategory
		@FindBy(id="edit-name")private WebElement  CategoryNameInput;
		@FindBy(xpath="(//input[contains(@type,'file')])[2]")private WebElement  imgFileUpload;
		@FindBy(xpath="//select[@id=\"edit-status\"]")private WebElement categoryStatus;
		@FindBy(xpath="//button[@id=\"submitBtn\"]")private WebElement  saveChangeBtn;
	    public SubCategoryPage()
	    {
	    	PageFactory.initElements(driver,this);
	    }
	    public  void enterSubcategoryName(String subcategoryName)
	    {
	    	subCategoryName.sendKeys(subcategoryName);
	    }
	    //Upload file
	    public void getuploadfile(String filePath1) throws AWTException
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
	    //choose subCategoryStatus
	    public  void selectSubCategoryStatus(String  statusText)
	    {
	    	Select select=new Select(subCategoryStatus);
	    	select.selectByVisibleText(statusText);				
	    }
	    //click on sumbit  button
	    public void clickOnSubmitBtn()
	    {
	    	SubmitBtn.click();
	    }
	    //add new sub category
	    public void addsubcatagory()
	    {
	    	addSubCategoryBtn.click();
	    }
	    //Get subCategory list
	    public boolean isSubCategoryAbsent(String categoryName) {
	        for (WebElement sublist : subCategoryList) {
	            String subCategoryText = sublist.getText();
	            if (subCategoryText.contains(categoryName)) {
	                return false; // Match found, so not absent
	            }
	        }
	        return true; // No match found, so it's absent
	    }
	    //Check category list is display or not
	    public boolean isSubcategoryCreatedMessageDisplayed()
	    {
	    	return subCategoryaddSucssmessage.isDisplayed() && subCategoryaddSucssmessage.getText().contains("Subcategory added successfully");
	    }
	    
	    //click on  subCategory Editoption
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
	     
	    //Delete subCategory 
	    public void clickOnDeleteSubCategory(String SubCategoryName) throws InterruptedException
	    {
	    	 for(WebElement sublist:subCategoryList)
	         {
	       	    String subCategoryList = sublist.getText();
	       	    if(subCategoryList.contains(SubCategoryName))
	       	    {
	       	    	deleteSubcategoryOption.click();
	       	    	Thread.sleep(1000);
	       	    	popusdeletbtn.click();
	       	    }
	         }
	    }
	    
	       
	    //subcategory is present in given list
	    public boolean isSubCategoryPresent(String categoryName) {
	        for (WebElement sublist : subCategoryList) {
	            String subCategoryText = sublist.getText();
	            if (subCategoryText.contains(categoryName)) {
	                return true; // Found a match
	            }
	        }
	        return false; // No match found
	    }
	    public boolean isDuplicateCategoryErrorDisplayed()
	    {
	    	 return duplicateErrorMsg.getText().contains("This category is already used, try another.")&&  duplicateErrorMsg.isDisplayed();
	    	
	    }
	    //
	   	public void enterCategoryName(String categoryName)
		{
			CategoryNameInput.clear();
			CategoryNameInput.sendKeys(categoryName);
			
		}
	    //update file
		public void getupdatefileUpload(String filePath) throws AWTException
		{
		      
			  CategoryNameInput.clear();
			  JavascriptExecutor js=(JavascriptExecutor)driver;  
		    	js.executeScript("arguments[0].click();",imgFileUpload);
				//Robat class
		         Robot robot=new Robot();
		         robot.delay(2000);
		    	//Type of filepat
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
		//updateCategory status
		public void categoryStatus(String CategoryStaus)
		{
			Select select=new Select(categoryStatus);
			select.selectByVisibleText(CategoryStaus);
		}
		//click on SavechangesBtn
		public void  clickOnSavechangesBtn()
		{
			saveChangeBtn.click();
		}
		
		
		
		
		

		
		
	    
	    
	

}
