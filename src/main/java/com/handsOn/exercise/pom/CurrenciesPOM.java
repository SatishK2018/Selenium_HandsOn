package com.handsOn.exercise.pom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class CurrenciesPOM {
	private WebDriver driver; 
	private WebElement currency;
	private WebElement price;
	private ArrayList<Double> prices;
	private List<WebElement> rows;
	private WebElement tableRows;
	private HashMap<String, Double> map;
    private LinkedHashMap<String, Double> sortedMap;
    private ArrayList<Double> list;
	private int numRows;
	
	public CurrenciesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Commodities")
	private WebElement commoditiesLnk; 
	
	@FindBy(partialLinkText="Currencies")
	private WebElement currenciesLnk; 
	
	
	public void clickCurrencies() {
		this.currenciesLnk.click();
	}
	
	/*public void getCurrencyValue() {
 		tableRows = driver.findElement(By.xpath("//*[@class=\"curdata\"]/table/tbody"));
		rows = tableRows.findElements(By.tagName("tr"));
		prices= new ArrayList<Double>();
		numRows = rows.size();
		
		for(int i=3; i<numRows;i++) {
			String currencyXpath = "//*[@class=\"curdata\"]/table/tbody/tr["+i+"]/td[1]/strong";
			String priceXpath = "//*[@class=\"curdata\"]/table/tbody/tr["+i+"]/td[2]";
			
			currency = driver.findElement(By.xpath(currencyXpath));
			price = driver.findElement(By.xpath(priceXpath));
						
			System.out.print(currency.getText());
			System.out.println(price.getText());
			
			Double num = Double.parseDouble(price.getText());
			prices.add(num);			
		}
	}
	
	public void sortPriceValues() {
		Collections.sort(prices);
		Collections.reverse(prices);
	}*/
	
	
	public ArrayList<Double> getSortedValues() {
		return prices;
	}
	
	
	public LinkedHashMap<String, Double> getSortedPrices() {
		
		map = new HashMap<>();
        sortedMap = new LinkedHashMap<>();
        list = new ArrayList<>();
        tableRows = driver.findElement(By.xpath("//*[@class=\"curdata\"]/table/tbody"));
		rows = tableRows.findElements(By.tagName("tr"));
		prices= new ArrayList<Double>();
		numRows = rows.size();
        
        for(int i=3; i<numRows;i++) {
			String currencyXpath = "//*[@class=\"curdata\"]/table/tbody/tr["+i+"]/td[1]/strong";
			String priceXpath = "//*[@class=\"curdata\"]/table/tbody/tr["+i+"]/td[2]";
			
			currency = driver.findElement(By.xpath(currencyXpath));
			price = driver.findElement(By.xpath(priceXpath));
						
			//System.out.print(currency.getText());
			//System.out.println(price.getText());
			
			Double num = Double.parseDouble(price.getText());
			map.put(currency.getText(), num);
        	}
        
			for (Map.Entry<String, Double> entry : map.entrySet()) {
				list.add(entry.getValue());
			}
			Collections.sort(list); 
			Collections.reverse(list);
        
			for (Double num : list) {
				for (Entry<String, Double> entry : map.entrySet()) {
					if (entry.getValue().equals(num)) {
						sortedMap.put(entry.getKey(), num);
                }
            }
        }
        //System.out.println(sortedMap);
        return sortedMap;
    }
	
}


