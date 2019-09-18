package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RTTC_044_POM_DeleteMulProduct {
	private WebDriver driver; 

	public RTTC_044_POM_DeleteMulProduct(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	//Path for LOGIN
		@FindBy(id="input-username")
		private WebElement userName; 
		
		@FindBy(id="input-password")
		private WebElement password;
		
		@FindBy(xpath="//*[@id=\"content\"]/div/div/div/div/div[2]/form/div[3]/button")
		private WebElement loginBtn; 
	
        @FindBy(xpath="//*[@id=\"button-menu\"]")
        private WebElement buttonmenu;

        @FindBy(linkText="Catalog")
        //(id="menu-catalog")
        private WebElement catalog;

        @FindBy(linkText="Products")
        //		xpath="//*[@id='menu-catalog']/ul/li[2]/a")
        private WebElement product;
        
        @FindBy(xpath="//td[contains(text(),'Ear Rings1')]/preceding-sibling::td/input[@type='checkbox']")
        //		xpath="//*[@id='menu-catalog']/ul/li[2]/a")
        private WebElement earRingCheckBox;
        
        @FindBy(xpath="//td[contains(text(),'Finger Ring')]/preceding-sibling::td/input[@type='checkbox']")
        //		xpath="//*[@id='menu-catalog']/ul/li[2]/a")
        private WebElement fingerRingCheckBox;
        
        
    

        public void sendUserName(String userName) {
    		this.userName.clear();
    		this.userName.sendKeys(userName);
    	}
    	
    	public void sendPassword(String password) {
    		this.password.clear(); 
    		this.password.sendKeys(password); 
    	}
    	
    	public void clickLoginBtn() {
    		this.loginBtn.click(); 
    	}
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
        
        public void clickEarRingCheckbox() {
        	// TODO Auto-generated method stub
        	this.earRingCheckBox.click();
        }
        public void clickFingerRingCheckbox() {
        	// TODO Auto-generated method stub
        	this.fingerRingCheckBox.click();
        }
 	}

