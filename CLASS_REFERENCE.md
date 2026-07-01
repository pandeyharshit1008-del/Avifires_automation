# Java Classes Reference Guide

## 📋 Complete Class Inventory

### 🏗️ Base Layer Classes

#### 1. **BaseTest.java**
- **Location:** `src/main/java/com/automation/base/`
- **Purpose:** Base test class with setup and teardown
- **Key Methods:**
  - `setUp()` - Initialize WebDriver and waits
  - `tearDown()` - Close browser and cleanup
  - `navigateTo(String url)` - Navigate to URL

#### 2. **DriverManager.java**
- **Location:** `src/main/java/com/automation/base/`
- **Purpose:** Manage WebDriver instance lifecycle
- **Key Methods:**
  - `initializeDriver()` - Initialize ChromeDriver
  - `getDriver()` - Get WebDriver instance
  - `quitDriver()` - Close browser
  - `closeDriver()` - Close current window

---

### 🎯 Locators Layer Classes

#### 3. **LoginLocators.java**
- **Location:** `src/main/java/com/automation/locators/`
- **Locators (12 total):**
  - `MOBILE_INPUT` - Mobile/email field
  - `PASSWORD_INPUT` - Password field
  - `LOGIN_BUTTON` - Login button
  - `REQUEST_OTP_BUTTON` - Request OTP button
  - `OTP_INPUT` - OTP input field
  - `VERIFY_OTP_BUTTON` - Verify OTP button
  - `ERROR_MESSAGE` - Error message
  - `SUCCESS_MESSAGE` - Success message
  - `REMEMBER_ME_CHECKBOX` - Remember me checkbox
  - `FORGOT_PASSWORD_LINK` - Forgot password link
  - `LOGIN_FORM` - Login form container
  - `OTP_FORM` - OTP form container

#### 4. **SignupLocators.java**
- **Location:** `src/main/java/com/automation/locators/`
- **Locators (14 total):**
  - `EMAIL_INPUT` - Email field
  - `MOBILE_INPUT` - Mobile field
  - `PASSWORD_INPUT` - Password field
  - `CONFIRM_PASSWORD_INPUT` - Confirm password field
  - `FIRST_NAME_INPUT` - First name field
  - `LAST_NAME_INPUT` - Last name field
  - `SIGNUP_BUTTON` - Signup button
  - `TERMS_CHECKBOX` - Terms checkbox
  - `EMAIL_VERIFY_LINK` - Email verification link
  - `VERIFICATION_SUCCESS_MSG` - Verification success message
  - `LOGIN_LINK` - Login link
  - `ERROR_MESSAGE` - Error message
  - `SUCCESS_MESSAGE` - Success message
  - `SIGNUP_FORM` - Signup form container

#### 5. **UploadLocators.java**
- **Location:** `src/main/java/com/automation/locators/`
- **Locators (11 total):**
  - `FILE_INPUT` - File input element
  - `UPLOAD_BUTTON` - Upload button
  - `SELECTED_FILE_NAME` - Selected file name display
  - `PROGRESS_BAR` - Upload progress bar
  - `SUCCESS_MESSAGE` - Upload success message
  - `ERROR_MESSAGE` - Upload error message
  - `UPLOADED_FILES_LIST` - Uploaded files list
  - `DELETE_FILE_BUTTON` - Delete file button
  - `DOWNLOAD_FILE_LINK` - Download file link
  - `UPLOAD_AREA` - Drag and drop area
  - `UPLOAD_STATUS` - Upload status text
  - `CANCEL_UPLOAD_BUTTON` - Cancel button
  - `UPLOAD_FORM` - Upload form container

---

### 📄 Page Object Classes

#### 6. **LoginPage.java**
- **Location:** `src/main/java/com/automation/pages/`
- **Purpose:** Login page actions and workflows
- **Key Methods (11 total):**
  - `navigateToLoginPage()` - Navigate to login
  - `enterMobile(String mobile)` - Enter mobile number
  - `enterPassword(String password)` - Enter password
  - `clickLoginButton()` - Click login
  - `clickRequestOtpButton()` - Request OTP
  - `enterOtp(String otp)` - Enter OTP
  - `clickVerifyOtpButton()` - Verify OTP
  - `login(String mobile, String password)` - Complete login
  - `loginWithTestData(String userType)` - Login with JSON data
  - `loginWithOtp(String mobile, String otp)` - OTP login
  - `verifyLoginSuccess()` - Verify success
  - `verifyErrorMessage()` - Check error message
  - `getErrorMessage()` - Get error text

