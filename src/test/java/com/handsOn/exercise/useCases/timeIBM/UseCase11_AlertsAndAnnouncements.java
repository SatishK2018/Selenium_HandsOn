
package com.handsOn.exercise.useCases.timeIBM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.handsOn.exercise.pom.AlertsPOM;
import com.handsOn.exercise.pom.EditPriorLockedPeriodPOM;
import com.handsOn.exercise.pom.TimesheetPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;


public class UseCase11_AlertsAndAnnouncements {
	
	private WebDriver driver;
	//private WebDriverWait wait;
	private String baseUrl;
	private String userName;
	private String password;
	private TimesheetPOM timePOM;
	private EditPriorLockedPeriodPOM editLkdPrdPOM;
	private AlertsPOM alertsPOM;
	private static Properties properties;
	private XWPFDocument document;
	private XWPFParagraph paragraph;
	private XWPFRun run;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.FIREFOX);
		
		document = new XWPFDocument();
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		
		alertsPOM = new AlertsPOM(driver);
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
		//Thread.sleep(1000);
		driver.quit();		
	}
	
	
	@Test 
	public void AlertsAndAnnouncements() throws Exception, Throwable  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		FileOutputStream out = new FileOutputStream(new File("./resources/AlertsAndAnnouncements.docx"));
				
		editLkdPrdPOM.clickCredentialSignin();
		timePOM.sendUsername(userName);
		timePOM.sendPassword(password);
		timePOM.clickLoginBtn();
		alertsPOM.clickAlerts();
		run.setFontSize(20);
		run.setText("Alerts/Announcements");
		run.addBreak();
		run.setFontSize(14);
		run.setText(alertsPOM.getAlertsAndAnnouncements());
		
		
		document.write(out);
	    out.close();
	    System.out.println("AlertsAndAnnouncements.docx written successully");
			
	}

}
