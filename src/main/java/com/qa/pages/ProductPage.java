package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage
{
	
	@FindBy(xpath="//a[@class=\"btn btn-added\"]/parent::div")private  WebElement  addProduct;
	@FindBy(xpath="//span[@id=\"select2-company_id-container\"]")private WebElement  selectCompanyDropDown;
	@FindBy(xpath="//select[@data-select2-id=\"select2-data-11-eef2\"]")private WebElement supplierDrpDown;
	@FindBy(xpath="//input[@class=\"select2-search__field\"]")private WebElement categarySection;
	@FindBy(xpath="//span[@id=\"select2-subcategory-container\"]")private WebElement subCategaryDropDown;
	@FindBy(xpath="//span[@id=\"select2-brand-container\"]")private WebElement brandDropDown;
	@FindBy(xpath="//input[@id=\"product_name\"]/parent::div")private WebElement  productName;
	@FindBy(xpath="//span[@id=\"select2-unit_id-container\"]")private WebElement selectUnits;
	@FindBy(xpath="/input[@id=\"roll_size\"]")private WebElement  rollsize;
	@FindBy(xpath="//input[@id=\"thickness\"]/parent::div")private WebElement thinknessInputFiled;
	@FindBy(xpath="//input[@name=\"hsn_code\"]")private WebElement HsnCode;
	@FindBy(xpath="//input[@name=\"quantityroll\"]")private WebElement queantityRoll;
	@FindBy(xpath="//input[@type=\"file\"]/parent::div[@class=\"mb-3 add-product\"]")private  WebElement imagesUploading;
	
	
	
	
	
	
	
	
	
	

}
