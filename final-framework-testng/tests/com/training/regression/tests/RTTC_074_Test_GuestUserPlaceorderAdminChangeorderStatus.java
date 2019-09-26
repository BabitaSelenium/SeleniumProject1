package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC_074_POM_GuestUserPlaceorderAdminChangeorderStatus;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
/*TC Description: To verify whether application allows user to place an order as guest user & admin to 
change order order status as complete.
Note Start****: Babita: 25-Sept-2019 - As a Guest user after order place not able 
to get orderID, or any clue on order placed in the session. 
After order place in Application order history or My orders not showing any details for which that can be searched by a admin user in Admin retail url.
This would be a defect Note END*****.*/

public class RTTC_074_Test_GuestUserPlaceorderAdminChangeorderStatus {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private RTTC_074_POM_GuestUserPlaceorderAdminChangeorderStatus GuestUserPlaceorderAdminChangeorderStatusPOM074;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		GuestUserPlaceorderAdminChangeorderStatusPOM074 = new RTTC_074_POM_GuestUserPlaceorderAdminChangeorderStatus(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get("http://retailm1.upskills.in/");
		
	}
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		//driver.quit();
	}
	
	@Test ( priority = 0)
	public void LoginTests() throws InterruptedException{
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//GuestUserPlaceorderAdminChangeorderStatusPOM074.clickSearchIcon();
		Thread.sleep(3000);
		//Instantiate Action Class        
        Actions actions = new Actions(driver);
      //Retrieve WebElement 'SearchIcon' to perform mouse hover 
        WebElement searchMagnifyingIcon = driver.findElement(By.xpath("//*[@id=\"search_button\"]"));
      //Mouse hover menuOption 'Search'
        actions.moveToElement(searchMagnifyingIcon).perform();
        System.out.println("Done Mouse hover on 'Search Magnify Icon' from Menu");
		//Sending text to searchbox
        driver.findElement(By.xpath("//*[@id=\"filter_keyword\"]")).sendKeys(("lacinia congue"),(Keys.ENTER));
        GuestUserPlaceorderAdminChangeorderStatusPOM074.clickProductImageLink();
		// Click on Add to Cart button
        GuestUserPlaceorderAdminChangeorderStatusPOM074.clickaddToCartBtn();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
      //Instantiate Action Class        
        Actions actionsCart = new Actions(driver);
      //Retrieve WebElement 'SearchIcon' to perform mouse hover 
        WebElement shoppingCart = driver.findElement(By.xpath("//i[@class='tb_icon ico-linea-ecommerce-bag']"));
      //Mouse hover menuOption 'Search'
        actionsCart.moveToElement(shoppingCart).perform();
        System.out.println("Done Mouse hover on 'Music' from Menu");
        GuestUserPlaceorderAdminChangeorderStatusPOM074.clickcheckoutBtn();
        Thread.sleep(2000);
     // Locate "Guest Checkout" radio button using xpath
        WebElement GuestCheckoutRadioBtn = driver.findElement(By
        				.xpath("//*[@id=\"collapse-checkout-option\"]/div/div/div[1]/div[1]/div/div[2]/label/input"));
        // Check if radio button is selected by default
        if (GuestCheckoutRadioBtn.isSelected()) {
                // Print message to console
        	System.out.println("Guest Checkout radio button is selected by default");
        } else {
        	// Click the radio button
        	GuestCheckoutRadioBtn.click();

         
        
		}
	
	}
	@Test ( priority = 1)
	public void billingAddressGuestUser() throws InterruptedException{
	 /*Enter valid credentials in Your Personal Details fields
	 Enter valid credentials in Your Address fields
	 click on My delivery and billing addresses are the same checkbox 
	 Click on continue button*/
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
		Thread.sleep(2000);
		GuestUserPlaceorderAdminChangeorderStatusPOM074.clickcontinueBtn();
		GuestUserPlaceorderAdminChangeorderStatusPOM074.textBoxfirstName("Bhabani");
		GuestUserPlaceorderAdminChangeorderStatusPOM074.textBoxlastName("Rout");
		GuestUserPlaceorderAdminChangeorderStatusPOM074.textBoxemail("bhabanirout@gmail.com");
		GuestUserPlaceorderAdminChangeorderStatusPOM074.textboxtel("9903212030");
		GuestUserPlaceorderAdminChangeorderStatusPOM074.textBoxaddress1("East Enclave Housing Complex");
		GuestUserPlaceorderAdminChangeorderStatusPOM074.textBoxaddress2("D 15");
		GuestUserPlaceorderAdminChangeorderStatusPOM074.textBoxcity("Kolkata");
		GuestUserPlaceorderAdminChangeorderStatusPOM074.textBoxPostcode("700156");
		
		/*WebElement cDropDown = driver.findElement(By.id("input-payment-country"));  
		Select cdropDown = new Select(cdropDown);
		cdropDown.selectByValue("India");  */
		js.executeScript("window.scrollBy(0,150)");
		//WebDriverWait wait = new WebDriverWait(driver,30);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='input-payment-zone']")));
		//driver.findElement(By.xpath("//select[@id='input-payment-zone']")).click();
		Select select = new Select(driver.findElement(By.xpath("//select[@id='input-payment-zone']")));
		System.out.print("\nElements found");
		select.selectByIndex(33);
		Thread.sleep(2000);
		GuestUserPlaceorderAdminChangeorderStatusPOM074.clickonContinue();
		//Free Shipping radio button should get selected & Add Comments
		// Locate "Free Shipping" radio button using xpath
        WebElement fressShippingRadioBtn = driver.findElement(By
        				.xpath("//input[@name='shipping_method']"));
        // Check if radio button is selected by default
        if (fressShippingRadioBtn.isSelected()) {
                // Print message to console
        	System.out.println("Free Shipping radio button is selected by default");
        } else {
        	// Click the radio button
        	fressShippingRadioBtn.click();        
        
		}
		GuestUserPlaceorderAdminChangeorderStatusPOM074.inputcommentText("This is for testing purpose");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,50)");
		// Click on continue button
		GuestUserPlaceorderAdminChangeorderStatusPOM074.clickonContBtnDelMethod();
		//	enter the text in Add Comments About Your Order textbox
		// Click on I have read and agree to the Terms & Conditions checkbox
		GuestUserPlaceorderAdminChangeorderStatusPOM074.clickonAgreeCheckbox();
		//Click on Continue button in Payment Method section
		Thread.sleep(2000);
		GuestUserPlaceorderAdminChangeorderStatusPOM074.clickonContinueBtnPayMethod();
	// Confirm Order step for the Guest user
		GuestUserPlaceorderAdminChangeorderStatusPOM074.clickonConfirmOrderBtn();
		GuestUserPlaceorderAdminChangeorderStatusPOM074.ReturnconfirmOrderMessage();
		// Check shopping cart for no product in it after order place
		GuestUserPlaceorderAdminChangeorderStatusPOM074.finalContinue();
		// To mouse hover on cart and perform click and vadite cart is empty
		//Instantiate Action Class        
        Actions actions = new Actions(driver);
      //Retrieve WebElement 'SearchIcon' to perform mouse hover 
        WebElement shoppingCartIcon = driver.findElement(By.xpath("//i[@class='tb_icon ico-linea-ecommerce-bag']"));
      //*[@id="button-confirm"]
        //Mouse hover menuOption 'Search'
        actions.moveToElement(shoppingCartIcon).perform();
        Thread.sleep(2000);
        //GuestUserPlaceorderAdminChangeorderStatusPOM074.shoppingcartmessage();
	}
	
	
	
	

}
