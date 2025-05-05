package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_TITLE;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

import static com.qa.opencart.constants.AppConstants.*; 

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private final By headers = By.cssSelector("div#content>h2");
	private final By search = By.name("search");
	private final By searchButton = By.cssSelector("div#search button");

	
	public AccountsPage(WebDriver driver) {
		
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}


	public String getAccPageTitle() {
		String title = elementUtil.waitForTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("Home page title is : "+title);
		return title;
		
	}
	
	public String getAccPageUrl() {
		String url = elementUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("Home page url is : "+url);
		return url;
		
	}

	public List<String> getAccPageHeaders() {
		List<WebElement> headerList = elementUtil.getElements(headers);
		List<String> headerValList = new ArrayList<>();
		
		for(WebElement e : headerList) {
			
			String text = e.getText();
			headerValList.add(text);
		}
		System.out.println("Header value list are: "+headerValList);
		return headerValList;
		
	}

	public SearchResultsPage doSearch(String searchKey) {
		
		elementUtil.doSendKeys(search, searchKey);
		elementUtil.doClick(searchButton);
		
		return new SearchResultsPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
