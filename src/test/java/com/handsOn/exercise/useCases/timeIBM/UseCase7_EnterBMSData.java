
package com.handsOn.exercise.useCases.timeIBM;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.Wait;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.handsOn.exercise.dataproviders.GratuityCalculatorDataProvider;
import com.handsOn.exercise.pom.TimesheetPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;

public class UseCase7_EnterBMSData {
	
	private WebDriver driver;
	//private WebDriverWait wait;
	private String baseUrl;
	private String userName;
	private String password;
	private TimesheetPOM timePOM;
	private static Properties properties;
		

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		timePOM = new TimesheetPOM(driver);
		baseUrl = properties.getProperty("time@IBM");
		userName = properties.getProperty("username");
		password = properties.getProperty("password");
		//wait = new WebDriverWait(driver,5);
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		//driver.quit();
	}
	
	// USE PREVIOUS HOURS 
	@Test 
	public void enterBMSDataPreviousHours() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		timePOM.clickW3Creds();
		timePOM.sendUsername(userName);
		timePOM.sendPassword(password);
		timePOM.clickLoginBtn();
		//timePOM.clickWkEndingDropDnHomeScr();
		//timePOM.searchWkEnding("May 7, 2021");
		//timePOM.selectWkEnding("May 7, 2021");
		timePOM.clickCopyPrevWkBtn();
		timePOM.clickZeroHrsChkBx();
		timePOM.clickWkEndingDropDn();
		timePOM.searchWkEnding("April 9, 2021");
		timePOM.selectWkEnding("April 9, 2021");
		timePOM.clickOKBtn();
		timePOM.clickSubmitBtn();
	}
	
	//USE ZERO HOURS
	@Test 
	public void enterBMSDataZeroHours() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		timePOM.clickW3Creds();
		timePOM.sendUsername(userName);
		timePOM.sendPassword(password);
		timePOM.clickLoginBtn();
		//timePOM.clickWkEndingDropDnHomeScr();
		//timePOM.searchWkEnding("May 7, 2021");
		//timePOM.selectWkEnding("May 7, 2021");
		timePOM.clickCopyPrevWkBtn();
		timePOM.clickWkEndingDropDn();
		timePOM.searchWkEnding("April 9, 2021");
		Thread.sleep(500);
		timePOM.selectWkEnding("April 9, 2021");
		timePOM.clickOKBtn();
		timePOM.enterHours("9");
		timePOM.clickSubmitBtn();
	}

}
