
package com.handsOn.exercise.useCases.moneyControl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.handsOn.exercise.pom.HomePagePOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;

public class UseCase2_GetShareValForMultiComp {
	
	private WebDriver driver;
	private String baseUrl;
	private HomePagePOM homePgPOM;
	private static Properties properties;
	private static HSSFWorkbook wb;		
	private static HSSFSheet sh;
	private static HSSFRow row;
	private static HSSFCell cell;
	private static HSSFCell currentNSEValue;
	private static HSSFCell changeNSEValue;
	private static HSSFCell changeNSEPercent;
	private static HSSFCell currentBSEValue;
	private static HSSFCell changeBSEValue;
	private static HSSFCell changeBSEPercent;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		wb = new HSSFWorkbook(new FileInputStream("./resources/CompanyShareValue.xls"));
		sh = wb.getSheet("Sheet1");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		homePgPOM = new HomePagePOM(driver);
		baseUrl = properties.getProperty("moneycontrol_baseURL");
		// open the browser
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test 
	public void getShareValue() throws InterruptedException, FileNotFoundException, IOException  {
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		int rows = sh.getPhysicalNumberOfRows();
		
		for(int i =1; i<rows; i++) {
			 row = sh.getRow(i);
			 cell = row.getCell(0);
			 String compName = cell.getStringCellValue();
			//System.out.println(compName);		
			homePgPOM.searchCompName(compName);
			homePgPOM.clickCompName(compName);
			currentNSEValue =row.createCell(1);
			currentNSEValue.setCellValue(homePgPOM.getCurrentNSEValue());
			//System.out.println(homePgPOM.getCurrentNSEValue());
			changeNSEValue =row.createCell(2);
			changeNSEValue.setCellValue(homePgPOM.getChangeNSEValue());
			//System.out.println(homePgPOM.getChangeNSEValue());
			changeNSEPercent =row.createCell(3);
			changeNSEPercent.setCellValue(homePgPOM.getChangeNSEPercent());
			//System.out.println(homePgPOM.getChangeNSEPercent());
			homePgPOM.clickBSELnk();
 			Thread.sleep(2000);
 			currentBSEValue =row.createCell(4);
			currentBSEValue.setCellValue(homePgPOM.getCurrentBSEValue());
			//System.out.println(homePgPOM.getCurrentBSEValue());
			changeBSEValue =row.createCell(5);
			changeBSEValue.setCellValue(homePgPOM.getChangeBSEValue());
			//System.out.println(homePgPOM.getChangeBSEValue());
			changeBSEPercent =row.createCell(6);
			changeBSEPercent.setCellValue(homePgPOM.getChangeBSEPercent());
			//System.out.println(homePgPOM.getChangeBSEPercent());
			wb.write(new FileOutputStream("./resources/CompanyShareValue_updated.xls"));
			
		}
	}
	
}
