package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.dataproviders.ReturnProductDataProviders_RTTC_75;
import com.training.generics.ScreenShot;
import com.training.pom.RTTC_075_POM_UserReturnOrderedProducts;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;


/*RTTC_075:TC description: To Verify whether application allows the user to 
return multiple ordered product.
Test data: Refer RTTD_011 of Test Data Sheet*/		
public class RTTC_075_Test_UserReturnOrderedproducts {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private RTTC_075_POM_UserReturnOrderedProducts UserReturnOrderedProductsPOM075;

	@BeforeClass
	public void setUpBeforeClass() throws IOException, InterruptedException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		UserReturnOrderedProductsPOM075 = new RTTC_075_POM_UserReturnOrderedProducts(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get("http://retailm1.upskills.in/");
		
		//Instantiate Action Class        
        Actions actions = new Actions(driver);
      //Retrieve WebElement 'User'Icon to perform mouse hover 
        WebElement userIconMousehover = driver.findElement(By.xpath("//*[@id=\"Menu_Wmt3OMY3\"]/nav/ul/li[2]/a/span/span/i"));
        actions.moveToElement(userIconMousehover).perform();
        Thread.sleep(1000);
		//Mouse hover on userIcon righthand side and click on Register from the list
        WebElement Loginuser = driver.findElement(By.xpath("//span[contains(text(),'LOGIN / REGISTER')]"));
        actions.moveToElement(Loginuser).click().perform();
        //Thread.sleep(3000);
        //Login with registered user
        UserReturnOrderedProductsPOM075.SendTextEmailID("babitafernandez@gmail.com");
        UserReturnOrderedProductsPOM075.sendPassword("abc12345");
        UserReturnOrderedProductsPOM075.clickLogin();
        //Mouser over on UserIcon and click on 'My orders'
		Actions actionMyorder = new Actions(driver);
		WebElement myOrder = driver.findElement(By.cssSelector("#Menu_Wmt3OMY3 > nav > ul > li.tb_link.dropdown.tb_menu_system_account_account.tb_selected > a > span > span > i"));
				actionMyorder.moveToElement(myOrder).perform();
				Thread.sleep(1000);
				System.out.println("Done Mouse hover on 'user' from Menu");
		WebElement myOrders = driver.findElement(By.xpath("//span[contains(text(),'MY ORDERS')]"));
		actionMyorder.moveToElement(myOrders).click().perform();
		System.out.println("Done clicked on  'My orders' from the user mouse hover");
		Thread.sleep(1000);
		
	}
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	// Get data set from excel.
	@Test(dataProvider = "excel-inputs", dataProviderClass = ReturnProductDataProviders_RTTC_75.class)
	public void AddProductExcelTest(String ordNo, String reason, String opened, String details) throws InterruptedException {
	//=============
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		// Step:  Click on view icon of order
		UserReturnOrderedProductsPOM075.clickviewBtn();	
		js.executeScript("window.scrollBy(0,100)");
		Thread.sleep(1000);
		// Step:  Click on Return icon
		UserReturnOrderedProductsPOM075.clickReturnBtn();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		UserReturnOrderedProductsPOM075.sendTextproductNameInfo("Lorem ipsum dolor sit amet");
		UserReturnOrderedProductsPOM075.sendTextproductCodeInfo("SKU-001");
		//Click on valid Reason for Return radio button
		// Click radio button of Product is opened category
		js.executeScript("window.scrollBy(0,150)");
		switch(ordNo.trim())
		{
		case "1.0":
			UserReturnOrderedProductsPOM075.clickFaulty();
			break;
		case "2.0":
			UserReturnOrderedProductsPOM075.clickDeadOnArrival();
			break;
		case "3.0":
			UserReturnOrderedProductsPOM075.clickDeadOnArrival();
			break;
		case "4.0":
			UserReturnOrderedProductsPOM075.clickReceivedWrg();
			break;
		default:
			UserReturnOrderedProductsPOM075.clickortherRadioBtn();
			break;
		}
		
		js.executeScript("window.scrollBy(0,150)");
		if(opened.equalsIgnoreCase("yes"))
		UserReturnOrderedProductsPOM075.clickOpenedYesRadio();
		else
			UserReturnOrderedProductsPOM075.clickOpenedNoRadio();
		Thread.sleep(1000);
		// Enter reason for return in Faulty or other details textbox
		UserReturnOrderedProductsPOM075.sendCommentText(details);
		js.executeScript("window.scrollBy(0,30)");
		// Click on Submit button
		UserReturnOrderedProductsPOM075.clickSubmitReturnBtn();
		//Return msg validation
		String returnMsgline1=driver.findElement(By.xpath("//*[@id=\"System_s3bhXNDb\"]/div[1]/p[1]")).getText();
		System.out.println(returnMsgline1);
		Assert.assertTrue(returnMsgline1.contains("return"),"No such Return message displayed"); 
		String returnMsgline2=driver.findElement(By.xpath("//*[@id=\"System_s3bhXNDb\"]/div[1]/p[2]")).getText();
		System.out.println(returnMsgline2);
		Assert.assertTrue(returnMsgline2.contains("notified"),"No such information on Return request"); 
		driver.findElement(By.xpath("//*[@id=\"Menu_QE5JV2J2\"]/nav/ul/li[2]/a/span")).click();
		System.out.println("=================================================");
		System.out.println(reason+"\t"+opened+"\t"+details);
		
		
	}
	
	

}