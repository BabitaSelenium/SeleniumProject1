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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC_044_POM_DeleteMulProduct;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;


	public class RTTC_044_Test_DeleteMulProduct {
		private WebDriver driver;
		private String baseUrl;
		private RTTC_044_POM_DeleteMulProduct DeleteMulProdPOM44;
		private static Properties properties;
		private ScreenShot screenShot;

		@BeforeClass
		public void setUpBeforeClass() throws IOException {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/others.properties");
			properties.load(inStream);
			driver = DriverFactory.getDriver(DriverNames.CHROME);
			DeleteMulProdPOM44 = new RTTC_044_POM_DeleteMulProduct(driver); 
			baseUrl = properties.getProperty("baseURL");
			screenShot = new ScreenShot(driver); 
			// open the browser 
			driver.get(baseUrl);
			DeleteMulProdPOM44=new RTTC_044_POM_DeleteMulProduct(driver);
		}

		
		
		@AfterClass
		public void tearDown() throws Exception {
			Thread.sleep(1000);
			driver.quit();
		}
		
		 //Login as Admin in Retail URL
	  	@Test( priority = 0)
	  		public void LoginTest1() throws InterruptedException {
	  		DeleteMulProdPOM44.sendUserName("admin");
	  		DeleteMulProdPOM44.sendPassword("admin@123");
	  		DeleteMulProdPOM44.clickLoginBtn();
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
	  	
	  	// Step 1: Click on Catalog icon
		@Test( priority = 1)
		public void catalogIconTest2() throws Throwable {
			DeleteMulProdPOM44.clickButtonMenu();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			DeleteMulProdPOM44.clickCatalogMenu();
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
		
		// Step 2. Click on Products link
		@Test( priority = 2)
				
			public void ProductListTest3() throws InterruptedException{
				JavascriptExecutor js = (JavascriptExecutor) driver;
				DeleteMulProdPOM44.clickProduct();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(2000);
				js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
				Thread.sleep(3000);
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
			//Adding 2 unique products to the Product list to perform multiple product deletion			
		
			
			// Step 3. Click on Check box of the product to delete <Ear Rings>
			// Step 4. Click on Check box of the product to delete <Finger Ring>
		@Test( priority = 3)
		public void deleteMultipleProductTest4() throws Throwable {
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			DeleteMulProdPOM44.clickEarRingCheckbox();
			js.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(5000);
			DeleteMulProdPOM44.clickFingerRingCheckbox();
			Thread.sleep(5000);
			js.executeScript("window.scrollBy(0,600)");
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			
			
			driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div/button[2]")).click();
			Thread.sleep(5000);
			driver.switchTo().alert().getText();
			//js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
			driver.switchTo().alert().accept();
			String actualtextdisplay=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]")).getText();
			//String expecteddisplay = "Success: You have modified Products!";
			Assert.assertTrue(actualtextdisplay.contains("Success"),"Delete confirm message not matched or Problem in loading url page"); 
			System.out.println("Steps 3 & 4 Tests are Sucessful :"+actualtextdisplay);
			System.out.println("Two Products < Ear Rings & finger ring> are deleted");
			Thread.sleep(5000);
			//js.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(3000);
			
	
}
}