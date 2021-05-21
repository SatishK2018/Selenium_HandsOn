package com.handsOn.exercise.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditPriorLockedPeriodPOM {
	private WebDriver driver; 
	private WebDriverWait wait;
	//private WebElement table;
	//private List<WebElement> rows;
	private Actions action;
	//private Select wkEndingDrpDn;
	
	public EditPriorLockedPeriodPOM(WebDriver driver1) {
		this.driver = driver1; 
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
		wait = new WebDriverWait(driver, 30);
	}
	
	@FindBy(xpath="//*[@class='statuses statuses-trigger']/app-locked-status/mat-icon")
	private WebElement lockedIcon;
	
	@FindBy(xpath="/html/body/app-root/app-week-view-container/app-unread-alerts/div/div/app-alert/div/div/div[3]")
	private WebElement errorMessg;
	
	@FindBy(xpath="//SPAN[@title='Activity code - description']")
	private WebElement activityCode;
	
	@FindBy(xpath="//*[@id=\"credentialSignin\"]")
	private WebElement credSignin;
	
	
	public void clickCredentialSignin() {
		wait.until(ExpectedConditions.visibilityOf(credSignin));
		this.credSignin.click();
	}
	
	public String getToolTipMesg() {
		action.moveToElement(lockedIcon).build().perform();
		return lockedIcon.getAttribute("mattooltip");
	}
	
	public String getErrorMesg() {
		wait.until(ExpectedConditions.visibilityOf(errorMessg));
		return this.errorMessg.getText();
	}
	
	public void clickActivityCode() {
		this.activityCode.click();
	}
	
	
}
