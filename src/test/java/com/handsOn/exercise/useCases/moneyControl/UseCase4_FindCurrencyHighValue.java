
package com.handsOn.exercise.useCases.moneyControl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
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
import com.handsOn.exercise.pom.CurrenciesPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;

public class UseCase4_FindCurrencyHighValue {
	
	private WebDriver driver;
	private String baseUrl;
	private CurrenciesPOM currenciesPOM;
	private JavascriptExecutor js;
	private static Properties properties;
	private static HSSFWorkbook wb;
	private static HSSFSheet sh;
	private static HSSFRow row;
	private static HSSFCell cell;
	private int var=0; 
	

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
		js = (JavascriptExecutor) driver;
		currenciesPOM = new CurrenciesPOM(driver);
		baseUrl = properties.getProperty("moneycontrol_baseURL");
		// open the browser
		driver.get(baseUrl);
		
		sh = wb.createSheet("High Value Currency");
		row = sh.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue("Currency");
		cell = row.createCell(1);
		cell.setCellValue("Price");
	}

	@AfterMethod
	public void tearDown() throws Exception {
		//Thread.sleep(1000);
		driver.quit();
		
	}
	
	@Test 
	public void getCurrencyHighValue() throws InterruptedException, FileNotFoundException, IOException  {
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		LinkedHashMap<String, Double> sortedData = new LinkedHashMap<>();
		
		js.executeScript("scroll(0,1000)");
		Thread.sleep(1000);
		currenciesPOM.clickCurrencies();
		js.executeScript("scroll(0,300)");
		currenciesPOM.getSortedPrices();
		
		sortedData = currenciesPOM.getSortedPrices();
		
		for(Map.Entry<String, Double> entry: sortedData.entrySet()) {
			row = sh.createRow(++var);
			cell = row.createCell(0);
			int len = entry.getKey().length();
			cell.setCellValue(entry.getKey().substring(0, len-1));
			cell = row.createCell(1);
			cell.setCellValue(entry.getValue());
			wb.write(new FileOutputStream("./resources/Currency High Value.xls"));
		}
		
	}
		
}
	
