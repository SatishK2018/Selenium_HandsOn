package com.handsOn.exercise.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TimesheetPOM {
	private WebDriver driver; 
	private WebDriverWait wait;
	//private WebElement table;
	//private List<WebElement> rows;
	private Actions actions;
	//private Select wkEndingDrpDn;
	
	public TimesheetPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, 30);
	}
	
	@FindBy(id="btn-copy-prev-week")
	private WebElement copyPrevWeekBtn; 
	
	@FindBy(id="credentialSignin")
	private WebElement credSignin;
	
	@FindBy(id="user-name-input")
	private WebElement Username;
	
	@FindBy(id="password-input")
	private WebElement password;
	
	@FindBy(id="login-button")
	private WebElement loginBtn;
	
	@FindBy(id="checkbox-zero-hours")
	private WebElement zeroHrsCheckbx;
	
	@FindBy(id="mat-select-1")
	private WebElement wkEndingDropDn; 
	
	@FindBy(xpath="//BUTTON[text()='Ok']")
	private WebElement okBtn;
	
	@FindBy(xpath="//*[@col-id='hours.fri']//SPAN[@class=\"border ng-star-inserted\"]")
	private WebElement hoursTxtbx;
	
	@FindBy(id="btn-timesheet-submit")
	private WebElement submitBtn;
	
	@FindBy(id="mat-select-filter")
	private WebElement searchBx;
	
	@FindBy(id="week-ending-arrow-next")
	private WebElement wkEndingArrowNxt;
	
	@FindBy(id="mat-select-week-ending-dropdown")
	private WebElement wkEndingDropDnHome;
	
	
	
	public void clickW3Creds() {
		this.credSignin.click();
	}
	
	public void sendUsername(String username) {
		this.Username.sendKeys(username);
	}
	
	public void sendPassword(String password) {
		this.password.sendKeys(password);
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click();
	}
	
	public void clickCopyPrevWkBtn() {
		this.copyPrevWeekBtn.click();
	}
	
	public void clickZeroHrsChkBx() {
		this.zeroHrsCheckbx.click();
	}
	
	public void clickWkEndingDropDn() {
		this.wkEndingDropDn.click();
	}
	
	public void clickWkEndingDropDnHomeScr() {
		this.wkEndingDropDnHome.click();
	}
	
	public void searchWkEnding(String date) {
		this.searchBx.sendKeys(date);
	}
	
	public void selectWkEnding(String date) {
		String xPath = "//SPAN[@class='mat-option-text'][text()=' "+date+" ']";
		WebElement selectDate = driver.findElement(By.xpath(xPath));
		wait.until(ExpectedConditions.visibilityOf(selectDate));
		driver.findElement(By.xpath(xPath)).click();
	}
	
	/*public void selectWkEndingFromDrpDn(String date) {
		
		wkEndingDrpDn = new Select(this.wkEndingDropDn);
		wkEndingDrpDn.selectByVisibleText(date);
	}*/
	
	public void clickOKBtn() {
		this.okBtn.click();
	}
	
	public void clickSubmitBtn() {
		wait.until(ExpectedConditions.visibilityOf(submitBtn));
		this.submitBtn.click();
	}
	
	public void enterHours(String hours) throws InterruptedException {
		//WebElement element1 = driver.findElement(By.xpath("//div[@col-id='hours.mon']//SPAN[@class='border ng-star-inserted']"));
		//WebElement element2 = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div/div/ag-grid-angular/div/div[1]/div[2]/div[3]/div[2]/div/div/div[3]/div[2]"));
		//wait.until(ExpectedConditions.visibilityOf(element));
				
		/*Actions actions = new Actions(driver);
		actions.moveToElement(element1);
		actions.click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(element2));
		actions.moveToElement(element2);
		actions.sendKeys("9").build().perform();*/
	
		
		WebElement element1 = driver.findElement(By.xpath("//*[@col-id='hours.mon']//SPAN[@class='border ng-star-inserted']"));
		WebElement element2 = driver.findElement(By.xpath("//*[@id='container']/div/div/div/ag-grid-angular/div/div[1]/div[2]/div[3]/div[2]/div/div/div[3]/div[2]"));
		actions.moveToElement(element1);
		actions.click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(element2));
		actions.moveToElement(element2);
		actions.sendKeys(hours,Keys.TAB).build().perform();
		Thread.sleep(200);
		actions.sendKeys(hours,Keys.TAB).build().perform();
		Thread.sleep(200);
		actions.sendKeys(hours,Keys.TAB).build().perform();
		Thread.sleep(200);
		actions.sendKeys(hours,Keys.TAB).build().perform();
		Thread.sleep(200);
		actions.sendKeys(hours,Keys.TAB).build().perform();
		Thread.sleep(100);
		//submitBtn.click();
	}
}
