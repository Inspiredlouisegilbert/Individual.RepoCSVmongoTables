package TelcoProjects;

import static org.testng.Assert.fail;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.junit.AfterClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import frameworkclasses.BasePage;
import frameworkclasses.DriverSetup;
import frameworkclasses.SeleniumFunctions;

public class TelcoProjectTstNGOne extends BasePage{
	String pURL = "http://demo.guru99.com/V1/index.php";
	String sFirstname = "Eon";
	String sLastname = "Kruger";
	String sEmailInvalid = "email@gmail";		//INVALID EMAIL ADDRESS FOR ASSERTION
	String sEmailValid = "email@gmail.com";
	String sAddressInvalid = "1010 hollywood drive, new york";		//INVALID ADDRESS FOR ASSERTION		//ESCAPE THE SPECIAL CHARACTER
	String sAddressValid = "1010 hollywood drive new york";
	String sContactnumber = "0115678989";
	boolean eleDisplayed;
	
	//Instantiate Selenium Functions
	SeleniumFunctions sfSelenium = new SeleniumFunctions();
	
	// driver variable
	//DRIVER NO LONGER NEEDED AS WE ARE EXTENDING THE DRIVER SETUPs
	//WebDriver driver;
	
	//NAVIGATE TO URL
	public void navigateToURL(String pURL) {
		driver.get(pURL);
		//sfSelenium.maximiseBrowserWindow();
	}
		
	public void clickBankProject() {
		sfSelenium.clickLink("Telecom Project");
		sfSelenium.clickLink("Add Customer");
	}
		
	public void generateInvalidInputData() {
		
		String sActualValue = driver.findElement(By.cssSelector("body.subpage:nth-child(2) span:nth-child(5) nav.left > a.logo:nth-child(2)")).getAttribute("value");
		
		//ASSERTION THAT GURU99 TELECOM TEXT IS VISIBLE
		try {
			
			//ATTEMPT 1
			eleDisplayed = driver.findElement(By.cssSelector("body.subpage:nth-child(2) span:nth-child(5) nav.left > a.logo:nth-child(2)")).isDisplayed();
			System.out.println("Assertion One: Guru99 telecom text is visible: " + eleDisplayed);
			sfSelenium.createTest("Guru99 telecom text is visible");
			
			//ATTEMPT2
			System.out.println("Test passed: Excpected value is: Guru99 telecom " + " Actual value is: " + sActualValue);
//			String sLogotext = driver.findElement(By.cssSelector("body:nth-child(2) span:nth-child(3) nav.left > a.logo:nth-child(2)")).getText();
//			Assert.assertEquals("Guru99 telecom", sLogotext);
			
			boolean eleDisplayed = driver.findElement(By.cssSelector("body.subpage:nth-child(2) span:nth-child(5) nav.left > a.logo:nth-child(2)")).isDisplayed();
			System.out.println("Additional assertion for text (used boolean) test passed: " + eleDisplayed);
		}
		catch(NoSuchElementException e) {
			System.out.println(e);
		}
		
		//ASSERTION THAT ADD CUSTOMER TEXT IS VISIBLE
		try {
			eleDisplayed = driver.findElement(By.cssSelector("body.subpage:nth-child(2) section.wrapper:nth-child(6) div.inner header.align-center:nth-child(1) > h1:nth-child(1)")).isDisplayed();
			System.out.println("Assertion Two: Add customer text is visible: " + eleDisplayed);
			sfSelenium.createTest("Add customer text is visible");
		}
			catch(NoSuchElementException e) {
				System.out.println(e);
		}
		
		driver.findElement(By.xpath("//label[@for='done']")).click();										//SELECT RADIO BUTTON
		driver.findElement(By.xpath("//input[@name='fname']")).sendKeys(sFirstname);						//INPUT VALID FIRST NAME
		driver.findElement(By.xpath("//input[@name='lname']")).sendKeys(sLastname);							//INPUT VALID LAST NAME NAME
		driver.findElement(By.id("email")).sendKeys(sEmailInvalid);											//INPUT INVALID EMAIL ADDRESS
		driver.findElement(By.xpath("//textarea[@name='addr'][@id='message']")).sendKeys(sAddressInvalid);	//INPUT INVALID ADDRESS
		driver.findElement(By.cssSelector("#telephoneno")).sendKeys(sContactnumber);
		
		//ASSERTIONS FOR INVALID EMAIL ADDRESS
		try {
		boolean eleDisplayed = driver.findElement(By.cssSelector("#message9")).isDisplayed();
		System.out.println("Assertion Three: Invalid email address check passed: " + eleDisplayed);
		sfSelenium.createTest("Invalid email address check passed");
		}
		catch(NoSuchElementException e) {
			System.out.println(e);
		}
		
		clickSubmit();
		
		//HANDLE POPUP
		try {
			//handle the alert
				Alert alert = this.driver.switchTo().alert();
				String sAlertMessage = alert.getText();
				Assert.assertEquals("please fill all fields", sAlertMessage);
				System.out.println("Expected popup message: " + sAlertMessage);
				alert.accept();
			}
		catch(Exception e) {
				System.out.println(e);
			}
	}
	
