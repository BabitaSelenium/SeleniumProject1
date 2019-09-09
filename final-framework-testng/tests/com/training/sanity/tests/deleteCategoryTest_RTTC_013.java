package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class deleteCategoryTest_RTTC_013 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM_RTTC_011 loginPOM;
	private ListOfCatalogPOM_RTTC_012 categoryPOM;
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
		categoryPOM=new ListOfCatalogPOM_RTTC_012(driver);
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
		//Verify expected page title and actual page title is same 
	//	Assert.assertTrue(actualtitle.equalsIgnoreCase(expectedTitle),"Page title not matched or Problem in loading url page"); 
		categoryPOM.clickButtonMenu();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		categoryPOM.clickCatalogMenu();
		categoryPOM.clickCategory();
		//===============================================================================
		//First get the entire html table and store this in a variable 'table' of type web element.
				// Grab the table 
				WebElement table = driver.findElement(By.xpath("//div[@class='table-responsive']")); 
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
		//==================================================================================
		
		driver.findElement(By.xpath("//td[contains(text(),'INDIAN')]/preceding-sibling::td/input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
		Thread.sleep(5000);
		String alerttext = driver.switchTo().alert().getText();
		System.out.println("Confirmation Message :"+alerttext);
		driver.switchTo().alert().accept();
		Thread.sleep(5000);
		String actualtextdisplay=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]")).getText();
		//String expecteddisplay = "Success: You have modified categories!";
		Assert.assertTrue(actualtextdisplay.contains("Success"),"Delete confirm message not matched or Problem in loading url page"); 
		System.out.println("Step 5 Test is Sucessful :"+actualtextdisplay);
		System.out.println("Catagory name INDIAN is deleted");
		screenShot.captureScreenShot("First");
		driver.close();
	}
	
}

