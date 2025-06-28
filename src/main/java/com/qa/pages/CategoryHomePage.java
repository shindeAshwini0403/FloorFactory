package com.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.util.BaseClass;

import io.qameta.allure.Step;

	public class CategoryHomePage extends BaseClass
	{
		@FindBy(xpath="//a[@class=\"btn btn-added\"]")private WebElement  addnewCategory;
		@FindBy(xpath="(//a[@class=\"me-2 p-2\"])[2]")private WebElement editCategoryOption;
		@FindBy(xpath="//a[@class=\"confirm-text p-2 delete-action\"]")private  WebElement deleteCategoryOption ;
		@FindBy(xpath="//table[@class=\"table datanew dataTable no-footer\"]//tbody//td[4]/a[1]")private WebElement categorystatus;
		@FindBy(xpath="//input[@class=\"form-control form-control-sm\"]")private WebElement  searchinput;
		@FindBy(xpath="(//a[@class=\"me-2 p-2\"])[1]")private WebElement subCategory ;
		@FindBy(xpath="//table[@id=\"DataTables_Table_0\"]//tbody//tr//td[2]")private  List<WebElement>categotyList;
		@FindBy(xpath="//a[@class=\"btn btn-searchset\"]")private WebElement searchBtn;
		@FindBy(xpath="//table[@class=\"table datanew dataTable no-footer\"]//tbody//tr//td[3]//img")private List<WebElement> categoryImgs;
		@FindBy(xpath="//button[@class=\"swal2-confirm swal2-styled\"]")private WebElement AlertdeleteBtn;
		@FindBy(xpath="//table[@class=\"table datanew dataTable no-footer\"]//tbody//tr[@class=\"odd\"]//td[text()=\"No matching records found\"]") private WebElement noResultMessage;
		@FindBy(xpath="(//table[@id=\"DataTables_Table_0\"]//tbody//tr//td)[2]")private List<WebElement> searchResults;
		//Edit category
		 @FindBy(id="edit-name")private WebElement  categoryName;
		 @FindBy(xpath="//input[contains(@type,'file')])[1]edit-name")private WebElement  uploadImg;
		 @FindBy(id="edit-status")private WebElement  catagoryStatus;
		 @FindBy(id="(//button[@class=\"btn btn-cancel me-2\"])[2]")private WebElement cancelBtn ;
		 @FindBy(id="UpdateBtn")private WebElement  changesaveBtn;
		
		public CategoryHomePage()
		{
			PageFactory.initElements(driver ,this);
		}
		 @Step//method
		public void clickOnCreatNewBtn() throws InterruptedException
		{
		      
		       addnewCategory.click();
		       Thread.sleep(1000);      
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
	    
	    // delete  a category
	    public void isdeleteCategory(String CategoryName)
	    {
	    	
	    	for(WebElement categories:categotyList)
	    	{
	    		String categoryText = categories.getText();
	    		
	    		if(categoryText.equalsIgnoreCase(CategoryName))
	    		{
	    			deleteCategoryOption.click();
	    			AlertdeleteBtn.click();
	    		  }
	    	  }
	       }
	    
	    //verify category list  to check deleted specific category or not.
	    public boolean isCategoryDeletedinlist(String categoryName)
	    
	    {
	    	for(WebElement Categories:categotyList)
	    	{
	    		if(Categories.getText().contains(categoryName))
	    		{
	    			return false;
	    		}
	    	}
	    	//Successfully deleted
	    	return true;
	    }
	    
	    @Step//get all categoryList
	    public boolean getAllCategoryNames(String CategoryName) throws InterruptedException
	    {
	    	System.out.println(CategoryName);
	    	for (WebElement category : categotyList) {
	            if (category.getText().equalsIgnoreCase(CategoryName)) {
	                return true;
	            }
	        }
	    	
	    		return false;
	    	 
	    }
	    
	    @Step//get number of categories
	    public int getCategoryCount()
	    {
	    	return categotyList.size();
	    	
	    }
	     @Step//update any Category in  list List
	    public void clickOneditOptionCategoryList(String categoryName)
	    {
	    	for(WebElement list:categotyList)
	    	{
	    		String categorylist = list.getText();
	    		if(categorylist.equalsIgnoreCase("categoryName"));
	    		{
	    			editCategoryOption.click(); 
	    		}
	    	}
	    
	    }
	    
	     @Step//search Functionality
	    public void searchCategory(String CategoryName) 
	    {
	    	searchinput.clear();
	    	searchinput.sendKeys(CategoryName);
	    	searchBtn.click();
	    }
	    
	    @Step//getsearchresult
	    public List<String> getSearchResult()
	    {
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
	    public  void search(String CategoryName) throws InterruptedException
	    {
	    	searchinput.clear();
	    	searchinput.sendKeys(CategoryName);
	        Thread.sleep(1000);
	    	
	    	
	    }
	     @Step//get search  Category result
	    public boolean isDisplayedSearchResultCount()
	    {
	        
	    	return searchResults.size()>0;
	    }
	    @Step//search error message
	    public boolean isNoResultDisplayed()
	    {
	    	return noResultMessage.getText().contains("No matching records found") &&noResultMessage.isDisplayed();	
	    }
	    @Step
	    public void clickAddSubCategoryOption(String categoryName)	
	    {
	    for(WebElement list:categotyList)
	    {
	    	String categoryList = list.getText();
	    	if(categoryList.equalsIgnoreCase(categoryName))
	    	{
	    		subCategory.click();
	    		break;
	    	}
	    }
	    }
	    @Step
	    public void enterCategoryName(String editCategoryName)
		  {
			    categoryName.clear();
			   categoryName.sendKeys(editCategoryName);
			  
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
		
	

}
