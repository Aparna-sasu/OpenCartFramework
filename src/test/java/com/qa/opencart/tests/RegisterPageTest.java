package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void RegisterPageTestSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] getUserRegTestData(){
	return new Object[][] {
		{"vishal","mehta","9876378620","vishal@123","yes"},
		{"jyothi","sharma","9876378698","jyothi@123","no"},
		{"archana","verma","9876378634","archana@123","yes"}
	};
}
	@DataProvider
	public Object[][] getUserRegData() {
	Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);	
	return regData;
	}
	
	@Test(dataProvider = "getUserRegData")
	public void userRegisterTest(String firstName,String lastName,String telephone,String password,String subscribe) throws InterruptedException {
		Assert.assertTrue(registerPage.userRegistration(firstName, lastName, telephone, password, subscribe));
		
		
		
	}
}
