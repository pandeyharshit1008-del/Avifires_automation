# UI Automation Java Selenium Framework

A simple, maintainable Java Selenium automation framework based on Page Object Model (POM) with support for login, signup, and document upload flows.

## 📋 Table of Contents

- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Framework Architecture](#framework-architecture)
- [Test Data & Credentials](#test-data--credentials)
- [OTP Handling](#otp-handling)
- [File Upload](#file-upload)
- [Adding New Tests](#adding-new-tests)
- [Troubleshooting](#troubleshooting)

---

## 🛠 Technology Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 17 | Programming Language |
| Selenium WebDriver | 4.15.0 | Web Automation |
| TestNG | 7.8.1 | Test Framework |
| Maven | 3.x | Build & Dependency Management |
| WebDriverManager | 5.7.0 | Automatic Driver Management |
| Jackson | 2.16.0 | JSON Parsing |
| Bouncy Castle | 1.70 | AES Encryption |
| Chrome | Latest | Browser (Tested) |

---

## ✅ Prerequisites

1. **Java 17+** - Install from [java.com](https://www.java.com)
   ```bash
   java -version  # Verify installation
   ```

2. **Maven 3.6+** - Install from [maven.apache.org](https://maven.apache.org)
   ```bash
   mvn -version  # Verify installation
   ```

3. **Chrome Browser** - Latest version installed
   - WebDriverManager will handle ChromeDriver automatically

4. **IDE** - IntelliJ IDEA or Eclipse (recommended)

---

## 📂 Project Structure

```
UI-Automation-JavaSelenium-Framework/
├── src/
│   ├── main/
│   │   ├── java/com/automation/
│   │   │   ├── base/
│   │   │   │   ├── BaseTest.java           # Base test class with setup/teardown
│   │   │   │   └── DriverManager.java      # WebDriver management
│   │   │   ├── pages/
│   │   │   │   ├── LoginPage.java          # Login page actions
│   │   │   │   ├── SignupPage.java         # Signup page actions
│   │   │   │   └── UploadDocumentPage.java # Upload page actions
│   │   │   ├── locators/
│   │   │   │   ├── LoginLocators.java      # Login page locators
│   │   │   │   ├── SignupLocators.java     # Signup page locators
│   │   │   │   └── UploadLocators.java     # Upload page locators
│   │   │   ├── utils/
│   │   │   │   ├── AssertUtils.java        # Assertion methods
│   │   │   │   ├── EncryptionUtils.java    # Credential encryption
│   │   │   │   ├── FileUploadUtils.java    # File upload utilities
│   │   │   │   ├── TestDataReader.java     # JSON test data reader
│   │   │   │   ├── OTPInterceptor.java     # OTP network interception
│   │   │   │   └── ConfigConstants.java    # Configuration constants
│   │   │   └── Config.java                 # Property loader
│   │   └── resources/
│   │       ├── config.properties           # Application configuration
│   │       └── testdata/
│   │           ├── users.json              # Login test data
│   │           └── signup_data.json        # Signup test data
│   └── test/
│       └── java/com/automation/
│           ├── login/
│           │   └── LoginTest.java          # Login test cases
│           ├── signup/
│           │   └── SignupTest.java         # Signup test cases
│           └── upload/
│               └── UploadDocumentTest.java # Upload test cases
├── testdata/
│   └── documents/
│       ├── sample.pdf
│       ├── document.docx
│       └── image.png
├── screenshots/                            # Test execution screenshots
├── pom.xml                                 # Maven configuration
├── testng.xml                              # TestNG suite configuration
└── README.md                               # This file
```

---

## 🚀 Setup Instructions

### 1. Clone or Download the Repository

```bash
cd UI-Automation-JavaSelenium-Framework
```

### 2. Install Dependencies

```bash
mvn clean install
```

This will download all required dependencies specified in `pom.xml`.

### 3. Verify Setup

```bash
mvn compile
```

If compilation is successful, the framework is ready to use.

---

## ⚙️ Configuration

### Application URL

Edit `src/main/resources/config.properties`:

```properties
app.url=https://your-app-domain.com
app.base.url=https://your-app-domain.com
otp.api.endpoint=https://your-app-domain.com/api/send-otp
wait.timeout.seconds=10
```

Also update `ConfigConstants.java` if needed:

```java
public static final String LOGIN_PAGE_URL = APP_BASE_URL + "/login";
public static final String SIGNUP_PAGE_URL = APP_BASE_URL + "/signup";
public static final String UPLOAD_PAGE_URL = APP_BASE_URL + "/upload";
public static final String DASHBOARD_URL = APP_BASE_URL + "/dashboard";
```

### Encryption Key

For security, update the encryption key in `ConfigConstants.java`:

```java
public static final String ENCRYPTION_KEY = "MySecretKey12345"; // 16 chars for AES-128
```

---

## 🧪 Running Tests

### Run All Tests

```bash
mvn test
```

### Run Specific Test Suite

```bash
mvn test -Dtest=LoginTest
```

### Run Specific Test Class

```bash
mvn test -Dtest=LoginTest#testValidLogin
```

### Run with TestNG Configuration

```bash
mvn test -Dtestng.suiteXmlFile=testng.xml
```

### Generate Test Reports

After running tests, view the TestNG report:

```
target/surefire-reports/index.html
```

---

## 🏗️ Framework Architecture

### 1. **Base Layer** (`BaseTest.java`)
- Initializes WebDriver before each test
- Sets up explicit waits
- Handles browser teardown after each test
- All test classes extend `BaseTest`

### 2. **Driver Management** (`DriverManager.java`)
- Singleton-like WebDriver management
- Automatic ChromeDriver setup via WebDriverManager
- Chrome options configuration
- Browser window maximization

### 3. **Locators Layer** (`LoginLocators.java`, etc.)
- Centralized element locators
- Only contains `By` locators
- No business logic
- Easy to maintain and update

### 4. **Page Layer** (`LoginPage.java`, etc.)
- Encapsulates page actions
- Methods for user interactions
- Integration with assertion utilities
- Clean separation of concerns

### 5. **Utilities Layer**
- **AssertUtils**: Reusable assertions
- **EncryptionUtils**: Credential encryption/decryption
- **TestDataReader**: JSON test data loading
- **FileUploadUtils**: File upload operations
- **OTPInterceptor**: Network response interception
- **ConfigConstants**: Centralized configuration

### 6. **Test Layer**
- Clean, readable test cases
- Data-driven approach
- Integration with page objects

---

## 🔐 Test Data & Credentials

### Encrypted Test Data

Test data is stored in `src/main/resources/testdata/`:

**users.json** - Login credentials
```json
{
  "validUser": {
    "mobile": "encrypted_value",
    "password": "encrypted_value"
  }
}
```

**signup_data.json** - Signup information
```json
{
  "validSignup": {
    "email": "encrypted_value",
    "mobile": "encrypted_value",
    "password": "encrypted_value"
  }
}
```

### Encrypting Credentials

To encrypt credentials before storing in JSON:

```java
import com.automation.utils.EncryptionUtils;

String encrypted = EncryptionUtils.encrypt("9876543210");
System.out.println(encrypted); // Use this in JSON file
```

### Loading Test Data

In your test:

```java
Map<String, String> userData = TestDataReader.getUser("validUser");
String mobile = userData.get("mobile");      // Already decrypted
String password = userData.get("password");  // Already decrypted
```

---

## 📱 OTP Handling

The framework uses **Selenium 4 Chrome DevTools Protocol** to intercept OTP from network responses.

### How It Works

1. `OTPInterceptor` starts listening to network responses
2. Application sends OTP request to backend
3. Framework intercepts the API response
4. OTP is extracted from JSON response
5. OTP is automatically entered in the form

### Usage Example

```java
LoginPage loginPage = new LoginPage(driver);
OTPInterceptor otpInterceptor = new OTPInterceptor(driver);

// Start listening
otpInterceptor.startListening();

// Navigate and request OTP
loginPage.navigateToLoginPage();
loginPage.enterMobile("9876543210");
loginPage.clickRequestOtpButton();

// Get captured OTP
String otp = otpInterceptor.getCapturedOtp();
loginPage.enterOtp(otp);
loginPage.clickVerifyOtpButton();

// Stop listening
otpInterceptor.stopListening();
```

### Configuration

OTP endpoint is configured in `ConfigConstants.java`:

```java
public static final String OTP_API_ENDPOINT = "https://your-app-domain.com/api/send-otp";
public static final String OTP_RESPONSE_FIELD = "otp";  // JSON field containing OTP
```

---

## 📤 File Upload

The framework supports uploading files from `testdata/documents/` directory.

### Supported Operations

```java
UploadDocumentPage uploadPage = new UploadDocumentPage(driver);

// Upload a file
uploadPage.uploadFile("sample.pdf");

// Verify upload success
boolean success = uploadPage.verifyUploadSuccess();

// Get file path
String filePath = FileUploadUtils.getFilePath("sample.pdf");

// Verify file exists
boolean exists = FileUploadUtils.verifyFileExists("sample.pdf");

// Check file extension
boolean allowed = FileUploadUtils.isFileExtensionAllowed("sample.pdf", "pdf", "docx");

// Get file size
long size = FileUploadUtils.getFileSize("sample.pdf");
```

### Adding Test Documents

1. Place files in `testdata/documents/` directory
2. Reference by filename in tests
3. Framework handles path resolution automatically

---

## 🆕 Adding New Tests

### Step 1: Create Locators (if new page)

```java
// src/main/java/com/automation/locators/YourPageLocators.java
package com.automation.locators;

import org.openqa.selenium.By;

public class YourPageLocators {
    public static final By YOUR_ELEMENT = By.id("yourId");
}
```

### Step 2: Create Page Object

```java
// src/main/java/com/automation/pages/YourPage.java
package com.automation.pages;

import com.automation.locators.YourPageLocators;
import org.openqa.selenium.WebDriver;

public class YourPage {
    private WebDriver driver;
    
    public YourPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void yourAction() {
        driver.findElement(YourPageLocators.YOUR_ELEMENT).click();
    }
}
```

### Step 3: Create Test Class

```java
// src/test/java/com/automation/yourfeature/YourTest.java
package com.automation.yourfeature;

import com.automation.base.BaseTest;
import com.automation.pages.YourPage;
import org.testng.annotations.Test;

public class YourTest extends BaseTest {
    
    @Test
    public void testYourFeature() {
        YourPage page = new YourPage(driver);
        page.yourAction();
        // Add assertions
    }
}
```

### Step 4: Add to TestNG Suite (testng.xml)

```xml
<test name="Your Tests">
    <classes>
        <class name="com.automation.yourfeature.YourTest"/>
    </classes>
</test>
```

---

## 🔍 Assertion Methods

The framework provides ready-to-use assertion methods in `AssertUtils`:

```java
// Check if element is visible
AssertUtils.assertVisible(driver, locator);

// Check if element exists in DOM
AssertUtils.assertExists(driver, locator);

// Check if element is clickable
AssertUtils.assertClickable(driver, locator);

// Check if element is enabled
AssertUtils.assertEnabled(driver, locator);

// Check if text equals expected
AssertUtils.assertTextEquals(driver, locator, "expectedText");

// Check if text contains expected
AssertUtils.assertContains(driver, locator, "expectedText");

// Check if URL contains expected
AssertUtils.assertUrlContains(driver, "expectedUrl");

// Check if URL equals expected
AssertUtils.assertUrlEquals(driver, "expectedUrl");
```

---

## 🐛 Troubleshooting

### Issue: ChromeDriver Not Found

**Solution:** WebDriverManager handles this automatically. If it fails:

```bash
mvn clean install -U  # Force update dependencies
```

### Issue: Element Not Found

**Solutions:**
1. Increase wait timeout in `ConfigConstants.java`
2. Verify locator is correct
3. Check if element is in a frame or shadow DOM

### Issue: Test Data Not Loading

**Solutions:**
1. Verify JSON file path is correct
2. Check JSON syntax is valid
3. Verify encryption key matches

### Issue: File Upload Fails

**Solutions:**
1. Verify file exists in `testdata/documents/`
2. Check file permissions
3. Verify file input element locator

### Issue: OTP Not Captured

**Solutions:**
1. Verify OTP API endpoint is correct
2. Check network listener is started before OTP request
3. Verify OTP JSON field name in `ConfigConstants.java`
4. Check browser DevTools Protocol is enabled

### Issue: Tests Running Slow

**Solutions:**
1. Reduce wait timeout (if reliable)
2. Run tests in parallel (update `testng.xml`)
3. Use headless mode (update `DriverManager.java`)

---

## 📚 Best Practices

1. **Use Page Objects**: Encapsulate page interactions in page classes
2. **Centralize Locators**: Keep all locators in dedicated classes
3. **Use Explicit Waits**: Avoid implicit waits, use explicit waits
4. **Reuse Utilities**: Use common utility methods instead of duplicating code
5. **Meaningful Names**: Use descriptive method and variable names
6. **Data Driven**: Use JSON for test data instead of hardcoding
7. **Log Strategically**: Log important events for debugging
8. **Handle Encryption**: Never commit plain text credentials
9. **Clean Code**: Follow single responsibility principle
10. **Documentation**: Keep README and code comments updated

---

## 📝 Example Test

```java
@Test
public void testCompleteLoginFlow() {
    // Arrange
    LoginPage loginPage = new LoginPage(driver);
    Map<String, String> userData = TestDataReader.getUser("validUser");
    
    // Act
    loginPage.login(userData.get("mobile"), userData.get("password"));
    
    // Assert
    AssertUtils.assertUrlContains(driver, "dashboard");
}
```

---

## 🔗 Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)

---

## 📄 License

This framework is open source and available under the MIT License.

---

## ✉️ Support

For issues, questions, or suggestions, please refer to the troubleshooting section or the framework documentation.

---

**Happy Testing! 🚀**

