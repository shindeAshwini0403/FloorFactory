package com.qa.extentreports;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.util.UtilityClass;

public class Listeners extends UtilityClass
	implements ITestListener 
	{
		
		ExtentReports extentReport;
	    ExtentTest extentTest;
	  
	    
		@Override
		public void onStart(ITestContext context)
		{
			 try {
				extentReport = ExtentclassReportes.generateExtendsReport();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Execution of Project Test started");
			
		}
		@Override
		public void onTestStart(ITestResult result) 
		{
			String testName = result.getName();
			 extentTest = extentReport.createTest(testName);
			 extentTest.log(Status.INFO,testName+"started executing");	  	  
		
		}
		
		@Override
		public void onTestSuccess(ITestResult result)
		{
			String testName = result.getName();
			extentTest.log(Status.PASS ,testName+"got succefully executed");
			
		}

	@Override
		public void onTestFailure(ITestResult result) 
		{
			
			 String testName = result.getName();
			 try {
				UtilityClass.takeScreenShort(testName);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			 
		      extentTest.log(Status.INFO,result.getThrowable());
			  extentTest.log(Status.FAIL,testName+"got failed");

		}
	
		@Override
		public void onTestSkipped(ITestResult result) 
		{
			
			      String testName = result.getName();
				  extentTest.log(Status.INFO,result.getThrowable());
				  extentTest.log(Status.SKIP,testName+"skip");

			
			
		}

		@Override
		public void onFinish(ITestContext context) 
		{
			extentReport.flush();
			System.out.println("Finished executing Project Tests");
		}
		


}
