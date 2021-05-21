package com.handsOn.exercise.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PFCalculatorPOM {
	private WebDriver driver; 
	private Actions action;
	//private WebDriverWait wait;
				
	
	public PFCalculatorPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(partialLinkText = "Personal Finance")
	private WebElement personalFinanceLnk; 
	
	@FindBy(partialLinkText = "Provident Fund Calculator")
	private WebElement pfCalculatorLnk;
	
	@FindBy(id="id_your_age")
	private WebElement urAgeTxtBx;
	
	@FindBy(name="basic_salary_monthly")
	private WebElement basicSalTxtBx;
	
	@FindBy(name="your_contribution")
	private WebElement contriTxtBx;;
	
	@FindBy(name="employers_contribuion")
	private WebElement employerContriTxtBx;
	
	@FindBy(name="annual_increase_in_salary")
	private WebElement anualIncrSalTxtBx;
	
	@FindBy(name="intend_to_retire")
	private WebElement retireAgeTxtBx;
	
	@FindBy(name="current_epf_balance")
	private WebElement epfBalTxtBx;
	
	@FindBy(name="current_interest_rate")
	private WebElement intrsRtTxtBx;
	
	@FindBy(xpath="//*[@name=\"frm_epf\"]/table/tbody/tr[12]/td/a")
	private WebElement calculateBtn;
	
	@FindBy(xpath="//*[@id=\"result_epf\"]/table/tbody/tr/td")
	private WebElement errorMsg;
	
	@FindBy(xpath="//*[@id=\"result_epf\"]/span/span")
	private WebElement accmulatedAmt;
	
	
	
	public void clickPersonalFinanceLnk() {
		action = new Actions(driver);
		action.moveToElement(this.personalFinanceLnk).build().perform();	
		//this.personalFinanceLnk.click();
	}
	
	public void clickPFCalculator() {
		this.pfCalculatorLnk.click();
	}
	
	public void sendAge(String age) {
		this.urAgeTxtBx.clear();
		this.urAgeTxtBx.sendKeys(age);
	}
	
	public void sendBasicSalary(String basicSal) {
		this.basicSalTxtBx.clear();
		this.basicSalTxtBx.sendKeys(basicSal);
	}
	
	public void sendUrEPFContribution(String epfContribution) {
		this.contriTxtBx.clear();
		this.contriTxtBx.sendKeys(epfContribution);
	}
	
	public void sendEmployerEPFContribution(String employerEPFContribution) {
		this.employerContriTxtBx.clear();
		this.employerContriTxtBx.sendKeys(employerEPFContribution);	
	}
	
	public void sendAnualIncrSal(String anualIncrSal) {
		this.anualIncrSalTxtBx.clear();
		this.anualIncrSalTxtBx.sendKeys(anualIncrSal);
	}
	
	public void sendRetireAge(String retireAge) {
		this.retireAgeTxtBx.clear();
		this.retireAgeTxtBx.sendKeys(retireAge);
	}
	
	public void sendEPFBalance(String epfBal) {
		this.epfBalTxtBx.clear();
		this.epfBalTxtBx.sendKeys(epfBal);
	}
	
	public void sendInterestRate(String intrstRate) {
		this.intrsRtTxtBx.clear();
		this.intrsRtTxtBx.sendKeys(intrstRate);
	}
	
	public void clickCalculateBtn() {
		this.calculateBtn.click();
	}
	
	public String getErrorMsg() {
		return this.errorMsg.getText();
	}
	
	public WebElement getErrMsg() {
		return errorMsg;
	}
	
	
	public String getAccmulatedAmt() {
		return this.accmulatedAmt.getText();
	}
	
	public void verifyErrorMsg(String errMsg) {
		String expectedErrMsg = "Please putin retire age correctly.";
		Assert.assertEquals(errMsg, expectedErrMsg);
	}
}