	public void clickSubmit()
	{
		//String sAccessDetails = driver.findElement(By.cssSelector("body.subpage:nth-child(2) section.wrapper:nth-child(7) div.inner header.align-center > h1:nth-child(1)")).getText();
		//CLICK SUBMIT BUTTON
		//driver.findElement(By.xpath("//input[@value='Submit']")).click();
		driver.findElement(By.xpath("//section[@id='main']//form[@action='insertcustomer.php']/div[@class='row uniform']//ul[@class='actions']//input[@name='submit']")).click();
		//Assert.assertEquals("Access Details to Guru99 Telecom", eleDisplayed);
	}
	
	public void generatevalidInput() throws InterruptedException
	{
		//CLEAR VALUES
		driver.findElement(By.cssSelector("#email")).clear();
		driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/form[1]/div[1]/div[7]/textarea[1]")).clear();
		
		driver.findElement(By.id("email")).sendKeys(sEmailValid);											//INPUT VALID EMAIL ADDRESS
		driver.findElement(By.xpath("//textarea[@name='addr'][@id='message']")).sendKeys(sAddressValid);	//INPUT VALID ADDRESS
		driver.findElement(By.cssSelector("#telephoneno")).sendKeys(sContactnumber);
		
		clickSubmit();
		Thread.sleep(5000);
		
//		//HANDLE POPUP
//		try {
//			//handle the alert
//				Alert alert = this.driver.switchTo().alert();
//				String sAlertMessage = alert.getText();
//				Assert.assertEquals("please fill all fields", sAlertMessage);
//				System.out.println("Expected popup message: " + sAlertMessage);
//				alert.accept();
//			}
//		catch(Exception e) {
//				System.out.println(e);
//			}
	}
	
	public void getUserID() throws IOException
	{
//		String sTextVisible = driver.findElement(By.cssSelector("body.subpage:nth-child(2) section.wrapper:nth-child(7) div.inner header.align-center > h1:nth-child(1)")).getText();
//		Assert.assertEquals("Access Details to Guru99 Telecom", sTextVisible);
//		System.out.println("Access details to Guru99 Telecom text is visible");
		String sCustomerID;
		Boolean bExpectedValue = true;
		Boolean bActualValue = false;
//		
//		sCustomerID = driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/h3[1]")).getText();
		sCustomerID = driver.findElement(By.cssSelector("body.subpage:nth-child(2) section.wrapper:nth-child(7) div.inner div.table-wrapper table.alt.access tbody:nth-child(1) tr:nth-child(1) td:nth-child(2) > h3:nth-child(1)")).getText();
		if (sCustomerID.length() > 1) {
			bActualValue = true;
			System.out.println("Customer ID: " + sCustomerID);
		}
		Assert.assertEquals(bExpectedValue, bActualValue);
		
//		sCustomerID = driver.findElement(By.cssSelector("body.subpage:nth-child(2) section.wrapper:nth-child(7) div.inner div.table-wrapper table.alt.access tbody:nth-child(1) tr:nth-child(1) td:nth-child(2) > h3:nth-child(1)")).getText();
//		if (sCustomerID.length() > 1) {
//			bActualValue = true;
//			System.out.println("Customer ID: " + sCustomerID);
//		}
//		Assert.assertEquals(bExpectedValue, bActualValue);
		
		//String sHomeVisible = driver.findElement(By.cssSelector("body.subpage:nth-child(2) section.wrapper:nth-child(7) div.inner div.table-wrapper ul.actions li:nth-child(1) > a.button")).getText();
		//Assert.assertEquals("Home", sHomeVisible);
//		System.out.println("Home button visible");
		
		//STORE CUSTOMER ID
		sCustomerID = driver.findElement(By.tagName("h3")).getText();
		if (sCustomerID.length() > 1) {
			bActualValue = true;		
			//System.out.println("Customer ID: " + sCustomerID);
			
			FileWriter myWriter = new FileWriter("temp.csv");
		    myWriter.write(sCustomerID);
		    myWriter.close();
		}
		Assert.assertEquals(bExpectedValue, bActualValue);
		System.out.println("CustomerID successfully written to \"temp.csv\"");
	}
	
	// Run Test Section
	@BeforeTest
	public void beforeClass() throws Exception {
		//this.driver = sfSelenium.getDriver();
		sfSelenium.startReport("Read From PDF Report", "PDF Report");
		sfSelenium.createTest("Test Started: Read From PDF Report");
	}
	
	@Test
	public void testOne() throws Exception {
		sfSelenium.startReport("Telco  Project", "Generate invalid input data");
		sfSelenium.createTest("Start Test");
		this.driver = sfSelenium.getDriver();
		navigateToURL(pURL);
		clickBankProject();
		generateInvalidInputData();
		System.out.println("Invalid data test passed");
	}
	
	@Test
	public void testTwo() throws Exception {
		sfSelenium.startReport("Telco  Project", "Generate valid input data");
		sfSelenium.createTest("Start Test");
		this.driver = sfSelenium.getDriver();
		generatevalidInput();
		System.out.println("Valid data test passed");
		getUserID();
		System.out.println("Get User ID test passed");
	}
	
	@Test
	//ASSERT THAT CLIENT ID HAS BEEN GENERATED AND PRINT THE VALUE
	public void testThree() throws Exception {
//		sfSelenium.startReport("Telco  Project", "Get the User ID");
//		sfSelenium.createTest("Start Test");
//		this.driver = sfSelenium.getDriver();
//		getUserID();
//		System.out.println("Get User ID test passed");
	}
	
	@AfterTest
	public void afterTest() throws Exception {
		sfSelenium.CloseSelenium();
	}
}