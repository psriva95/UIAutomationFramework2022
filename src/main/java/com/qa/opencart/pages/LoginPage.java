package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.Errors;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwd = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By loginErrorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");	
	
	//public page class constructor 
	//addding lines of code
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getLoginPageTitle() {
		return elementUtil.waitForTitle(Constants.DEFAULT_TIME_OUT,
				Constants.LOGIN_PAGE_TITLE);
	}
	
	public String getLoginPageUrl() {
		return elementUtil.waitForUrl(Constants.DEFAULT_TIME_OUT, 
				Constants.LOGIN_PAGE_FRACTION_URL);
	}
	
	public boolean isForgotPwdLinkExist() {
		return elementUtil.doIsDisplayed(forgotPwd);
	}
		
	public AccountsPage doLogin(String un, String pwd) {
		elementUtil.waitForElementVisible(email, Constants.DEFAULT_TIME_OUT).sendKeys(un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public boolean doInvalidLogin(String un, String pwd) {
		WebElement ele = elementUtil.waitForElementVisible(email, Constants.DEFAULT_TIME_OUT);
		ele.clear();
		ele.sendKeys(un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
		String actualErrorMsg = elementUtil.doElementGetText(loginErrorMsg);
		if(actualErrorMsg.contains(Errors.LOGIN_PAGE_ERROR))
		{
			return true;
		}
		return false;
	}
	
	public boolean isRegistrationLinkExist() {
		return elementUtil.waitForElementVisible(registerLink, Constants.DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public RegistrationPage navigateToRegisterPage() {
		if(isRegistrationLinkExist()) {
			elementUtil.doClick(registerLink);
			return new RegistrationPage(driver);
		}
		return null;
	}
}
