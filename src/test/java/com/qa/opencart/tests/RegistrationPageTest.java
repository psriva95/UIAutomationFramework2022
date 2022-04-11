package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	
	@BeforeClass
	public void regPageSetup() {
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "test" + System.currentTimeMillis() +"@gmail.com";
		return email;
	}
//	
//	@DataProvider
//	public Object[][] getRegisterData() {
//		return new Object[][] {
//			{"Pooja","Srivastava","9999888777","pooja123","yes"},
//			{"Rajat","Srivastava","9999888777","rajat123","no"},
//			{"Anjali","Gupta","9999888777","anjali123","yes"},
//		};
//	}
	@DataProvider
	public Object[][] getRegisterData(){
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);		
	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstname, String lastname,
			String telephone, String password, String subscribe) {
		Assert.assertTrue(registrationPage.doRegister(
				firstname,lastname,getRandomEmail(),telephone,password,subscribe));
	}
}
