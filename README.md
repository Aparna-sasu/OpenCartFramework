# Selenium Automation Framework - OpenCart

This repository showcases a well-structured **Selenium Automation Framework** for testing the OpenCart application. It leverages **Java**, **TestNG**, **Allure Reporting**, and **Chaintest** to provide a scalable and maintainable test automation solution.

------------------

## Tech Stack

- **Language**: Java  
- **Test Framework**: TestNG  
- **Build Tool**: Maven  
- **Reporting**: Allure, Chaintest  
- **Design Pattern**: Page Object Model (POM)  
- **Browser Driver**: WebDriver Manager  
- **Others**: Listeners, ConfigReader, Utilities

---

## 📁 Project Structure

<pre> src/ ├── main/java/com/qa/opencart/ │ ├── constants/ # Framework-wide constants │ ├── errors/ # Custom error messages or enums │ ├── exceptions/ # Custom exception handling │ ├── factory/ # Driver factory and browser init logic │ ├── listeners/ # TestNG Listeners for logging/screenshots │ ├── pages/ # Page classes following POM │ └── utils/ # Utilities like ExcelReader, JSExecutor, etc. │ ├── test/java/com/qa/opencart/ │ ├── base/ # Base test setup and teardown │ └── tests/ # Test classes for all features │ ├── resources/ │ ├── config/ # config.properties for env & URLs │ ├── testdata/ # Test data in JSON/Excel/CSV │ └── testrunners/ # Chaintest runner files │ ├── chain.properties # Chaintest configuration ├── .gitignore # Files to exclude from Git └── pom.xml # Maven dependencies </pre>

## How to Run Tests

### Prerequisites:
- Java 8 or higher
- Maven
- Chrome/Firefox browser installed
- Allure CLI installed for report generation

**View Allure Report:**
allure serve target/allure-results

**Reporting**
- Allure Report: Offers visual and structured insights into each test case.
- Chaintest Report: Lightweight HTML reporting that shows the test flow with screenshots.

**Features Covered**
- Cross-browser support 
- Screenshot capture on failure
- Dynamic waits using WebDriverWait
- Page Object Model for maintainability
- TestNG Annotations and Listeners
- Custom Assertions and Error Handling
- External test data configuration

  
