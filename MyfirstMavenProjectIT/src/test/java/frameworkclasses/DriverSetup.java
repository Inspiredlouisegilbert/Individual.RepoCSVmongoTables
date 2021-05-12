package frameworkclasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DriverSetup {
	public WebDriver driver;

	/**
	 * This function will execute before each Test tag in testng.xml
	 * @param browser
	 * @throws Exception
	 */
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception{
		String pdriverDir = this.getProperties("driverdir");
		//Check if parameter passed from TestNG is 'firefox'
		if(browser.equalsIgnoreCase("firefox")){
		//create firefox instance
			System.setProperty("webdriver.gecko.driver", pdriverDir+"geckodriver.exe");
			setDriver(new FirefoxDriver());
		}
		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			//set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver",pdriverDir+"chromedriver.exe");
			//create chrome instance
			setDriver(new ChromeDriver());
		}
		//Check if parameter passed as 'Edge'
				else if(browser.equalsIgnoreCase("Edge")){
					//set path to Edge.exe
					System.setProperty("webdriver.edge.driver",".\\MicrosoftWebDriver.exe");
					//create Edge instance
					setDriver(new EdgeDriver());
				}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//DATA CONFIG SETUP
	public String getProperties(String pPropertyKey) {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream("config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		try {
				p.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return p.getProperty(pPropertyKey);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
}
