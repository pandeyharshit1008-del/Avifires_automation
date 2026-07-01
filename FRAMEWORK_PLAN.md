# Java Selenium Automation Framework Plan

## 1. Final Folder Structure

```
UI-Automation-JavaSelenium-Framework/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── automation/
│   │               ├── base/
│   │               │   ├── BaseTest.java
│   │               │   └── DriverManager.java
│   │               ├── pages/
│   │               │   ├── LoginPage.java
│   │               │   ├── SignupPage.java
│   │               │   └── UploadDocumentPage.java
│   │               ├── locators/
│   │               │   ├── LoginLocators.java
│   │               │   ├── SignupLocators.java
│   │               │   └── UploadLocators.java
│   │               ├── utils/
│   │               │   ├── AssertUtils.java
│   │               │   ├── EncryptionUtils.java
│   │               │   ├── FileUploadUtils.java
│   │               │   ├── OTPInterceptor.java
│   │               │   ├── TestDataReader.java
│   │               │   └── ConfigConstants.java
│   │               └── Config.java
│   ├── test/
│   │   └── java/
│   │       └── com/
│   │           └── automation/
│   │               ├── login/
│   │               │   └── LoginTest.java
│   │               ├── signup/
│   │               │   └── SignupTest.java
│   │               └── upload/
│   │                   └── UploadDocumentTest.java
│   └── resources/
│       ├── testdata/
│       │   ├── users.json
│       │   └── signup_data.json
│       └── config.properties
├── testdata/
│   └── documents/
│       ├── sample.pdf
│       ├── document.docx
│       └── image.png
├── screenshots/
│   └── (test execution screenshots go here)
├── pom.xml
└── README.md
```

---

## 2. Class List & Responsibilities

### Base Layer

| Class | Responsibility |
|-------|-----------------|
| **BaseTest.java** | Abstract base test class extending TestNG. Initialize WebDriver, setup/teardown, maximize window, configure waits |
| **DriverManager.java** | Manage ChromeDriver instance. Initialize WebDriver using WebDriverManager. Handle driver creation and cleanup |

### Locators Layer

| Class | Responsibility |
|-------|-----------------|
| **LoginLocators.java** | Store all By locators for Login page (mobile field, password field, login button, OTP input, etc.) |
| **SignupLocators.java** | Store all By locators for Signup page (email, mobile, password, confirm password, signup button, etc.) |
| **UploadLocators.java** | Store all By locators for Upload page (file input, upload button, success message, etc.) |

### Page Layer

| Class | Responsibility |
|-------|-----------------|
| **LoginPage.java** | Implement login flow. Methods: enterMobile(), enterPassword(), clickLogin(), clickRequestOtp(), enterOtp(), verifyLoginSuccess() |
| **SignupPage.java** | Implement signup flow. Methods: enterEmail(), enterMobile(), enterPassword(), enterConfirmPassword(), clickSignup(), verifySignupSuccess() |
| **UploadDocumentPage.java** | Implement document upload flow. Methods: uploadFile(), verifyUploadSuccess(), getUploadedFileName() |

### Utils Layer

| Class | Responsibility |
|-------|-----------------|
| **AssertUtils.java** | Reusable assertion methods: assertVisible(), assertExists(), assertClickable(), assertEnabled(), assertTextEquals(), assertContains(), assertUrlContains() |
| **EncryptionUtils.java** | Encrypt/decrypt credentials. Methods: encrypt(), decrypt(). Use simple AES encryption for security |
| **FileUploadUtils.java** | Handle file upload operations. Methods: uploadFile(), getFilePath(), verifyFileExists() |
| **OTPInterceptor.java** | Intercept network responses to capture OTP. Methods: startListening(), stopListening(), getOtpFromResponse(), extractOtp() using CDP |
| **TestDataReader.java** | Read test data from JSON files. Methods: readTestData(), getUser(), getSignupData() |
| **ConfigConstants.java** | Store all configuration constants (OTP API endpoint, implicit wait timeout, explicit wait timeout, etc.) |

### Configuration

| Class | Responsibility |
|-------|-----------------|
| **Config.java** | Load application URL and other config properties from config.properties file |

### Test Classes

