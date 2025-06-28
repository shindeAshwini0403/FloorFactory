package com.qa.extentreports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


	public class ExtentclassReportes 
	{
		public  static ExtentReports  generateExtendsReport() throws IOException
		{
		ExtentReports extentReport = new ExtentReports();
		File extentReportfile=new File("C:\\Users\\sashw\\AutomationProjects\\New folder\\FloorFactoryP\\FloorFactory\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter= new ExtentSparkReporter(extentReportfile);
		
		// Set optional configuration,----> report title, author, etc
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Automation Test ExtentReport");
	    sparkReporter.config().setDocumentTitle(" ExtentReport");
	    sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	    
	    extentReport.attachReporter(sparkReporter);
	    
	 // Optional: add system info
	     Properties configProp=new Properties();
	 	 File configProfile=new File("C:\\Users\\sashw\\AutomationProjects\\New folder\\FloorFactoryP\\FloorFactory\\src\\main\\java\\com\\qa\\config\\config.properties");
	     try {
	 	 FileInputStream fisConfigProp =new FileInputStream(configProfile);
	     configProp.load(fisConfigProp);
	     }catch(Throwable e)
	     {
	    	 e.printStackTrace();
	     }
	     extentReport.setSystemInfo("Project_Name",configProp.getProperty("ProjectName"));
	     extentReport.setSystemInfo("Application_URL",configProp.getProperty("URL"));
	     extentReport.setSystemInfo("Browser_name",configProp.getProperty("Browser"));
	     extentReport.setSystemInfo("Host_name",configProp.getProperty("HostName"));
	     extentReport.setSystemInfo("Environment",configProp.getProperty("Environment"));
	     extentReport.setSystemInfo("os.Name",configProp.getProperty("os.name"));
	     
	     return extentReport;
	}

}




