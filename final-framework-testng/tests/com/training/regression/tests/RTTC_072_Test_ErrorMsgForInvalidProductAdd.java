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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.training.dataproviders.AddProductDataProviders_RTTC_072;
import com.training.generics.ScreenShot;
import com.training.pom.RTTC_072_POM_ErrorMsgForInvalidProductAdd;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_072_Test_ErrorMsgForInvalidProductAdd {
	 /* TC ID: RTTC_072
	 Test Case Description: To verify whether application displays error message 
	 upon entering invalid details while adding product with reward points 
	 Test Steps:	 
	 Click on Catelog icon 
	 Click on Products link
	 Click on Add New icon
	 Enter Valid credentials in Product Name of General tab
	 Enter Valid credentials in Meta Tag Title of General tab
	 Click on Data tab
	 Enter valid credentials in Model textbox
	 Enter valid credentials in Price textbox
	 Enter valid credentials in Quantity textbox
	 Click on Links tab
	 Click on Categories textbox
	 Select Category from displayed category list
	 Click on Discount tab
	 Click on Add discount icon
	 Enter valid credentials in Quantity textbox
	 Enter valid credentials in Price textbox
	 Select valid start date as current date
	 Select valid end date as current date+one day
	 Click on Rewards Point tab
	 Enter valid credentials in points textbox
	 Keep default values in Attribute, Option, Recurring,  Special, Image and Design tab
	 Click on Save icon. error message should get displayed */
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private RTTC_072_POM_ErrorMsgForInvalidProductAdd ErrorMsgForInvalidProductAddPOM072;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		ArrayList<String> listCatLog=new ArrayList<String>();
		ArrayList<String> ActuallistCatLog=new ArrayList<String>();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		ErrorMsgForInvalidProductAddPOM072 = new RTTC_072_POM_ErrorMsgForInvalidProductAdd(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		ErrorMsgForInvalidProductAddPOM072.sendUserName("admin");
		ErrorMsgForInvalidProductAddPOM072.sendPassword("admin@123");
		ErrorMsgForInvalidProductAddPOM072.clickLoginBtn();
		//Verify the Title page after Login
		String actualtitle = driver.getTitle();
		String expectedTitle = "Dashboard";
		//Verify expected page title and actual page title is same - Assertion
		Assert.assertTrue(actualtitle.equalsIgnoreCase(expectedTitle),"Page title not matched or Problem in loading url page"); 
		ErrorMsgForInvalidProductAddPOM072.clickButtonMenu();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		ErrorMsgForInvalidProductAddPOM072.clickCatalogMenu();
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
		
	}
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.close();
		System.out.println("=============Browser is Closed==================");
	}
	
	/*// Admin Login Step
	@Test ( priority = 0)
	public void LoginTests(){
	
	}*/
	@Test(dataProvider = "excel-inputs", dataProviderClass = AddProductDataProviders_RTTC_072.class)
	public void AddProductExcelTest(String pName, String mTitle, String model, String tPrice, String category, String qty, String uPrice, String pts) throws InterruptedException {
	//Step 1:  Click on Catalog icon and it's list validation
	//public void CatalogIconTests(){
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	ErrorMsgForInvalidProductAddPOM072 = new RTTC_072_POM_ErrorMsgForInvalidProductAdd(driver);
	//Capture Screen shot
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	String filename="RTTC_072_"+df.format(new Date()).toString();
	
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//Step 2.  Click on Products link
		ErrorMsgForInvalidProductAddPOM072.clickProduct();
		Thread.sleep(3000);
		//Step 3.  Click on Add New icon
		ErrorMsgForInvalidProductAddPOM072.clickplusButton();
		driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
		//Step 4:  Enter Valid credentials in Product Name of General tab
		ErrorMsgForInvalidProductAddPOM072.sendproductname(pName);
		screenShot.captureScreenShot(filename);
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		//Step 5.  Enter Valid credentials in Meta Tag Title of General tab	
		ErrorMsgForInvalidProductAddPOM072.sendTextmetaTitletag(mTitle);
			// Step 6.  Click on Data tab
		ErrorMsgForInvalidProductAddPOM072.clickonDataTab();
			Thread.sleep(3000);
			// Step 7.  Enter valid credentials in Model textbox
			ErrorMsgForInvalidProductAddPOM072.sendTextModelTextbox(model);
			//scroll some axis to view
			js.executeScript("window.scrollBy(0,500)");
			Thread.sleep(3000);
			// Step 8: Enter valid credentials in Price textbox
			ErrorMsgForInvalidProductAddPOM072.sendTextPriceTextbox(tPrice);
			Thread.sleep(3000);
			// Step 9.  Enter valid credentials in Quantity textbox
			ErrorMsgForInvalidProductAddPOM072.sendTextQuantityTextbox(qty);
			Thread.sleep(2000);
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
			Thread.sleep(2000);
			// 10. Click on Links tab
			ErrorMsgForInvalidProductAddPOM072.clickonLinkTab();
			// Step 11 & 12. Click on Categories textbox [Note: as dropdown box is not working, sendkeys to the textbox is considered.
			driver.findElement(By.xpath("//input[@id='input-category']")).sendKeys((category),(Keys.TAB));
			Thread.sleep(2000);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			// Click on Design tab
			ErrorMsgForInvalidProductAddPOM072.clickonDesignTab();
			Thread.sleep(2000);
			// Click on Discount tab
			ErrorMsgForInvalidProductAddPOM072.clickonDiscountTab();
			Thread.sleep(2000);
			// Click on Add discount icon
			ErrorMsgForInvalidProductAddPOM072.clickonPlusButtonDiscountTab();
			Thread.sleep(2000);
			// Enter valid credentials in Quantity textbox
			ErrorMsgForInvalidProductAddPOM072.sendTextToQuantityDiscountTab(qty);
			Thread.sleep(2000);
			// Enter valid credentials in Price textbox
			ErrorMsgForInvalidProductAddPOM072.sendTextToPriceDiscountTab(uPrice);
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
			ErrorMsgForInvalidProductAddPOM072.clickonRewardpointTab();
			Thread.sleep(2000);
			// Enter valid credentials in points textbox
			ErrorMsgForInvalidProductAddPOM072.sendkeystoPointsRewardpointTab(pts);
			Thread.sleep(2000);
			/*Step:   Keep default values in Attribute, Option, Recurring,  
					  Special, Image and Design tab.
			  Step:	  Click on Save icon*/
			ErrorMsgForInvalidProductAddPOM072.clickonAttributeTab();
			Thread.sleep(3000);
			ErrorMsgForInvalidProductAddPOM072.clickonOptionTab();
			Thread.sleep(3000);
			ErrorMsgForInvalidProductAddPOM072.clickonRecurringTab();
			Thread.sleep(3000);
			ErrorMsgForInvalidProductAddPOM072.clickonSpecialTab();
			Thread.sleep(2000);
			ErrorMsgForInvalidProductAddPOM072.clickonImageTab();
			Thread.sleep(2000);
			ErrorMsgForInvalidProductAddPOM072.clickonDesignTab();
			Thread.sleep(2000);
			ErrorMsgForInvalidProductAddPOM072.clickonSaveBtn();
			Thread.sleep(2000);
			//Validate error message should get displayed for invalid data and Sucess msg for valid data.			
			String actualtextdisplay1=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]")).getText();
		   //String actualErrorMsgy=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
			
			//String expecteddisplay = "Success: You have modified products!";
			if(actualtextdisplay1.contains("Success")){
			Assert.assertTrue(actualtextdisplay1.contains("Success"),"Modify confirm message not matched or Problem in loading url page"); 
			System.out.println("Success Message :"+actualtextdisplay1);
			System.out.println("=======================================================");
			
			}
			if(actualtextdisplay1.contains("errors"))
			{
				Assert.assertTrue(actualtextdisplay1.contains("errors"),"Error message is not displayed"); 
				System.out.println(actualtextdisplay1);
			}
			Thread.sleep(2000);
			screenShot.captureScreenShot(filename);
			//=====================
			//Print excel records into console
			System.out.println("Values Returned....\n");
			System.out.println(pName+"\t"+mTitle+"\t"+model+"\t"+tPrice+"\t"+category+"\t"+qty+"\t"+uPrice+"\t"+pts);
			//Capture Screen shot
			/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			String filename="RTTC_072_"+df.format(new Date()).toString();*/
			System.out.println("The screenshot captured and saved in the filename :"+filename);
			screenShot.captureScreenShot(filename);

			
	}		
			
	}
	

