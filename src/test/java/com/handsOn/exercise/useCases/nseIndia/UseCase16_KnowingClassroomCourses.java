
package com.handsOn.exercise.useCases.nseIndia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.handsOn.exercise.pom.ClassroomCoursesPOM;
import com.handsOn.exercise.utility.DriverFactory;
import com.handsOn.exercise.utility.DriverNames;


public class UseCase16_KnowingClassroomCourses {
	
	private WebDriver driver;
	private String baseUrl;
	private ClassroomCoursesPOM coursesPOM;
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
		baseUrl = properties.getProperty("nseIndia_baseURL");
		coursesPOM = new ClassroomCoursesPOM(driver);
		document = new XWPFDocument();
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		driver.get(baseUrl);
		driver.manage().deleteAllCookies();
		
	}

	@AfterMethod
	public void tearDown() throws Exception {
		//Thread.sleep(100);
		driver.quit();		
	}
	
	
	@Test 
	public void equitiesHolidays() throws Exception, Throwable  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		FileOutputStream out = new FileOutputStream(new File("./resources/Classroom Course Details.docx"));
		
		coursesPOM.clickLearnLnk();
		coursesPOM.clickClassroomCoursesLnk();
		driver.manage().deleteAllCookies();
		coursesPOM.clickCurrencyAndCommodities();
		Thread.sleep(200);
		run.setFontSize(20);
		run.setText("Currency And Commodities");
		run.addBreak();
		run.setFontSize(14);
		run.setText(coursesPOM.getCourseDuration());
		run.addBreak();
		run.setFontSize(20);
		run.setText("OVERVIEW");
		run.addBreak();
		run.setText(coursesPOM.getOverview());
		run.addBreak();
		coursesPOM.clickCourseOutline();
		run.setFontSize(20);
		run.setText("COURSE OUTLINE");
		run.addBreak();
		run.setFontSize(14);
		run.setText(coursesPOM.getCourseOutline());
		run.addBreak();
		coursesPOM.clickCourseOffering();
		run.setFontSize(20);
		run.setText("COURSE OFFERING");
		run.addBreak();
		run.setFontSize(14);
		run.setText(coursesPOM.getCourseOffering());
		run.addBreak();
		coursesPOM.clickEligibility();
		run.setFontSize(20);
		run.setText("COURSE ELIGIBILITY");
		run.addBreak();
		run.setFontSize(14);
		run.setText(coursesPOM.getCourseEligibility());
		run.addBreak();
		coursesPOM.clickBenefits();
		run.setFontSize(20);
		run.setBold(true);
		run.setText("COURSE BENEFITS");
		run.addBreak();
		run.setFontSize(14);
		run.setBold(false);
		run.setText(coursesPOM.getCourseBenefits());
		run.addBreak();
		
		
		document.write(out);
	    out.close();
		
		
		System.out.println("Classroom Course Details.docx written successully");
			
	}
	
}









