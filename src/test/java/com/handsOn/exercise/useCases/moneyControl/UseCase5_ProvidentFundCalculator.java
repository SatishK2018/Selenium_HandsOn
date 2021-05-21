
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
//import com.handsOn.exercise.dataproviders.PFCalculatorDataProvider;
import com.handsOn.exercise.pom.PFCalculatorPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;

public class UseCase5_ProvidentFundCalculator {
	
	private WebDriver driver;
	private String baseUrl;
	private PFCalculatorPOM pfCalculatorPOM;
	private static Properties properties;
	private static HSSFWorkbook wb;		
	private static HSSFSheet sh;
	private static HSSFRow row;
	private static HSSFCell cell;
	private static HSSFCell accumulatedAmt;
	//private int rowNum=0;
		

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		wb = new HSSFWorkbook(new FileInputStream("./resources/PF Calculator.xls"));
		sh = wb.getSheet("Sheet1");
	
		row = sh.getRow(0);		
		accumulatedAmt = row.createCell(8);
		accumulatedAmt.setCellValue("Accumulated Amount");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		pfCalculatorPOM = new PFCalculatorPOM(driver);
		baseUrl = properties.getProperty("moneycontrol_baseURL");
		// open the browser
		driver.get(baseUrl);
		
		//row=sh.getRow(++rowNum);
		
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test //(dataProvider = "xls-PF_CalculatorData", dataProviderClass = PFCalculatorDataProvider.class)
	//public void PFCalculator(String age,String basicSal,String epfContribution,String emplContribution,String incrSal,String retireAge,String epfBal,String interestRt) throws InterruptedException, FileNotFoundException, IOException  {
	public void PFCalculator() throws InterruptedException, FileNotFoundException, IOException  {	
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		int rows = sh.getPhysicalNumberOfRows();
		
		//screenShot.captureScreenShot("Login Process To Moneycontrol.com"+"-"+scenario+"_screen"+ i++);
		pfCalculatorPOM.clickPersonalFinanceLnk();
		Thread.sleep(1000);
		pfCalculatorPOM.clickPFCalculator();
		
		for(int i =1; i<rows; i++) {
			row = sh.getRow(i);
						
			cell = row.getCell(0);
			Integer age = (int) cell.getNumericCellValue();
			pfCalculatorPOM.sendAge(age.toString());
			cell = row.getCell(1);
			Long basicSal = (long) cell.getNumericCellValue();
			pfCalculatorPOM.sendBasicSalary(basicSal.toString());
			cell = row.getCell(2);
			Integer epfContribution = (int) cell.getNumericCellValue();
			pfCalculatorPOM.sendUrEPFContribution(epfContribution.toString());
			cell = row.getCell(3);
			Integer emplContribution = (int) cell.getNumericCellValue();
			pfCalculatorPOM.sendEmployerEPFContribution(emplContribution.toString());
			cell = row.getCell(4);
			Integer incrSal = (int) cell.getNumericCellValue();
			pfCalculatorPOM.sendAnualIncrSal(incrSal.toString());
			cell = row.getCell(5);
			Integer retireAge = (int) cell.getNumericCellValue();
			pfCalculatorPOM.sendRetireAge(retireAge.toString());
			cell = row.getCell(6);
			Long epfBal = (long) cell.getNumericCellValue();
			pfCalculatorPOM.sendEPFBalance(epfBal.toString());
			cell = row.getCell(7);
			Integer interestRt = (int) cell.getNumericCellValue();
			pfCalculatorPOM.sendInterestRate(interestRt.toString());
			pfCalculatorPOM.clickCalculateBtn();
			
			accumulatedAmt = row.createCell(8);
			accumulatedAmt.setCellValue(pfCalculatorPOM.getAccmulatedAmt());
			
			wb.write(new FileOutputStream("./resources/PF Calculator.xls"));
		}
	}
}
