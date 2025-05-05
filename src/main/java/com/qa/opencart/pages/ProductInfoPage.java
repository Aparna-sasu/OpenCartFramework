package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	private Map<String,String> productMap;

	private final By productHeader = By.tagName("h1");
	private final By productImages = By.cssSelector("ul.thumbnails img");
	private final By productMetaData = By.xpath("//div[@class = 'col-sm-4']/ul[@class='list-unstyled'][1]/li");
	private final By productPriceData = By.xpath("//div[@class = 'col-sm-4']/ul[@class='list-unstyled'][2]/li");




	public ProductInfoPage(WebDriver driver) {

		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}


	public String getProductHeader() {
		String header = elementUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_TIMEOUT).getText();
		System.out.println("product header: "+ header);
		return header;
	}

	public int getProductImagesCount() {
		int imageCount = elementUtil.waitForAllElementsVisible(productImages, AppConstants.MEDIUM_DEFAULT_TIMEOUT).size();
		System.out.println("total number of product images: "+ imageCount);
		return imageCount;
	}

	public Map<String, String> getProductDetailsMap() {

		productMap = new HashMap<String,String>();
		productMap.put("ProductHeader", getProductHeader());
		productMap.put("ProductCount", String.valueOf(getProductImagesCount()));
		getProductInfoMetaData();
		getProductPriceData();
		System.out.println("full product details"+ productMap);
		return productMap;

	}

	//Brand: Apple
	//Product Code: Product 18
	//Reward Points: 800
	//Availability: Out Of Stock

	private void getProductInfoMetaData() {
		
		List<WebElement> productMetaDataList = elementUtil.waitForAllElementsVisible(productMetaData, AppConstants.DEFAULT_TIMEOUT);

		for(WebElement e: productMetaDataList) {

			String metaData = e.getText();
			String[] meta = metaData.split(":");
			String metaKey = meta[0];
			String metaValue = meta[1];

			productMap.put(metaKey, metaValue);

		}
	}

	private void getProductPriceData() {

		List<WebElement> productPriceDataList = elementUtil.waitForAllElementsVisible(productPriceData, AppConstants.DEFAULT_TIMEOUT);

		String productPrice = productPriceDataList.get(0).getText();
		String exTaxProductPrice = productPriceDataList.get(1).getText().split(":")[1].trim();
		productMap.put("ProductPrice", productPrice);
		productMap.put("ExTaxPrice", exTaxProductPrice);
	}
}
