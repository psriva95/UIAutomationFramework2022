package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By search = By.name("search");
	private By searchBtn = By.cssSelector("div#search button");
	private By header = By.cssSelector("div#logo a");
	private By accSecList = By.cssSelector("div#content h2");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getAccountsPageTitle() {
		return elementUtil.waitForTitle(Constants.DEFAULT_TIME_OUT, 
					Constants.ACCOUNT_PAGE_TITLE);
	}
	
	public boolean isAccountsPageHeaderExist() {
		return elementUtil.doIsDisplayed(header);
	}
	
	public boolean isSearchEixst() {
		return elementUtil.doIsDisplayed(search);
	}
	
	public List<String> getAccountsPageSectionsList() {
		List<WebElement> secList = elementUtil.getElements(accSecList);
		List<String> accSecValList = new ArrayList<String>();
		for(WebElement e : secList) {
			String text = e.getText();
			accSecValList.add(text);
		}
	return accSecValList;	
	}
	
	public SearchResultsPage doSearch(String productName) {
		if(isSearchEixst()) {
			elementUtil.doSendKeys(search, productName);
			elementUtil.doClick(searchBtn);
			return new SearchResultsPage(driver);
		}
		return null;
	}	
}
