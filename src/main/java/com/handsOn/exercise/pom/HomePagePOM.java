package com.handsOn.exercise.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePagePOM {
	
	private WebDriver driver;
	private Actions action;
	//private WebDriverWait wait;
				
	
	public HomePagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
	}
	
	@FindBy(id="search_str")
	private WebElement searchTxtBox; 
	
	@FindBy(xpath="//*[@id='autosuggestlist']/ul/li[1]/a")
	private WebElement autoSuggest;
	
	@FindBy(id="nsecp")
	private WebElement currentNSEValue;
	
	@FindBy(id="nsechange")
	private WebElement changeNSEValue;
	
	@FindBy(id="bsecp")
	private WebElement currentBSEValue;
	
	@FindBy(xpath="//*[@id='inp_bse_display']/div[1]/div/div[1]/div[3]")
	private WebElement changeBSEValue;
	
	@FindBy(linkText="BSE")
	private WebElement bseLink;
	
	public void searchCompName(String compName) {
		this.searchTxtBox.sendKeys(compName);	
	}
	
	
	public void clickCompName(String compName) throws InterruptedException {
		Thread.sleep(1000);
		action.click(driver.findElement(By.partialLinkText(compName))).perform();
	}
	
	
	public String getCurrentNSEValue() throws InterruptedException {
		Thread.sleep(1000);
		return this.currentNSEValue.getAttribute("data-numberanimate-value");
	}
	
	
	public String getChangeNSEValue() {
		
		String[] str = this.changeNSEValue.getText().split(" ",2);
		String str1 = null;
		
		for (int i = 0; i < str.length-1; i++) {
			str1 = str[i];
		}
		return str1;		
	}
	
	
	public String getChangeNSEPercent() {
		String[] str = this.changeNSEValue.getText().split(" ",2);
		String str1 = null;
		
		for (int i = 1; i < str.length; i++) {
			str1 = str[i];
		}
		int len = str1.length()-1;
		return str1.substring(1,len);
	}
	
	
	public String getCurrentBSEValue() {
		return this.currentBSEValue.getAttribute("data-numberanimate-value");
	}
	
	
	public String getChangeBSEValue() {
		//return this.changeBSEValue.getText();
		String[] str = this.changeBSEValue.getText().split(" ",2);
		String str1 = null;
		
		for (int i = 0; i < str.length-1; i++) {
			str1 = str[i];
		}
		return str1;
	}
	
	
	public String getChangeBSEPercent() {
		String[] str = this.changeBSEValue.getText().split(" ",2);
		String str1 = null;
		
		for (int i = 1; i < str.length; i++) {
			str1 = str[i];
		}
		return str1.substring(1,str1.length()-1);
	}
	
	
	public void clickBSELnk() {
		this.bseLink.click();
	}
	
	
}
