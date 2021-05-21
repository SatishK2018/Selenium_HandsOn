
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.handsOn.exercise.pom.CorpAnnouncementPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;


public class UseCase13_CorporateAnnouncement {
	
	private WebDriver driver;
	//private WebDriverWait wait;
	private String baseUrl;
	private JavascriptExecutor js;
	private CorpAnnouncementPOM corpAnnouncPOM;
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
		sh = wb.createSheet("Announcement");
		row = sh.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue("Company Name");
		cell = row.createCell(1);
		cell.setCellValue("Subject");
		cell = row.createCell(2);
		cell.setCellValue("Details");
		cell = row.createCell(3);
		cell.setCellValue("Broadcast Date/Time");		
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.FIREFOX);
		baseUrl = properties.getProperty("nseIndia_baseURL");
		js = (JavascriptExecutor) driver;
		corpAnnouncPOM = new CorpAnnouncementPOM(driver);
		//wait = new WebDriverWait(driver,5);
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		//Thread.sleep(100);
		driver.quit();		
	}
	
	
	@Test 
	public void corporateAnnouncement() throws Exception, Throwable  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		js.executeScript("window.scrollBy(0,1300)");				
		//corpAnnouncPOM.clickCorporateAnnouncement();
		Thread.sleep(100);
		for(int slideNum=60; slideNum<64; slideNum++) {
			row=sh.createRow(rowCount);
			cell  = row.createCell(0);
			cell1 = row.createCell(1);
			cell2 = row.createCell(2);
			cell3 = row.createCell(3);
			cell.setCellValue(corpAnnouncPOM.getCardHeader(slideNum));
			cell1.setCellValue(corpAnnouncPOM.getSubject(slideNum));
			cell2.setCellValue(corpAnnouncPOM.getDetails(slideNum));
			cell3.setCellValue(corpAnnouncPOM.getBroadcastDtTime(slideNum));
			//corpAnnouncPOM.clickDownloadLink(slideNum);
			rowCount++;
		}
		
		try {
			wb.write(new FileOutputStream("./resources/Corp Announcement.xls"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			wb.write(new FileOutputStream("./resources/Corp Announcement.xls"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("AlertsAndAnnouncements.docx written successully");
			
	}
	
}









