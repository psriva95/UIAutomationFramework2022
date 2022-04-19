package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 200 - Design Accounts page for open cart application")
@Story("US 101 - desgin login page features")
@Story("US 102 - desgin accounts page features")
public class AccountsPageTest extends BaseTest {
	
	@Description("pre login for accounts page tests")
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"),
				prop.getProperty("password"));
	}
	
	@Test
	@Description("accounts Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	public void accountsPageTitleTest() {
		String actualAccountsPageTitle = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(actualAccountsPageTitle, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	@Description("accounts Page header Test")
	@Severity(SeverityLevel.NORMAL)
	public void accPageHeaderTest() {
		Assert.assertTrue(accountsPage.isAccountsPageHeaderExist());
	}
	
	@Test
	@Description("search Exist Test")
	@Severity(SeverityLevel.CRITICAL)
	public void searchExistTest() {
		Assert.assertTrue(accountsPage.isSearchEixst());
	}
	
	@Test
	@Description("acc Sections Test")
	@Severity(SeverityLevel.NORMAL)
	public void accSectionsTest() {
		List<String> actualAccSecList = accountsPage.getAccountsPageSectionsList();
		System.out.println("Account Section List : " + actualAccSecList);
		Assert.assertEquals(actualAccSecList, Constants.ACCOUNTS_SEC_LIST);
	}
	
	@Test
	@Description("Search Header Test")
	@Severity(SeverityLevel.NORMAL)
	public void searchTest() {
		searchResultsPage = accountsPage.doSearch("macbook");
		String actSearchHeader = searchResultsPage.getResultsPageHeaderValue();
		Assert.assertTrue(actSearchHeader.contains("macbook"));
	}
	
	@Test
	@Description("check product count test after search")
	@Severity(SeverityLevel.CRITICAL)
	public void searchProductTest() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		int actProductCount = searchResultsPage.getProductSearchCount();
		Assert.assertEquals(actProductCount, Constants.MACBOOK_PRODUCT_COUNT);
	}
	
	@Test
	@Description("check product list test after search")
	@Severity(SeverityLevel.CRITICAL)
	public void getSerachProductListTest() {
		searchResultsPage  = accountsPage.doSearch("Macbook");
		List<String> actProdList = searchResultsPage.getProductResultsList();
		System.out.println(actProdList);
	}
	
}
