package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100 - Design login page for open cart application")
@Story("US 101 - desgin login page features")
public class LoginPageTest extends BaseTest{
	
	
	@Test
	@Description("Register link Test.....")
	@Severity(SeverityLevel.CRITICAL)
	public void isRegisterLinkExists() {
		Assert.assertTrue(loginPage.isRegistrationLinkExist());
	}
	
	
	@Test
	@Description("login Page Title Test.....")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Page title " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	@Description("login Page url Test.....")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		System.out.println("Login Page Url " + url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	@Description("check forgot pwd link Test.....")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test
	@Description("login Title Test with correct username and correct password.....")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
	accountsPage =	loginPage.doLogin(prop.getProperty("username"),
				prop.getProperty("password"));
		Assert.assertTrue(accountsPage.isAccountsPageHeaderExist());
	}

	
}
