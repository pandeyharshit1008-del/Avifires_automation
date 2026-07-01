# Troubleshooting & FAQ Guide

## 🆘 Common Issues & Solutions

### Maven & Build Issues

#### ❌ Issue: "Maven command not found"
```
Error: 'mvn' is not recognized as an internal or external command
```
**Solution:**
1. Install Maven from https://maven.apache.org/download.cgi
2. Set JAVA_HOME and Maven in environment variables
3. Add Maven bin folder to PATH
4. Verify: `mvn -version`

---

#### ❌ Issue: Build fails with "Java version not supported"
```
Error: [ERROR] Source option 17 is no longer supported
```
**Solution:**
1. Install Java 17 or later
2. Set JAVA_HOME to Java 17 installation
3. Verify: `java -version`
4. Rebuild: `mvn clean install`

---

#### ❌ Issue: Dependencies not downloading
```
Error: Could not find artifact in repository
```
**Solution:**
```bash
# Force update all dependencies
mvn clean install -U

# Or clear Maven cache
rm -rf ~/.m2/repository
mvn clean install
```

---

### WebDriver Issues

#### ❌ Issue: "ChromeDriver not found"
```
Error: The path to the driver executable must be set
```
**Solution:**
WebDriverManager handles this automatically. If it fails:
```bash
# Clear WebDriverManager cache
rm -rf ~/.wdm

# Rebuild with updated dependencies
mvn clean install -U
```

---

#### ❌ Issue: "Version mismatch" between Chrome and ChromeDriver
```
Error: This version of ChromeDriver only works with Chrome version X
```
**Solution:**
1. Update Chrome to latest version
2. Clear WebDriverManager cache
3. Let WebDriverManager download correct version
4. Rebuild: `mvn clean install`

---

