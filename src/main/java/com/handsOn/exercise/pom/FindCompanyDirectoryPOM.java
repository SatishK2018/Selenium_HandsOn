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


public class FindCompanyDirectoryPOM {
	private WebDriver driver; 
	private WebDriverWait wait;
	private Actions action;
	private WebElement selectedOption;
		
	public FindCompanyDirectoryPOM(WebDriver driver) throws Exception {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
		wait = new WebDriverWait(driver, 30);
	}
	
	
	@FindBy(linkText = "INVEST")
	private WebElement investLnk;
	
	@FindBy(linkText = "LIST")
	private WebElement listLnk;
	
	@FindBy(partialLinkText = "Company Directory")
	private WebElement compDirectoryOption;
	
	@FindBy(partialLinkText = "Click here to Open Company Directory")
	private WebElement openCompDirectoryLnk;
	
	@FindBy(id = "CREquityInput")
	private WebElement searchTxtBx;
	
	//@FindBy(xpath = "//button[@type='button'][text()='GO']")
	//private WebElement goBtn;
			
	
	public void clickInvestLnk() {
		wait.until(ExpectedConditions.visibilityOf(investLnk));
		action.moveToElement(this.investLnk).build().perform();
	}
	
	public void clickListLnk() {
		wait.until(ExpectedConditions.visibilityOf(listLnk));
		action.moveToElement(this.listLnk).build().perform();
	}
	
	public void clickCompDirectoryLnk() {
		driver.manage().deleteAllCookies();
		wait.until(ExpectedConditions.visibilityOf(compDirectoryOption));
		this.compDirectoryOption.click();
	}
	
	public void clickOpenCompDirectoryLnk() throws Exception {
		driver.manage().deleteAllCookies();
		wait.until(ExpectedConditions.visibilityOf(openCompDirectoryLnk));
		this.openCompDirectoryLnk.click();
	}
	
	public void searchCompName(String searchWord) throws Exception {
		driver.manage().deleteAllCookies();
		wait.until(ExpectedConditions.visibilityOf(searchTxtBx));
		this.searchTxtBx.clear();
		this.searchTxtBx.click();
		this.searchTxtBx.sendKeys(searchWord);
	}
	
	public void clickGOBtn() throws Exception {
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].style.transform='scale(1)';", goBtn);
		driver.manage().deleteAllCookies();
		WebElement goBtn = driver.findElement(By.cssSelector("#company_equity > div.row.align-items-center.mb-2 > div:nth-child(2) > div > button"));
		wait.until(ExpectedConditions.elementToBeClickable(goBtn));
		//this.goBtn.click();
		Thread.sleep(1000);
		goBtn.click();
		//action.moveToElement(goBtn).click().build().perform();
	}
	
	public String getOfficeType(int row) {
		String xpath="//*[@id='CFcorpComDirEquityTable']/tbody/tr["+row+"]/td[1]";
		WebElement officeType = driver.findElement(By.xpath(xpath));
		return officeType.getText();
	}
	
	public String getAddress(int row) {
		String xpath="//*[@id='CFcorpComDirEquityTable']/tbody/tr["+row+"]/td[2]";
		WebElement address = driver.findElement(By.xpath(xpath));
		return address.getText();
	}
	
	public String getCity(int row) {
		String xpath="//*[@id='CFcorpComDirEquityTable']/tbody/tr["+row+"]/td[3]";
		WebElement city = driver.findElement(By.xpath(xpath));
		return city.getText();
	}
	
	public String getPin(int row) {
		String xpath="//*[@id='CFcorpComDirEquityTable']/tbody/tr["+row+"]/td[4]";
		WebElement pin = driver.findElement(By.xpath(xpath));
		return pin.getText();
	}
	
	public String getTelephoneNum(int row) {
		String xpath="//*[@id='CFcorpComDirEquityTable']/tbody/tr["+row+"]/td[5]";
		WebElement telephoneNum = driver.findElement(By.xpath(xpath));
		return telephoneNum.getText();
	}
	
	public String getFaxNum(int row) {
		String xpath="//*[@id='CFcorpComDirEquityTable']/tbody/tr["+row+"]/td[6]";
		WebElement faxNum = driver.findElement(By.xpath(xpath));
		return faxNum.getText();
	}
	
	public String getEmail(int row) {
		String xpath="//*[@id='CFcorpComDirEquityTable']/tbody/tr["+row+"]/td[7]";
		WebElement email = driver.findElement(By.xpath(xpath));
		return email.getText();
	}
	
	public String getCompWebsite(int row) {
		String xpath="//*[@id='CFcorpComDirEquityTable']/tbody/tr["+row+"]/td[8]";
		WebElement compWebsite = driver.findElement(By.xpath(xpath));
		return compWebsite.getText();
	}
	
	public void selectItemFromAutoSuggestList(String option) {
		driver.manage().deleteAllCookies();
		List<WebElement> list = driver.findElements(By.xpath("//*[@class=\"tt-dataset tt-dataset-Company\"]"));
		//System.out.println("Auto Suggest List ::" + list.size());
		
		switch(option) {
			
			case "First":
				String xpath1 = "//*[@id='company_equity']/div[1]/div[1]/div/span/div/div/div[1]/p/span[1]";
				selectedOption = driver.findElement(By.xpath(xpath1));
				//System.out.println(option.getText());
				action.moveToElement(selectedOption).click().build().perform();
				break;
			case "Middle":
				int mid = (list.size()/2)+1;
				String xpath2 = "//*[@id='company_equity']/div[1]/div[1]/div/span/div/div/div["+mid+"]/p/span[1]";
				selectedOption = driver.findElement(By.xpath(xpath2));
				//System.out.println(option.getText());
				action.moveToElement(selectedOption).click().build().perform();
				break;
			case "Last":
				int last= list.size()+1;
				String xpath3 = "//*[@id='company_equity']/div[1]/div[1]/div/span/div/div/div["+last+"]/p/span[1]";
				selectedOption = driver.findElement(By.xpath(xpath3));
				//System.out.println(option.getText());
				action.moveToElement(selectedOption).click().build().perform();
				break;
			default:
				String xpath4 = "//*[@id='company_equity']/div[1]/div[1]/div/span/div/div/div[1]/p/span[1]";
				selectedOption = driver.findElement(By.xpath(xpath4));
				//System.out.println(option.getText());
				action.moveToElement(selectedOption).click().build().perform();
				break;
		}
		
		/* for(int i=1;i<list.size()+1;i++) {
				String xpath = "//*[@id=\"company_equity\"]/div[1]/div[1]/div/span/div/div/div["+i+"]/p/span[1]";
				WebElement option = driver.findElement(By.xpath(xpath));
				System.out.println(option.getText());
			}*/
	}
	
	public int getTotalNumberOfRows() {
		WebElement TogetRows = driver.findElement(By.xpath("//table[@id='CFcorpComDirEquityTable']"));
		List<WebElement>TotalRowsList = TogetRows.findElements(By.tagName("tr"));
		return TotalRowsList.size();
	}
	
	public WebElement getSearchTxtBx() {
		return searchTxtBx;
	}

	public void setSearchTxtBx(WebElement searchTxtBx) {
		this.searchTxtBx = searchTxtBx;
	}
	

	public WebElement getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(WebElement selectedOption) {
		this.selectedOption = selectedOption;
	}
	
}
	
	