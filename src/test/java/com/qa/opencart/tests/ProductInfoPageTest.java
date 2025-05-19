package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void ProductInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	//AAA -  Arrange Act Assert( only one assert per test)
	
	@DataProvider
	public Object[][] getProductTestData() {
	
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"macbook", "MacBook Air"},
			{"imac", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1"}
			
		};
	}
	
	@DataProvider
	public Object[][] getProductImagesData() {
	
		return new Object[][] {
			{"macbook", "MacBook Pro",4},
			{"macbook", "MacBook Air",4},
			{"imac", "iMac",3},
			{"samsung", "Samsung SyncMaster 941BW",1},
			{"samsung", "Samsung Galaxy Tab 10.1",7}
			
		};
	}
	
	@DataProvider
	public Object[][] getProductImagesexcelData() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);	
	 
	}
	@DataProvider
	public Object[][] getProductCSVData() {
		return CSVUtil.csvData("product");	
	 
	}
	
	@Test(dataProvider = "getProductTestData")
	public void productHeaderTest(String searchKey, String productName) {
		
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, productName);
	}
	
	@Test(dataProvider = "getProductCSVData")
	public void productImageCountTest(String searchKey, String productName, String expectedImageCount) {
		
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actProductImageCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(String.valueOf(actProductImageCount), expectedImageCount);
	}
	
	//Brand: Apple
	//Product Code: Product 18
	//Reward Points: 800
	//Availability: Out Of Stock
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		
		Map<String, String> actualProductDetails = productInfoPage.getProductDetailsMap();
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(actualProductDetails.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductDetails.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductDetails.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actualProductDetails.get("ProductPrice"), "$2,000.00");
		
		
	}
}
