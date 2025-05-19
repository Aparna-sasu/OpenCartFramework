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

src/
├── main/java/com/qa/opencart/
│   ├── constants/          # Framework-wide constants
│   ├── errors/             # Custom error messages or enums
│   ├── exceptions/         # Custom exception handling
│   ├── factory/            # Driver factory and browser initialization logic
│   ├── listeners/          # TestNG listeners for logging, screenshots, etc.
│   ├── pages/              # Page Object Model (POM) classes
│   └── utils/              # Utility classes (ExcelReader, JSExecutor, etc.)
│
├── test/java/com/qa/opencart/
│   ├── base/               # Test base class for setup and teardown
│   └── tests/              # Feature test classes
│
├── resources/
│   ├── config/             # Configuration files (e.g., config.properties)
│   ├── testdata/           # Test data files (JSON, Excel, CSV)
│   └── testrunners/        # Chaintest runner files

├── chaintest.properties    # Chaintest configuration
├── .gitignore              # Git ignore rules
├── pom.xml                 # Maven project descriptor

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

  
