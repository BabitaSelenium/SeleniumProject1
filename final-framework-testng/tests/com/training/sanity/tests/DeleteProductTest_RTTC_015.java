package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ListOfCatalogPOM_RTTC_012;
import com.training.pom.LoginPOM_RTTC_011;
import com.training.pom.SearchFilterListProductPOM_RTTC_014;
import com.training.pom.DeleteProductPOM_RTTC_015;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class DeleteProductTest_RTTC_015 {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM_RTTC_011 loginPOM;
	private ListOfCatalogPOM_RTTC_012 categoryPOM;
	private DeleteProductPOM_RTTC_015 productPOM;
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
	//	categoryPOM=new ListOfCatalogPOM_RTTC_012(driver);
		productPOM=new DeleteProductPOM_RTTC_015(driver);
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
	//	driver.findElement(By.xpath("//*[@id='menu-catalog']/ul/li[2]/a")).click();
		//Thread.sleep(2000);
		WebElement prodcutLink = driver.findElement(By.cssSelector("#menu-catalog > ul > li.active.open > a"));
		//WebElement productlink = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[3]/ul[1]/li[2]/a[1]"));
		prodcutLink.click();
		driver.findElement(By.xpath("//*[@id=\"input-name\"]")).sendKeys("Ear Rings");
		//Click on Filter button
		driver.findElement(By.id("button-filter")).click();  
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		js.executeScript("window.scrollBy(1000,0)");
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
				WebElement objCheckbox = driver.findElement(By.xpath("//*[@id=\"form-product\"]/div/table/tbody/tr[1]/td[1]/input"));
				objCheckbox.click();
				driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div/button[2]")).click();
				Thread.sleep(5000);
				driver.switchTo().alert().getText();
				
				driver.switchTo().alert().accept();
				String actualtextdisplay=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]")).getText();
				//String expecteddisplay = "Success: You have modified Products!";
				Assert.assertTrue(actualtextdisplay.contains("Success"),"Delete confirm message not matched or Problem in loading url page"); 
				System.out.println("Step 5 Test is Sucessful :"+actualtextdisplay);
				System.out.println("One Product Ear Rings is deleted");
				Thread.sleep(5000);
				js.executeScript("window.scrollBy(0,1000)");
				Thread.sleep(3000);
				driver.close();
				
	}
}