| Class | Responsibility |
|-------|-----------------|
| **LoginTest.java** | Test login functionality with valid/invalid credentials, OTP flow |
| **SignupTest.java** | Test signup functionality with valid/invalid data, email verification |
| **UploadDocumentTest.java** | Test document upload with different file types and sizes |

---

## 3. Key Design Decisions

### OTP Handling Architecture
- Use Selenium 4 Chrome DevTools Protocol (CDP)
- Listen to network requests/responses
- Monitor OTP API endpoint (configurable)
- Extract OTP from response JSON payload
- Return OTP to test flow automatically

### Security
- Store credentials encrypted in JSON files
- Decrypt only when needed during test execution
- Never log credentials or sensitive data

### Waits Strategy
- No implicit waits (avoid flakiness)
- Use explicit waits (WebDriverWait) with meaningful wait conditions
- Configure timeout as constant (default: 10 seconds)

### Data Management
- Test data stored in JSON format under resources/testdata/
- Separate JSON files for different flows (users.json, signup_data.json)
- TestDataReader utility to fetch data

### Logging
- Simple System.out.println() only
- Log key events: browser launch, navigation, OTP captured, file uploaded, assertions
- No external logging framework

---

## 4. Dependencies for pom.xml

```xml
<!-- Core Dependencies -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.15.0</version>
</dependency>

<!-- WebDriverManager -->
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.7.0</version>
</dependency>

<!-- TestNG -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.8.1</version>
    <scope>test</scope>
</dependency>

<!-- Jackson for JSON -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.16.0</version>
</dependency>

<!-- Encryption (Bouncy Castle) -->
<dependency>
    <groupId>org.bouncycastle</groupId>
    <artifactId>bcprov-jdk15on</artifactId>
    <version>1.70</version>
</dependency>
```

---

## 5. Maven Configuration

```
Java Version: 17
Maven Compiler Plugin: 3.11.0
Surefire Plugin: 3.1.2 (for running tests)
```

---

## 6. Configuration Files

### config.properties
```properties
# Application Configuration
app.url=https://your-app-domain.com
app.base.url=https://your-app-domain.com

# OTP Configuration
otp.api.endpoint=https://your-app-domain.com/api/send-otp
otp.response.field=otp

# Wait Configuration
wait.timeout.seconds=10
```

### users.json
```json
{
  "validUser": {
    "mobile": "encrypted_mobile_number",
    "password": "encrypted_password"
  },
  "invalidUser": {
    "mobile": "encrypted_invalid_mobile",
    "password": "encrypted_wrong_password"
  }
}
```

---

## 7. Tech Stack Summary

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming Language |
| Selenium | 4.15.0 | Web Automation |
| TestNG | 7.8.1 | Test Framework |
| Maven | Latest | Build Tool |
| WebDriverManager | 5.7.0 | Driver Management |
| Jackson | 2.16.0 | JSON Parsing |
| Bouncy Castle | 1.70 | AES Encryption |

---

## 8. Implementation Order

1. **Phase 1**: Setup base infrastructure (BaseTest, DriverManager, pom.xml)
2. **Phase 2**: Create utilities (ConfigConstants, EncryptionUtils, TestDataReader, AssertUtils)
3. **Phase 3**: Create locators (LoginLocators, SignupLocators, UploadLocators)
4. **Phase 4**: Create page objects (LoginPage, SignupPage, UploadDocumentPage)
5. **Phase 5**: Create test data (JSON files, test documents)
6. **Phase 6**: Create advanced utilities (OTPInterceptor, FileUploadUtils)
7. **Phase 7**: Create test classes (LoginTest, SignupTest, UploadDocumentTest)
8. **Phase 8**: Documentation and final setup

---

## Key Points

✅ **Simple & Maintainable**: No unnecessary abstraction layers  
✅ **POM Pattern**: Clear separation of locators and page actions  
✅ **Explicit Waits**: All waits are explicit using WebDriverWait  
✅ **Security**: Credentials encrypted with AES  
✅ **OTP Handling**: Network interception via CDP  
✅ **Configurable**: All constants in one place  
✅ **Reusable Utils**: Common methods in utility classes  
✅ **Easy to Extend**: Add new tests without modifying framework core  

---

