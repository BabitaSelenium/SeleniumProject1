package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RTTC_041_POM_AdminFilterProduct {
private WebDriver driver; 
	
	public RTTC_041_POM_AdminFilterProduct(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Path for LOGIN
	@FindBy(id="input-username")
	private WebElement userName; 
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(xpath="//*[@id=\"content\"]/div/div/div/div/div[2]/form/div[3]/button")
	private WebElement loginBtn; 
	
	//After login all path to navigate as per testcase
		
	@FindBy(xpath="//*[@id=\"button-menu\"]")
	private WebElement buttonmenu;
	
	@FindBy(linkText="Catalog")
	//(id="menu-catalog")
	private WebElement catalog;
	
	@FindBy(linkText="Products")
	//		xpath="//*[@id='menu-catalog']/ul/li[2]/a")
	private WebElement product;
	
	//Filter button path after entering price in the textbox
		@FindBy(xpath= "//input[@ id='input-price']")
		private WebElement pricetextbox;
	
	// Step 6 filter button
	@FindBy(xpath= "//button[@type='button' and @id='button-filter']")
	private WebElement filterbtn;
		
	//Step 9:Model textbox path
	@FindBy(xpath= "//input[@id='input-model']")
	private WebElement modeltextbox;
	
	//Step 11: Quantity textbox
	@FindBy(xpath= "//input[@id='input-quantity']")
	private WebElement quantityTextbox;
		
	// methods for LOGIN clicks
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	//Methods to navigates to all elements after LOGIN
	public void clickButtonMenu() {
		// TODO Auto-generated method stub
		this.buttonmenu.click();
	}
	
	public void clickCatalogMenu() {
		// TODO Auto-generated method stub
		this.catalog.click();
	}
	
	public void clickProduct() {
		// TODO Auto-generated method stub
		this.product.click();
	}
	//Giving input as 515 in textbox
	public void sendKeysPrice(String pricetextbox) {
		// TODO Auto-generated method stub
		this.pricetextbox.clear();
		this.pricetextbox.sendKeys(pricetextbox);
	}
    //click on filter
	public void filterbutton() {
		// TODO Auto-generated method stub
		this.filterbtn.click();
	}
	public void sendKeysModel(String modeltextbox) {
		// TODO Auto-generated method stub
		this.modeltextbox.clear();
		this.modeltextbox.sendKeys(modeltextbox);
	}
	public void sendKeysQuantity(String quantityTextbox) {
		// TODO Auto-generated method stub
		this.quantityTextbox.clear();
		this.quantityTextbox.sendKeys(quantityTextbox);
	}
}
