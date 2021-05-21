
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
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.handsOn.exercise.dataproviders.GratuityCalculatorDataProvider;
import com.handsOn.exercise.pom.GratuityCalculatorPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;

public class UseCase6_GratuityCalculator {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl;
	private GratuityCalculatorPOM gratuityCalPOM;
	private static Properties properties;
	private static HSSFWorkbook wb;		
	private static HSSFSheet sh;
	private static HSSFRow row;
	private static HSSFCell cell;
	private static HSSFCell gratuityAmt;
	//private int rowNum=0;
		

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		wb = new HSSFWorkbook(new FileInputStream("./resources/Gratuity Calculator.xls"));
		
		/*sh = wb.getSheet("Positive Cases");
		row = sh.getRow(0);		
		gratuityAmt= row.createCell(3);
		gratuityAmt.setCellValue("Gratuity Amount");*/
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.FIREFOX);
		gratuityCalPOM = new GratuityCalculatorPOM(driver);
		baseUrl = properties.getProperty("moneycontrol_baseURL");
		wait = new WebDriverWait(driver,5);
		// open the browser
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test //(dataProvider = "xls-GratuityCalculatorData_PositiveCases", dataProviderClass = GratuityCalculatorDataProvider.class)
	public void GratuityCalculator_PositiveFlow() throws InterruptedException, FileNotFoundException, IOException  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		sh = wb.getSheet("Positive Cases");
		int rows = sh.getPhysicalNumberOfRows();
		
		row = sh.getRow(0);		
		gratuityAmt= row.createCell(3);
		gratuityAmt.setCellValue("Gratuity Amount");
		
		gratuityCalPOM.clickPersonalFinanceLnk();
		gratuityCalPOM.clickGratuityCalculator();
		gratuityCalPOM.clickYesBtn();
		
		for(int i =1; i<rows; i++) {
			row = sh.getRow(i);
			gratuityAmt = row.createCell(3);
			
			cell = row.getCell(0);
			Long salary = (long) cell.getNumericCellValue();
			gratuityCalPOM.sendSalary(salary.toString());
			cell = row.getCell(1);
			Integer year = (int) cell.getNumericCellValue();
			gratuityCalPOM.sendYear(year.toString());
			cell = row.getCell(2);
			Integer month = (int) cell.getNumericCellValue();
			gratuityCalPOM.sendMonth(month.toString());
			gratuityCalPOM.clickCalculateBtn();
		
			gratuityAmt.setCellValue(gratuityCalPOM.getGratuityAmt());
			
			gratuityCalPOM.clickResetBtn();
			wb.write(new FileOutputStream("./resources/Gratuity Calculator.xls"));
		}
	}
	
	@Test //(dataProvider = "xls-GratuityCalculatorData_NegativeCases", dataProviderClass = GratuityCalculatorDataProvider.class)
	//public void GratuityCalculator_NegativeFlow(String salary,String year,String month,String expectedMesg) throws InterruptedException, FileNotFoundException, IOException  {
	public void GratuityCalculator_NegativeFlow() throws InterruptedException, FileNotFoundException, IOException  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		sh = wb.getSheet("Negative Cases");
		int rows = sh.getPhysicalNumberOfRows();
		
		gratuityCalPOM.clickPersonalFinanceLnk();
		gratuityCalPOM.clickGratuityCalculator();
		gratuityCalPOM.clickYesBtn();
		
		for(int i =1; i<rows; i++) {
			row = sh.getRow(i);
			cell = row.getCell(0);
			Long salary = (long) cell.getNumericCellValue();
			if(salary > 0)
				gratuityCalPOM.sendSalary(salary.toString());
			cell = row.getCell(1);
			Integer year = (int) cell.getNumericCellValue();
			if(year>0)
				gratuityCalPOM.sendYear(year.toString());
			cell = row.getCell(2);
			Integer month = (int) cell.getNumericCellValue();
			if(month>0)
				gratuityCalPOM.sendMonth(month.toString());
			cell = row.getCell(3);
			String expectedMesg = cell.getStringCellValue();
			gratuityCalPOM.clickCalculateBtn();
			
			wait.until(ExpectedConditions.alertIsPresent());
			
			 String actualMesg = driver.switchTo().alert().getText();
			 Alert alert = driver.switchTo().alert();
			 alert.accept();
			 gratuityCalPOM.verifyErrMesg(actualMesg, expectedMesg);	
			
			gratuityCalPOM.clickResetBtn();
			
		}
		
	}
}
