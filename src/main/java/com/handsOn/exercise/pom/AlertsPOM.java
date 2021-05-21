package com.handsOn.exercise.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPOM {
	private WebDriver driver; 
	private WebDriverWait wait;
	//private Actions action;
		
	public AlertsPOM(WebDriver driver1) throws Exception {
		this.driver = driver1; 
		PageFactory.initElements(driver, this);
		//action = new Actions(driver);
		wait = new WebDriverWait(driver, 30);
	}
	
	@FindBy(xpath="//app-root/nav/div[2]/div/div/div[2]/app-icon-alerts/a")
	private WebElement alerts;
	
	@FindBy(xpath="//div[@class='content']")
	private WebElement alertAndAnnouncemet;
	
	public void clickAlerts() {
		wait.until(ExpectedConditions.visibilityOf(alerts));
		this.alerts.click();
	}
	
	public String getAlertsAndAnnouncements() {
		wait.until(ExpectedConditions.visibilityOf(alertAndAnnouncemet));
		return this.alertAndAnnouncemet.getText();
	}
	
}
