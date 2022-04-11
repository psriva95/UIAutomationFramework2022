package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By searchHeader = By.cssSelector("div#content h1");
	private By products = By.cssSelector("div.caption a");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getResultsPageHeaderValue() {
		if(elementUtil.doIsDisplayed(searchHeader)) {
			return elementUtil.doElementGetText(searchHeader);
		}
		return null;
	}
	
	public List<String> getProductsResultsList() {
		List<WebElement> productsList = 
				elementUtil.waitForElementsToBeVisible(Constants.DEFAULT_TIME_OUT, 
				products);
		List<String> productsValList = new ArrayList<String>();
		for(WebElement e:productsList) {
			String text = e.getText();
			productsValList.add(text);
		}
		return productsValList;	
	}
	
	public int getProductSearchCount() {
		return elementUtil.waitForElementsToBeVisible(Constants.DEFAULT_TIME_OUT, 
				products).size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Product name :" + productName);
		List<WebElement> productsList = 
				elementUtil.waitForElementsToBeVisible(Constants.DEFAULT_TIME_OUT, 
				products);
		for(WebElement e:productsList) {
			String text = e.getText();
			if(text.equalsIgnoreCase(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	 }
	
}
