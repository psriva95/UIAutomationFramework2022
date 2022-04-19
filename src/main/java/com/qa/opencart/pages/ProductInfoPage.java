package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	
	private By productHeader = By.cssSelector("div#content h1");
	private By img = By.cssSelector("div#content img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By successMsg = By.cssSelector("div.alert.alert-success.alert-dismissible");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getProductHeaderText() {
		return elementUtil.doElementGetText(productHeader);
	}
	
	public int getProductImagesCount() {
		return elementUtil.waitForElementsToBeVisible(Constants.DEFAULT_TIME_OUT, img).size();
	}
	
	private Map<String,String> metaDataMap;
	
	public Map<String,String> getProductInfo() {
		metaDataMap = new LinkedHashMap<String,String>();
		metaDataMap.put("productname", getProductHeaderText().trim());
		metaDataMap.put("producimagescount", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceMetaData();
		return metaDataMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaDataList =elementUtil.getElements(productMetaData);
		for(WebElement e : metaDataList) {
			String text = e.getText().trim();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			metaDataMap.put(metaKey,metaValue);
		}
	}
	private void getProductPriceMetaData() {
		List<WebElement> metaDataPriceList =elementUtil.getElements(productPriceData);
		String price = metaDataPriceList.get(0).getText().trim();
		String exPrice = metaDataPriceList.get(1).getText().trim();
		metaDataMap.put("price",price);
		metaDataMap.put("extaxprice",exPrice);
	}
	
}
