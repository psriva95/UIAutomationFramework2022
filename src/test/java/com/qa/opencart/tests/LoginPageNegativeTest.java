package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest{
	
	
	@DataProvider
	public Object[][] getLoginNegativeData(){
			return new Object[][] {
				{"test123@gmail.com","!23"},
				{"test123@gmail.com","!3424"},
			};
		}
	
	@Test(dataProvider = "getLoginNegativeData")
	public void loginInvalidTest(String un, String password) {
			Assert.assertTrue(loginPage.doInvalidLogin(un, password));
	}


}
