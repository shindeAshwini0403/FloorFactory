package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

public class UtilityClass
 extends BaseClass
{
	public static final int IMPLICIT_WAIT_TIME=0;
	public static final int PAGE_WAIT_TIME=20;
	
	
	
	//ExelSheet
	  public static String getTestData(int rowindex ,int cellindex) throws EncryptedDocumentException, IOException
	  {
		  FileInputStream File=new FileInputStream("C:\\Users\\sashw\\AutomationProjects\\New folder\\FloorFactoryP\\FloorFactory\\TestData\\FloorFactory.xlsx");
		 Sheet sh = WorkbookFactory.create(File).getSheet("Sheet1");
		 String value = sh.getRow(rowindex).getCell(cellindex).getStringCellValue();
		  return value;
		  }

	  
	//Capture ScreenShort
	  public static File takeScreenShort( String TestMethodName ) throws IOException
	   {
		    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		    File dest = new File("C:\\Users\\sashw\\AutomationProjects\\New folder\\FloorFactoryP\\FloorFactory\\FailTestCaseScreenShot\\screenshot_" + TestMethodName + "_" + timestamp + ".jpg");
		    FileHandler.copy(src, dest); 
		    return dest ;
		    
	   }
	  

	  
	   //PropertyFile
	   public static String getPFData(String key) throws IOException
		{
			FileInputStream file=new FileInputStream("C:\\Users\\sashw\\AutomationProjects\\New folder\\FloorFactoryP\\FloorFactory\\src\\main\\java\\com\\qa\\config\\config.properties");
			Properties p=new Properties();
			p.load(file);
			
			String value = p.getProperty(key);
			
			return value;
		}
	
	
		

}
