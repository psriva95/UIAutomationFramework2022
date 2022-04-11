package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
		private WebDriver driver;

		public JavaScriptUtil(WebDriver driver) {
			this.driver = driver;
		}

		public void flash(WebElement element) {
			String bgcolor = element.getCssValue("backgroundColor");
			for (int i = 0; i < 15; i++) {
				changeColor("rgb(0,200,0)", element);// 1
				changeColor(bgcolor, element);// 2
			}
		}

		private void changeColor(String color, WebElement element) {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}

		public String getTitleByJS() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("return document.title;").toString();
		}

		public void refreshBrowserByJS() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("history.go(0)");
		}

		public void generateAlert(String message) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("alert('" + message + "')");
		}

		public String getPageInnerText() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("return document.documentElement.innerText;").toString();
		}

		public void clickElementByJS(WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}

		public void sendKeysUsingWithId(String id, String value) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
		}

		public void scrollPageDown() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}

		public void scrollPageDown(String height) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, '" + height + "')");
		}

		public void scrollPageUp() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		}

		public void scrollIntoView(WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		}

		public void drawBorder(WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", element);
		}
//	WebDriver driver;
//
//	public JavaScriptUtil(WebDriver driver) {
//		this.driver = driver;
//	}
//	
//	public void generateAlert(String message) {
//		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//		javascriptExecutor.executeScript("alert('"+message+"')");
//		
//	}
//	/*
//	 * javascript return
//	 * get all text
//	 * content testing
//	 * 
//	 */
//	public String getInnerPageText(String message) {
//		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//		return javascriptExecutor.executeScript("return document.documentElement.innerText;").toString(); 
//		
//	}
//	/*
//	 * title is part of head
//	 * title is a tag
//	 * By.tagName("title")-- doesnt work
//	 * body tags only work for driver. find element
//	 */
//	public String getTitleByJs() {
//		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//		return javascriptExecutor.executeScript("return document.title;").toString();
//	}
//	
//	/*
//	 * refresh page
//	 */
//	public void refreshBrowserByJs() {
//		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//		javascriptExecutor.executeScript("history.go(0)");
//	}
//	
//	public void drawBorder(WebElement ele) {
//		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//		javascriptExecutor.executeScript("arguments[0].style.border='3px solid red'",ele);
//	}
//	
//	public void flash(WebElement ele) {
//		String bgcolor = ele.getCssValue("backgroundColor");
//		for(int i = 0; i<15; i++) {
//			changeColor("rgb(0,200,0)",ele);
//			changeColor(bgcolor, ele);
//		}
//	}
//	
//	private void changeColor(String color, WebElement element) {
//		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//		javascriptExecutor.executeScript("arguments[0].style.backgroundColor ='"+color+"'", element);
//		try {
//			Thread.sleep(20);
//		}catch(InterruptedException e) {
//			
//		}
//	}
//	
//	public void scrollIntoView(WebElement element) {
//		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",element);
//	}
//	
//	public void scrollPageDown(String height) {
//		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//		javascriptExecutor.executeScript("window.scrollBy(0,'"+height+"'");
//	}
//	public void scrollPageDown() {
//		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//		javascriptExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight");
//	}
//	public void scrollPageUp() {
//		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//		javascriptExecutor.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	//}
}

