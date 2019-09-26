package com.training.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RTTC_071_POM_AdminAddMultipleProducts {
private WebDriver driver; 
	
	public RTTC_071_POM_AdminAddMultipleProducts(WebDriver driver) {
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
	
	///for adding product path
	@FindBy (xpath="//div[@class='pull-right']//a[@class='btn btn-primary']")
	private WebElement plusbutton;
	
	//Textbox forProduct name
	@FindBy (xpath="//input[@id='input-name1']")
	private WebElement productname;

	//Textbob for Meta Tag Title
	@FindBy (xpath="//input[@id='input-meta-title1']")
	private WebElement metaTagTitle;
	
	//Step6: Data tab path
	@FindBy (xpath="//a[contains(text(),'Data')]")
	private WebElement dataTab;
	
	//Step 7: Model textbox path
	@FindBy (xpath="//input[@id='input-model']")
	private WebElement modelTextbox;
	
	    // Step 8: Price Textbox path
	    @FindBy (xpath="//input[@id='input-price']")
	    private WebElement priceTextbox;
	
	    // Step 9: Quantity Textbox path
		@FindBy (xpath="//input[@id='input-quantity']")
		private WebElement quantityTextbox;
		
	    // Step 10: Link tab path	
		@FindBy (xpath="//a[contains(text(),'Links')]")
		private WebElement linktab;
		
	    //Step 11: dropdown box not listed, so input textbox path considered
		@FindBy (xpath="//input[@id='input-category']")
		private WebElement  CategoriesTextbox;
		
		
		// Step 13: all default links path<Attribute,Option,Recurring,Discount
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[4]/a")
		private WebElement  AttributeTab;
		
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[5]/a")
		private WebElement  optionTab;
		
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[6]/a")
		private WebElement recurringTab;
		
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[7]/a")
		private WebElement discountTab;
		
		@FindBy (xpath="//*[@id=\"discount\"]/tfoot/tr/td[2]/button")
		private WebElement plusButtonDiscountTab;
		
		@FindBy (xpath="//*[@id=\"discount-row0\"]/td[2]/input")
		private WebElement quantityDiscountTab;
		
		@FindBy (xpath="//*[@id=\"discount-row0\"]/td[4]/input")
		private WebElement priceDiscountTab;
		
		
		
		
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[8]/a")
		private WebElement  specialTab;
		
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[9]/a")
		private WebElement imageTab;
		
		@FindBy (xpath="//*[@id=\"form-product\"]/ul/li[10]/a")
		private WebElement rewardpointTab;
		
		@FindBy (xpath="//*[@id=\"input-points\"]")
		private WebElement pointsRewardTab;
		
		
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
	public void clickplusButton() {
		// TODO Auto-generated method stub
		this.plusbutton.click();
	}
	public void sendproductname(String productname) {
		this.productname.clear();
		this.productname.sendKeys(productname);
		// this.productname.getText();
	}
	public void sendTextmetaTitletag(String metaTagTitle) {
		this.metaTagTitle.clear();
		this.metaTagTitle.sendKeys(metaTagTitle);
	}
	public void clickonDataTab() {
		this.dataTab.click();
			}
	public void sendTextModelTextbox(String modelTextbox) {
		this.modelTextbox.clear();
		this.modelTextbox.sendKeys(modelTextbox);
	}
	public void sendTextPriceTextbox(String priceTextbox) {
		this.priceTextbox.clear();
		this.priceTextbox.sendKeys(priceTextbox);
	}
	public void sendTextQuantityTextbox(String quantityTextbox) {
		this.quantityTextbox.clear();
		this.quantityTextbox.sendKeys(quantityTextbox);
	}
	public void clickonLinkTab() {
		this.linktab.click();
			}
	public void sendTextCategoriesTextbox(String CategoriesTextbox) throws InterruptedException {
		this.CategoriesTextbox.clear();
		//String script = 
		
		
	Thread.sleep(1000);
	
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
	
	public void clickonPlusButtonDiscountTab() {
		this.plusButtonDiscountTab.click();
	}
	// Enter valid credentials in Quantity textbox
	public void sendTextToQuantityDiscountTab(String quantityDiscountTab ) {
		this.quantityDiscountTab.clear();
		this.quantityDiscountTab.sendKeys(quantityDiscountTab);
	}
	
	// Enter valid credentials in Price textbox
	public void sendTextToPriceDiscountTab(String priceDiscountTab) {
		this.priceDiscountTab.clear();
		//this.priceDiscountTab.sendKeys(quantityDiscountTab);
		this.priceDiscountTab.sendKeys(priceDiscountTab);
		
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
	public void sendkeystoPointsRewardpointTab(String pointsRewardTab) {
	this.pointsRewardTab.clear(); 
	this.pointsRewardTab.sendKeys(pointsRewardTab);
	}
	
	public void clickonDesignTab() {
		this.designTab.click();
	}
	public void clickonSaveBtn() {
		this.saveBtn.click();
	}
}
