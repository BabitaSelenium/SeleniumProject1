package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class deletecategoryPOM_RTTC_013 {
private WebDriver driver; 
	
	public deletecategoryPOM_RTTC_013(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
		
	@FindBy(xpath="//*[@id=\"button-menu\"]")
	private WebElement buttonmenu;
	
	@FindBy(id="menu-catalog")
	private WebElement catalog;
	
	@FindBy(linkText= "Categories")
	//xpath="//*[@id=\"menu-catalog\"]/ul/li[1]/a")
	
	private WebElement category;
	
	
	public void clickButtonMenu() {
		// TODO Auto-generated method stub
		this.buttonmenu.click();
	}
	
	public void clickCatalogMenu() {
		// TODO Auto-generated method stub
		this.catalog.click();
	}
	
	public void clickCategory() {
		// TODO Auto-generated method stub
		this.category.click();
	}
}



