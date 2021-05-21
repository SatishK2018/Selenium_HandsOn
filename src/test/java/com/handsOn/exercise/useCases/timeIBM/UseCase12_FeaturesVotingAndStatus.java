
package com.handsOn.exercise.useCases.timeIBM;

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
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.handsOn.exercise.pom.EditPriorLockedPeriodPOM;
import com.handsOn.exercise.pom.FeatureVotingPOM;
import com.handsOn.exercise.pom.TimesheetPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;


public class UseCase12_FeaturesVotingAndStatus {
	
	private WebDriver driver;
	//private WebDriverWait wait;
	private String baseUrl;
	private String userName;
	private String password;
	private TimesheetPOM timePOM;
	private EditPriorLockedPeriodPOM editLkdPrdPOM;
	private FeatureVotingPOM featuresVotingPOM;
	private static Properties properties;
	private static HSSFWorkbook wb;
	private static HSSFSheet sh;
	private static HSSFRow row;
	private static HSSFCell cell,cell1,cell2,cell3;
	

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		wb = new HSSFWorkbook();
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.FIREFOX);
		
		featuresVotingPOM = new FeatureVotingPOM(driver);
		timePOM = new TimesheetPOM(driver);
		editLkdPrdPOM = new EditPriorLockedPeriodPOM(driver);
		baseUrl = properties.getProperty("time@IBM");
		userName = properties.getProperty("username");
		password = properties.getProperty("password");
		//wait = new WebDriverWait(driver,5);
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(100);
		driver.quit();		
	}
	
	
	@Test 
	public void featureVotingAndStatus() throws Exception, Throwable  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//FileOutputStream out = new FileOutputStream(new File("./resources/AlertsAndAnnouncements.docx"));
				
		editLkdPrdPOM.clickCredentialSignin();
		timePOM.sendUsername(userName);
		timePOM.sendPassword(password);
		timePOM.clickLoginBtn();
		featuresVotingPOM.clickFeatureVoting();
		featuresVotingPOM.clickInVoting();
		sh = wb.createSheet("In Voting");
		row=sh.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue(featuresVotingPOM.getInVotingCount());
		this.enterDataToExcel();
		Thread.sleep(200);
		featuresVotingPOM.clickInDevelopment();
		sh = wb.createSheet("In Development");
		row=sh.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue(featuresVotingPOM.getInDevelopmentCount());
		this.enterDataToExcel();
		Thread.sleep(200);
		featuresVotingPOM.clickImplemented();
		sh = wb.createSheet("Implemented");
		row=sh.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue(featuresVotingPOM.getImplementedCount());
		this.enterDataToExcel();
		Thread.sleep(200);
		featuresVotingPOM.clickDeferred();
		sh = wb.createSheet("Deferred");
		/*row=sh.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue(featuresVotingPOM.getImplementedCount());
		this.moveToNextPages();*/
		
		try {
			wb.write(new FileOutputStream("./resources/Feature Voting.xls"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			wb.write(new FileOutputStream("./resources/Feature Voting.xls"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("AlertsAndAnnouncements.docx written successully");
			
	}
	
	public void enterDataToExcel() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		int rowCount=1;
		
		while(featuresVotingPOM.getTotalNumberOfRows()>0) {
			
			for(int rowNum=1; rowNum<featuresVotingPOM.getTotalNumberOfRows()+1; rowNum++) {
				row=sh.createRow(rowCount);
				cell  = row.createCell(0);
				cell1 = row.createCell(1);
				cell2 = row.createCell(2);
				cell3 = row.createCell(3);
				//xpath ="//tbody/tr["+rowNum+"]/td";
				Thread.sleep(500);
				String xpath1= "//tbody/tr["+rowNum+"]/td/app-feature-item/div/div[1]/app-feature-item-vote";
				String xpath2= "//table/tbody/tr["+rowNum+"]/td/app-feature-item/div/div[2]/p[1]";
				String xpath3= "//table/tbody/tr["+rowNum+"]/td/app-feature-item/div/div[2]/p[2]";
				String xpath4= "//table/tbody/tr["+rowNum+"]/td/app-feature-item/div/div[2]/p[3]";
				//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath1))));
				Thread.sleep(500);
				cell.setCellValue(featuresVotingPOM.getData(xpath1));
				cell1.setCellValue(featuresVotingPOM.getData(xpath2));
				cell2.setCellValue(featuresVotingPOM.getData(xpath3));
				cell3.setCellValue(featuresVotingPOM.getData(xpath4));
				rowCount++;
			}
			if(featuresVotingPOM.getNextBtn().isEnabled())
				featuresVotingPOM.clickNextBtn();
			else
				break;
		}
		System.gc();
		
	}
	
}









