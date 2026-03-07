package com.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.util.BaseClass;

import io.qameta.allure.Step;

	public class CategoryHomePage extends BaseClass
	{
		@FindBy(xpath="//a[@class=\"btn btn-added\"]")private WebElement  addnewCategory;
		@FindBy(xpath="(//a[@class=\"me-2 p-2\"])[2]")private WebElement editCategoryOption;
		@FindBy(xpath="//table[@id='DataTables_Table_0']//tbody/tr/td//a[@class=\"confirm-text p-2 delete-action\"]")private  List< WebElement> deleteCategoryOption ;
		@FindBy(xpath="//table[@class=\"table datanew dataTable no-footer\"]//tbody//td[4]/a[1]")private WebElement categorystatus;
		@FindBy(xpath="//input[@class=\"form-control form-control-sm\"]")private WebElement  searchinput;
		@FindBy(xpath="//table[@id=\"DataTables_Table_0\"]//tbody//tr//td[1]//a[1]")private  List <WebElement> subCategory ;
		@FindBy(xpath="//table[@id=\"DataTables_Table_0\"]//tbody//tr//td[2]")private  List<WebElement>categotyList;
		@FindBy(xpath="//a[@class=\"btn btn-searchset\"]")private WebElement searchBtn;
		@FindBy(xpath="//table[@class=\"table datanew dataTable no-footer\"]//tbody//tr//td[3]//img")private List<WebElement> categoryImgs;
		@FindBy(xpath="//button[@class=\"swal2-confirm swal2-styled\"]")private WebElement AlertdeleteBtn;
		@FindBy(xpath="//table[@class=\"table datanew dataTable no-footer\"]//tbody//tr[@class=\"odd\"]//td[text()=\"No matching records found\"]") private WebElement noResultMessage;
		@FindBy(xpath="(//table[@id=\"DataTables_Table_0\"]//tbody//tr//td)[2]")private List<WebElement> searchResults;
		@FindBy(xpath="//button[normalize-space()='OK']")private WebElement deleteConfirmationButton;
		//Edit category
		 @FindBy(css="div[id='edit-category'] div[class='modal-content']") private WebElement editDilogBox;
		 @FindBy(xpath="(//div[contains(@Class,\"modal-content\")])[2]//div[@class=\"modal-body custom-modal-body\"]//input[@id=\"edit-name\"]")private WebElement  EditcategoryName;
		 @FindBy(xpath="//input[contains(@type,'file')])[1]edit-name")private WebElement  uploadImg;
		 @FindBy(id="edit-status")private WebElement  catagoryStatus;
		 @FindBy(id="(//button[@class=\"btn btn-cancel me-2\"])[2]")private WebElement cancelBtn ;
		 @FindBy(id="UpdateBtn")private WebElement  changesaveBtn;
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		public CategoryHomePage()
		{
			PageFactory.initElements(driver ,this);
		}
		 @Step//method
		public void clickOnCreatNewBtn() throws InterruptedException
		{
		       addnewCategory.click();  
		}
		 
		 @Step//pageTitle
		public  String getpageTitle()
		{
			return  driver.getTitle();
		}
		
		@Step//change category status
	    public String changeCategoryStatusCategoryHomePage(String categoryName)
	    {
	    	for(WebElement categories:categotyList)
	    	{
	    		String categoryText = categories.getText();
	    		
				if(categoryText.equalsIgnoreCase(categoryName))
	    		{
	    			categorystatus.click();
	    		   break;
	    		}
	    	}
	    	return categorystatus.getText();
			   
	    }
	    
	   @Step ("Delete category")
	    public void isdeleteCategory(String CategoryName)
	    {
	    	//Loop through the category names and find target index
	    	for(int i=0;i<=categotyList.size();i++)
	    	{
	    		String categoryNames= categotyList.get(i).getText().trim();
	    		
	    		if(categoryNames.equalsIgnoreCase(CategoryName))
	    		{
	    			//Found the category ..Click the corresponding Delete button from the collection
	    			WebElement deleteBtn = deleteCategoryOption.get(i);
	    			deleteBtn.click();
	    			break;
	    		  }
	    		
	    	  }
	       }
	    
	   
	    
	    @Step//get all categoryList
	    public boolean isCategoryPresentInList(String CategoryName) throws InterruptedException
	    {
	    	ArrayList<String>categoryNames=new ArrayList<>();
	    	
	    
	    	for (WebElement category : categotyList)
	    	{
	    		categoryNames.add(category.getText().trim());	
	           
	          }
	    	   //check category present in list
	    		return categoryNames.contains(CategoryName);
	    }
	    
	    @Step//get number of categories
	    public int getCategoryCount()
	    {
	    	return categotyList.size();
	    }
	    
	     //update any Category in  list List
	    public void clickOneditOptionCategoryList(String categoryName)
	    {
	    	 wait.until(ExpectedConditions.visibilityOf(editCategoryOption));
	    	for(WebElement list:categotyList)
	    	{
	    		String categorylist = list.getText();
	    		System.out.println(categorylist);
	    		if(categorylist.equalsIgnoreCase("categoryName"));
	    		{
	    			editCategoryOption.click(); 
	    			//break;
	    		}
	    	}
	    
	    }
	    
//	     @Step//search Functionality
//	    public void searchCategory(String CategoryName) 
//	    {
//	    	searchinput.clear();
//	    	
//	    	searchinput.sendKeys(CategoryName);
//	    	searchBtn.click();
//	    }
	    
	    @Step//getsearchresult
	    public List<String> getSearchResult()
	    {
	    	wait.until(ExpectedConditions.visibilityOfAllElements(categotyList));
	    	List<String>CategoryNames=new ArrayList<>();
	    	for(WebElement lists:categotyList)
	    	{
	    		CategoryNames.add(lists.getText());
	    		
	    	}
	    	return CategoryNames;
	    }
	    @Step//category images
	    public  List<WebElement> getcategoryImg()
	    {
	    		return categoryImgs;	
	    }
	   
	    @ Step//search product
	    public  void enterSearchInput(String CategoryName) throws InterruptedException
	    {
	    	searchinput.clear();
	    	searchinput.sendKeys(CategoryName);
	    }
	    
	     @Step//get search  Category result
	    public boolean isDisplayedSearchResultCount()
	    {
	    	return searchResults.size()>0;
	    }
	     
	    @Step//search error message
	    public boolean isNoResultDisplayedSearchFunctionality()
	    {
	    	
	    	    return noResultMessage.isDisplayed() && noResultMessage.getText().contains("No matching records found");
	    	}
	    
	    @Step
	    public void clickAddSubCategoryOption(String categoryName)	
	    {
	    	 wait.until(ExpectedConditions.visibilityOfAllElements(subCategory));
	    	for(int i=0;i<=categotyList.size();i++)
	    	{
	    		String categoryNames= categotyList.get(i).getText().trim();
	    		
	    		if(categoryNames.equalsIgnoreCase(categoryName))
	    		{
	    			//Found the category ..Click the corresponding Delete button from the collection
	    			WebElement subc = subCategory.get(i);
	    			subc.click();
	    			break;
	    		  }
	    	}
	   
	    }
	
	    public void enterCategoryName(String editCategoryName)
		  {
	    	
	    	 wait.until(ExpectedConditions.visibilityOf(EditcategoryName));
	    	  if (!EditcategoryName.isEnabled())
	    	  {
	    	        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('disabled')", EditcategoryName);
	    	    }
	    	        EditcategoryName.clear();
	    	         EditcategoryName.sendKeys(editCategoryName);
		  }
		 
		 
		  @Step//selectCategory Status
		  public void getUpdateCategoryStatus(String status)
		  {
			Select select=new Select(catagoryStatus) ;
			select.selectByVisibleText(status);
		  }
		  
		  @Step//click on cancel Btn
		  public void clickCancelBtn()
		  {
			  cancelBtn.click();
		  }
		  
		 @Step  //click on savaUpdateBtn
		  public void clickonChangeSaveBtn()
		  {
			  changesaveBtn.click();
		  }
		 public void getfileUpload(String file) throws AWTException
			{
				JavascriptExecutor js=(JavascriptExecutor)driver;  
		    	js.executeScript("arguments[0].click();",uploadImg);
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
		 public void waitForCategoryListToBeVisible()
		 {
			 wait.until(ExpectedConditions.visibilityOfAllElements( categotyList));
	}
		 //Handle confirmation popup (Alert)
		 public void clickOnDeleteConfirmationButton()
		 {
			 AlertdeleteBtn.click();
			 wait.until(ExpectedConditions.elementToBeClickable(deleteConfirmationButton));

			    deleteConfirmationButton.click();  // Click "Confirm" or "Yes" in Dialog
		 }
	}
	
