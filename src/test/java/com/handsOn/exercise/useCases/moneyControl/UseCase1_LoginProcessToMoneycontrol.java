
package com.handsOn.exercise.useCases.moneyControl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.handsOn.exercise.dataproviders.LoginDataProvider;
//import com.handsOn.exercise.generics.ScreenShot;
import com.handsOn.exercise.pom.LoginPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;

public class UseCase1_LoginProcessToMoneycontrol {
	
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	//private ScreenShot screenShot;
		

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.FIREFOX);
		loginPOM = new LoginPOM(driver);
		baseUrl = properties.getProperty("moneycontrol_baseURL");
		//screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test (dataProvider = "xls-inputs_positive case", dataProviderClass = LoginDataProvider.class,priority = 1)
	public void loginToMoneycontrol_PositiveTest(String email, String password,String scenario) throws InterruptedException  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS); //int i=0;
		
		//loginPOM.clickContinueLnk();
		loginPOM.clickHelloLogin();
		loginPOM.clickLogin();
		loginPOM.switchToChildFrame();
		loginPOM.sendEmail(email);
		loginPOM.sendPassword(password);
		loginPOM.clickLoginBtn();
		//screenShot.captureScreenShot("Login Process To Moneycontrol.com"+"-"+scenario+"_screen"+ i++);
		loginPOM.switchToParentFrame();
		loginPOM.clickUserName();
		//screenShot.captureScreenShot("Login Process To Moneycontrol.com"+"-"+scenario+"_screen"+ i++);
		loginPOM.clickLogoutBtn();
		//screenShot.captureScreenShot("Login Process To Moneycontrol.com"+"-"+scenario+"_screen"+ i);
	}
	
	@Test (dataProvider = "xls-inputs_negative case", dataProviderClass = LoginDataProvider.class,priority = 2)
	public void loginToMoneycontrol_NegativeTest(String email, String password,String scenario) throws InterruptedException  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS); 
		
		//loginPOM.clickContinueLnk();
		loginPOM.clickHelloLogin();
		loginPOM.clickLogin();
		loginPOM.switchToChildFrame();
		loginPOM.sendEmail(email);
		loginPOM.sendPassword(password);
		loginPOM.clickLoginBtn();
		loginPOM.verifyErrorMessage();
		//screenShot.captureScreenShot("Login Process To Moneycontrol.com"+"-"+scenario);
	}
}
