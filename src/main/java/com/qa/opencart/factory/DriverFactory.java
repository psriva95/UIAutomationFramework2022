package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.utils.Browser;
import com.qa.opencart.utils.Errors;

import io.github.bonigarcia.wdm.WebDriverManager;
public class DriverFactory {
//git remote set-url origin https://<TOEKN>@github.com/USERNAME/REPOSITORY.git
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
//	public static final Logger log = Logger.getLogger(DriverFactory.class);

	/**
	 * This method is used to init webdriver on basis of given browser name
	 * This will take care of local and remote execution
	 * @param browserName
	 * @return
	 */
	public WebDriver init_driver (Properties prop) {
		
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		String browserName=  prop.getProperty("browser").trim();
		if(browserName.equalsIgnoreCase(Browser.CHROME_BROWSER)) {
			WebDriverManager.chromedriver().setup();
			//System.setProperty(Browser.CHROME_DRIVER_BINARY_KEY,Browser.CHROME_DRIVER_PATH);
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase(Browser.FIREFOX_BROWSER)) {
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty(Browser.CHROME_DRIVER_BINARY_KEY,Browser.CHROME_DRIVER_PATH);
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase(Browser.SAFARI_BROWSER)) {
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else {
			System.out.println(Errors.INVALID_BROWSER + browserName);
		}
		
		/*
		 * driver.manage().deleteAllCookies(); 
		 * driver.manage().window().maximize();
		 * driver.get(prop.getProperty("url").trim());
		 */
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	/**
	 * This method returns prop file
	 * @return
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;
		
		//mvn clean install -Denv="qa" -Dbrowser="chrome"
		//mvn clean install --- environment missing
		String envName = System.getProperty("env");
		System.out.println("Running tests on : " + envName);
		
		if(envName==null) {
			System.out.println("No environment is provided - Tests will be executed on QA Environment");
			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			switch (envName.toLowerCase()) {
			case "qa" : 
				try {
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case "stage" : 
				try {
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case "dev" : 
				try {
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case "uat" : 
				try {
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case "prod" : 
				try {
					ip = new FileInputStream("./src/test/resources/config/config.properties");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
				
			default :
				System.out.println("Please provide correct environment");
				break;
			}
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
	}
	
	
	public static String getScreenshot() {
		
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destFile = new File(path);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
