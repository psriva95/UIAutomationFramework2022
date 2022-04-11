package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"),
				prop.getProperty("password"));
	}
	
	@Test
	public void accountsPageTitleTest() {
		String actualAccountsPageTitle = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(actualAccountsPageTitle, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accPageHeaderTest() {
		Assert.assertTrue(accountsPage.isAccountsPageHeaderExist());
	}
	
	@Test
	public void searchExistTest() {
		Assert.assertTrue(accountsPage.isSearchEixst());
	}
	
	@Test
	public void accSectionsTest() {
		List<String> actualAccSecList = accountsPage.getAccountsPageSectionsList();
		System.out.println("Account Section List : " + actualAccSecList);
		Assert.assertEquals(actualAccSecList, Constants.ACCOUNTS_SEC_LIST);
	}
	
	@Test
	public void searchTest() {
		searchResultsPage = accountsPage.doSearch("macbook");
		String actSearchHeader = searchResultsPage.getResultsPageHeaderValue();
		Assert.assertTrue(actSearchHeader.contains("macbook"));
	}
	
	@Test
	public void searchProductTest() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		int actProductCount = searchResultsPage.getProductSearchCount();
		Assert.assertEquals(actProductCount, Constants.MACBOOK_PRODUCT_COUNT);
	}
	
	@Test
	public void getSerachProductListTest() {
		searchResultsPage  = accountsPage.doSearch("Macbook");
		List<String> actProdList = searchResultsPage.getProductsResultsList();
		System.out.println(actProdList);
	}
	
}
