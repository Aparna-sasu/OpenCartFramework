package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

//@Listeners(ChainTestListener.class)
public class BaseTest {

	WebDriver driver;
	DriverFactory driverFactory;
	protected LoginPage loginPage;
	protected Properties prop;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	/*/
	 * instead of hard coding the value, provide the prop reference to the initDriver.
	 */
	@Parameters({"browser"})
	
	@BeforeTest
	public void setup(String browserName) {
		driverFactory = new DriverFactory();
		prop = driverFactory.initProp();
		
		//browserName is passed from .xml file.
		if(browserName!=null) {
			prop.setProperty("browser", browserName);	
		}
		
		driver = driverFactory.initDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@AfterMethod // will run after each @test method
	public void attachScreenshot(ITestResult result) {
		
		if(!result.isSuccess()) {// only for failure test cases -- true
			
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
	}
	@AfterTest
	public void tearDown() {
		
		driver.quit();
	}
}
