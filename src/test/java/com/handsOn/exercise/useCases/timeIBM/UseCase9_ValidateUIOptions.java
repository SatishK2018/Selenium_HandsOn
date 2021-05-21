
package com.handsOn.exercise.useCases.timeIBM;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.handsOn.exercise.pom.EditPriorLockedPeriodPOM;
import com.handsOn.exercise.pom.TimesheetPOM;
import com.handsOn.exercise.pom.UIOptionsPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;
import com.handsOn.exercise.generics.ScreenShot;

public class UseCase9_ValidateUIOptions {
	
	private WebDriver driver;
	//private WebDriverWait wait;
	private String baseUrl;
	private String userName;
	private String password;
	private UIOptionsPOM uiOptionsPOM;
	private TimesheetPOM timePOM;
	private EditPriorLockedPeriodPOM editLkdPrdPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.FIREFOX);
		uiOptionsPOM = new UIOptionsPOM(driver);
		timePOM = new TimesheetPOM(driver);
		editLkdPrdPOM = new EditPriorLockedPeriodPOM(driver);
		baseUrl = properties.getProperty("time@IBM");
		userName = properties.getProperty("username");
		password = properties.getProperty("password");
		screenShot = new ScreenShot(driver);
		//wait = new WebDriverWait(driver,5);
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		//Thread.sleep(1000);
		driver.quit();		
	}
	
	
	@Test 
	public void validateUIOptions() throws Exception, Throwable  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		editLkdPrdPOM.clickCredentialSignin();
		timePOM.sendUsername(userName);
		timePOM.sendPassword(password);
		timePOM.clickLoginBtn();
		/*timePOM.clickWkEndingDropDnHomeScr();
		timePOM.searchWkEnding("May 14, 2021");
		timePOM.selectWkEnding("May 14, 2021");
		timePOM.clickCopyPrevWkBtn();
		timePOM.clickWkEndingDropDn();
		timePOM.searchWkEnding("April 9, 2021");
		Thread.sleep(500);
		timePOM.selectWkEnding("April 9, 2021");
		timePOM.clickOKBtn();*/
		uiOptionsPOM.clickToolbarGridSwitch();
		screenShot.captureScreenShot("UI Options_Compact View");
		Thread.sleep(100);
		uiOptionsPOM.clickToolbarGridSwitch();
		screenShot.captureScreenShot("UI Options_Standard View");
		Thread.sleep(100);
		uiOptionsPOM.clickToolbarWkendBtn();
		screenShot.captureScreenShot("UI Options_Show Weekend");
		Thread.sleep(100);
		uiOptionsPOM.clickToolbarCollapseBtn();
		screenShot.captureScreenShot("UI Options_Collapse All");
		Thread.sleep(100);
		uiOptionsPOM.clickToolbarExpandBtn();
		screenShot.captureScreenShot("UI Options_Expand All");
		Thread.sleep(100);
		timePOM.enterHours("9");
		Thread.sleep(200);
		uiOptionsPOM.clickToolbarUndoBtn();
		uiOptionsPOM.clickOKBtn();
			
	}

}
