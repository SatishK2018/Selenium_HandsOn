package com.handsOn.exercise.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.WebDriverWait;


public class CommoditiesPOM {
	private WebDriver driver; 
	private WebElement goldRateTab;
	//private Actions action;
	//private WebDriverWait wait;
	
	public CommoditiesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		//action = new Actions(driver);
	}
	
	@FindBy(linkText="Commodities")
	private WebElement commoditiesLnk; 
	
	@FindBy(partialLinkText="GOLD")
	private WebElement GoldLnk; 
	
	
	public void clickCommodities() {
		this.commoditiesLnk.click();
	}
	
	
	public void clickGOLDLnk() {
		this.GoldLnk.click();
	}
	
		
	public void clickTab(int tab){
		String id = "commodity_tab";
		String newID = (String)id + tab;
		goldRateTab = driver.findElement(By.xpath("//*[@id="+"\""+newID+"\""+"]"));
		goldRateTab.click();
	}
	
	public String getTabName() {
		return this.goldRateTab.getText();
	}
	
	
	public String getPrice(int index) {
		String id = "commodity_innertab";
		String newID = (String)id + index;
		String xPath = "//*[@id="+"\""+newID+"\""+"]/div/table/tbody/tr/td[1]/div[2]/div[1]/span[1]";
		
		return driver.findElement(By.xpath(xPath)).getText();
		
	}
	
	
	public String getChangePrice(int index) {
		String id = "commodity_innertab";
		String newID = (String)id + index;
		String priceXPath = "//*[@id="+"\""+newID+"\""+"]/div/table/tbody/tr/td[1]/div[2]/div[1]/strong";
		
		WebElement changePrice = driver.findElement(By.xpath(priceXPath));
		String[] str = changePrice.getText().split(" ",2);
		String str1 = null;
			
				for (int i = 0; i < str.length-1; i++) {
					str1 = str[i];
				}
					return str1;
		}
		
	
	public String getChangePercent(int index) {
		String id = "commodity_innertab";
		String newID = (String)id + index;
		String xPath = "//*[@id="+"\""+newID+"\""+"]/div/table/tbody/tr/td[1]/div[2]/div[1]/strong";
		int len;
		
		WebElement changePrice = driver.findElement(By.xpath(xPath));
		String[] str = changePrice.getText().split(" ",2);
		String str1 = null;
		
			for (int i = 1; i < str.length; i++) {
				str1 = str[i];
			}
			len = str1.length()-1;
			return str1.substring(1,len);
	}
	
}

