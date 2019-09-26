package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.training.dataproviders.LoginDataProviders;
import com.training.dataproviders.VerifyAddedProductDataProvider_RTTC_073;
import com.training.generics.ScreenShot;
import com.training.pom.RTTC_073_POM_VerifyAddedProductStoredInDatabase;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_073_Test_VerifyAddedProductStoredInMariaDatabase {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private RTTC_073_POM_VerifyAddedProductStoredInDatabase VerifyAddedProductStoredInDatabasePOM073;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		VerifyAddedProductStoredInDatabasePOM073 = new RTTC_073_POM_VerifyAddedProductStoredInDatabase(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		//LOG IN
		VerifyAddedProductStoredInDatabasePOM073.sendUserName("admin");
		VerifyAddedProductStoredInDatabasePOM073.sendPassword("admin@123");
		VerifyAddedProductStoredInDatabasePOM073.clickLoginBtn();
		//Verify the Title page after Login
		String actualtitle = driver.getTitle();
		String expectedTitle = "Dashboard";
		//Verify expected page title and actual page title is same - Assertion
		Assert.assertTrue(actualtitle.equalsIgnoreCase(expectedTitle),"Page title not matched or Problem in loading url page"); 
		//Step 1:  Click on Catalog icon
		VerifyAddedProductStoredInDatabasePOM073 = new RTTC_073_POM_VerifyAddedProductStoredInDatabase(driver);
		VerifyAddedProductStoredInDatabasePOM073.clickButtonMenu();
		VerifyAddedProductStoredInDatabasePOM073.clickCatalogMenu();
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
	}
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	
	/*@Test ( priority = 0)
	public void LoginTests(){
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	VerifyAddedProductStoredInDatabasePOM073.sendUserName("admin");
	VerifyAddedProductStoredInDatabasePOM073.sendPassword("admin@123");
	VerifyAddedProductStoredInDatabasePOM073.clickLoginBtn();
	//Verify the Title page after Login
	String actualtitle = driver.getTitle();
	String expectedTitle = "Dashboard";
	//Verify expected page title and actual page title is same - Assertion
	Assert.assertTrue(actualtitle.equalsIgnoreCase(expectedTitle),"Page title not matched or Problem in loading url page"); 
	
	}*/
	
	
	/*@Test ( priority = 1)
	//Step 1:  Click on Catalog icon
	public void CatalogIconTests(){
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	VerifyAddedProductStoredInDatabasePOM073 = new RTTC_073_POM_VerifyAddedProductStoredInDatabase(driver);
	VerifyAddedProductStoredInDatabasePOM073.clickButtonMenu();
	VerifyAddedProductStoredInDatabasePOM073.clickCatalogMenu();
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
	
	}*/
	
	@Test ( priority = 0)
		public void SeachProductBeforeAddingToProductListTest() throws InterruptedException{
		//driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		Thread.sleep(2000);
		VerifyAddedProductStoredInDatabasePOM073.searchProductName("A Finger Ring");
		Thread.sleep(2000);
		VerifyAddedProductStoredInDatabasePOM073.searchPriceTextbox("500");
		Thread.sleep(2000);
		VerifyAddedProductStoredInDatabasePOM073.searchModelTextbox("SKU-012");
		VerifyAddedProductStoredInDatabasePOM073.searchQuantityTextbox("50");
			//Click on Filter button
			driver.findElement(By.id("button-filter")).click();
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// This  will scroll down the page by  1000 pixel vertical		
	        js.executeScript("window.scrollBy(0,1000)");
	        js.executeScript("window.scrollBy(3000,0)");
	        driver.findElement(By.xpath("//input[@id='input-name']")).clear();
	       driver.navigate().refresh();
	       //
	     //Step 2.  Click on Products link
			VerifyAddedProductStoredInDatabasePOM073.clickProduct();
			Thread.sleep(3000);
			// 3.  Click on Add New icon
			VerifyAddedProductStoredInDatabasePOM073.clickplusButton();
			driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
			//Step 4:  Enter Valid credentials in Product Name of General tab
			VerifyAddedProductStoredInDatabasePOM073.sendproductname("A Finger Ring");
			Thread.sleep(3000);
			js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
	        //	Step 5.  Enter Valid credentials in Meta Tag Title of General tab	
			VerifyAddedProductStoredInDatabasePOM073.sendTextmetaTitletag("Finger Ring for ladies");
			// Step 6.  Click on Data tab
			VerifyAddedProductStoredInDatabasePOM073.clickonDataTab();
			Thread.sleep(3000);
			// Step 7.  Enter valid credentials in Model textbox
			VerifyAddedProductStoredInDatabasePOM073.sendTextModelTextbox("SKU-012");
			//scroll some axis to view
			js.executeScript("window.scrollBy(0,500)");
			Thread.sleep(3000);
			// Step 8: Enter valid credentials in Price textbox
			VerifyAddedProductStoredInDatabasePOM073.sendTextPriceTextbox("500");
			Thread.sleep(3000);
			// Step 9.  Enter valid credentials in Quantity textbox
			VerifyAddedProductStoredInDatabasePOM073.sendTextQuantityTextbox("50");
			Thread.sleep(2000);
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
			Thread.sleep(2000);
			// 10. Click on Links tab
			VerifyAddedProductStoredInDatabasePOM073.clickonLinkTab();
			// Step 11 & 12. Click on Categories textbox [Note: as dropdown box is not working, sendkeys to the textbox is considered.
			driver.findElement(By.xpath("//input[@id='input-category']")).sendKeys(("EARRINGS"),(Keys.TAB));
			Thread.sleep(2000);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			/*   Step 13. Keep default values in Attribute, Option, Recurring, 
			     Discount, Special, Image, Reward Points and Design tab           */
			VerifyAddedProductStoredInDatabasePOM073.clickonAttributeTab();
			Thread.sleep(3000);
			VerifyAddedProductStoredInDatabasePOM073.clickonOptionTab();
			Thread.sleep(3000);
			VerifyAddedProductStoredInDatabasePOM073.clickonRecurringTab();
			Thread.sleep(3000);
			VerifyAddedProductStoredInDatabasePOM073.clickonDiscountTab();
			Thread.sleep(2000);
			VerifyAddedProductStoredInDatabasePOM073.clickonSpecialTab();
			Thread.sleep(2000);
			VerifyAddedProductStoredInDatabasePOM073.clickonImageTab();
			Thread.sleep(2000);
			VerifyAddedProductStoredInDatabasePOM073.clickonRewardpointTab();
			Thread.sleep(2000);
			VerifyAddedProductStoredInDatabasePOM073.clickonDesignTab();
			Thread.sleep(2000);
	      
	       
	       
		
	}
	/*@Test ( priority = 3)
	public void AddProductTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//Step 2.  Click on Products link
		VerifyAddedProductStoredInDatabasePOM073.clickProduct();
		Thread.sleep(3000);
		// 3.  Click on Add New icon
		VerifyAddedProductStoredInDatabasePOM073.clickplusButton();
		driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
		//Step 4:  Enter Valid credentials in Product Name of General tab
		VerifyAddedProductStoredInDatabasePOM073.sendproductname("A Finger Ring");
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
        //	Step 5.  Enter Valid credentials in Meta Tag Title of General tab	
		VerifyAddedProductStoredInDatabasePOM073.sendTextmetaTitletag("Finger Ring for ladies");
		// Step 6.  Click on Data tab
		VerifyAddedProductStoredInDatabasePOM073.clickonDataTab();
		Thread.sleep(3000);
		// Step 7.  Enter valid credentials in Model textbox
		VerifyAddedProductStoredInDatabasePOM073.sendTextModelTextbox("SKU-012");
		//scroll some axis to view
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		// Step 8: Enter valid credentials in Price textbox
		VerifyAddedProductStoredInDatabasePOM073.sendTextPriceTextbox("500");
		Thread.sleep(3000);
		// Step 9.  Enter valid credentials in Quantity textbox
		VerifyAddedProductStoredInDatabasePOM073.sendTextQuantityTextbox("50");
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		Thread.sleep(2000);
		// 10. Click on Links tab
		VerifyAddedProductStoredInDatabasePOM073.clickonLinkTab();
		// Step 11 & 12. Click on Categories textbox [Note: as dropdown box is not working, sendkeys to the textbox is considered.
		driver.findElement(By.xpath("//input[@id='input-category']")).sendKeys(("EARRINGS"),(Keys.TAB));
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		   Step 13. Keep default values in Attribute, Option, Recurring, 
		     Discount, Special, Image, Reward Points and Design tab           
		VerifyAddedProductStoredInDatabasePOM073.clickonAttributeTab();
		Thread.sleep(3000);
		VerifyAddedProductStoredInDatabasePOM073.clickonOptionTab();
		Thread.sleep(3000);
		VerifyAddedProductStoredInDatabasePOM073.clickonRecurringTab();
		Thread.sleep(3000);
		VerifyAddedProductStoredInDatabasePOM073.clickonDiscountTab();
		Thread.sleep(2000);
		VerifyAddedProductStoredInDatabasePOM073.clickonSpecialTab();
		Thread.sleep(2000);
		VerifyAddedProductStoredInDatabasePOM073.clickonImageTab();
		Thread.sleep(2000);
		VerifyAddedProductStoredInDatabasePOM073.clickonRewardpointTab();
		Thread.sleep(2000);
		VerifyAddedProductStoredInDatabasePOM073.clickonDesignTab();
		Thread.sleep(2000);
	}*/
		@Test ( priority = 1)
		
		public void SaveButtonClickForAddedProductTest() throws InterruptedException {
		VerifyAddedProductStoredInDatabasePOM073.clickonSaveBtn();
		Thread.sleep(2000);
		String actualtextdisplay=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]")).getText();
		//String expecteddisplay = "Success: You have modified products!";
		Assert.assertTrue(actualtextdisplay.contains("Success"),"Modify confirm message not matched or Problem in loading url page"); 
		System.out.println("As per RTTD_009 of Test Data Sheet - Multiple Products have been added sucessufully in the Product list");
		System.out.println("*Application allowed admin to add multiple product with the rewards point, Hence this TC execution is passed!");
		//Capture Screen shot
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String filename="RTTC_073_"+df.format(new Date()).toString();
		System.out.println(filename);
		screenShot.captureScreenShot(filename);
		//=====================
		}
		@Test ( priority = 2)
		//This Step do a search of the added product in the application
		public void SeachProductAfterAddOfProductListTest() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		VerifyAddedProductStoredInDatabasePOM073.searchProductName("A Finger Ring");
		VerifyAddedProductStoredInDatabasePOM073.searchPriceTextbox("500");
		VerifyAddedProductStoredInDatabasePOM073.searchModelTextbox("SKU-012");
		VerifyAddedProductStoredInDatabasePOM073.searchQuantityTextbox("50");
			//Click on Filter button
			driver.findElement(By.id("button-filter")).click();
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// This  will scroll down the page by  1000 pixel vertical		
	        js.executeScript("window.scrollBy(0,1000)");
	        js.executeScript("window.scrollBy(3000,0)");
		

	}
		
		@Test ( priority = 3)
		// This step display the search result of the added product from product list in Application URL
		public void SeachProductTablefromProductListTest() throws InterruptedException{
		WebElement searchtable = driver.findElement(By.xpath("//*[@id=\"form-product\"]/div"));
		List<WebElement> allRowsSearch = searchtable.findElements(By.tagName("tr")); 
		System.out.println(allRowsSearch.size());
		// And iterate over them, getting the cells 
		for (WebElement row : allRowsSearch) { 
		    List<WebElement> cellsSearch = row.findElements(By.tagName("td")); 

		    // Print the contents of each cell
		    for (WebElement cell : cellsSearch) { 
		        System.out.print(cell.getText()+"\t");
                  }
		    System.out.println();
		    
		                          }
		}
		@Test(priority = 4, dataProvider = "db-inputs", dataProviderClass = VerifyAddedProductDataProvider_RTTC_073.class)
		public void loginDBTest(String pname, String mTitle, String model, String tprice, String category, String qty, String uprice, String pts) {
			//This @test display the data into the Console from Maria DB product table
			System.out.println("Values Returned from Maria DB....\n");
			System.out.println(pname+"\t"+mTitle+"\t"+model+"\t"+tprice+"\t"+category+"\t"+qty+"\t"+uprice+"\t"+pts);

		}
  }

