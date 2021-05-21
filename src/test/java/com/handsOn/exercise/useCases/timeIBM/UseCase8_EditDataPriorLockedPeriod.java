
package com.handsOn.exercise.useCases.timeIBM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.Wait;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.handsOn.exercise.pom.EditPriorLockedPeriodPOM;
import com.handsOn.exercise.pom.TimesheetPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;

public class UseCase8_EditDataPriorLockedPeriod {
	
	private WebDriver driver;
	//private WebDriverWait wait;
	private String baseUrl;
	private String userName;
	private String password;
	private EditPriorLockedPeriodPOM editLkdPrd;
	private TimesheetPOM timePOM;
	private static HSSFWorkbook wb;
	private static HSSFSheet sh;
	private static HSSFRow row;
	private static HSSFCell cell;
	private static Properties properties;
	private GregorianCalendar calendar;	
	

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		wb = new HSSFWorkbook();
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.FIREFOX);
		editLkdPrd = new EditPriorLockedPeriodPOM(driver);
		timePOM = new TimesheetPOM(driver);
		baseUrl = properties.getProperty("time@IBM");
		userName = properties.getProperty("username");
		password = properties.getProperty("password");
		calendar = new GregorianCalendar();
		//wait = new WebDriverWait(driver,5);
		driver.get(baseUrl);
		sh = wb.createSheet("Errors");
		
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		//driver.quit();
	}
	
	@Test 
	public void editLockedPeriod() throws InterruptedException, FileNotFoundException, IOException {
		
		int date =  calendar.get(Calendar.DATE); 
		int month = calendar.get(Calendar.MONTH);
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND); 
		String path = "./resources/";
		String fileName ="";
		
		fileName = new Integer(date).toString() + "-"
				  + new Integer(month+1).toString() + "-"
				  + new Integer(hour).toString() +"-"
				  + new Integer(minute).toString() +"-" 
				  + new Integer(second).toString() +".xls"; 
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		editLkdPrd.clickCredentialSignin();
		timePOM.sendUsername(userName);
		timePOM.sendPassword(password);
		timePOM.clickLoginBtn();
		timePOM.clickWkEndingDropDnHomeScr();
		timePOM.searchWkEnding("April 23, 2021");
		timePOM.selectWkEnding("April 23, 2021");
		row = sh.createRow(0);
		cell=row.createCell(0);
		cell.setCellValue(editLkdPrd.getToolTipMesg());
		row = sh.createRow(1);
		cell=row.createCell(0);
		cell.setCellValue(editLkdPrd.getErrorMesg());
		editLkdPrd.clickActivityCode();
		timePOM.clickSubmitBtn();
		row = sh.createRow(2);
		cell=row.createCell(0);
		cell.setCellValue(editLkdPrd.getErrorMesg());
		editLkdPrd.getErrorMesg();
		
		try {
			wb.write(new FileOutputStream(path+fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			wb.write(new FileOutputStream(path+fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