#### ❌ Issue: Browser won't start
```
Error: Failed to start Chrome browser
```
**Solution:**
1. Verify Chrome is installed: Check `C:\Program Files\Google\Chrome\`
2. Ensure Chrome is not locked by another process
3. Update Chrome to latest version
4. Check Chrome DevTools is enabled
5. Try running in headless mode (update DriverManager.java)

---

### Test Execution Issues

#### ❌ Issue: Tests timeout
```
Error: org.openqa.selenium.TimeoutException
```
**Solution:**
1. Increase wait timeout in ConfigConstants.java:
```java
public static final int WAIT_TIMEOUT_SECONDS = 15; // Increase from 10
```

2. Verify element locators are correct
3. Check if element is in iframe or shadow DOM
4. Add explicit waits in page methods

---

#### ❌ Issue: Element not found
```
Error: NoSuchElementException: no such element
```
**Solution:**
1. Verify element ID/locator in browser DevTools (F12)
2. Update locator in corresponding Locators class
3. Check if element appears dynamically (add wait)
4. Check if element is hidden/disabled
5. Verify CSS selector syntax if using CSS

---

#### ❌ Issue: Test runs but all tests fail
```
All tests show FAILED status
```
**Solution:**
1. Verify app URL is correct in config.properties
2. Ensure app is running and accessible
3. Check internet connection
4. Verify test data is correct
5. Check browser console for JavaScript errors (F12)

---

### Test Data Issues

#### ❌ Issue: "Test data not found"
```
Warning: Test data file not found at src/main/resources/testdata/users.json
```
**Solution:**
1. Verify JSON files exist in correct location:
   - `src/main/resources/testdata/users.json`
   - `src/main/resources/testdata/signup_data.json`
2. Check file names are correct (case-sensitive)
3. Verify JSON syntax is valid (use JSON validator)
4. Check file is readable

---

#### ❌ Issue: Encrypted data not decrypting
```
Error: Failed to decrypt credentials
```
**Solution:**
1. Verify encryption key matches in ConfigConstants.java
2. Verify encrypted values in JSON are correct
3. Check if encryption algorithm changed
4. Use EncryptionUtils to re-encrypt test data:
```java
String encrypted = EncryptionUtils.encrypt("your-mobile");
System.out.println(encrypted); // Use in JSON
```

---

#### ❌ Issue: "JSON parsing error"
```
Error: com.fasterxml.jackson.core.JsonParseException
```
**Solution:**
1. Validate JSON syntax:
   - Use online JSON validator
   - Check for missing commas, quotes
   - Ensure no trailing commas
2. Verify file encoding is UTF-8
3. Check if file contains BOM (Byte Order Mark)

---

### OTP Issues

#### ❌ Issue: OTP not captured
```
OTP is always null
```
**Solution:**
1. Verify OTP API endpoint in ConfigConstants.java:
```java
public static final String OTP_API_ENDPOINT = "https://your-app/api/send-otp";
```

2. Check OTP response JSON field name:
```java
public static final String OTP_RESPONSE_FIELD = "otp"; // Verify field name
```

3. Ensure OTPInterceptor.startListening() is called before OTP request
4. Verify Chrome DevTools Protocol is working
5. Check browser console for network errors

---

#### ❌ Issue: "Network listener not started"
```
✗ Network listener started (but doesn't work)
```
**Solution:**
1. Verify driver is ChromeDriver (not other browser)
2. Ensure DevTools connection is active
3. Check if firewall blocks DevTools
4. Try restarting browser and listener

---

### File Upload Issues

#### ❌ Issue: File not found during upload
```
✗ File not found: testdata/documents/sample.pdf
```
**Solution:**
1. Place files in correct directory:
   ```
   testdata/documents/sample.pdf
   ```
2. Use relative path from project root
3. Verify file name is correct (case-sensitive)
4. Check file permissions are readable
5. Ensure file extension is correct

---

#### ❌ Issue: Upload fails silently
```
Upload method returns false but no error shown
```
**Solution:**
1. Verify file input element locator is correct
2. Check if file input is hidden (sendKeys should still work)
3. Verify upload button is clickable
4. Check upload endpoint is working
5. Add more explicit waits if needed

---

#### ❌ Issue: "File extension not allowed"
```
✗ File extension not allowed: pdf
```
**Solution:**
1. Update allowed extensions in your code:
```java
FileUploadUtils.isFileExtensionAllowed(
    fileName, "pdf", "docx", "xlsx"  // Add extensions here
);
```

2. Or use in page method:
```java
FileUploadUtils.isFileExtensionAllowed("sample.pdf", "pdf", "docx");
```

---

### Assertion Issues

#### ❌ Issue: Assertion always fails
```
✗ Element is not visible: By.id("someId")
```
**Solution:**
1. Verify element ID/locator is correct
2. Add explicit wait in page method:
```java
wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
```

3. Check if element is hidden by CSS (display: none)
4. Verify element is not in iframe
5. Use browser DevTools to inspect element

---

#### ❌ Issue: "Text does not match"
```
✗ Text does not match. Expected: "Login", Actual: "Sign In"
```
**Solution:**
1. Get actual text from page: Right-click → Inspect → Check text
2. Update expected text in assertion
3. Use `assertContains()` if exact match not needed
4. Check for extra spaces or special characters
5. Use `trim()` if needed

---

### Configuration Issues

#### ❌ Issue: "Property not found"
```
Warning: Could not find property key
```
**Solution:**
1. Verify property exists in config.properties
2. Check property key name is correct
3. Verify file is in `src/main/resources/`
4. Check file syntax: `key=value`
5. No spaces around = sign

---

#### ❌ Issue: "Application URL incorrect"
```
Navigation fails or wrong page loads
```
**Solution:**
1. Verify URL in config.properties:
```properties
app.url=https://your-correct-app-domain.com
app.base.url=https://your-correct-app-domain.com
```

2. Verify URL is accessible:
   - Open in browser
   - Check internet connection
   - Verify VPN/proxy if needed

3. Update page URLs in ConfigConstants.java if needed

---

### IDE Issues

#### ❌ Issue: "Project not recognized in IDE"
```
IDE shows red errors everywhere
```
**Solution:**
1. Refresh Maven project:
   - Right-click project → Maven → Reload Projects
2. Clear IDE cache:
   - Close IDE
   - Delete `.idea` folder (IntelliJ)
   - Delete `.project` folder (Eclipse)
   - Reopen project
3. Mark as Maven project if needed

---

#### ❌ Issue: "JUnit/TestNG framework not recognized"
```
Cannot run tests from IDE
```
**Solution:**
1. Add TestNG to IDE:
   - IntelliJ: Preferences → Plugins → TestNG → Install
   - Eclipse: Help → Eclipse Marketplace → Search TestNG → Install
2. Right-click test class → Run As → TestNG Test
3. If still not working, run from Maven: `mvn test`

---

## ❓ Frequently Asked Questions

### Q: Do I need to update locators for every app?
**A:** Yes. Each app has different element IDs/names. Update locators in LoginLocators.java, SignupLocators.java, and UploadLocators.java to match your app.

---

### Q: Can I use this framework with other browsers?
**A:** Currently configured for Chrome only. To support Firefox/Edge, update DriverManager.java to create appropriate driver instance.

---

### Q: How do I add new test scenarios?
**A:** 
1. Create test data in JSON file
2. Create test method in existing test class
3. Call page methods and use assertions

---

### Q: Is the encryption secure for production?
**A:** The current AES encryption is reasonable for test environments. For production, consider:
- Using Azure Key Vault or AWS Secrets Manager
- Implementing additional security layers
- Regular key rotation

---

### Q: Can tests run in parallel?
**A:** Yes. Update testng.xml to run tests in parallel:
```xml
<suite name="..." parallel="tests" thread-count="3">
```

---

### Q: How do I view detailed test logs?
**A:** Test execution details are printed with ✓ and ✗ symbols. For more details:
1. Check console output
2. View TestNG reports in `target/surefire-reports/`
3. Add custom logging in page methods

---

### Q: Can I use this with CI/CD tools?
**A:** Yes! The framework is CI/CD ready. Example for Jenkins:
```groovy
stage('Test') {
    steps {
        sh 'mvn clean test'
    }
}
post {
    always {
        junit 'target/surefire-reports/*.xml'
    }
}
```

---

### Q: How do I handle dynamic waits better?
**A:** The framework uses explicit waits by default. For more control:
```java
WebDriverWait customWait = new WebDriverWait(driver, 
    Duration.ofSeconds(20));
customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
```

---

### Q: What if the app requires authentication?
**A:** Use TestDataReader to load credentials:
```java
Map<String, String> userData = TestDataReader.getUser("validUser");
String username = userData.get("mobile");
String password = userData.get("password");
loginPage.login(username, password);
```

---

### Q: How do I handle alerts/pop-ups?
**A:** Add to page methods:
```java
Alert alert = driver.switchTo().alert();
alert.accept(); // or alert.dismiss();
```

---

### Q: Can I extend the framework for API testing?
**A:** The framework is UI-focused. For API testing, consider a separate framework like RestAssured.

---

### Q: How do I handle screenshot capture?
**A:** Add to BaseTest.java:
```java
TakesScreenshot ts = (TakesScreenshot) driver;
File file = ts.getScreenshotAs(OutputType.FILE);
// Save to screenshots/ folder
```

---

## 🔍 Debug Tips

### 1. Enable Browser DevTools
```
Press F12 in browser during test execution
- Inspect element to verify locators
- Check network tab for API calls
- View console for JavaScript errors
```

### 2. Add Print Statements
```java
System.out.println("Current URL: " + driver.getCurrentUrl());
System.out.println("Page Title: " + driver.getTitle());
```

### 3. Take Screenshots on Failure
```java
@AfterMethod(alwaysRun = true)
public void captureScreenshot(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
        // Take screenshot
    }
}
```

### 4. Increase Logging
Update log messages in page methods:
```java
System.out.println("Attempting to click: " + locator);
driver.findElement(locator).click();
System.out.println("Successfully clicked");
```

### 5. Use Breakpoints
Set breakpoints in page methods and debug tests step-by-step (IDE feature).

---

## 📞 Getting Help

1. **Check Documentation:**
   - README.md - Complete guide
   - QUICK_START.md - Quick reference
   - CLASS_REFERENCE.md - Class details

2. **Search for Issue:**
   - Check this troubleshooting guide
   - Search in GitHub issues
   - Check Stack Overflow

3. **Check Code Examples:**
   - Review test classes for patterns
   - Check page objects for implementations
   - Look at utility methods for examples

---

**Still stuck? Check your specific error in this guide or refer to the comprehensive README.md file.** 🚀

