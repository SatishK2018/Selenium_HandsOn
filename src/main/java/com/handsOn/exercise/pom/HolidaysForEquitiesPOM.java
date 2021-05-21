package com.handsOn.exercise.pom;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HolidaysForEquitiesPOM {
	private WebDriver driver; 
	private WebDriverWait wait;
	private Actions action;
		
	public HolidaysForEquitiesPOM(WebDriver driver1) throws Exception {
		this.driver = driver1; 
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
		wait = new WebDriverWait(driver, 30);
	}
	
	
	@FindBy(linkText = "RESOURCES")
	private WebElement resourcesLnk;
	
	@FindBy(linkText = "Holidays")
	private WebElement holidaysLnk;
	
	@FindBy(partialLinkText = "Clearing Holidays")
	private WebElement clearingHolidaysLnk;
	
	@FindBy(id="holidayTable")
	private WebElement holidayTradingTbl;
	
	@FindBy(id = "holidayClearingTable")
	private WebElement holidayClearingTbl;
	
	@FindBy(xpath = "//*[@id='holiday_trading']/div[2]/div/p[1]")
	private WebElement textMsg;
	
	@FindBy(id = "weekendHolidayTable")
	private WebElement wkendHolidayTbl;
			
	
	public void clickResourcesLnk() {
		wait.until(ExpectedConditions.visibilityOf(resourcesLnk));
		action.moveToElement(this.resourcesLnk).build().perform();
	}
	
	public void clickHolidaysLnk() {
		driver.manage().deleteAllCookies();
		wait.until(ExpectedConditions.visibilityOf(holidaysLnk));
		action.click(this.holidaysLnk).build().perform();
	}
	
	public void clickClearingHolidaysLnk() {
		driver.manage().deleteAllCookies();
		wait.until(ExpectedConditions.visibilityOf(clearingHolidaysLnk));
		this.clearingHolidaysLnk.click();
	}
	
	public String getTextMesg() {
		return this.textMsg.getText();
	}
	
	public WebElement getHolidayTradingTbl() {
		return holidayTradingTbl;
	}

	public void setHolidayTradingTbl(WebElement holidayTradingTbl) {
		this.holidayTradingTbl = holidayTradingTbl;
	}

	public WebElement getHolidayClearingTbl() {
		return holidayClearingTbl;
	}

	public void setHolidayClearingTbl(WebElement holidayClearingTbl) {
		this.holidayClearingTbl = holidayClearingTbl;
	}

	public WebElement getWkendHolidayTbl() {
		return wkendHolidayTbl;
	}

	public void setWkendHolidayTbl(WebElement wkendHolidayTbl) {
		this.wkendHolidayTbl = wkendHolidayTbl;
	}

	public int getTotalNumberOfRows_TradingHolidayTbl() {
		driver.manage().deleteAllCookies();
		wait.until(ExpectedConditions.visibilityOf(holidayTradingTbl));
		List<WebElement>TotalRowsList = holidayTradingTbl.findElements(By.tagName("tr"));
		return TotalRowsList.size();
	}
	
	public int getTotalNumberOfRows_ClearingHolidayTbl() {
		driver.manage().deleteAllCookies();
		wait.until(ExpectedConditions.visibilityOf(holidayClearingTbl));
		List<WebElement>TotalRowsList = holidayClearingTbl.findElements(By.tagName("tr"));
		return TotalRowsList.size();
	}
	
	public int getTotalNumberOfRows_WkEndHolidayTbl() {
		List<WebElement>TotalRowsList = wkendHolidayTbl.findElements(By.tagName("tr"));
		return TotalRowsList.size();
	}
	
	public String getSrNo(String tableID,int rowNum) {
		return driver.findElement(By.xpath("//*[@id='"+tableID+"']/tbody/tr["+rowNum+"]/td[1]")).getText();
	}
	
	public String getDate(String tableID,int rowNum) {
		return driver.findElement(By.xpath("//*[@id='"+tableID+"']/tbody/tr["+rowNum+"]/td[2]")).getText();
	}
	
	public String getDay(String tableID,int rowNum) {
		return driver.findElement(By.xpath("//*[@id='"+tableID+"']/tbody/tr["+rowNum+"]/td[3]")).getText();
	}
	
	public String getDescription(String tableID,int rowNum) {
		return driver.findElement(By.xpath("//*[@id='"+tableID+"']/tbody/tr["+rowNum+"]/td[4]")).getText();
	}
}
	
	
	
	
	
//*[@id="holidayClearingTable"]/tbody/tr[1]/td[1]
//*[@id="holidayTable"]/tbody/tr[1]/td[1]
//*[@id="weekendHolidayTable"]/tbody/tr[1]/td[1]	