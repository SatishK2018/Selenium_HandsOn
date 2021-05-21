package com.handsOn.exercise.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GratuityCalculatorPOM {
	private WebDriver driver; 
	//private WebDriverWait wait;
				
	
	public GratuityCalculatorPOM(WebDriver driver1) {
		this.driver = driver1; 
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(partialLinkText = "Personal Finance")
	private WebElement personalFinanceLnk; 
	
	@FindBy(partialLinkText="Gratuity Calculator")
	private WebElement gratuityCalculatorLnk;
	
	@FindBy(xpath="//SPAN[@class='radiotext'][text()='Yes']")
	private WebElement yesBtn;
	
	@FindBy(id="salary")
	private WebElement salaryTxtFld;
	
	@FindBy(id="year")
	private WebElement yearTxtFld;
	
	@FindBy(id="month")
	private WebElement monthTtFld;
	
	@FindBy(id="graduity_calc_btn")
	private WebElement calculateBtn;
	
	@FindBy(id="reset_btn")
	private WebElement resetBtn;
	
	@FindBy(id="graduity_amt")
	private WebElement gratuityAmt;
	
	
	public void clickPersonalFinanceLnk() {
		this.personalFinanceLnk.click();
	}
	
	public void clickGratuityCalculator() {
		this.gratuityCalculatorLnk.click();
	}
	
	public void clickYesBtn() {
		this.yesBtn.click();
	}
	
	public void sendSalary(String salary) {
		this.salaryTxtFld.sendKeys(salary);
	}
	
	public void sendYear(String year) {
		this.yearTxtFld.sendKeys(year);
	}
	
	public void sendMonth(String month) {
		this.monthTtFld.sendKeys(month);
	}
	
	public void clickCalculateBtn() {
		this.calculateBtn.click();
	}
	
	public void clickResetBtn() {
		this.resetBtn.click();
	}
	
	public String getGratuityAmt() {
		return this.gratuityAmt.getText();
	}
	
	public void verifyErrMesg (String actualResult, String expectedResult ) {
		Assert.assertEquals(actualResult, expectedResult);
	}
	
}
