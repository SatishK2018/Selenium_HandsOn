package com.handsOn.exercise.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ClassroomCoursesPOM {
	private WebDriver driver; 
	private WebDriverWait wait;
	private Actions action;
		
	public ClassroomCoursesPOM(WebDriver driver1) throws Exception {
		this.driver = driver1; 
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
		wait = new WebDriverWait(driver, 30);
	}
	
	
	@FindBy(linkText = "LEARN")
	private WebElement learnLnk;
	
	@FindBy(linkText = "Class Room Courses")
	private WebElement classroomCourcesLnk;
	
	@FindBy(partialLinkText = "Currency and Commodities")
	private WebElement currAndCommLnk;
	
	@FindBy(partialLinkText = "COURSE OUTLINE")
	private WebElement courseOutlineLnk;
	
	@FindBy(partialLinkText = "COURSE OFFERING")
	private WebElement courseOfferingLnk;
	
	@FindBy(partialLinkText = "ELIGIBILITY")
	private WebElement eligibilityLnk;
	
	@FindBy(partialLinkText = "BENEFITS")
	private WebElement benefitsLnk;
	
	@FindBy(xpath = "//*[contains(@id,'-0')]/div/p")
	private WebElement overview;
	
	@FindBy(xpath = "//*[contains(@id,'-1')]/div/ul")
	private WebElement courseOutline;
	
	@FindBy(xpath = "//*[contains(@id,'-2')]/div/ul")
	private WebElement courseOffering;
	
	@FindBy(xpath = "//*[contains(@id,'-3')]/div/ul")
	private WebElement courseEligibility;
	
	@FindBy(xpath = "//*[contains(@id,'-4')]/div")
	private WebElement benefits;
	
	@FindBy (xpath="//table/tbody/tr/td[1]")
	private WebElement courseDuration;	
			
	
	
	public void clickLearnLnk() {
		wait.until(ExpectedConditions.visibilityOf(learnLnk));
		action.moveToElement(this.learnLnk).build().perform();
	}
	
	public void clickClassroomCoursesLnk() {
		driver.manage().deleteAllCookies();
		wait.until(ExpectedConditions.visibilityOf(classroomCourcesLnk));
		action.click(this.classroomCourcesLnk).build().perform();
	}
	
	public void clickCurrencyAndCommodities() {
		driver.manage().deleteAllCookies();
		wait.until(ExpectedConditions.visibilityOf(currAndCommLnk));
		this.currAndCommLnk.click();
	}
	
	public void clickCourseOutline() {
		driver.manage().deleteAllCookies();
		this.courseOutlineLnk.click();
	}
	
	public void clickCourseOffering() {
		driver.manage().deleteAllCookies();
		this.courseOfferingLnk.click();
	}
	
	public void clickEligibility() {
		driver.manage().deleteAllCookies();
		this.eligibilityLnk.click();
	}
	
	public void clickBenefits() {
		driver.manage().deleteAllCookies();
		this.benefitsLnk.click();
	}
	
	public String getCourseDuration() {
		return this.courseDuration.getText();
	}
	
	public String getOverview() {
		return this.overview.getText();
	}
	
	public String getCourseOutline() {
		return this.courseOutline.getText();
	}

	public String getCourseOffering() {
		return this.courseOffering.getText();
	}
	
	public String getCourseEligibility() {
		return this.courseEligibility.getText();
	}
	
	public String getCourseBenefits() {
		return this.benefits.getText();
	}
}
	
	
