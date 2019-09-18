package com.training.sanity.tests;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC_042_POM_AdminAddProduct;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_042_Test_AdminAddProduct {
	
	private WebDriver driver;
	private String baseUrl;
	//Object declaration for POM file
	private RTTC_042_POM_AdminAddProduct AddProdcutPOM042;
	
	
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		//Instantiate POM file
		AddProdcutPOM042 = new RTTC_042_POM_AdminAddProduct(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		/*driver = DriverFactory.getDriver(DriverNames.CHROME);
		//Instantiate POM file
		AddProdcutPOM042 = new RTTC_042_POM_AdminAddProduct(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);*/
		
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	    //Login as Admin in Retail URL
        @Test
        public void LoginTest1() throws InterruptedException {
	    AddProdcutPOM042.sendUserName("admin");
	    AddProdcutPOM042.sendPassword("admin@123");
	    AddProdcutPOM042.clickLoginBtn();
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
        
        //Catalog icon click,list displayed <compared with actual list from TC> checked with Assertion
	    @Test(dependsOnMethods = { "LoginTest1" })
	   	public void CatalogMenuTest2() {			
			AddProdcutPOM042.clickButtonMenu();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			AddProdcutPOM042.clickCatalogMenu();
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
			@Test (dependsOnMethods = { "LoginTest1", "CatalogMenuTest2" })
			public void ProductTest3() throws InterruptedException{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			AddProdcutPOM042.clickProduct();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(2000);
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
			//Product List with product details matching filter criteria should get displayed
			WebElement searchtable = driver.findElement(By.xpath("//*[@id=\"form-product\"]"));
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
			
			// Add Product steps. validation of Textbox and sendkeys value.
			// General tab: Product name, MetaTitletag. 
			// Data tab: Model, Price, Qty values entered. 	
			@Test (dependsOnMethods = { "LoginTest1", "CatalogMenuTest2", "ProductTest3" })
			public void ProductTest4() throws InterruptedException{
			AddProdcutPOM042.clickplusButton();
			driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			AddProdcutPOM042.sendproductname("Finger Ring");
			Thread.sleep(3000);
			AddProdcutPOM042.sendTextmetaTitletag("Finger Ring for ladies");
			//scroll up
			AddProdcutPOM042.clickonDataTab();
			AddProdcutPOM042.sendTextModelTextbox("SKU-012");
			//scroll some axis to view
			js.executeScript("window.scrollBy(0,500)");
			Thread.sleep(3000);
			AddProdcutPOM042.sendTextPriceTextbox("500");
			Thread.sleep(3000);
			AddProdcutPOM042.sendTextQuantityTextbox("80");
			Thread.sleep(2000);
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
			}
			
			// Default values to check in Link Tab, Attribute Tab, Option tab,Recurring tab, Discount tab,Special tab, Image tab, Rewardpoint tab, Design tab
			@Test (dependsOnMethods = { "LoginTest1", "CatalogMenuTest2", "ProductTest3", "ProductTest4"  })
			public void DefaultTabTest5() throws InterruptedException{			
			//JavascriptExecutor js = (JavascriptExecutor) driver;
			AddProdcutPOM042.clickonLinkTab();
			// try for display & enabled checks for Catagory dropdownbox
/*			WebElement Catagorydropdown = driver.findElement(By.xpath("//*[@id=\"input-category\"]"));
			Boolean dropdownPresent= Catagorydropdown.isDisplayed();
			if(dropdownPresent==true)
	        {
	            System.out.println("Dropdown is appearing");
	        }
	        else{
	            System.out.println("Dropdown is not appearing");
	        }
			Boolean dropdownEnable= Catagorydropdown.isEnabled();
			if(dropdownEnable==true)
	        {
	            System.out.println("Dropdown is Enabled");
	        }
	        else{
	            System.out.println("Dropdown is not enabled");
	        }
			
			
			Catagorydropdown.click();
			Thread.sleep(3000);
			Catagorydropdown.sendKeys(("EARRINGS"),(Keys.TAB));
			Thread.sleep(4000);*/
			Thread.sleep(4000);
			//JavascriptExecutor js = (JavascriptExecutor)driver; 
		//	AddProdcutPOM042.sendTextCategoriesTextbox.clear();
			AddProdcutPOM042.sendTextCategoriesTextbox("Value");
		//	js.executeScript("document.querySelector(\"input[placeholder='Manufacturer']\").value='BBB MANUFACTURER'");
			Thread.sleep(2000);
			//js.executeScript("document.querySelector(\"input[placeholder='Categories']\").value='EARRINGS'");
	        
			//below code for send keys worked
			//driver.findElement(By.xpath("//input[@id='input-category']")).sendKeys(("EARRINGS"),(Keys.TAB));
			//Thread.sleep(3000);
			//Scroll down
	        Thread.sleep(4000);
			//js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			//Scroll up 
		//	js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
			//Attribute tab
			AddProdcutPOM042.clickonAttributeTab();
			Thread.sleep(3000);
			AddProdcutPOM042.clickonOptionTab();
			Thread.sleep(3000);
			AddProdcutPOM042.clickonRecurringTab();
			Thread.sleep(3000);
			AddProdcutPOM042.clickonDiscountTab();
			Thread.sleep(2000);
			AddProdcutPOM042.clickonSpecialTab();
			Thread.sleep(2000);
			AddProdcutPOM042.clickonImageTab();
			Thread.sleep(2000);
			AddProdcutPOM042.clickonRewardpointTab();
			Thread.sleep(2000);
			AddProdcutPOM042.clickonDesignTab();
			Thread.sleep(2000);
			}
			
			@Test (dependsOnMethods = { "LoginTest1", "CatalogMenuTest2", "ProductTest3", "ProductTest4", "DefaultTabTest5"  })
			public void SaveButtonTest6() throws InterruptedException{
			
			AddProdcutPOM042.clickonSaveBtn();
			Thread.sleep(2000);
			String actualtextdisplay=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]")).getText();
			//String expecteddisplay = "Success: You have modified products!";
			Assert.assertTrue(actualtextdisplay.contains("Success"),"Modify confirm message not matched or Problem in loading url page"); 
			System.out.println("Product is added");
			System.out.println("=======================================================");
			Thread.sleep(2000);
			screenShot.captureScreenShot("First");
			}
	    
}	 


		


