package assessmentPages;

import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import frameworkclasses.BasePage;
import frameworkclasses.DriverSetup;
import frameworkclasses.SeleniumFunctions;

public class assessmentLandingPage extends BasePage{

		String pURL = "https://www.geewiz.co.za/";
		String sProductsearch = "Solar";
		String sProductLowStock = "DJI Osmo Pocket Charging Case, Open Box";
		String sQuantity = "10";
		boolean eleDisplayed;
		
		//Instantiate Selenium Functions
		static SeleniumFunctions sfSelenium = new SeleniumFunctions();
		
		// driver variable
		//WebDriver driver;
		
		public void landingpage() {
			driver.get(pURL);
			String sHotInfo = driver.findElement(By.xpath("/html//body[@id='index']/div[@class='hotinfo_top']/table//td/span/span")).getText();
			
			try {
			Reporter.log("expected ------------------"+ "Please note: Due to the nationwide civil unrest, there may be some unforeseen courier and/or stock delays. We apologise for the inconvenience and will do our best to try keep them to a minimum");
			Reporter.log("actual ------------------"+ sHotInfo);
			Assert.assertEquals("Please note: Due to the nationwide civil unrest, there may be some unforeseen courier and/or stock delays. We apologise for the inconvenience and will do our best to try keep them to a minimum", sHotInfo);
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}

		public void searchproduct() throws InterruptedException {
			driver.findElement(By.xpath("//div[@id='search_widget']/form[@action='//www.geewiz.co.za/search']/input[@name='s']")).sendKeys(sProductsearch);
			driver.findElement(By.xpath("//div[@id='search_widget']/form[@action='//www.geewiz.co.za/search']//i[@class='material-icons search']")).click();
			driver.findElement(By.xpath("/html//div[@id='js-product-list-top']/div[@class='row']//button[@class='btn-unstyle select-title']/i[.='']")).click();
			driver.findElement(By.linkText("PRICE, LOW TO HIGH")).click();
			Thread.sleep(5000);
			driver.findElement(By.linkText("SwitchBot Curtain")).click();
			Thread.sleep(5000);
//			driver.findElement(By.xpath("/html//input[@id='quantity_wanted']")).clear();
			driver.findElement(By.xpath("/html//input[@id='quantity_wanted']")).sendKeys(sQuantity);
			driver.findElement(By.xpath("/html//form[@id='add-to-cart-or-refresh']/div[@class='product-add-to-cart']//span[@class='input-group-btn-vertical']/button[1]/i[@class='material-icons touchspin-up']")).click();
			Thread.sleep(5000);
}
		
		public void submit() throws InterruptedException {
			driver.findElement(By.xpath("/html//form[@id='add-to-cart-or-refresh']//div[@class='add']/button[1]")).click();
			Thread.sleep(5000);
		}
		
		public void viewcart() throws InterruptedException {

			driver.findElement(By.xpath("/html//form[@id='add-to-cart-or-refresh']//div[@class='add']/button[1]")).click();
			
		//HANDLE POPUP NOT VALID FOR THIS ASSESSMENT
		//I USED ELEMENT DISPLAYED WITH BOOLEAN VALUE
		try {
				Alert alert = this.driver.switchTo().alert();
				String sAlertMessage = alert.getText();
				Assert.assertEquals("Please note the current stock status is:", sAlertMessage);
				System.out.println("Expected popup message: " + sAlertMessage);
				alert.accept();
			}
		catch(Exception e) {
				System.out.println(e);
			}
		
			//QUANTITY VALUE IN TABLE
			WebElement getQuantity = driver.findElement(By.xpath("//form[@id='add-to-cart-or-refresh']/section[@class='product-discounts']/table[@class='table-product-discounts']//td[.='5']"));
			System.out.println("Quantity: " + getQuantity.getText());
			
			//QUANTITY AMOUNT IN TABLE
			WebElement getAmount = driver.findElement(By.xpath("//form[@id='add-to-cart-or-refresh']/section[@class='product-discounts']/table[@class='table-product-discounts']//td[.='R2,255']"));
			System.out.println("Amount: " + getAmount.getText());
		
			Thread.sleep(5000);
			eleDisplayed = driver.findElement(By.xpath("//div[@id='product_confirmation_modal']/div[@class='confirmations']/p")).isDisplayed();
			System.out.println("Expected text: IN STOCK AT OVERSEAS SUPPLIER - DISPATCHED/COLLECTION IN 14-20 DAYS " + eleDisplayed);
			Thread.sleep(5000);
			
			//CLICK CONTINUE BUTTON
			driver.findElement(By.xpath("//div[@id='product_confirmation_modal']/div[@class='actions']/button[1]")).click();
			System.out.println("Product successfully added to to your shopping cart");
			Thread.sleep(5000);

		}
		
		public void getcarttotal() throws IOException {
			String scarttotal = driver.findElement(By.xpath("/html//div[@id='blockcart-modal']/div[@role='document']/div[@class='modal-content']//div[@class='cart-content']/p[2]/span[@class='value']")).getText();
			int x= scarttotal.indexOf("R");
			
			System.out.println("Cart total is: " + scarttotal);
			
			if (x > 100) {
				System.out.println("Cart total is greater than R100.00 cart amount is: " + scarttotal);
			}
			else
				System.out.println("Cart total is not greater than R100.00 cart amount is: " + scarttotal);
			
			driver.findElement(By.xpath("/html//div[@id='blockcart-modal']/div[@role='document']/div[@class='modal-content']//div[@class='cart-content-btn']/button[@type='button']")).click();
			
			//WRITE TO FILE
			FileWriter myWriter = new FileWriter("CartTotal.csv");
			myWriter.write(scarttotal);
			myWriter.close();
			System.out.println("Cart total has been exported to file: " + "CartTotal.csv");
		}
		
		public void getcarttotalLessThan() {
			
		}
}