package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

public class ListOfCatagories_RTTC_012 {

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
	public void validLoginTest() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		String actualtitle = driver.getTitle();
		System.out.println("Actual Title :"+actualtitle);
		String expectedTitle = "Dashboard";
		System.out.println("Expected Title :"+expectedTitle);
		//Verify expected page title and actual page title is same 
		Assert.assertTrue(actualtitle.equalsIgnoreCase(expectedTitle),"Page title not matched or Problem in loading url page"); 
		System.out.println("Both Expected and Actual titles are matching on the Page");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		categoryPOM.clickButtonMenu();
		categoryPOM.clickCatalogMenu();
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
				
		        categoryPOM.clickCategory();
		// Printing table header of a web table assuming first row as header
				List<WebElement> allHeadersOfTable= driver.findElements(By.xpath("//*[@id=\"form-category\"]/div/table"));
				//System.out.println("Headers in table are below:");
			//	System.out.println("No. of rows found: "+allHeadersOfTable.size());
				System.out.println("Categories Table displayed as below-");
				System.out.println("====================================");
				for(WebElement header:allHeadersOfTable)
				{
					System.out.println(header.getText());
				}
				actualtitle = driver.getTitle();
				System.out.println("Actual Title of Category Screen :"+actualtitle);
				expectedTitle = "Categories";
				System.out.println("Expected Title of Category Screen :"+expectedTitle);
				Assert.assertTrue(actualtitle.equalsIgnoreCase(expectedTitle),"Categories Page title not matched or Problem in loading url page");
				System.out.println("Both Actual and Expected Title of Category page are matched :"+expectedTitle);
				System.out.println("Validation of Dashboard, Catalog and Category Pages are Passed");
				screenShot.captureScreenShot("First");
	}
	
}

