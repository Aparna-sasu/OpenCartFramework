package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.List; 

@Feature("F 55: Open Cart AccountPage Features")
public class AccountsPageTest extends BaseTest{

	// first it will go to basetest, inidriverfactory will be run, launch the browser and then come to 
	// method with before class annotation.
	//This is page chaining model.
	
	@BeforeClass
	public void accPageSetup() {
		
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("Checking account page title...")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccPageTitle(),HOME_PAGE_TITLE);
		}
	
	@Test
	public void accPageUrlTest() {
		Assert.assertTrue(accPage.getAccPageUrl().contains(HOME_PAGE_FRACTION_URL));
		}
	
	@Test
	public void accPageHeaderListTest() {
		List<String> actAccPageHeaderValList = accPage.getAccPageHeaders();
		
		Assert.assertEquals(actAccPageHeaderValList, expected_accPageHeaderValList);
		}
	
}
