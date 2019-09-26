package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.dataproviders.AddProductDataProviders_RTTC_071;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import com.training.pom.RTTC_071_POM_AdminAddMultipleProducts;

public class RTTC_071_Test_Admin_AddMultipleProducts {
	private WebDriver driver;
	private String baseUrl;
	//private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private RTTC_071_POM_AdminAddMultipleProducts AdminAddMultipleProdcutPOM071;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		AdminAddMultipleProdcutPOM071 = new RTTC_071_POM_AdminAddMultipleProducts(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		AdminAddMultipleProdcutPOM071.sendUserName("admin");
		AdminAddMultipleProdcutPOM071.sendPassword("admin@123");
		AdminAddMultipleProdcutPOM071.clickLoginBtn();
		//Verify the Title page after Login
		String actualtitle = driver.getTitle();
		String expectedTitle = "Dashboard";
		//Verify expected page title and actual page title is same - Assertion
		Assert.assertTrue(actualtitle.equalsIgnoreCase(expectedTitle),"Page title not matched or Problem in loading url page"); 
		AdminAddMultipleProdcutPOM071 = new RTTC_071_POM_AdminAddMultipleProducts(driver);
		AdminAddMultipleProdcutPOM071.clickButtonMenu();
		AdminAddMultipleProdcutPOM071.clickCatalogMenu();
		//creating new generic arraylist for Catalog
		ArrayList<String> listCatLog=new ArrayList<String>();
		ArrayList<String> ActuallistCatLog=new ArrayList<String>();
		//Adding object in arraylist   
		listCatLog.add("Categories");   
		listCatLog.add("Products");    
		listCatLog.add("Recurring Profiles");
		List<WebElement> objLinks  = driver.findElements(By.tagName("a"));
		System.out.println("Expected List in the Catagories  "+listCatLog);
		System.out.println("Actual List of Categories");
		for  (WebElement objCurrentLink : objLinks) {
			String strLinkText =  objCurrentLink.getText();
			if(listCatLog.contains(strLinkText)){
			System.out.println(strLinkText);
			
			ActuallistCatLog.add(strLinkText);
			}
		}
		Collections.sort(listCatLog);
		Collections.sort(ActuallistCatLog);
		Assert.assertTrue(listCatLog.equals(ActuallistCatLog),"Catalog Lists are not matching.");
		System.out.println("===================================================");
		AdminAddMultipleProdcutPOM071.clickProduct();
	}
	@AfterClass
	public void tearDown() throws Exception {
		String actualtextdisplay=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]")).getText();
		//String expecteddisplay = "Success: You have modified products!";
		Assert.assertTrue(actualtextdisplay.contains("Success"),"Modify confirm message not matched or Problem in loading url page"); 
		System.out.println("As per RTTD_009 of Test Data Sheet - Multiple Products have been added sucessufully in the Product list");
		System.out.println("*Application allowed admin to add multiple product with the rewards point, Hence this TC execution is passed!");
		driver.quit();
	}

	@Test(dataProvider = "excel-inputs", dataProviderClass = AddProductDataProviders_RTTC_071.class)
	public void AddProductExcelTest(String pName, String mTitle, String model, String tPrice, String category, String qty, String uPrice, String pts) throws InterruptedException {
		/*loginPOM.sendUserName(userName);
		loginPOM.sendPassword(password);
		loginPOM.clickLoginBtn();*/
		//====================
		 // Step 3: Click on Add New icon
		AdminAddMultipleProdcutPOM071.clickplusButton();
		driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
		//Step 4:  Enter Valid credentials in Product Name of General tab
		AdminAddMultipleProdcutPOM071.sendproductname(pName);
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		AdminAddMultipleProdcutPOM071.sendTextmetaTitletag(mTitle);
		AdminAddMultipleProdcutPOM071.clickonDataTab();
		AdminAddMultipleProdcutPOM071.sendTextModelTextbox(model);
		//scroll some axis to view
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		AdminAddMultipleProdcutPOM071.sendTextPriceTextbox(tPrice);
		Thread.sleep(3000);
		AdminAddMultipleProdcutPOM071.sendTextQuantityTextbox("1");
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		AdminAddMultipleProdcutPOM071.clickonLinkTab();
		//AdminAddMultipleProdcutPOM071.sendTextCategoriesTextbox(category);
		driver.findElement(By.xpath("//input[@id='input-category']")).sendKeys((category),(Keys.TAB));
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		// Click on Discount tab
		AdminAddMultipleProdcutPOM071.clickonDiscountTab();
		Thread.sleep(2000);
		// Click on Add discount icon
		AdminAddMultipleProdcutPOM071.clickonPlusButtonDiscountTab();
		Thread.sleep(2000);
		// Enter valid credentials in Quantity textbox
		AdminAddMultipleProdcutPOM071.sendTextToQuantityDiscountTab(qty);
		Thread.sleep(2000);
		// Enter valid credentials in Price textbox
		AdminAddMultipleProdcutPOM071.sendTextToPriceDiscountTab(uPrice);
		Thread.sleep(2000);
		// Select valid start date as current date
		//input[@placeholder='Date Start']
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date=c.getTime();
		System.out.println("Today's Date is :"+date);
		driver.findElement(By.xpath("//input[@placeholder='Date Start']")).sendKeys(dateFormat.format(date));
		Thread.sleep(2000);
		//Select valid end date as current date+one day
		//*[@id="discount-row0"]/td[6]/div/input
		c.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow=c.getTime();
		driver.findElement(By.xpath("//*[@id=\"discount-row0\"]/td[6]/div/input")).sendKeys(dateFormat.format(tomorrow));
		//	 Click on Rewards Point tab
		AdminAddMultipleProdcutPOM071.clickonRewardpointTab();
		Thread.sleep(2000);
		// Enter valid credentials in points textbox
		AdminAddMultipleProdcutPOM071.sendkeystoPointsRewardpointTab(pts);
		Thread.sleep(2000);
		// Keep default values in Attribute, Option, Recurring,  Special, Image and Design tab
		AdminAddMultipleProdcutPOM071.clickonAttributeTab();
		Thread.sleep(3000);
		AdminAddMultipleProdcutPOM071.clickonOptionTab();
		Thread.sleep(3000);
		AdminAddMultipleProdcutPOM071.clickonRecurringTab();
		Thread.sleep(3000);
		AdminAddMultipleProdcutPOM071.clickonSpecialTab();
		Thread.sleep(2000);
		AdminAddMultipleProdcutPOM071.clickonImageTab();
		Thread.sleep(2000);
		AdminAddMultipleProdcutPOM071.clickonSaveBtn();
		Thread.sleep(2000);
		
		
		
	
		screenShot.captureScreenShot("First");
		//=====================
		System.out.println("Values Returned....\n");
		System.out.println(pName+"\t"+mTitle+"\t"+model+"\t"+tPrice+"\t"+category+"\t"+qty+"\t"+uPrice+"\t"+pts);
		//
		screenShot.captureScreenShot(pName);

	}

}
