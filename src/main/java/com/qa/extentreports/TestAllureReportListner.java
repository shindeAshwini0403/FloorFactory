package com.qa.extentreports;

import java.util.Arrays;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.util.BaseClass;

import io.qameta.allure.Attachment;

public class TestAllureReportListner extends BaseClass implements ITestListener
{
	private static String getTestMethodName(ITestResult result)
	{
		return  result.getMethod().getConstructorOrMethod().getName();
		
	}
	@Attachment(value = "Failure Screenshot", type = "image/png")

	    public byte[] saveScreenshotPNG() {
	        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	    }

	    // Optional: Add logs if needed
	    @Attachment(value = "{0}", type = "text/plain")
	    public static String saveTextLog(String message) {
	        return message;
	    }
	    @Attachment(value = "Stack Trace", type = "text/plain")
	    public String getStackTrace(Throwable throwable) {
	        return Arrays.toString(throwable.getStackTrace());
	    }
	  
	@Override
	public void onStart(ITestContext context) {
		System.out.println("🔷 Test Execution Started: " + context.getName());
	}
	


	@Override
	public void onTestStart(ITestResult result)
	{
		  System.out.println("Test Started: " + getTestMethodName(result)+"start");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(" Test Passed: " +getTestMethodName(result)+"succeed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		 System.out.println("Test Failed: "+ getTestMethodName(result)+"failed" );
		
		 Object testclass = result.getInstance();
		 if(driver instanceof WebDriver)
		 {
			 System.out.println("Screeshot captured for test case: "+ getTestMethodName(result)+"failed" );
			 saveScreenshotPNG() ;
			 
		 }
		 //Save a log on allure
		 saveTextLog( getTestMethodName(result));
		  getStackTrace(result.getThrowable());
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		 System.out.println("Test Skipped: " + getTestMethodName(result)+"skip");
	}

	
	
	@Override
	public void onFinish(ITestContext context)
	{
		 System.out.println("Test Execution Finished: " + context.getName());
		
	}
	

}
