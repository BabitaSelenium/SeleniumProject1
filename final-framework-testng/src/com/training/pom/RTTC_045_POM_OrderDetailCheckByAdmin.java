package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RTTC_045_POM_OrderDetailCheckByAdmin {
private WebDriver driver; 
	
	public RTTC_045_POM_OrderDetailCheckByAdmin(WebDriver driver) {
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
	
	@FindBy(linkText="Sales")
	//xpath=//div[@class='pull-right']//a[1]     copy xpath //*[@id=\"content\"]/div[1]/div/div/a[1]
	private WebElement sales;
	
	@FindBy(linkText="Orders")
	//(xpath=//*[@id=\"menu-sale\"]/ul/li[1]/a)
	private WebElement orders;
	
	// Step 3: View Icon
	@FindBy(xpath="//*[@id=\"form-order\"]/div/table/tbody/tr[1]/td[8]/a[1]/i")
	//(xpath=//*[@id=\"menu-sale\"]/ul/li[1]/a)
	private WebElement viewIcon;
	
	
	//Step 4 Invoice Icon
	@FindBy(xpath="//*[@id=\"content\"]/div[1]/div/div/a[1]")
	//(xpath=//div[@class='pull-right']//a[1])
	private WebElement invoiceIcon;
	
	/*@FindBy(xpath="/html/body/div/div/h1")
	//(xpath=//div[@class='pull-right']//a[1])
	private WebElement invoiceNo;*/
	
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
	
	public void clickSalesMenu() {
		// TODO Auto-generated method stub
		this.sales.click();
	}
	
	public void clickOrdersMenu() {
		// TODO Auto-generated method stub
		this.orders.click();
	}
	public void clickViewIcon() {
		// TODO Auto-generated method stub
		this.viewIcon.click();
	}
		
	public void clickInvoiceIcon() {
		// TODO Auto-generated method stub
		this.invoiceIcon.click();
	}
	
	/*public void InvoiceNumber() {
		// TODO Auto-generated method stub
		this.invoiceNo.click();
	}*/
	
}
