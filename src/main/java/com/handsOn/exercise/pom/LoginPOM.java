package com.handsOn.exercise.pom;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPOM {
	private WebDriver driver; 
	private Actions action;
	//private WebDriverWait wait;
	
	public LoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
	}
	
	@FindBy(linkText="Hello, Login")
	private WebElement helloLogin; 
	
	@FindBy(linkText="Log-in")
	private WebElement login; 
	
	@FindBy(linkText="Logout")
	private WebElement logout;
	
	@FindBy(css="div.textfieldbox:nth-child(2) > input:nth-child(1)")
	private WebElement email; 
	
	@FindBy(css=".usepwd > input:nth-child(2)")
	private WebElement password; 
	
	@FindBy(css="#ACCT_LOGIN_SUBMIT")
	private WebElement loginBtn;
	
	@FindBy(xpath="//SPAN[@class='usr_nm']")
	private WebElement userName; 
	
	@FindBy(css="div.error_txt:nth-child(16)")
	private WebElement errorTxt;
	
	@FindBy(id="myframe")
	private WebElement frame;
	
	@FindBy(partialLinkText="moneycontrol.com")
	private WebElement continueLnk;
	
	
	
	public WebElement getContinueLnk() {
		return continueLnk;
	}

	public void setContinueLnk(WebElement continueLnk) {
		this.continueLnk = continueLnk;
	}

	public void clickContinueLnk () {
		this.continueLnk.click();
	}
	
	public void clickHelloLogin() throws InterruptedException {
		Thread.sleep(3000);
		action.moveToElement(this.helloLogin).build().perform();
	}
	
	public void clickLogin() throws InterruptedException {
		Thread.sleep(3000);
		this.login.click(); 
	}
	
	public void switchToChildFrame() throws InterruptedException {

		driver.switchTo().frame(driver.findElement(By.id("myframe")));
		//Thread.sleep(2000);
		/*driver.findElement(By.cssSelector("div.textfieldbox:nth-child(2) > input:nth-child(1)")).sendKeys("sjdskjdksjd");
		driver.findElement(By.id("pwd")).sendKeys(password);
		driver.findElement(By.id("ACCT_LOGIN_SUBMIT")).click();
		driver.switchTo().defaultContent();*/
	}
	
	public void sendEmail(String email) {
		this.email.clear();
		this.email.sendKeys(email);
	}
	
	public void sendPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn () {
		this.loginBtn.click();
	}
	
	public void verifyErrorMessage() {
		String actual = this.errorTxt.getText();
		String expected = "Invalid User Id/EmailID or Password. Please try again.";
		
		Assert.assertEquals(actual, expected);				
	}
	
	public void verifyEmailAddress() {
		String actual = this.userName.getText();
		String expected = " Rameshk202140rediffmailcom ";
		if(errorTxt.isDisplayed())	
			Assert.assertEquals(actual, expected);				
	}
	
	public void clickUserName() throws InterruptedException {
		Thread.sleep(3000);
		action.moveToElement(this.userName).build().perform(); 
	}
	
	public void clickLogoutBtn() throws InterruptedException {
		Thread.sleep(3000);
		this.logout.click();
	}
	
	public void switchToParentFrame() {
		driver.switchTo().parentFrame();
	}
	
	
}

