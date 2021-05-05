package BankProjectGenerateData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import frameworkclasses.SeleniumFunctions;

public class BankProjectGenerateDataKeywords {

	//INSTANTIATE NEW INSTANCE OF SELENIUM FUNCTIONS
	SeleniumFunctions sfSelenium = new SeleniumFunctions();
	
	//DRIVER VARIABLE
	WebDriver driver;
	
	// Set URL
	String pURL = "http://demo.guru99.com";
	

	
	//Set invalid data
	String sInvalidId = "test@test";
	String sInvalidPassword = "Passwo";
	
	//Create a new file
	String sFormatedData = createNewCSVFile();
	File log = new File("C:\\tmp\\IDs"+sFormatedData+".csv");
	
	//Return the date string
	public String createNewCSVFile() {
		//Set the date string
		Date date = Calendar.getInstance().getTime();  
	    DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");  
	    String strDate = dateFormat.format(date);  
	    //System.out.println("Converted String: " + strDate); 
	    return strDate;
		
	}
	
	//NAVIGATE TO URL
	public void navigateToURL(String pURL) {
		driver.get(pURL);
		//sfSelenium.maximiseBrowserWindow();
	}
	
	//NAVIGATE TO BANK PROJECT
	//CLICK ON THE BANKING LINK
		public void clickBankProject() {
			sfSelenium.clickLink("Bank Project");
			//sfSelenium.clickLink("here");
		}
		
		public void validateEmailAddress(String sValidateEmailAddress) {
			//CREATE TEST TO VALIDATE EMAIL ADRESS
			//BY XPATH TEMPLATE (By.xpath("//input[@='']"))
			sfSelenium.clickLink("here");
			driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(sValidateEmailAddress);
			driver.findElement(By.name("btnLogin")).click();
		}
	
		//GENERATE USERNAME AND PASSWORD
		public void generateValidLogins(int randomisednumber) throws IOException, InterruptedException {
			//sfSelenium.clickLink("Bank Project");
			//sfSelenium.clickLink("here");
			String cssUserID = "body:nth-child(2) table:nth-child(10) tbody:nth-child(1) tr:nth-child(4) > td:nth-child(2)";
			String sUserID = this.driver.findElement(By.cssSelector(cssUserID)).getText();
			String cssPassword = "body:nth-child(2) table:nth-child(10) tbody:nth-child(1) tr:nth-child(5) > td:nth-child(2)";
			String sPassword = this.driver.findElement(By.cssSelector(cssPassword)).getText();
			String pOutcome = "";
			
			//NAVIGATE BACK TO THE BANK PROJECT
			sfSelenium.clickLink("Bank Project");
			//sfSelenium.clickLink("here");
			populateInpute(sUserID,sPassword);
			writeToFile(sUserID,sPassword,randomisednumber);
			
		}
			
			
			//POPULATE INPUT FIELDS
		public void populateInpute(String pUsersID ,String pPassword) {
			sfSelenium.populateInputField(By.name("uid"), pUsersID);
			sfSelenium.populateInputField(By.name("password"), pPassword);
		
			driver.findElement(By.name("btnLogin")).click();
			
			
//			if	(pOutcome.equals(driver.findElement() {
//					successfulLogin();
//					sfSelenium.createTest("Successful login: Login was successful");
//			}
//				else {
//					unsuccessfulPopup();
//					sfSelenium.createTest("Run Alert Failure: Unsuccessful login popup text test");
//			}
		}
		
		//CREATE METHOD TO CLICK ON CLEAR AND ASSERT THAT INPUT FIELDS HAS BEEN REMOVED
		public void successfulLogin() {
			//ASSERT FOR IMAGE AFTER SUCCESSFUL LOGIN
			boolean eleDisplayed = driver.findElement(By.cssSelector("table.layout:nth-child(5) tbody:nth-child(1) tr:nth-child(1) td:nth-child(1) center:nth-child(1) > img:nth-child(1)")).isDisplayed();
			System.out.println("Successful Login Assertion passed: " + eleDisplayed);
			sfSelenium.createTest("Successful Login");
		}
		
		public void unsuccessfulPopup() throws InterruptedException {
			//HANDLE UNSUCCESSFUL POPUP ALERT
			sfSelenium.createTest("Run Alert Failure: Unsuccessful login popup text test");
			String pExpectedMessage = "User is not valid";
			
			//CREATE AN OBJECT OF THE POPUP
			
				Alert alert = this.driver.switchTo().alert();
				String sAlertMessage = alert.getText();
				System.out.println(sAlertMessage);

				alert.accept();
				sfSelenium.updateReport(sAlertMessage,pExpectedMessage);
				
			Thread.sleep(500);
		}
		
		public void writeToFile (String p1, String p2, int randomisednumber) throws IOException, InterruptedException {
			
			this.driver = sfSelenium.getDriver();
//			
			//String randomnum = String.valueOf(sfSelenium.generateRandomData());
//			FileWriter csvWriter = new FileWriter("C:\\tmp\\new.csv");
//			csvWriter.append("UserName,");
//			//csvWriter.append(",");
//			csvWriter.append("ID");
//			csvWriter.append("\n");
//			csvWriter.append(sValidateEmailAddress+",");
//			csvWriter.append(randomnum);
//			csvWriter.append("\n");			
//			csvWriter.flush();
//			csvWriter.close();
			
			 //File log = new File("C:\\tmp\\new.csv");
					    try{
					    if(log.exists()==false){
					            System.out.println("We had to make a new file.");
					            log.createNewFile();
					    }
					    PrintWriter out = new PrintWriter(new FileWriter(log, true));
					    
						out.append(p1 + "," + p2 + "," +randomisednumber+"\n");
					    out.close();
					    }catch(IOException e){
					        System.out.println("COULD NOT LOG!!");
					    }
	}
	
	//IF STATEMENT FOR SUCCESS
	//GENERATE REPORT
	//TAKE SCREENSHOTS
	//ASSERT ON IMAGE
	//ADDITIONAL ASSERT ON IS DISPLAYED TEXT
	
	//IF STATEMENT FOR UNSUCCESSFUL LOGIN
	//HANDLE THE ALERT POPUP
	//GENERATE REPORT
	//TAKE SCREENSHOTS
	
	//RUN TESTS
		public void testCase1 () throws IOException, InterruptedException {
			//Create a randomised email address
			int randomisednumber = sfSelenium.generateRandomData();
			String sValidateEmailAddress = randomisednumber+"@gmail.com";
			sfSelenium.startReport("Bank  Project", "Generate Input Data");
			sfSelenium.createTest("Start Test");
			this.driver = sfSelenium.getDriver();
			navigateToURL(pURL);
			clickBankProject();
			validateEmailAddress(sValidateEmailAddress);
			generateValidLogins(randomisednumber);
			successfulLogin();
		}
		
		public void testCase2 () throws IOException, InterruptedException {
			sfSelenium.createTest("Validate Login Test");
			navigateToURL(pURL);
			clickBankProject();
			populateInpute(sInvalidId,sInvalidPassword);
			writeToFile(sInvalidId,sInvalidPassword,-1);
			unsuccessfulPopup();
		}
	
		public void cleanup () throws IOException, InterruptedException {
			sfSelenium.createTest("Run Test: clean up");
			// set the value for driver
			this.driver = sfSelenium.getDriver();
			//Thread.sleep(5000);
			sfSelenium.CloseSelenium();
			this.driver.quit();
		}
}
