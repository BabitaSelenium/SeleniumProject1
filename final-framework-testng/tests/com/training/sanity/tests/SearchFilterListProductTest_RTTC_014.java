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
import com.training.pom.LoginPOM_RTTC_011;
import com.training.pom.ListOfCatalogPOM_RTTC_012;
import com.training.pom.SearchFilterListProductPOM_RTTC_014;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class SearchFilterListProductTest_RTTC_014 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM_RTTC_011 loginPOM;
	//private ListOfCatalogPOM_RTTC_012 categoryPOM;
	private SearchFilterListProductPOM_RTTC_014 productPOM;
	private SearchFilterListProductPOM_RTTC_014 catalogPOM;
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
		loginPOM = new LoginPOM_RTTC_011(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//categoryPOM=new ListOfCatalogPOM_RTTC_012(driver);
		catalogPOM=new SearchFilterListProductPOM_RTTC_014(driver);
		productPOM=new SearchFilterListProductPOM_RTTC_014(driver);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void validLoginTest() throws Throwable {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		String actualtitle = driver.getTitle();
		System.out.println("Actual Title :"+actualtitle);
		String expectedTitle = "Dashboard";
		System.out.println("Expected Title :"+expectedTitle);
		//Verify expected page title and actual page title is same 
		Assert.assertTrue(actualtitle.equalsIgnoreCase(expectedTitle),"Page title not matched or Problem in loading url page"); 
		System.out.println("Both Expected and Actual titles are matching on the Page");
		productPOM.clickButtonMenu();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		productPOM.clickCatalogMenu();
		productPOM.clickProduct();
		Thread.sleep(2000);
		
		WebElement prodcutLink = driver.findElement(By.cssSelector("#menu-catalog > ul > li.active.open > a"));
		//WebElement productlink = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[3]/ul[1]/li[2]/a[1]"));
		prodcutLink.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,3000)");
		js.executeScript("window.scrollBy(3000,0)");
		//First get the entire html table and store this in a variable ‘webtable’ of type web element.
		// Grab the table 
		WebElement table = driver.findElement(By.xpath("//*[@id=\"form-product\"]/div/table")); 
		// Now get all the TR elements from the table 
		List<WebElement> allRows = table.findElements(By.tagName("tr")); 
		System.out.println(allRows.size());
		// And iterate over them, getting the cells 
		for (WebElement row : allRows) { 
		    List<WebElement> cells = row.findElements(By.tagName("td")); 

		    // Print the contents of each cell
		    for (WebElement cell : cells) { 
		        System.out.print(cell.getText()+"\t");
		    }
		    System.out.println();
		}
		//Product List with product product details are displayed...use assertion
		// Enter valid credentials in Product Name textbox
		//Validate Product title page
		driver.findElement(By.xpath("//*[@id=\"input-name\"]")).sendKeys("Integer vitae iaculis massa");
		//Click on Filter button
		driver.findElement(By.id("button-filter")).click();
		
		// This  will scroll down the page by  1000 pixel vertical		
        js.executeScript("window.scrollBy(0,1000)");
        js.executeScript("window.scrollBy(3000,0)");
        //Scroll down to see complete list
		//Product List with product details matching filter criteria should get displayed --as a part of assertion
		//Enter Valid credentials in Price textbox - 515
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"input-name\"]")).clear();
		driver.findElement(By.xpath("//input[@id='input-price']")).sendKeys("515");
		driver.findElement(By.xpath("//button[@id='button-filter']")).click();
		js.executeScript("window.scrollBy(0,1000)");
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
		Assert.assertTrue(allRowsSearch.size()>1,"No Such Product Found");
				screenShot.captureScreenShot("First");
	}
	
}


