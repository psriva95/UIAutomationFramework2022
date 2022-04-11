package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	public DriverFactory driverFactory;
	public WebDriver driver;
	public LoginPage loginPage;
	public Properties prop;
	public AccountsPage accountsPage;
	public SearchResultsPage searchResultsPage;
	public RegistrationPage registrationPage;
	public ProductInfoPage productInfoPage;
	
	public SoftAssert softAssert;

	@BeforeTest
	public void setup() {
		driverFactory = new DriverFactory();
		prop = driverFactory.init_prop();
		driver = driverFactory.init_driver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
