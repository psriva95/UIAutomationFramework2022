package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LoginPageNegativeTest extends BaseTest{
	
	@Epic("Epic 100 - Design login page for open cart application")
	@Story("US 101 - desgin login page features")
	@DataProvider
	public Object[][] getLoginNegativeData(){
			return new Object[][] {
				{"test123@gmail.com","!23"},
				{"test123@gmail.com","!3424"},
			};
		}
	@Description("login Title Test with invalid credentials.....")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider = "getLoginNegativeData")
	public void loginInvalidTest(String un, String password) {
			Assert.assertTrue(loginPage.doInvalidLogin(un, password));
	}


}
