
package com.handsOn.exercise.useCases.nseIndia;

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
import com.handsOn.exercise.pom.HolidaysForEquitiesPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;


public class UseCase15_HolidaysForEquities {
	
	private WebDriver driver;
	private String baseUrl;
	private HolidaysForEquitiesPOM holidaysPOM;
	private static Properties properties;
	private static HSSFWorkbook wb;
	private static HSSFSheet sh;
	private static HSSFRow row;
	private static HSSFCell cell,cell1,cell2,cell3;
	int rowCount=1;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		wb = new HSSFWorkbook();
		sh = wb.createSheet("Holidays For The Equities");
		row = sh.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue("SR.NO.");
		cell = row.createCell(1);
		cell.setCellValue("DATE");
		cell = row.createCell(2);
		cell.setCellValue("DAY");
		cell = row.createCell(3);
		cell.setCellValue("DESCRIPTION");	
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		baseUrl = properties.getProperty("nseIndia_baseURL");
		holidaysPOM = new HolidaysForEquitiesPOM(driver);
		driver.get(baseUrl);
		driver.manage().deleteAllCookies();
		
	}

	@AfterMethod
	public void tearDown() throws Exception {
		//Thread.sleep(100);
		driver.quit();		
	}
	
	
	@Test 
	public void equitiesHolidays() throws Exception, Throwable  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		holidaysPOM.clickResourcesLnk();
		holidaysPOM.clickHolidaysLnk();
		driver.manage().deleteAllCookies();
		enterDataToExcel(holidaysPOM.getHolidayTradingTbl().getAttribute("id"),holidaysPOM.getTotalNumberOfRows_TradingHolidayTbl());
		enterDataToExcel(holidaysPOM.getWkendHolidayTbl().getAttribute("id"),holidaysPOM.getTotalNumberOfRows_WkEndHolidayTbl());
		holidaysPOM.clickClearingHolidaysLnk();
		enterDataToExcel(holidaysPOM.getHolidayClearingTbl().getAttribute("id"),holidaysPOM.getTotalNumberOfRows_ClearingHolidayTbl());
				
		try {
			wb.write(new FileOutputStream("./resources/Holidays For Equities.xls"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			wb.write(new FileOutputStream("./resources/Holidays For Equities.xls"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("AlertsAndAnnouncements.docx written successully");
			
	}
	
	
	
	public void enterDataToExcel(String tableID,int totalNumberOfRows) {
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		
		row=sh.createRow(rowCount);
		if(tableID.equals("holidayTable")) {
			cell  = row.createCell(0);
			cell.setCellValue("Trading Holidays");
			rowCount++;
		}else if(tableID.equals("weekendHolidayTable")) {
			cell  = row.createCell(0);
			cell.setCellValue(holidaysPOM.getTextMesg());
			rowCount++;
		}else if(tableID.equals("holidayClearingTable")) {
			cell  = row.createCell(0);
			cell.setCellValue("Clearing Holidays");
			rowCount++;
		}
			
		for(int rowNum=1; rowNum<totalNumberOfRows; rowNum++) {
			
			row=sh.createRow(rowCount);
			cell  = row.createCell(0);
			cell1 = row.createCell(1);
			cell2 = row.createCell(2);
			cell3 = row.createCell(3);
				
			cell.setCellValue(holidaysPOM.getSrNo(tableID, rowNum));
			cell1.setCellValue(holidaysPOM.getDate(tableID, rowNum));
			cell2.setCellValue(holidaysPOM.getDay(tableID, rowNum));
			cell3.setCellValue(holidaysPOM.getDescription(tableID, rowNum));
				
			rowCount++;
		}
		row=sh.createRow(++rowCount);
	}

}









