package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RTTC_075_POM_UserReturnOrderedProducts {
private WebDriver driver; 
	
	public RTTC_075_POM_UserReturnOrderedProducts(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	//User mover, Login/Register
	//span[contains(text(),'LOGIN / REGISTER')]
	/*@FindBy(id="//input[@id='input-email']")
	private WebElement emailID; */
	
	//Path for LOGIN
	@FindBy(id="input-email")
	private WebElement emailID; 
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(xpath="//input[@class='btn btn-primary']")
	private WebElement loginBtn; 
	
	//Home Button
	
	@FindBy(xpath="//span[contains(text(),'Home')]")
	private WebElement homeBtn;
	
	@FindBy(xpath="//*[@id=\"System_s3bhXNDb\"]/div[1]/table/tbody/tr[1]/td[7]/a")
	private WebElement viewBtn;
	
	//@FindBy(xpath="//*[@id=\"System_s3bhXNDb\"]/div/div[1]/table/tbody/tr[1]/td[6]/a[2]")
	@FindBy(xpath="//*[@id=\"System_s3bhXNDb\"]/div/div[1]/table/tbody/tr[1]/td[6]/a")
	private WebElement returnBtn;
	
	@FindBy(xpath="//input[@id='input-product']")
	private WebElement productnameInfo;
	
	@FindBy(xpath="//input[@id='input-model']")
	private WebElement productCodeInfo;

	@FindBy(xpath="//*[@id=\"return_request_form\"]/fieldset[2]/div[4]/div/div[1]/label/input")
	private WebElement radioBtnDeadOnArrival;
	
	@FindBy(xpath="//*[@id=\"return_request_form\"]/fieldset[2]/div[4]/div/div[2]/label/input")
	private WebElement radioBtnFaulty;
	
	@FindBy(xpath="//*[@id=\"return_request_form\"]/fieldset[2]/div[4]/div/div[3]/label/input")
	private WebElement orderError;
	
	@FindBy(xpath="//*[@id=\"return_request_form\"]/fieldset[2]/div[4]/div/div[4]/label/input")
	private WebElement ortherBtn;
	
	@FindBy(xpath="//*[@id=\"return_request_form\"]/fieldset[2]/div[4]/div/div[5]/label/input")
	private WebElement receivedWrg;
	
	@FindBy(xpath="//*[@id=\"return_request_form\"]/fieldset[2]/div[5]/div/label[1]/input")
	private WebElement openedYesRadio;
	
	@FindBy(xpath="//*[@id=\"return_request_form\"]/fieldset[2]/div[5]/div/label[2]/input")
	private WebElement openedNoRadio;
	
	@FindBy(xpath="//*[@id=\"input-comment\"]")
	private WebElement commentText;
	
	@FindBy(xpath="//*[@id=\"return_request_form\"]/div/div/input")
	//*[@id="return_request_form"]/div/div/input
	private WebElement submitReturnBtn;
	
	@FindBy(xpath="//*[@id=\"System_s3bhXNDb\"]/div[2]/div/a")
	//*[@id="System_s3bhXNDb"]/div[2]/div/a
	private WebElement continueBtnAfterReturn;
	
	
	
	public void SendTextEmailID(String emailID) {
		this.emailID.clear();
		this.emailID.sendKeys(emailID);
	}
	public void sendPassword(String password) {
		//this.password.clear();
		this.password.sendKeys(password);
	}
	
	public void clickLogin() {
		this.loginBtn.click();
	}
	public void clickHome() {
		this.homeBtn.click();
	}
	public void clickviewBtn() {
		this.viewBtn.click();
	}
	
	public void clickReturnBtn() {
		this.returnBtn.click();
	}
	//As product name is not populating sending text
	public void sendTextproductNameInfo(String productnameInfo) {
		//this.password.clear();
		this.productnameInfo.sendKeys(productnameInfo);
	}
	
	//As product code is not populating in return so sending text it this field
	public void sendTextproductCodeInfo(String productCodeInfo) {
		//this.password.clear();
		this.productCodeInfo.sendKeys(productCodeInfo);
	}
	
	
	public void clickDeadOnArrival() {
		this.radioBtnDeadOnArrival.click();
	}
	public void clickFaulty() {
		this.radioBtnFaulty.click();
	}
	public void clickOrderError() {
		this.orderError.click();
	}
	public void clickortherRadioBtn() {
		this.ortherBtn.click();
	}
	public void clickReceivedWrg() {
		this.receivedWrg.click();
	}
	
	public void clickOpenedYesRadio() {
		this.openedYesRadio.click();
	}
	
	
	
	public void clickOpenedNoRadio() {
		this.openedNoRadio.click();
	}
	
	public void sendCommentText(String commentText) {
		//this.password.clear();
		this.commentText.sendKeys(commentText);
	}
	
	public void clickSubmitReturnBtn() {
		this.submitReturnBtn.click();
	}
	
	public void clickContinueBtnAfterReturn() {
		this.continueBtnAfterReturn.click();
	}
}
