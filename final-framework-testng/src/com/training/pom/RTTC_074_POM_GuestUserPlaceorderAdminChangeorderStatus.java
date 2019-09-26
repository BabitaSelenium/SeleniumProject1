package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RTTC_074_POM_GuestUserPlaceorderAdminChangeorderStatus {
	private WebDriver driver; 
	
	public RTTC_074_POM_GuestUserPlaceorderAdminChangeorderStatus(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Path for LOGIN
	@FindBy(xpath="//*[@id=\"filter_keyword\"]")
	private WebElement searchIcon; 
	
	@FindBy(xpath="//*[@id=\"filter_keyword\"]")
	private WebElement searchText; 

 //Image link
	
	@FindBy(xpath="//img[contains(@class,'lazyloaded')]")
	private WebElement productImageLink; 
	
	//Add to Cart
	@FindBy(xpath="//button[@id='button-cart']")
	private WebElement addToCartBtn; 

	//Checkout button
	@FindBy(xpath="//a[contains(text(),'Checkout')]")
	private WebElement checkoutBtn;
	
	@FindBy(xpath="//*[@id=\"button-account\"]")
	private WebElement continueBtninchk;
	
	
	
	//Billing details for Guest user
	
	@FindBy(xpath="//*[@id=\"input-payment-firstname\"]")
	private WebElement firstName;
	
	@FindBy(xpath="//*[@id=\"input-payment-lastname\"]")
	private WebElement lastName;
	
	@FindBy(xpath="//input[@id='input-payment-email']")
	private WebElement email;
	
	@FindBy(xpath="//input[@id='input-payment-telephone']")
	private WebElement tel;
	
	@FindBy(xpath="//*[@id=\"input-payment-address-1\"]")
	private WebElement address1;
	
	@FindBy(xpath="//*[@id=\"input-payment-address-2\"]")
	private WebElement address2;
	
	@FindBy(xpath="//input[@id='input-payment-city']")
	private WebElement city;
	
	@FindBy(xpath="//*[@id=\"input-payment-postcode\"]")
	private WebElement postcode;
	
	@FindBy(xpath="//*[@id=\"input-payment-country\"]")
	private WebElement country;
	
	@FindBy(xpath="//select[@id='input-payment-zone']")
	private WebElement state;
	
	@FindBy(xpath="//input[@id='button-guest']")
	private WebElement continueBtn;
	
	//Text box comment
		@FindBy(xpath="//textarea[@name='comment']")
	private WebElement commentText;
	
	//Continue Button in Delivery Method -
		@FindBy(xpath="//input[@id='button-shipping-method']")
		private WebElement contBtnDeliveryMethod;
		
	// Agree check box
		@FindBy(xpath="//*[@id=\"collapse-payment-method\"]/div/div[2]/div[1]/label/input")
		
		
		////input[@name='agree']
		private WebElement agreeCheckbox;
		
		//Continue btn in step 5: Payment method
		@FindBy(xpath="//input[@id='button-payment-method']")
		private WebElement continueBtnPayMethod;
		
		//Confirm Order button for placing order
		@FindBy(xpath="//*[@id=\"button-confirm\"]")
		
		private WebElement confirmOrderBtn;
		
		//Confirm order message
		@FindBy(xpath="//*[@id=\"PageTitleSystem_B083XYxw\"]/h1")
		private WebElement confirmOrderMessage;
		
		//After Confirm order message, continue button
					
				@FindBy(xpath="//*[@id=\"System_s3bhXNDb\"]/div[2]/div/a")
				private WebElement continueBtnfinal;	
		
		//
				@FindBy(xpath="//p[contains(text(),'Your shopping cart is empty!')]")
				private WebElement shopcart;

public void SendTextSearchIcon(String searchText) {
	this.searchText.clear();
	this.searchText.sendKeys(searchText);
}
public void clickSearchIcon() {
	//this.searchIcon.clear();
	this.searchIcon.click();
}

public void clickProductImageLink() {
		this.productImageLink.click();
}

public void clickaddToCartBtn() {
	this.addToCartBtn.click();
}
public void clickcheckoutBtn() {
	this.checkoutBtn.click();
}
public void clickcontinueBtn() {
	this.continueBtninchk.click();
}
//Billing details steps

	public void textBoxfirstName(String firstName) {
	this.firstName.clear();
	this.firstName.sendKeys(firstName);
	}
	public void textBoxlastName(String lastName) {
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
	}
		
	public void textBoxemail(String email) {
		this.email.clear();
		this.email.sendKeys(email);
	}	
			
	public void textboxtel(String tel) {
		this.tel.clear();
		this.tel.sendKeys(tel);
	}			
	public void textBoxaddress1(String address1) {
		this.address1.clear();
		this.address1.sendKeys(address1);				
	}
	public void textBoxaddress2(String address2) {
		this.address2.clear();
		this.address2.sendKeys(address2);
						
	}					
	public void textBoxcity(String city) {
		this.city.clear();
		this.city.sendKeys(city);
	}
	public void textBoxPostcode(String postcode) {
		this.postcode.clear();
		this.postcode.sendKeys(postcode);
	}
	public void clickonContinue() {
			this.continueBtn.click();
}
	
	//Text box comment
	
	public void inputcommentText(String commentText) {
			this.commentText.sendKeys(commentText);
}
	//Continue Button in Delivery Method -
	public void clickonContBtnDelMethod() {
		this.contBtnDeliveryMethod.click();
}
	//Agree check method
	public void clickonAgreeCheckbox() {
		this.agreeCheckbox.click();
}
	//click on Continue btn in payment method
		public void clickonContinueBtnPayMethod() {
			this.continueBtnPayMethod.click();
	}
	
		
		//click on Continue btn in payment method
				public void clickonConfirmOrderBtn() {
					this.confirmOrderBtn.click();
			}
//Confirm Order message
				
				public void ReturnconfirmOrderMessage() {
					this.confirmOrderMessage.getText();
			}
				
				public void finalContinue() {
					this.continueBtnfinal.click();
			}
				
				public void shoppingcartmessage() {
					this.shopcart.getText();
			}
				
}