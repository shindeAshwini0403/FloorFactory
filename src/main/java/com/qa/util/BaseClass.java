package com.qa.util;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;

public class BaseClass
{
	public static WebDriver driver;
	public static void initializeBrowser(String browserName) throws Exception
	{
		ChromeOptions op=new ChromeOptions();
         op.addArguments("--disable-notifications");
         
	
	if(browserName.equalsIgnoreCase("Chrome"))
	{
		driver=new ChromeDriver(op);
		
	}
	else if(browserName.equalsIgnoreCase("FireFox"))
	{
		driver=new FirefoxDriver();
		
	}
	else if(browserName.equalsIgnoreCase("Edge"))
	{
		driver=new EdgeDriver();
		
	}
	else if(browserName.equalsIgnoreCase("IE"))
	{
		driver=new  InternetExplorerDriver();
	}
	  else
	  {
		throw new Exception("IncorrectBrowser");
	  }
	
	
	driver.manage().window().maximize();
	//manage all cookies
	driver.manage().deleteAllCookies();
    // Set a timeout of 20 seconds for page loading
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(UtilityClass.PAGE_WAIT_TIME));
	// Set an implicit wait of 10 seconds
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(UtilityClass.IMPLICIT_WAIT_TIME));


	driver.get(UtilityClass.getPFData("URL"));
	 //driver.get("https://fflive.omsdev.in/");
	
 }
	
		
	}
   
		 
		


