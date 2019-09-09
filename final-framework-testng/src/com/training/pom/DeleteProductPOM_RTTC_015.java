package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteProductPOM_RTTC_015 {
private WebDriver driver; 
	
	public DeleteProductPOM_RTTC_015(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
		
	@FindBy(xpath="//*[@id=\"button-menu\"]")
	private WebElement buttonmenu;
	
	@FindBy(linkText="Catalog")
	//(id="menu-catalog")
	private WebElement catalog;
	
	@FindBy(linkText="Products")
	//		xpath="//*[@id='menu-catalog']/ul/li[2]/a")
	private WebElement product;
	
	
	public void clickButtonMenu() {
		// TODO Auto-generated method stub
		this.buttonmenu.click();
	}
	
	public void clickCatalogMenu() {
		// TODO Auto-generated method stub
		this.catalog.click();
	}
	
	public void clickProduct() {
		// TODO Auto-generated method stub
		this.product.click();
	}
}


