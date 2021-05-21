package com.handsOn.exercise.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIOptionsPOM {
	private WebDriver driver; 
	private WebDriverWait wait;
	//private Actions action;
		
	
	public UIOptionsPOM(WebDriver driver1) {
		this.driver = driver1; 
		PageFactory.initElements(driver, this);
		//action = new Actions(driver);
		wait = new WebDriverWait(driver, 30);
	}
	
	@FindBy(id="btn-toolbar-grid-switch")
	private WebElement gridBtn;
	
	@FindBy(id="btn-toolbar-weekend")
	private WebElement wkendBtn;
	
	@FindBy(id="btn-toolbar-expand")
	private WebElement expandBtn;
	
	@FindBy(id="btn-toolbar-collapse")
	private WebElement collapseBtn;
	
	@FindBy(id="btn-toolbar-template")
	private WebElement templateBtn;
	
	@FindBy(id="btn-toolbar-undo")
	private WebElement undoBtn;
	
	@FindBy(xpath="//BUTTON[text()='Ok']")
	private WebElement okBtn;
	
	
	public void clickToolbarGridSwitch() {
		wait.until(ExpectedConditions.visibilityOf(gridBtn));
		this.gridBtn.click();
	}
	
	public void clickToolbarWkendBtn() {
		wait.until(ExpectedConditions.visibilityOf(wkendBtn));
		this.wkendBtn.click();
	}
	
	public void clickToolbarExpandBtn() {
		wait.until(ExpectedConditions.visibilityOf(expandBtn));
		this.expandBtn.click();
	}
	
	public void clickToolbarCollapseBtn() {
		wait.until(ExpectedConditions.visibilityOf(collapseBtn));
		this.collapseBtn.click();
	}
	
	public void clickToolbarTepmlateBtn() {
		wait.until(ExpectedConditions.visibilityOf(templateBtn));
		this.templateBtn.click();
	}
	
	public void clickToolbarUndoBtn() {
		wait.until(ExpectedConditions.visibilityOf(undoBtn));
		this.undoBtn.click();
	}
	
	public void clickOKBtn() {
		wait.until(ExpectedConditions.visibilityOf(okBtn));
		this.okBtn.click();
	}
	

}