#### 7. **SignupPage.java**
- **Location:** `src/main/java/com/automation/pages/`
- **Purpose:** Signup page actions and workflows
- **Key Methods (13 total):**
  - `navigateToSignupPage()` - Navigate to signup
  - `enterEmail(String email)` - Enter email
  - `enterFirstName(String firstName)` - Enter first name
  - `enterLastName(String lastName)` - Enter last name
  - `enterMobile(String mobile)` - Enter mobile
  - `enterPassword(String password)` - Enter password
  - `enterConfirmPassword(String password)` - Confirm password
  - `checkTermsCheckbox()` - Check terms
  - `clickSignupButton()` - Click signup
  - `signup(...)` - Complete signup flow
  - `signupWithTestData(String dataKey)` - Signup with JSON data
  - `verifySignupSuccess()` - Verify success
  - `verifyErrorMessage()` - Check error
  - `getErrorMessage()` - Get error text

#### 8. **UploadDocumentPage.java**
- **Location:** `src/main/java/com/automation/pages/`
- **Purpose:** Document upload page actions
- **Key Methods (10 total):**
  - `navigateToUploadPage()` - Navigate to upload
  - `uploadFile(String fileName)` - Upload file
  - `verifyUploadSuccess()` - Verify upload success
  - `verifyUploadError()` - Check upload error
  - `getErrorMessage()` - Get error text
  - `getSelectedFileName()` - Get file name
  - `deleteUploadedFile()` - Delete file
  - `getUploadStatus()` - Get status
  - `verifyUploadedFilesList()` - Verify files list

---

### 🛠️ Utility Classes

#### 9. **AssertUtils.java**
- **Location:** `src/main/java/com/automation/utils/`
- **Purpose:** Reusable assertion methods
- **Methods (8 total):**
  - `assertVisible(WebDriver, By)` - Element visible
  - `assertExists(WebDriver, By)` - Element exists
  - `assertClickable(WebDriver, By)` - Element clickable
  - `assertEnabled(WebDriver, By)` - Element enabled
  - `assertTextEquals(WebDriver, By, String)` - Text equals
  - `assertContains(WebDriver, By, String)` - Text contains
  - `assertUrlContains(WebDriver, String)` - URL contains
  - `assertUrlEquals(WebDriver, String)` - URL equals

#### 10. **ConfigConstants.java**
- **Location:** `src/main/java/com/automation/utils/`
- **Purpose:** Centralized configuration constants
- **Constants Groups:**
  - Application Configuration (URLs, base URLs)
  - OTP Configuration (endpoint, response field)
  - Wait Configuration (timeout, polling)
  - File Paths (test data, documents, screenshots)
  - Encryption Configuration (algorithm, key)
  - Chrome Driver Configuration (headless mode)
  - Page URLs (login, signup, upload, dashboard)
  - Log Messages (predefined log texts)

#### 11. **EncryptionUtils.java**
- **Location:** `src/main/java/com/automation/utils/`
- **Purpose:** Encrypt/decrypt sensitive credentials
- **Methods:**
  - `encrypt(String plainText)` - Encrypt text (returns Base64)
  - `decrypt(String encryptedText)` - Decrypt text

#### 12. **TestDataReader.java**
- **Location:** `src/main/java/com/automation/utils/`
- **Purpose:** Read test data from JSON files
- **Methods:**
  - `readJsonFile(String filePath)` - Read JSON file
  - `getUser(String userType)` - Get user credentials
  - `getSignupData(String dataKey)` - Get signup data
  - `getJsonValue(String filePath, String key)` - Get JSON value

#### 13. **FileUploadUtils.java**
- **Location:** `src/main/java/com/automation/utils/`
- **Purpose:** File upload operations and validation
- **Methods:**
  - `getFilePath(String fileName)` - Get file path
  - `verifyFileExists(String fileName)` - Check if exists
  - `getFileSize(String fileName)` - Get file size
  - `getFileExtension(String fileName)` - Get extension
  - `isFileExtensionAllowed(String, String...)` - Validate extension

#### 14. **OTPInterceptor.java**
- **Location:** `src/main/java/com/automation/utils/`
- **Purpose:** Intercept OTP from network responses via CDP
- **Methods:**
  - `startListening()` - Start network listener
  - `stopListening()` - Stop network listener
  - `extractOtp(String responseBody)` - Extract OTP from JSON
  - `getCapturedOtp()` - Get captured OTP
  - `setCapturedOtp(String otp)` - Set OTP manually
  - `resetCapturedOtp()` - Reset OTP

---

### ⚙️ Configuration Classes

#### 15. **Config.java**
- **Location:** `src/main/java/com/automation/`
- **Purpose:** Load properties from config.properties
- **Methods:**
  - `getProperty(String key)` - Get property value
  - `getProperty(String key, String defaultValue)` - Get with default
  - `getAppUrl()` - Get application URL
  - `getOtpApiEndpoint()` - Get OTP endpoint
  - `getWaitTimeout()` - Get wait timeout

---

### 🧪 Test Classes

