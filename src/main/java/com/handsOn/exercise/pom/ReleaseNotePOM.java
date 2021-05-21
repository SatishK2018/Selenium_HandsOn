package com.handsOn.exercise.pom;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReleaseNotePOM {
	private WebDriver driver; 
	private WebDriverWait wait;
	private Actions action;
	private Robot robot;	
	
	public ReleaseNotePOM(WebDriver driver1) throws Exception {
		this.driver = driver1; 
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
		robot = new Robot();
		wait = new WebDriverWait(driver, 30);
	}
	
	@FindBy(xpath="//a[text()='v1.10.0']")
	private WebElement versionNum;
	
	@FindBy(xpath="//table/tr[1]/td[2]/p[1]")
	private WebElement version;
	
	@FindBy(xpath="//table/tr[1]/td[2]/p[2]")
	private WebElement tag;
	
	@FindBy(xpath="//table/tr[1]/td[2]/p[3]")
	private WebElement buildAt;
	
	@FindBy(xpath="//table/tr[1]/td[2]/p[4]")
	private WebElement environment;
	
	@FindBy(xpath="//table/tr[1]/td[3]/p[1]")
	private WebElement proxyAPIVer;
	
	@FindBy(xpath="//table/tr[1]/td[3]/p[2]")
	private WebElement rfcAdapterVer;
	
	@FindBy(xpath="//table/tr[1]/td[3]/p[3]")
	private WebElement siwReaderVer; 
	
	@FindBy(xpath="//a[text()='Release notes']")
	private WebElement releaseNotes;
	
	@FindBy(xpath="//a[text()='Privacy policy']")
	private WebElement privacyPolicy; 
	
	@FindBy(xpath="//a[text()='Cookie policy']")
	private WebElement cookiePolicy;
	
	@FindBy(xpath="//a[text()='Disclaimer']")
	private WebElement disclaimer;
	
	@FindBy(xpath="//BUTTON[text()='Ok']")
	private WebElement okBtn;
	
	@FindBy(xpath="//BUTTON[text()='Continue']")
	private WebElement continueBtn;
	
	@FindBy(xpath="//app-release-notes-container/div/div/table/tbody/tr[1]")
	private WebElement releaseNoteInfo;
	
	@FindBy(xpath="//app-pi-notice-dialog/mat-dialog-content")
	private WebElement disclaimerInfo;
	
	@FindBy(xpath="//div[@class='widget-wrapper']/p")
	private WebElement privacyPolicyDetails;

	
	
	public void clickVersionNumber() {
		wait.until(ExpectedConditions.visibilityOf(versionNum));
		this.versionNum.click();
	}
	
	public String getVersion() {
		wait.until(ExpectedConditions.visibilityOf(version));
		return this.version.getText();
	}
	
	public String getTagName() {
		return this.tag.getText();
	}
	
	public String getBuild() {
		return this.buildAt.getText();
	}
	
	public String getEnvironment() {
		return this.environment.getText();
	}
	
	public String getAPIVersion() {
		return this.proxyAPIVer.getText();
	}
	
	public String getAdapterVersion() {
		return this.rfcAdapterVer.getText();
	}
	
	public String getReaderVersion() {
		return this.siwReaderVer.getText();
	}
	
	public void clickReleaseNotes() throws Exception {
		action.contextClick(releaseNotes).build().perform();
		this.openNewTab();
	}
	
	public void clickPrivacyPolicy() {
		action.contextClick(privacyPolicy).build().perform();
		this.openNewTab();
	}
	
	public void clickCookiePolicy() {
		action.contextClick(cookiePolicy).build().perform();
		this.openNewTab();
	}
	
	public void clickDisclaimer() {
		this.disclaimer.click();
	}
	
	public void clickOKBtn() {
		wait.until(ExpectedConditions.visibilityOf(okBtn));
		this.okBtn.click();
	}
	
	public void clickContinueBtn() {
		wait.until(ExpectedConditions.visibilityOf(continueBtn));
		this.continueBtn.click();
	}
	
	public String getReleaseNoteInfo() {
		wait.until(ExpectedConditions.visibilityOf(releaseNoteInfo));
		return this.releaseNoteInfo.getText();
	}
	
	public String getPrivacyPolicyDetails() {
		wait.until(ExpectedConditions.visibilityOf(privacyPolicyDetails));
		return this.privacyPolicyDetails.getText();
	}
	
	public String getDisclaimerInfo() {
		wait.until(ExpectedConditions.visibilityOf(disclaimerInfo));
		return this.disclaimerInfo.getText();
	}
	
	public void openNewTab() {
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	

}
