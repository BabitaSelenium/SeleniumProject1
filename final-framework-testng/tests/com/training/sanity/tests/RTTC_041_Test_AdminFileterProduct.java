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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC_041_POM_AdminFilterProduct;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_041_Test_AdminFileterProduct {
	private WebDriver driver;
	private String baseUrl;
	private RTTC_041_POM_AdminFilterProduct filterProduct041;
	
	
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		filterProduct041 = new RTTC_041_POM_AdminFilterProduct(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void validLoginTest() throws Throwable {
		filterProduct041.sendUserName("admin");
		filterProduct041.sendPassword("admin@123");
		filterProduct041.clickLoginBtn(); 
		String actualtitle = driver.getTitle();
		System.out.println("Actual Title :"+actualtitle);
		String expectedTitle = "Dashboard";
		System.out.println("Expected Title :"+expectedTitle);
		//Verify expected page title and actual page title is same 
		Assert.assertTrue(actualtitle.equalsIgnoreCase(expectedTitle),"Page title not matched or Problem in loading url page"); 
		System.out.println("Both Expected and Actual titles are matching on the Page");
		filterProduct041.clickButtonMenu();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		filterProduct041.clickCatalogMenu();
		//creating new generic arraylist for Catalog link
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
		//	
		
		filterProduct041.clickProduct();
		Thread.sleep(2000);
		
		//
		WebElement prodcutLink = driver.findElement(By.cssSelector("#menu-catalog > ul > li.active.open > a"));
		//WebElement productlink = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[3]/ul[1]/li[2]/a[1]"));
		prodcutLink.click();
		driver.findElement(By.xpath("//*[@id=\"input-name\"]")).sendKeys("Integer vitae iaculis massa");
		//Click on Filter button
		driver.findElement(By.id("button-filter")).click();  
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//Scroll down
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(2000);
		//Scroll up
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
				//Step 5: Enter Valid credentials i.e 515 in Price textbox. below method called from POM.
				filterProduct041.sendKeysPrice("515");
				//Step 6: Click on filter button
				filterProduct041.filterbutton();
				//Scroll down
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(2000);
				//Scroll up
				js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
				//Step 6 table validation as per filter criteria
				//Product List with product details matching filter criteria should get displayed
				WebElement searchtableFilter = driver.findElement(By.xpath("//div[@class='table-responsive']"));
				List<WebElement> allRowsSearchFilter = searchtableFilter.findElements(By.tagName("tr")); 
				System.out.println(allRowsSearchFilter.size());
				// And iterate over them, getting the cells 
				for (WebElement row : allRowsSearchFilter) { 
				    List<WebElement> cellsSearch1 = row.findElements(By.tagName("td")); 

				    // Print the contents of each cell
				    for (WebElement cell : cellsSearch1) { 
				        System.out.print(cell.getText()+"\t");
	                      }
				    System.out.println();
				    
				}	
				//Step 7 dropdown box
			    Select dropdown1 = new Select(driver.findElement(By.xpath("//select[@id='input-status']")));
				dropdown1.selectByVisibleText("Enabled");
				//Step 8. Click on Filter button
				driver.findElement(By.xpath ("//button[@type='button' and @id='button-filter']")).click();
				//Scroll down
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(3000);
				//Scroll up
				js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
				Thread.sleep(3000);
				//Step 9  
				filterProduct041.sendKeysModel("SKU-003");
				filterProduct041.filterbutton();
				//Scroll down
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(3000);
				//Scroll up
				js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
				Thread.sleep(3000);
				
				//Step 11: Enter qty 49 and click on filter button
				filterProduct041.sendKeysQuantity("49");
				filterProduct041.filterbutton();
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(3000);
				//Scroll up
				js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
				Thread.sleep(3000);
				//Step 13: Image dropdown box select enabled option and click on filter
				Select dropdownimage = new Select(driver.findElement(By.xpath("//select[@id='input-image']")));
				dropdownimage.selectByVisibleText("Enabled");
				driver.findElement(By.xpath ("//button[@type='button' and @id='button-filter']")).click();
				//Scroll down
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(3000);
				//Scroll up
				js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
				Thread.sleep(3000);	
	}
	
	           

}

