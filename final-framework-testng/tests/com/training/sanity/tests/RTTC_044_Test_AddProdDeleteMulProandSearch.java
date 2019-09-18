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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC_044_POM_AddProdDeleteMulProandSearch;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_044_Test_AddProdDeleteMulProandSearch {
	private WebDriver driver;
	private String baseUrl;
	private RTTC_044_POM_AddProdDeleteMulProandSearch AddProDeleteMulProdPOM44;
	private static Properties properties;
	private ScreenShot screenShot;
	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		AddProDeleteMulProdPOM44 = new RTTC_044_POM_AddProdDeleteMulProandSearch(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		//AddProDeleteMulProdPOM44=new RTTC_044_POM_AddProdDeleteMulProandSearch(driver);
	}
    
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	

	//Login as Admin in Retail URL
  	@Test( priority = 0)
  		public void LoginTest1() throws InterruptedException {
  		AddProDeleteMulProdPOM44.sendUserName("admin");
  		AddProDeleteMulProdPOM44.sendPassword("admin@123");
  		AddProDeleteMulProdPOM44.clickLoginBtn();
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
 			AddProDeleteMulProdPOM44.clickButtonMenu();
 			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 			AddProDeleteMulProdPOM44.clickCatalogMenu();
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
 				AddProDeleteMulProdPOM44.clickProduct();
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
 		/*RTTC_044: As Ear Rings & Finger Ring are present in more numbers in the product list table.
 		1.For this test case first I am doing a search the products search text box 
 		2.Adding two unique products 
 		3.After that deleting those two products
 		4.Then search deleted item as a part of validation*/
 		
 		
 		
 		// Add of one product
 		@Test ( priority = 3)
		public void ProductTest4() throws InterruptedException{
 			AddProDeleteMulProdPOM44.clickplusButton();
		driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		AddProDeleteMulProdPOM44.sendproductname("A Tshirt");
		Thread.sleep(3000);
		AddProDeleteMulProdPOM44.sendTextmetaTitletag("Tshirt for Kids");
		//scroll up
		AddProDeleteMulProdPOM44.clickonDataTab();
		AddProDeleteMulProdPOM44.sendTextModelTextbox("SKU-099");
		//scroll some axis to view
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		AddProDeleteMulProdPOM44.sendTextPriceTextbox("499");
		Thread.sleep(3000);
		AddProDeleteMulProdPOM44.sendTextQuantityTextbox("27");
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		}
 	// Default values to check in Link Tab, Attribute Tab, Option tab,Recurring tab, Discount tab,Special tab, Image tab, Rewardpoint tab, Design tab
 				@Test ( priority = 4)
 				public void DefaultTabTest5() throws InterruptedException{			
 				JavascriptExecutor js = (JavascriptExecutor) driver;
 				AddProDeleteMulProdPOM44.clickonLinkTab();
 				
 				//driver.findElement(By.xpath("//input[@id='input-category']")).sendKeys(("EARRINGS"),(Keys.TAB));
 				//Thread.sleep(3000);
 				//Scroll down
 		        Thread.sleep(4000);
 				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
 				//Scroll up 
 				js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
 				//Attribute tab
 		       AddProDeleteMulProdPOM44.clickonAttributeTab();
 				Thread.sleep(3000);
 				AddProDeleteMulProdPOM44.clickonOptionTab();
 				Thread.sleep(3000);
 				AddProDeleteMulProdPOM44.clickonRecurringTab();
 				Thread.sleep(3000);
 				AddProDeleteMulProdPOM44.clickonDiscountTab();
 				Thread.sleep(2000);
 				AddProDeleteMulProdPOM44.clickonSpecialTab();
 				Thread.sleep(2000);
 				AddProDeleteMulProdPOM44.clickonImageTab();
 				Thread.sleep(2000);
 				AddProDeleteMulProdPOM44.clickonRewardpointTab();
 				Thread.sleep(2000);
 				AddProDeleteMulProdPOM44.clickonDesignTab();
 				Thread.sleep(2000);
 				AddProDeleteMulProdPOM44.clickonSaveBtn();
 				Thread.sleep(3000);
 				
                //Search for the product
 				driver.navigate().refresh();
 				driver.findElement(By.xpath("//input[@id='input-name']")).sendKeys("A Tshirt");
 				//Click on Filter button
 				driver.findElement(By.id("button-filter")).click();
 				
 				// This  will scroll down the page by  1000 pixel vertical		
 		        js.executeScript("window.scrollBy(0,1000)");
 		        js.executeScript("window.scrollBy(3000,0)");
 				}
 				
 			// Add of second product
 		 		@Test ( priority = 5)
 				public void AddSecProductTest6() throws InterruptedException{
 		 			AddProDeleteMulProdPOM44.clickplusButton();
 				driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
 				JavascriptExecutor js = (JavascriptExecutor) driver;
 				AddProDeleteMulProdPOM44.sendproductname("A Trouser");
 				Thread.sleep(3000);
 				AddProDeleteMulProdPOM44.sendTextmetaTitletag("Trouser for Kids");
 				//scroll up
 				AddProDeleteMulProdPOM44.clickonDataTab();
 				AddProDeleteMulProdPOM44.sendTextModelTextbox("SKU-109");
 				//scroll some axis to view
 				js.executeScript("window.scrollBy(0,500)");
 				Thread.sleep(3000);
 				AddProDeleteMulProdPOM44.sendTextPriceTextbox("599");
 				Thread.sleep(3000);
 				AddProDeleteMulProdPOM44.sendTextQuantityTextbox("30");
 				Thread.sleep(2000);
 				js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
 				}
 		 	// Default values to check in Link Tab, Attribute Tab, Option tab,Recurring tab, Discount tab,Special tab, Image tab, Rewardpoint tab, Design tab
 		 				@Test ( priority = 6)
 		 				public void DefaultTab2ndProductTest7() throws InterruptedException{			
 		 				JavascriptExecutor js = (JavascriptExecutor) driver;
 		 				AddProDeleteMulProdPOM44.clickonLinkTab();
 		 				
 		 				//driver.findElement(By.xpath("//input[@id='input-category']")).sendKeys(("EARRINGS"),(Keys.TAB));
 		 				//Thread.sleep(3000);
 		 				//Scroll down
 		 		        Thread.sleep(4000);
 		 				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
 		 				//Scroll up 
 		 				js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
 		 				//Attribute tab
 		 		       AddProDeleteMulProdPOM44.clickonAttributeTab();
 		 				Thread.sleep(3000);
 		 				AddProDeleteMulProdPOM44.clickonOptionTab();
 		 				Thread.sleep(3000);
 		 				AddProDeleteMulProdPOM44.clickonRecurringTab();
 		 				Thread.sleep(3000);
 		 				AddProDeleteMulProdPOM44.clickonDiscountTab();
 		 				Thread.sleep(2000);
 		 				AddProDeleteMulProdPOM44.clickonSpecialTab();
 		 				Thread.sleep(2000);
 		 				AddProDeleteMulProdPOM44.clickonImageTab();
 		 				Thread.sleep(2000);
 		 				AddProDeleteMulProdPOM44.clickonRewardpointTab();
 		 				Thread.sleep(2000);
 		 				AddProDeleteMulProdPOM44.clickonDesignTab();
 		 				Thread.sleep(2000);
 		 				AddProDeleteMulProdPOM44.clickonSaveBtn();
 		 				Thread.sleep(2000);
 		 				}
 		 			//Search for the 2nd product
 		 				@Test ( priority = 7)
 		 				public void search2ndProdTest8() throws InterruptedException {
 		 				WebElement searchProduct = driver.findElement(By.xpath("//input[@id='input-name']"));
 		 				searchProduct.clear();
 		 				searchProduct.sendKeys("A Trouser");
 		 				//Click on Filter button
 		 				driver.findElement(By.id("button-filter")).click();
 		 				Thread.sleep(2000);
 		 				JavascriptExecutor js = (JavascriptExecutor) driver;
 		 				// This  will scroll down the page by  1000 pixel vertical		
 		 		        js.executeScript("window.scrollBy(0,1000)");
 		 		        js.executeScript("window.scrollBy(3000,0)");
 		 		       driver.navigate().refresh();
 		 		       driver.findElement(By.xpath("//input[@id='input-name']")).clear();
 		 		       driver.findElement(By.id("button-filter")).click();
 		 		       
 		 		        
 		 		        
 		 				}
 		 				
 		 			// Step 3. Click on Check box of the product to delete <Tshirt>
 		 				// Step 4. Click on Check box of the product to delete <Trouser>
 		 			@Test( priority = 8)
 		 			public void deleteMultipleProductTest9() throws Throwable {
 		 				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 		 				//driver.findElement(By.id("button-filter")).click();
		 				Thread.sleep(2000);
 		 				JavascriptExecutor js = (JavascriptExecutor) driver;
 		 				AddProDeleteMulProdPOM44.clicktshirtCheckbox();
 		 				js.executeScript("window.scrollBy(0,1000)");
 		 				Thread.sleep(5000);
 		 				AddProDeleteMulProdPOM44.clicktrouserCheckbox();
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
 		 				System.out.println("Two Products < 'A Tshirt' & 'A Trouser' > are deleted");
 		 				Thread.sleep(5000);
 		 				//js.executeScript("window.scrollBy(0,1000)");
 		 				Thread.sleep(3000);
 		 			}
 		 				@Test( priority = 9)
 		 				public void searchtableForPro1AfetrDeleteTest10() throws InterruptedException{
 		 					WebElement searchProduct = driver.findElement(By.xpath("//input[@id='input-name']"));
 	 		 				searchProduct.clear();
 	 		 				searchProduct.sendKeys("A Tshirt");
 	 		 				//Click on Filter button
 	 		 				driver.findElement(By.id("button-filter")).click();
 	 		 				Thread.sleep(2000);	
 		 				WebElement searchtablePrd1 = driver.findElement(By.xpath("//*[@id=\"form-product\"]"));
 		 				List<WebElement> allRowsSearch = searchtablePrd1.findElements(By.tagName("tr")); 
 		 				System.out.println(allRowsSearch.size());
 		 				int flag = 0;
 		 				for (WebElement row : allRowsSearch) { 
 		 				    List<WebElement> cellsSearch = row.findElements(By.tagName("td")); 

 		 				    // Print the contents of each cell
 		 				    for (WebElement cell : cellsSearch) { 
 		 				        System.out.print(cell.getText()+"\t");
 		 				      if(cell.getText().contains("No results!")){
 		 		 					System.out.println("No Result Found in Product1 table...");
 		 		 					flag=1;
 		 		 					break;
 		 				      }
 		 		          }
 		 				    System.out.println();
 		 				    if(flag == 1)
 		 				    	break;
 		 				    
 		 				 }
 		 				}
 		 				@Test( priority = 10)
 		 				public void searchtableForPro2AfetrDeleteTest11() throws InterruptedException{
 		 					WebElement searchProduct2 = driver.findElement(By.xpath("//input[@id='input-name']"));
 	 		 				searchProduct2.clear();
 	 		 				searchProduct2.sendKeys("A Trouser");
 	 		 				//Click on Filter button
 	 		 				driver.findElement(By.id("button-filter")).click();
 	 		 				Thread.sleep(2000);	
 	 		 				int flag = 0;
 		 				WebElement searchtablePrd2 = driver.findElement(By.xpath("//*[@id=\"form-product\"]"));
 		 				List<WebElement> allRowsSearch = searchtablePrd2.findElements(By.tagName("tr")); 
 		 				System.out.println(allRowsSearch.size());
 		 				
 		 				for (WebElement row : allRowsSearch) { 
 		 				    List<WebElement> cellsSearch = row.findElements(By.tagName("td")); 

 		 				    // Print the contents of each cell
 		 				    for (WebElement cell : cellsSearch) { 
 		 				        System.out.print(cell.getText()+"\t");
 		 				      if(cell.getText().contains("No results!")){
 		 		 					System.out.println("No Result Found in Product 2 table...");
 		 		 					flag=1;
 		 		 					break;
 		 				      }
 		 		          }
 		 				    System.out.println();
 		 				    if(flag == 1)
 		 				    	break;
 		 				 }
 		 				}
}

