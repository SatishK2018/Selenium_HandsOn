
package com.handsOn.exercise.useCases.timeIBM;

/* Expected “handle” to be a string, got [object Undefined] undefined
 * Getting above error when the script is executed on Geckodriver as Geckodriver has a bug in the build 
 * which I'm using for testing.It is resolved on the 0.27.0 version. Instead of upgrading the geckodriver 
 * version, I've executed this script on chromedriver
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
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
import com.handsOn.exercise.pom.EditPriorLockedPeriodPOM;
import com.handsOn.exercise.pom.ReleaseNotePOM;
import com.handsOn.exercise.pom.TimesheetPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;


public class UseCase10_ReleaseNoteDetails {
	
	private WebDriver driver;
	//private WebDriverWait wait;
	private String baseUrl;
	private String userName;
	private String password;
	private ReleaseNotePOM releaseNotePOM;
	private TimesheetPOM timePOM;
	private EditPriorLockedPeriodPOM editLkdPrdPOM;
	private static Properties properties;
	private XWPFDocument document;
	private XWPFParagraph paragraph;
	private XWPFRun run;
	private String parent;
	private Set<String> winids;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		
		document = new XWPFDocument();
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		
		releaseNotePOM = new ReleaseNotePOM(driver);
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
	public void releaseNoteDetails() throws Exception, Throwable  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		FileOutputStream out = new FileOutputStream(new File("./resources/ReleaseNoteDetails.docx"));
		//Parent Window
		parent = driver.getWindowHandle();
		
		editLkdPrdPOM.clickCredentialSignin();
		timePOM.sendUsername(userName);
		timePOM.sendPassword(password);
		timePOM.clickLoginBtn();
		
		//Application Version Details
		releaseNotePOM.clickVersionNumber();
		setHeading("About Time@IBM");
		run.setText(releaseNotePOM.getVersion());
		run.addBreak();
		run.setText(releaseNotePOM.getTagName());
		run.addBreak();
		run.setText(releaseNotePOM.getBuild());
		run.addBreak();
		run.setText(releaseNotePOM.getEnvironment());
		run.addBreak();
		run.setText(releaseNotePOM.getAPIVersion());
		run.addBreak();
		run.setText(releaseNotePOM.getAdapterVersion());
		run.addBreak();
		run.setText(releaseNotePOM.getReaderVersion());
		run.addBreak();
		
		//Release Notes
		setHeading("Release notes");
		releaseNotePOM.clickReleaseNotes();
		switchToChildWindow();
		run.setText(releaseNotePOM.getReleaseNoteInfo());
		run.addBreak();
		driver.close();
		driver.switchTo().window(parent);
		
		//Privacy Policy
		setHeading("IBM Internal Privacy Statement");
		releaseNotePOM.clickPrivacyPolicy();
		switchToChildWindow();
		run.setText(releaseNotePOM.getPrivacyPolicyDetails());
		run.addBreak();
		driver.close();
		driver.switchTo().window(parent);
		
		//Disclaimer
		setHeading("Disclaimer");
		releaseNotePOM.clickDisclaimer();
		run.setText(releaseNotePOM.getDisclaimerInfo());
		releaseNotePOM.clickContinueBtn();
		
		document.write(out);
	    out.close();
	    System.out.println("ReleaseNoteDetails.docx written successully");
			
	}
	
	public void setHeading(String heading) {
		run.setFontSize(14);
		run.addBreak();
		run.setText(heading);
		run.addBreak();
	}
	
	public void switchToChildWindow() throws Exception {
		winids = driver.getWindowHandles();
		Thread.sleep(200);
		driver.switchTo().window(winids.toArray()[1].toString());
	}

}