#### 16. **LoginTest.java**
- **Location:** `src/test/java/com/automation/login/`
- **Test Cases (5 total):**
  - `testValidLogin()` - Test valid login
  - `testInvalidLogin()` - Test invalid login
  - `testOtpLogin()` - Test OTP login
  - `testLoginPageElements()` - Verify page elements
  - `testEmptyCredentialsValidation()` - Test validation

#### 17. **SignupTest.java**
- **Location:** `src/test/java/com/automation/signup/`
- **Test Cases (5 total):**
  - `testValidSignup()` - Test valid signup
  - `testSignupWithInvalidEmail()` - Test invalid email
  - `testSignupPasswordMismatch()` - Test password mismatch
  - `testSignupPageElements()` - Verify page elements
  - `testEmptyFieldsValidation()` - Test validation

#### 18. **UploadDocumentTest.java**
- **Location:** `src/test/java/com/automation/upload/`
- **Test Cases (7 total):**
  - `testUploadPdfFile()` - Upload PDF
  - `testUploadDocxFile()` - Upload DOCX
  - `testUploadImageFile()` - Upload image
  - `testUploadNonExistentFile()` - Test error handling
  - `testUploadPageElements()` - Verify elements
  - `testFileExtensionValidation()` - Validate extension
  - `testFileSize()` - Get file size
  - `testMultipleFileUploads()` - Upload multiple files

---

## 📊 Summary Statistics

| Category | Count |
|----------|-------|
| **Base Classes** | 2 |
| **Locator Classes** | 3 |
| **Page Object Classes** | 3 |
| **Utility Classes** | 6 |
| **Configuration Classes** | 1 |
| **Test Classes** | 3 |
| **Total Java Classes** | 18 |
| **Total Test Cases** | 17 |
| **Total Methods** | 130+ |
| **Assertion Methods** | 8 |
| **Locators** | 37 |

---

## 🔄 Class Dependencies

```
BaseTest
  ├── depends on: DriverManager, ConfigConstants
  └── used by: LoginTest, SignupTest, UploadDocumentTest

LoginPage
  ├── depends on: LoginLocators, AssertUtils, TestDataReader, ConfigConstants
  └── used by: LoginTest

SignupPage
  ├── depends on: SignupLocators, AssertUtils, TestDataReader, ConfigConstants
  └── used by: SignupTest

UploadDocumentPage
  ├── depends on: UploadLocators, AssertUtils, FileUploadUtils, ConfigConstants
  └── used by: UploadDocumentTest

TestDataReader
  ├── depends on: EncryptionUtils, ConfigConstants, Jackson
  └── used by: LoginPage, SignupPage, LoginTest, SignupTest

OTPInterceptor
  ├── depends on: ConfigConstants, Jackson, Chrome DevTools
  └── used by: LoginTest, LoginPage (optional)

FileUploadUtils
  ├── depends on: ConfigConstants
  └── used by: UploadDocumentPage, UploadDocumentTest

EncryptionUtils
  ├── depends on: ConfigConstants
  └── used by: TestDataReader

Config
  ├── depends on: Nothing
  └── used by: Other classes for property loading
```

---

## 🔍 Class Access Levels

| Class | Access | Reason |
|-------|--------|--------|
| All classes | public | Accessible across test framework |
| All methods | public | Framework-wide accessibility |
| TestDataReader | public | Used by tests and page objects |
| AssertUtils | public | Used everywhere for assertions |
| ConfigConstants | public | Constants used throughout |
| OTPInterceptor | public | Advanced feature utility |

---

## 📚 Package Organization

```
com.automation
├── base                    # Base infrastructure
│   ├── BaseTest.java
│   └── DriverManager.java
│
├── locators               # Element locators
│   ├── LoginLocators.java
│   ├── SignupLocators.java
│   └── UploadLocators.java
│
├── pages                  # Page objects
│   ├── LoginPage.java
│   ├── SignupPage.java
│   └── UploadDocumentPage.java
│
├── utils                  # Utilities
│   ├── AssertUtils.java
│   ├── ConfigConstants.java
│   ├── EncryptionUtils.java
│   ├── FileUploadUtils.java
│   ├── OTPInterceptor.java
│   └── TestDataReader.java
│
├── Config.java            # Configuration loader
│
└── [test packages]
    ├── login
    ├── signup
    └── upload
```

---

## 🎯 Quick Class Reference

**For Element Selection:** Use `*Locators.java` classes

**For Element Interaction:** Use `*Page.java` classes

**For Verification:** Use `AssertUtils.java`

**For Test Data:** Use `TestDataReader.java`

**For Encryption:** Use `EncryptionUtils.java`

**For File Operations:** Use `FileUploadUtils.java`

**For OTP Capture:** Use `OTPInterceptor.java`

**For Configuration:** Use `ConfigConstants.java` or `Config.java`

**For Base Setup:** Extend `BaseTest.java`

---

**Happy coding! Use this reference to navigate the framework. 🚀**

