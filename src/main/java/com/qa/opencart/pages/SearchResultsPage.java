package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private final By resultsProduct = By.cssSelector("div.product-thumb");
	
	
	public SearchResultsPage(WebDriver driver) {
		
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}


	public int getResultsProductCount() {
		int searchCount = elementUtil.waitForAllElementsVisible(resultsProduct, AppConstants.DEFAULT_TIMEOUT).size();
		
		System.out.println("Total number of search products are:"+ searchCount);
	
	return searchCount;
	}

	public ProductInfoPage selectProduct(String productName) {
		System.out.println("product name :"+ productName);
		elementUtil.doClick(By.linkText(productName));
		
		return new ProductInfoPage(driver);
	}
}
