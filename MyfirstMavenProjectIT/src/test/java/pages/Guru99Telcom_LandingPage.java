package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;

import frameworkclasses.BasePage;
import frameworkclasses.SeleniumFunctions;

public class Guru99Telcom_LandingPage extends BasePage {

	//First Test. This title should be verified in LandingPageTest class
    public String getTitle() {
        return driver.getTitle();
		} 
	
	public String verifyTelcomLandingPage() {
        
       	//Instantiate Selenium Functions
		String pURL = "http://demo.guru99.com/telecom/index.html";
		
		// Verify that Guru99 telecom Heading is available //
		String verifyTelcomLandingPage = driver.findElement(By.cssSelector("#header .logo")).getText();
	    Assert.assertEquals(verifyTelcomLandingPage, "Guru99 telecom");
		
	  //Verify that Add Customer Heading is available //
	 	String actual_link = driver.findElement(By.linkText ("Add Customer")).getText(); 
	 	Assert.assertEquals(actual_link, "Add Customer");  
	 	
	 		 	
		// Click on Add Customer
		driver.findElement(By.linkText("Add Customer")).click();  
		
		return verifyTelcomLandingPage();
		
 
    }
	
public String Click_AddTariffPlanToCustomerPage() {       
       	
	//Verify that Add Tariff Plan to Customer Link is available //
 	String actual_link = driver.findElement(By.linkText ("Add Tariff Plan to Customer")).getText(); 
 	Assert.assertEquals(actual_link, "Add Tariff Plan to Customer");  
 	
 	
		// Click on Add Tariff Plan to Customer
		driver.findElement(By.linkText("Add Tariff Plan to Customer")).click();  
		
		return Click_AddTariffPlanToCustomerPage();
		 
	}
}