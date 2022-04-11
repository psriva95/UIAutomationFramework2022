package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private JavaScriptUtil jsExecutor;
	// never create webdriver static
	// if all are static ,only one memory allocation
	// for parallel execution, other threads will go into wait
	// parallel exec will not happen
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsExecutor = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		if(Boolean.parseBoolean(DriverFactory.highlight))
			jsExecutor.flash(driver.findElement(locator));
		return driver.findElement(locator);
	}
	
	public List<String> getLinksTextList(By locator) {
		List<String> linkText = new ArrayList<String>();
		List<WebElement> element = getElements(locator);
		for(WebElement e : element) {
			String text = e.getText();
				if(!text.isEmpty())
					linkText.add(text);
		}
		return linkText;
	}
	
	public List<String> getElementAttributeList(By locator,String attrName) {
		List<WebElement> list = getElements(locator);
		List<String> eleme = new ArrayList<String>();
		for(WebElement e : list) {
			String att = e.getAttribute(attrName);
			eleme.add(att);
		}
		return eleme;
	}
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String Value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(Value);
	}

	public By getBy(String locatorType, String locatorValue) {

		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "cssselector":
			locator = By.cssSelector(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);
			break;
		case "tagname":
			locator = By.tagName(locatorValue);
			break;
		default:
			break;
		}
		return locator;
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public boolean doIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}
	
	//https://www.orangehrm.com/orangehrm-30-day-trial/

	//****************Drop Down Utils*****************************
	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doSelectByValue(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByValue(text);
	}
	
	public List<String> doGetDropDownOptions(By locator) {
		Select select = new Select(getElement(locator));
		List<String> l = new ArrayList<String>();
		List<WebElement> list = select.getOptions();
		for(WebElement e: list) {
			l.add(e.getText());
		}
		return l;
	}
	
	public void doSelectByDropDownValue(By locator, String Value) {
		Select select = new Select(getElement(locator));
		List<WebElement> countryList = select.getOptions();
		for(WebElement e : countryList) {
			String text = e.getText();
			if(text.equals(Value)) {
				e.click();
				break;
			}
		}
	}
	
	public void doSelectChoice(By locator, String... choose) {
		if(!choose[0].equalsIgnoreCase("all")) {
			List<WebElement> list = getElements(locator);
			for(WebElement e : list) {
				String text = e.getText();
				for(int i = 0; i < choose.length; i++) {
					if(text.equals(choose[i])) {
						e.click();
						break;
					}
				}
			}
		}
		else
		{	try {
			List<WebElement> list = getElements(locator);
			for(WebElement e : list) {
				e.click();
			}
		}
		catch(ElementNotInteractableException e )
		{
			System.out.println("choices are over...");
		}
		}
	}
	public  void selectSubMenu(By parentMenu, By childMenu) throws InterruptedException{
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);
		getElement(childMenu).click();
		
	}
	public  void selectSubMenu(By parentMenu, By childMenu,By childMenu2) throws InterruptedException{
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(childMenu)).perform();
		Thread.sleep(2000);
		getElement(childMenu).click();
		
	}
	public  void selectSubMenu(By parentMenu, By childMenu,By childMenu2,By childMenu3) throws InterruptedException{
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(childMenu)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(childMenu2)).perform();
		Thread.sleep(2000);
		getElement(childMenu).click();
		
	}
	
	public  void selectRightClickMenu(By rightClick, By rightClickOptions, String value) {
		doContextClick(rightClick);
		List<WebElement> list = getElements(rightClickOptions);
		for(WebElement e : list) {
			String text = e.getText();
			System.out.println(text);
			if(text.equalsIgnoreCase(value)) {
				e.click();
			break;
		}
		}
	}
	
	public  List<String> getRightClickOptions(By rightClick, By rightClickOptions) {
		List<String> rightClickItems = new ArrayList<String>();
		doContextClick(rightClick);
		List<WebElement> list = getElements(rightClickOptions);
		for(WebElement e : list) {
			String text = e.getText();
			rightClickItems.add(text);
		}
		return rightClickItems;	
	}
	
	public  int getRightClickOptionsCount(By rightClick, By rightClickOptions) {
		return getRightClickOptions(rightClick,rightClickOptions).size();
	}
	
	public  void doContextClick(By locator) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(locator)).perform();
	}
	/**
	 * Clicks in the middle of the given element. Equivalent to: Actions.moveToElement(onElement).click()
	 * @param locator
	 */
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}
	
	/**
	 * Equivalent to calling: Actions.click(element).sendKeys(keysToSend).
	 * @param locator
	 * @param value
	 */
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}
	//****************************
	
	
	public  WebElement waitForElementPresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	/*
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed 
	 * but also has a height and width that is greater than 0.
	 */
	public  WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public  WebElement waitForElementPresent(By locator, int timeout, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout),
				Duration.ofMillis(pollingTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public  WebElement waitForElementVisible(By locator, int timeout,int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout),
				Duration.ofMillis(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public  Alert waitFotAlert(int timeOut) {
		WebDriverWait driverWait = new WebDriverWait(driver,Duration.ofSeconds(10));
		return driverWait.until(ExpectedConditions.alertIsPresent()); // dont need to switch to alert..

	}
	public  void acceptAlert(int timeOut) {
		waitFotAlert(timeOut).accept();
	}
	public  void dismissAlert(int timeOut) {
		waitFotAlert(timeOut).dismiss();
	}
	public  String getAlertText(int timeOut) {
		return waitFotAlert(timeOut).getText();
	}
	public  String waitForUrl(int timeout,String urlFraction) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
	public  String waitForUrlToOBe(int timeout,String url) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		if(wait.until(ExpectedConditions.urlToBe(url))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
	public String waitForTitleContains(int timeout, String titleFraction) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		}
		return null;
	}
	public String waitForTitle(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		if(wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
		}
		return null;
	}
	public  WebDriver waitForFrame(int timeout, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));

	}
	public  WebDriver waitForFrameByIndex(int timeout, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));

	}
	public  WebDriver waitForFrameByString(int timeout, String frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));

	}
	public  WebDriver waitForFrameByElement(int timeout, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));

	}
	public List<WebElement> waitForElementsToBeVisible (int timeout, By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public WebElement retryingElement(By locator, int timeOut , int intervalTime) {
		WebElement element = null;
		int attempts = 0;
		while(attempts<timeOut) {
			try {
				element = getElement(locator);
				break;
			}catch(NoSuchElementException e){
				System.out.println("Element is not found in attempt " + attempts );
				try {
					Thread.sleep(intervalTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}
		if(element==null) {
			try {
				throw new Exception("ElementNotFoundException");
			} catch (Exception e) {
				System.out.println("Element is not found exception..tried for : " + timeOut
						+ " with the interval of " +intervalTime+" ms" );
			}
		}
		return element;
	}
	public WebElement retryingElement(By locator, int timeOut) {
		WebElement element = null;
		int attempts = 0;
		while(attempts<timeOut) {
			try {
				element = getElement(locator);
				break;
			}catch(NoSuchElementException e){
				System.out.println("Element is not found in attempt " + attempts );
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}
		if(element==null) {
			try {
				throw new Exception("ElementNotFoundException");
			} catch (Exception e) {
				System.out.println("Element is not found exception..tried for : " + timeOut
						+ " with the interval of 500 ms" );
			}
		}
		return element;
	}
	public void waitForPageLoad(int timeout) {
		long endTime = System.currentTimeMillis()+timeout;
		while(System.currentTimeMillis()<endTime) {
			System.out.println(String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")));
			if(String.valueOf(((JavascriptExecutor)driver).
					executeScript("return document.readyState")).equals("complete")) {
				break;
			}
		}
	}
	
	public WebElement waitForElementPresentUsingFluentWait(By locator, int timeout,int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class,
						ElementNotInteractableException.class);
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public WebElement waitForElementVisibleUsingFluentWait(By locator, int timeout,int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class);
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebDriver waitForFramePresentUsingFluentWait(By locator, int timeout,int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchFrameException.class);	
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	public Alert waitForAlertPresentUsingFluentWait(By locator, int timeout,int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoAlertPresentException.class);	
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
}
