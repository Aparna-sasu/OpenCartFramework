package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.BrowserException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static String highlight;
	
	public static final Logger log = LogManager.getLogger(DriverFactory.class); // you can generate warning message, info, fatal or error.
/*/
 * here, the entire prop ref is passed, get the browser name by passing the key value.
 *these are toggle features - headless, incognito, highlight features in the config properties.
*/

	public WebDriver initDriver(Properties prop) {

		log.info("properties: "+ prop);
		String browserName = prop.getProperty("browser");
		//System.out.println("browser name is :" + browserName);
		log.info("browser name is :" + browserName);
		
		optionManager = new OptionsManager(prop);
		
		highlight = prop.getProperty("highlight").trim();
		
		switch(browserName.trim().toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver(optionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
			
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			//System.out.println("Please provide valid browser name...");
			log.error("Please provide valid browser name...");
			throw new BrowserException("====INVALID BROWSER===");
		}

//		driver.get(prop.getProperty("url"));
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();

		getDriver().get(prop.getProperty("url"));// login page url
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
		
		//return driver;
	}
	
	/*/
	 * getDriver : get local copy of the driver.
	 */
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/*/
	 * this is used to init the config properties.
	 * prop.load(fileinputstream) -> the load method in properties class will load
	 * all the key values present in the config prop file into the prop object.
	 */
	
	// mvn clean install -Denv = "qa" ( will supply data from command line. clean means it says maven, clean the target folder an dinstall it again.)
	// Running the test cases using maven from command line. 
	// -D is used to supply the environment variable. -D and then write the variable name, here in this case env is the variable name. 
	// here env = "qa". so, 
	// mvn clean install -Denv = "qa" is saying that please run my test cases in qa environment.
	// go to the project in command line, when written the above command, maven says , i will go to your pom.xml file
	// then, it will check if all the dependencies are tehre.
	// download all the jar files reqrd.
	// then checks the plugins (compiler plugin : compile teh code, surefire; to run the test)
	// who runs the test testng runs the test.
	// in the surefire plugin , we give suite xml path of xml.
	// which plugin is used to run testcases -  it is surefire.
	// compiler plugin is used to compile the code.
	
	// from surefire, it will go to testng.xml > in testng, we have testclass. testng decides thred count.
	// testng to the baseclass (read browser props)> testclass > page class> page class internally calls the element util.
	// and then page class returns soemthing> then the assertions takes place in the testclass.
	
	public Properties initProp() {

		String envName = System.getProperty("env");
		//System.out.println("Running the test on this environment: "+ envName);
		log.info("Running the test on this environment: "+ envName);
		FileInputStream ip = null;
		prop = new Properties();
		try {
		if(envName == null) {
			//System.out.println("env is null hence , running the test on qa envmnt.");
			log.warn("env is null hence , running the test on qa envmnt.");
			ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
		}
		else {
			System.out.println("Running test on env: "+ envName);
			switch (envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
			case "prod":
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				break;
			default:
				log.error("invalid envname:" + envName);
				break;
			}
			
		}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}
	
	/* take screenshot
	 * 
	 * remote webdriver implementing webdriver also implementing javascriptexecutor, screenshot interface which is
	 * implemeted by remote webdriver.
	 * Remote Webdriver implements screenshot interface (getScreeshotAs method).
	 * 
	 * ThreadLocal - for every thread create copy of your generics here it is webdriver, the advantage is 
	 * the thread will go to the base test , base test will call particular test class, then go tot he chrome case,
	 * copy of chrome driver will be created. and will be given to the corresponding thread.
	 * 
	 *likewise local copy of the browser drivers are created and given to every thread.Thus, 
	 *all thread gets their own local driver copy.
	 *
	 *
	 * 
	 */
	
	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;
	}
	}
