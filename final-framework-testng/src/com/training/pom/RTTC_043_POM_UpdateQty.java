package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RTTC_043_POM_UpdateQty {
private WebDriver driver; 
	
	public RTTC_043_POM_UpdateQty(WebDriver driver) {
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
	
	@FindBy(xpath="//tr[1]//td[8]//a[1]")
	//		xpath="//*[@id='menu-catalog']/ul/li[2]/a")
	// #form-product > div > table > tbody > tr:nth-child(1) > td:nth-child(8) > a
	private WebElement editIcon;
	
	//Step 4: Data tab path
	@FindBy (xpath="//a[contains(text(),'Data')]")
	private WebElement dataTab;
		
	    // Step 5: Quantity Textbox path
		@FindBy (xpath="//input[@id='input-quantity']")
		private WebElement quantityTextbox;
		
	    // Step 7: Link tab path	
		@FindBy (xpath="//a[contains(text(),'Links')]")
		private WebElement linktab;
		 
		// Step 7: all default links path<Attribute,Option,Recurring,Discount, Special,Image, Reward Points and Design tab>
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[4]/a")
		private WebElement  AttributeTab;
		
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[5]/a")
		private WebElement  optionTab;
		
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[6]/a")
		private WebElement recurringTab;
		
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[7]/a")
		private WebElement discountTab;
		
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[8]/a")
		private WebElement  specialTab;
		
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[9]/a")
		private WebElement imageTab;
		
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[10]/a")
		private WebElement rewardpointTab;
		
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[11]/a")
		private WebElement designTab;
		
		// SAVE button path
		@FindBy (xpath="//*[@id=\"content\"]/div[1]/div/div/button")
		private WebElement saveBtn;

//methods for LOGIN clicks
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
	
	public void clickEditIcon() {
		// TODO Auto-generated method stub
		this.editIcon.click();
	}
	
	public void clickonDataTab() {
		this.dataTab.click();
			}
	
	
	public void sendTextQuantityTextbox(String quantityTextbox) {
		this.quantityTextbox.clear();
		this.quantityTextbox.sendKeys(quantityTextbox);
	}
	public void clickonLinkTab() {
		this.linktab.click();
			}
	
	public void clickonAttributeTab() {
		this.AttributeTab.click();
	}
	public void clickonOptionTab() {
		this.optionTab.click();
	}
	public void clickonRecurringTab() {
		this.recurringTab.click();
	}
	public void clickonDiscountTab() {
		this.discountTab.click();
	}
	public void clickonSpecialTab() {
		this.specialTab.click();
	}
	public void clickonImageTab() {
		this.imageTab.click();
	}
	public void clickonRewardpointTab() {
		this.rewardpointTab.click();
	}
	public void clickonDesignTab() {
		this.designTab.click();
	}
	public void clickonSaveBtn() {
		this.saveBtn.click();
	}
}
