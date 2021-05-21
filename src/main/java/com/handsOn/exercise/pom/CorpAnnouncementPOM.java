package com.handsOn.exercise.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CorpAnnouncementPOM {
	private WebDriver driver; 
	private WebDriverWait wait;
	//private Actions action;
		
	public CorpAnnouncementPOM(WebDriver driver) throws Exception {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		//action = new Actions(driver);
		wait = new WebDriverWait(driver, 30);
	}
	
	
	@FindBy(partialLinkText = "Corporate Announcements")
	private WebElement corpAnnouncement;
	
			
	public void clickCorporateAnnouncement() {
		wait.until(ExpectedConditions.visibilityOf(corpAnnouncement));
		this.corpAnnouncement.click();
	}
	
	public String getCardHeader(int slideNum) {
		String xpath="//*[@id='slick-slide"+slideNum+"']/div/div[2]/div[1]/h4";
		WebElement cardHeader = driver.findElement(By.xpath(xpath));
		return cardHeader.getText();
	}
	
	public String getSubject(int slideNum) {
		String xpath="//*[@id='slick-slide"+slideNum+"']/div/div[2]/div[2]/p";
		WebElement subject = driver.findElement(By.xpath(xpath));
		return subject.getText();
	}
	
	public String getDetails(int slideNum) {
		String xpath="//*[@id='slick-slide"+slideNum+"']/div/div[2]/div[3]/p";
		WebElement details = driver.findElement(By.xpath(xpath));
		return details.getText();
	}
	
	public String getBroadcastDtTime(int slideNum) {
		String xpath="//*[@id='slick-slide"+slideNum+"']/div/div[2]/div[4]/p[1]";
		WebElement BroadcastDtTime = driver.findElement(By.xpath(xpath));
		return BroadcastDtTime.getText();
	}
	
	public void clickDownloadLink(int slideNum) {
		String xpath="//*[@id='slick-slide"+slideNum+"']/div/div[2]/div[4]/p[2]/a";
		WebElement downloadLnk = driver.findElement(By.xpath(xpath));
		downloadLnk.click();
	}
	
	
}
	
	