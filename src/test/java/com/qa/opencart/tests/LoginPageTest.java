package com.qa.opencart.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Feature("F 50: Open Cart - Login Feature")
@Epic("Epic 100: design pages for open cart app.")
@Story("US 101: Implement login page for open cart app.") // whatever user story is in jira, you can provide that user story number here in story.

public class LoginPageTest extends BaseTest {

	@Description("Checking open cart Login page title...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Aparna")
	
	@Test
	public void loginPageTitleTest() {

		String actualTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Inside loginPageTitleTest()...this is the actTitle:" + actualTitle);
		AssertJUnit.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);

	}

	@Description("Checking open cart Login page url...")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Aparna")
	@Test(description="checking login page url...")
	public void loginPageUrlTest() {

		String actualUrl = loginPage.getLoginPageUrl();
		AssertJUnit.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Description("Checking open cart Login page title...")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Aparna")
	@Test(description ="checking Forgot pasword link ")
	public void forgotPasswordLinkExistTest() {

		boolean actForgotPasswordExist = loginPage.isForgotPasswordLinkDisplayed();
		AssertJUnit.assertTrue(actForgotPasswordExist);
	}
	
	@Description("Checking user is able to login with valid credentials...")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Aparna")
	@Test(priority = Short.MAX_VALUE, description = "Login with valid credentials...")
	public void doLoginTest() {

		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		ChainTestListener.log("Inside doLogintest()...");
		AssertJUnit.assertEquals(accPage.getAccPageTitle(), AppConstants.HOME_PAGE_TITLE);
	}
	
	@Test(enabled=false)
	public void forgotPswd() {
		System.out.println("forgot pswd");
	}
}
