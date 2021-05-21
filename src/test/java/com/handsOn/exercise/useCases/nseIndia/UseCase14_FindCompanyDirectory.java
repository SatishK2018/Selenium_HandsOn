
package com.handsOn.exercise.useCases.nseIndia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.handsOn.exercise.pom.FindCompanyDirectoryPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;


public class UseCase14_FindCompanyDirectory {
	
	private WebDriver driver;
	//private WebDriverWait wait;
	private String baseUrl;
	private FindCompanyDirectoryPOM compDirectoryPOM;
	private static Properties properties;
	private static HSSFWorkbook wb;
	private static HSSFSheet sh;
	private static HSSFRow row;
	private static HSSFCell cell,cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8;
	//private String parent;
	private Set<String> winids;
	int rowCount=1;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		wb = new HSSFWorkbook();
		sh = wb.createSheet("Company Directory");
		row = sh.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue("Company Name");
		cell = row.createCell(1);
		cell.setCellValue("Office");
		cell = row.createCell(2);
		cell.setCellValue("Address");
		cell = row.createCell(3);
		cell.setCellValue("City");	
		cell = row.createCell(4);
		cell.setCellValue("PIN");	
		cell = row.createCell(5);
		cell.setCellValue("Telephone");	
		cell = row.createCell(6);
		cell.setCellValue("Fax");
		cell = row.createCell(7);
		cell.setCellValue("Email");	
		cell = row.createCell(8);
		cell.setCellValue("Company Website");	
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		baseUrl = properties.getProperty("nseIndia_baseURL");
		compDirectoryPOM = new FindCompanyDirectoryPOM(driver);
		//wait = new WebDriverWait(driver,5);
		driver.get(baseUrl);
		driver.manage().deleteAllCookies();
		
	}

	@AfterMethod
	public void tearDown() throws Exception {
		//Thread.sleep(100);
		//driver.quit();		
	}
	
	
	@Test 
	public void compDirectory() throws Exception, Throwable  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		//Parent Window
		//parent = driver.getWindowHandle();
		
		compDirectoryPOM.clickInvestLnk();
		//compDirectoryPOM.clickListLnk();
		compDirectoryPOM.clickCompDirectoryLnk();
		compDirectoryPOM.clickOpenCompDirectoryLnk();
		switchToChildWindow();
		compDirectoryPOM.searchCompName("Apollo");
		compDirectoryPOM.selectItemFromAutoSuggestList("Last");
		//GO button is not working in Chrome
		compDirectoryPOM.clickGOBtn();
		enterDataToExcel();
		
		
		
		//driver.switchTo().window(parent);
		
		try {
			wb.write(new FileOutputStream("./resources/CompanyDirectory.xls"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			wb.write(new FileOutputStream("./resources/CompanyDirectory.xls"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("AlertsAndAnnouncements.docx written successully");
			
	}
	
	public void switchToChildWindow() throws Exception {
		winids = driver.getWindowHandles();
		Thread.sleep(500);
		driver.switchTo().window(winids.toArray()[1].toString());
	}
	
	public void enterDataToExcel() {
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
			
		while(compDirectoryPOM.getTotalNumberOfRows()>0) {
			
			for(int rowNum=1; rowNum<compDirectoryPOM.getTotalNumberOfRows()+1; rowNum++) {
					row=sh.createRow(rowCount);
					cell  = row.createCell(0);
					cell1 = row.createCell(1);
					cell2 = row.createCell(2);
					cell3 = row.createCell(3);
					cell4 = row.createCell(4);
					cell5 = row.createCell(5);
					cell6 = row.createCell(6);
					cell7 = row.createCell(7);
					cell8 = row.createCell(8);
					//Thread.sleep(500);
					cell.setCellValue(compDirectoryPOM.getSelectedOption().getText());
					cell1.setCellValue(compDirectoryPOM.getOfficeType(rowNum));
					cell2.setCellValue(compDirectoryPOM.getAddress(rowNum));
					cell3.setCellValue(compDirectoryPOM.getCity(rowNum));
					cell4.setCellValue(compDirectoryPOM.getPin(rowNum));
					cell5.setCellValue(compDirectoryPOM.getTelephoneNum(rowNum));
					cell6.setCellValue(compDirectoryPOM.getFaxNum(rowNum));
					cell7.setCellValue(compDirectoryPOM.getEmail(rowNum));
					cell8.setCellValue(compDirectoryPOM.getCompWebsite(rowNum));
					rowCount++;
			}
		}
		
	}
	
}









