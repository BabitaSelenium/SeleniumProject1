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
import com.training.pom.RTTC_043_POM_UpdateQty;
import com.training.pom.RTTC_045_POM_OrderDetailCheckByAdmin;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_045_Test_OrderDetailCheckByAdmin {
	private WebDriver driver;
    private String baseUrl;
    //Object declaration for POM file
    private RTTC_045_POM_OrderDetailCheckByAdmin OrderDeatailPOM045;
    private static Properties properties;
    private ScreenShot screenShot;
    
    @BeforeClass
  	public void setUpBeforeClass() throws IOException {
  		properties = new Properties();
  		FileInputStream inStream = new FileInputStream("./resources/others.properties");
  		properties.load(inStream);
  		driver = DriverFactory.getDriver(DriverNames.CHROME);
  		//Instantiate POM file
  		OrderDeatailPOM045 = new RTTC_045_POM_OrderDetailCheckByAdmin(driver); 
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
  		OrderDeatailPOM045.sendUserName("admin");
  		OrderDeatailPOM045.sendPassword("admin@123");
  		OrderDeatailPOM045.clickLoginBtn();
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
  	
 //  Step 1. Click on Sales Icon
  	@Test( priority = 1)
   	public void CatalogMenuTest2() {			
  		OrderDeatailPOM045.clickButtonMenu();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		OrderDeatailPOM045.clickSalesMenu();
			
		//creating new generic arraylist for Sales Menu
		ArrayList<String> listSales=new ArrayList<String>();
		ArrayList<String> ActuallistSales=new ArrayList<String>();
		//Adding object in arraylist   
		listSales.add("Orders");   
		listSales.add("Recurring Profiles");    
		listSales.add("Returns");
		listSales.add("Gift Vouchers");
		List<WebElement> objLinks  = driver.findElements(By.tagName("a"));
		System.out.println("Expected List in the Catagories  "+listSales);
		System.out.println("Actual List of Sales Menu: ");
		for  (WebElement objCurrentLink : objLinks) {
			String strLinkText =  objCurrentLink.getText();
			if(listSales.contains(strLinkText)){
			System.out.println(strLinkText);
			
			ActuallistSales.add(strLinkText);
			}
		}
		Collections.sort(listSales);
		Collections.sort(ActuallistSales);
		Assert.assertTrue(listSales.equals(ActuallistSales),"Sales Lists are not matching.");
		System.out.println("===================================================");
    }

//    2. click on Orders link
  	@Test ( priority = 2)
	public void OrderTest3() throws InterruptedException{
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	OrderDeatailPOM045.clickOrdersMenu();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	Thread.sleep(2000);
	js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
	//  
	WebElement orderTable = driver.findElement(By.xpath("//*[@id=\"form-order\"]"));
	List<WebElement> allRowsTable = orderTable.findElements(By.tagName("tr")); 
	System.out.println("Total number of rows in Product Table are: "+allRowsTable.size());
	for (WebElement row : allRowsTable) { 
	    List<WebElement> columns = row.findElements(By.tagName("td")); 
	    System.out.println("Total number of columns in Product Table are: "+columns.size());
	    break;
                               
	}
	System.out.println("===================================================");
    }
//    3. Click on View icon beside the ordered product
  	@Test ( priority = 3)
	public void ViewIconTest3() throws InterruptedException{
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	OrderDeatailPOM045.clickViewIcon();
	Thread.sleep(3000);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	Thread.sleep(3000);
	js.executeScript("window.scrollTo(0, -document.body.scrollHeight);"); 	

}
  	//    4. click on Generate icon of invoice tab
    
  	@Test ( priority = 4)
	public void InvoiceGenerateTest4() throws InterruptedException{
  		String parentHandle = driver.getWindowHandle();
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	OrderDeatailPOM045.clickInvoiceIcon();
	Thread.sleep(3000);
	for (String winHandle : driver.getWindowHandles()) {
	    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
	}
	
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	String invoiceNo=driver.findElement(By.xpath("//h1[contains(text(),'Invoice #')]")).getText();
  	
	System.out.println(invoiceNo);
  	}


}