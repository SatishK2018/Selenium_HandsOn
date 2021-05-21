package com.handsOn.exercise.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FeatureVotingPOM {
	private WebDriver driver; 
	private WebDriverWait wait;
	//private Actions action;
		
	public FeatureVotingPOM(WebDriver driver) throws Exception {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		//action = new Actions(driver);
		wait = new WebDriverWait(driver, 30);
	}
	
	@FindBy(xpath="//a[text()='Feature voting']")
	private WebElement featureVoting;
	
	@FindBy(partialLinkText = "In voting")
	private WebElement inVoting;
	
	@FindBy(partialLinkText = "In development")
	private WebElement inDevelopment;
	
	@FindBy(partialLinkText = "Implemented")
	private WebElement implemented;
	
	@FindBy(partialLinkText = "Deferred")
	private WebElement deferred;
	
	@FindBy(xpath="//mat-paginator/div/div/div/button[1]")
	private WebElement prevBtn;
	
	@FindBy(xpath="//mat-paginator/div/div/div/button[2]")
	private WebElement nextBtn;
	
	@FindBy(xpath="//div[@class='mat-paginator-range-label']")
	WebElement pages;
	
	
	
	
	public WebElement getNextBtn() {
		return nextBtn;
	}

	public void setNextBtn(WebElement nextBtn) {
		this.nextBtn = nextBtn;
	}

	public void clickFeatureVoting() {
		wait.until(ExpectedConditions.visibilityOf(featureVoting));
		this.featureVoting.click();
	}
	
	public void clickInVoting() {
		wait.until(ExpectedConditions.visibilityOf(inVoting));
		this.inVoting.click();
	}
	
	public String getInVotingCount() {
		wait.until(ExpectedConditions.visibilityOf(inVoting));
		return this.inVoting.getText();
	}
	
	public void clickInDevelopment() {
		wait.until(ExpectedConditions.visibilityOf(inDevelopment));
		this.inDevelopment.click();
	}
	
	public String getInDevelopmentCount() {
		wait.until(ExpectedConditions.visibilityOf(inDevelopment));
		return this.inDevelopment.getText();
	}
	
	public void clickImplemented() {
		wait.until(ExpectedConditions.visibilityOf(implemented));
		this.implemented.click();
	}
	
	public String getImplementedCount() {
		wait.until(ExpectedConditions.visibilityOf(implemented));
		return this.implemented.getText();
	}
	
	public void clickDeferred() {
		wait.until(ExpectedConditions.visibilityOf(deferred));
		this.deferred.click();
	}
	
	public String getDeferredCount() {
		wait.until(ExpectedConditions.visibilityOf(deferred));
		return this.deferred.getText();
	}
	
	public void clickPreviousBtn() {
		this.prevBtn.click();
	}
	
	public void clickNextBtn() {
		this.nextBtn.click();
	}
	
	public String getData(String xpath) {
		
		return driver.findElement(By.xpath(xpath)).getText();
	}
	
	public int getTotalNumberOfRows() {
		WebElement TogetRows = driver.findElement(By.xpath("//app-root/app-feature-voting-container/div/div/table/tbody"));
		List<WebElement>TotalRowsList = TogetRows.findElements(By.tagName("tr"));
		return TotalRowsList.size();
		
	}
}
	
	