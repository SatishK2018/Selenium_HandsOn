
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.handsOn.exercise.pom.CommoditiesPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;

public class UseCase3_CaptureGoldCommodityPrice {
	
	private WebDriver driver;
	private String baseUrl;
	private CommoditiesPOM commodityPOM;
	private JavascriptExecutor js;
	private static Properties properties;
	private static HSSFWorkbook wb;
	private static HSSFSheet sh;
	private static HSSFRow row;
	private static HSSFCell cell;
	

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		wb = new HSSFWorkbook();
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		// Window handle doesn't work in Firefox 80+ so executed this case in CHROME
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		commodityPOM = new CommoditiesPOM(driver);
		js = (JavascriptExecutor) driver;
		baseUrl = properties.getProperty("moneycontrol_baseURL");
		// open the browser
		driver.get(baseUrl);
		
		sh = wb.createSheet("Gold Price");
		row = sh.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue("Date");
		cell = row.createCell(1);
		cell.setCellValue("Price");
		cell = row.createCell(2);
		cell.setCellValue("Change Price");
		cell = row.createCell(3);
		cell.setCellValue("Change Percent");
	}

	@AfterMethod
	public void tearDown() throws Exception {
		//Thread.sleep(1000);
		driver.quit();
		
	}
	
	@Test 
	public void getShareValue() throws InterruptedException, FileNotFoundException, IOException  {
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//String winHandleBefore = driver.getWindowHandle();
		String parent = driver.getWindowHandle();
				
		commodityPOM.clickCommodities();
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(1000);
		commodityPOM.clickGOLDLnk();
		
		// Switch to new window opened
		// This code doesn't work in Firefox 80+
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}

		for (int i=0; i<2;i++) {
			commodityPOM.clickTab(i);
			/*System.out.println(commodityPOM.getTabName());
			System.out.println(commodityPOM.getPrice(i));
			System.out.println(commodityPOM.getChangePrice(i));
			System.out.println(commodityPOM.getChangePercent(i));*/
			
			row = sh.createRow(i+1);
			cell = row.createCell(0);
			cell.setCellValue(commodityPOM.getTabName());
			cell = row.createCell(1);
			cell.setCellValue(commodityPOM.getPrice(i));
			cell = row.createCell(2);
			cell.setCellValue(commodityPOM.getChangePrice(i));
			cell = row.createCell(3);
			cell.setCellValue(commodityPOM.getChangePercent(i));
			try {
				wb.write(new FileOutputStream("./resources/Gold Commodity Price Details.xls"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				wb.write(new FileOutputStream("./resources/Gold Commodity Price Details1.xls"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
			
		driver.close();

		// Switch back to original browser (first window)
		driver.switchTo().window(parent);
		
	}	
		
}
	
