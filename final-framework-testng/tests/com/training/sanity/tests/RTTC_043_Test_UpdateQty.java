package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC_042_POM_AdminAddProduct;
import com.training.pom.RTTC_043_POM_UpdateQty;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

public class RTTC_043_Test_UpdateQty {
      private WebDriver driver;
      private String baseUrl;
      //Object declaration for POM file
      private RTTC_043_POM_UpdateQty UpdateQtyPOM043;
      private static Properties properties;
      private ScreenShot screenShot;
      
  
  @BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		//Instantiate POM file
		UpdateQtyPOM043 = new RTTC_043_POM_UpdateQty(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
  @AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
  }
  		//Login as Admin in Retail URL
  	@Test( priority = 0)
  		public void LoginTest1() throws InterruptedException {
  			UpdateQtyPOM043.sendUserName("admin");
  			UpdateQtyPOM043.sendPassword("admin@123");
  			UpdateQtyPOM043.clickLoginBtn();
  			String actualtitle = driver.getTitle();
  			System.out.println("Actual Title :"+actualtitle);
  			String expectedTitle = "Dashboard";
  			System.out.println("Expected Title :"+expectedTitle);
	     //Verify expected page title and actual page title is same 
  			Assert.assertTrue(actualtitle.equalsIgnoreCase(expectedTitle),"Page title not matched or Problem in loading url page"); 
  			System.out.println("Both Expected and Actual titles are matching on the Page");
  			System.out.println("===================================================");
    	//System.out.println("LoginTest1 is successful");
}
  		//  Step 1: Click on Catalog icon   
    @Test( priority = 1)
   	public void CatalogMenuTest2() {			
    	UpdateQtyPOM043.clickButtonMenu();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		UpdateQtyPOM043.clickCatalogMenu();
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

//  Step 2: Click on Products link
    
    @Test ( priority = 2)
	public void ProductTest3() throws InterruptedException{
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	UpdateQtyPOM043.clickProduct();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	Thread.sleep(2000);
	js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
	//  
	WebElement productTable = driver.findElement(By.xpath("//*[@id=\"form-product\"]/div/table"));
	List<WebElement> allRowsTable = productTable.findElements(By.tagName("tr")); 
	System.out.println("Total number of rows in Product Table are: "+allRowsTable.size());
	for (WebElement row : allRowsTable) { 
	    List<WebElement> columns = row.findElements(By.tagName("td")); 
	    System.out.println("Total number of columns in Product Table are: "+columns.size());
	    break;
                               
	}
	System.out.println("===================================================");
    }
//  Step 3: Click on Edit icon of the Product
    @Test ( priority = 3)
	public void ProductEditIconTest4() throws InterruptedException{
	JavascriptExecutor js = (JavascriptExecutor) driver;
	UpdateQtyPOM043.clickEditIcon();
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	Thread.sleep(2000);
	js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
    
    }
    // Step 4: Click on Data tab
    // Step 5: Clear data from Quantity textbox
    // Step 6: Enter valid credentials in Quantity textbox
    @Test ( priority = 4)
	public void DataTabTest4() throws InterruptedException{
    	UpdateQtyPOM043.clickonDataTab();
    	Thread.sleep(3000);
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,500)");
    	Thread.sleep(3000);
    	UpdateQtyPOM043.sendTextQuantityTextbox("50");
    	Thread.sleep(3000);
    	//Scroll Up
    	js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
    	    }
    

//  Step 7: Keep default values in Links, Attribute, Option, Recurring, Discount, Special, Image, Reward Points and Design tab
    @Test ( priority = 5)
	public void DefaultTabTest5() throws InterruptedException{
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	UpdateQtyPOM043.clickonLinkTab();
		Thread.sleep(3000);
		//Scroll down
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		//Scroll up 
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		//Attribute tab
		UpdateQtyPOM043.clickonAttributeTab();
		Thread.sleep(3000);
		UpdateQtyPOM043.clickonOptionTab();
		Thread.sleep(3000);
		UpdateQtyPOM043.clickonRecurringTab();
		Thread.sleep(3000);
		UpdateQtyPOM043.clickonDiscountTab();
		Thread.sleep(2000);
		UpdateQtyPOM043.clickonSpecialTab();
		Thread.sleep(2000);
		UpdateQtyPOM043.clickonImageTab();
		Thread.sleep(2000);
		UpdateQtyPOM043.clickonRewardpointTab();
		Thread.sleep(2000);
		UpdateQtyPOM043.clickonDesignTab();
		Thread.sleep(2000);
    }


//  Step 8: Click on Save icon
    @Test ( priority = 6)
	public void SaveIconTest6() throws InterruptedException{
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	UpdateQtyPOM043.clickonSaveBtn();
    	Thread.sleep(2000);
    	String actualtextdisplay=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]")).getText();
		//String expecteddisplay = "Success: You have modified products!";
		Assert.assertTrue(actualtextdisplay.contains("Success"),"Modify confirm message not matched or Problem in loading url page"); 
		System.out.println("Product is modified");
		
		System.out.println("=======================================================");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,2000)");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		Thread.sleep(2000);
		screenShot.captureScreenShot("First");
    }
}

