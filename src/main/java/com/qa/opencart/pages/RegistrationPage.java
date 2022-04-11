package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	private By firstname = By.cssSelector("input#input-firstname");
	private By lastname = By.cssSelector("input#input-lastname");
	private By email = By.cssSelector("input#input-email");
	private By telephone = By.cssSelector("input#input-telephone");
	private By password = By.cssSelector("input#input-password");
	private By confirmpwd = By.cssSelector("input#input-confirm");
	private By subscribeYes = By.xpath("//input[@name='newsletter' and @value=1]");
	private By subscribeNo = By.xpath("//input[@name='newsletter' and @value=0]");
	private By privacyPolicy = By.xpath("//input[@name='agree' and @value=1]");
	private By submitBtn = By.xpath("//input[@type='submit']");
	
	private By successAccountHeader = By.cssSelector("div#content h1");
	//private By finalSubmit = By.linkText("continue");
	private By logout = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	
	public String getRegisterPageTitle() {
		return elementUtil.waitForTitle(Constants.DEFAULT_TIME_OUT, 
				Constants.REGISTER_PAGE_TITLE);
	}
	
	public String getRegisterPageUrl() {
		return elementUtil.waitForUrl(Constants.DEFAULT_TIME_OUT, 
				Constants.REGISTER_PAGE_FRACTION_URL);
	}
	
	public Boolean doRegister(String fn, String ln, String emailId, 
			String tel,String pwd,String subscribe) {
		elementUtil.waitForElementVisible(this.firstname, Constants.DEFAULT_TIME_OUT).clear();
		elementUtil.doSendKeys(firstname,fn);
		elementUtil.doSendKeys(lastname, ln);
		elementUtil.doSendKeys(email, emailId);
		elementUtil.doSendKeys(telephone, tel);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doSendKeys(confirmpwd, pwd);
	
		if(subscribe.equalsIgnoreCase("yes"))
			elementUtil.doClick(subscribeYes);
		else
			elementUtil.doClick(subscribeNo);
		
		elementUtil.doClick(privacyPolicy);
		elementUtil.doClick(submitBtn);
		
		if(getAccountRegisterSuccessMsg().contains(Constants.REGISTER_SUCCESS_MSG))
			{
				goToRegisterPage();
				return true;
			}
		return false;
	}
	
	public String getAccountRegisterSuccessMsg() {
		return elementUtil.waitForElementVisible(successAccountHeader, Constants.DEFAULT_TIME_OUT).getText();
	}
	
	private void goToRegisterPage() {
		elementUtil.doClick(logout);
		elementUtil.doClick(registerLink);
	}
}
