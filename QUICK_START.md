# Quick Start Guide

## 📋 Getting Started in 5 Minutes

### 1. **Prerequisites Check** (1 min)

```bash
# Check Java version
java -version
# Expected: Java 17+

# Check Maven version
mvn -version
# Expected: Maven 3.6+
```

### 2. **Build the Project** (2 min)

```bash
cd c:\UI-Automation-JavaSelenium-Framework-
mvn clean install
```

### 3. **Configure Application URL** (1 min)

Edit `src/main/resources/config.properties`:

```properties
app.url=https://your-actual-app-domain.com
```

### 4. **Update Test Data** (1 min)

Edit `src/main/resources/testdata/users.json` with encrypted credentials:

```bash
# First, encrypt your credentials
# Use: EncryptionUtils.encrypt("your-mobile")
# Store encrypted values in JSON
```

### 5. **Run Tests** (1 min)

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=LoginTest
```

---

## 🚀 First Test Run

### Option 1: CLI (Recommended for CI/CD)

```bash
mvn test
```

### Option 2: IDE (IntelliJ/Eclipse)

1. Open project in IDE
2. Right-click on `LoginTest.java`
3. Select "Run LoginTest"

### Option 3: TestNG Suite

```bash
mvn test -Dtestng.suiteXmlFile=testng.xml
```

---

## 📝 What Gets Tested

### ✅ Login Tests
- Valid login with credentials
- Invalid login error handling
- OTP-based login flow
- Login page element visibility
- Empty credentials validation

### ✅ Signup Tests
- Valid signup with all details
- Invalid email handling
- Password mismatch detection
- Signup page element visibility
- Empty fields validation

### ✅ Upload Tests
- PDF file upload
- DOCX file upload
- Image file upload
- Non-existent file handling
- Upload page elements
- File extension validation
- File size verification
- Multiple file uploads

---

## 🔧 Common Tasks

### Add New Test

1. Create page locators in `src/main/java/com/automation/locators/`
2. Create page object in `src/main/java/com/automation/pages/`
3. Create test class in `src/test/java/com/automation/[feature]/`
4. Add test to `testng.xml`

### Update Locators

```java
// File: LoginLocators.java
public static final By NEW_ELEMENT = By.id("newId");
```

### Add Test Data

```json
// File: users.json
{
  "newUser": {
    "mobile": "encrypted_value",
    "password": "encrypted_value"
  }
}
```

### View Test Results

```
target/surefire-reports/index.html
```

---

## 🔐 Security Best Practices

✅ **DO:**
- Encrypt credentials before storing
- Use ConfigConstants for sensitive values
- Keep encryption key secure
- Store key in environment variables

❌ **DON'T:**
- Hardcode credentials in code
- Commit unencrypted passwords
- Share encryption keys in repositories
- Log sensitive information

---

## 📊 Project Statistics

| Component | Count | Status |
|-----------|-------|--------|
| **Test Classes** | 3 | ✅ Ready |
| **Test Cases** | 17 | ✅ Ready |
| **Page Objects** | 3 | ✅ Ready |
| **Locator Classes** | 3 | ✅ Ready |
| **Utility Classes** | 6 | ✅ Ready |
| **Base Classes** | 2 | ✅ Ready |
| **Test Data Files** | 2 | ✅ Ready |

---

## 🆘 Troubleshooting

### Issue: Maven build fails

**Solution:**
```bash
mvn clean install -U
```

### Issue: WebDriver not found

**Solution:** WebDriverManager auto-downloads ChromeDriver. If it fails:
```bash
# Clear Maven cache
rm -rf ~/.m2/repository/io/github/bonigarcia
mvn clean install
```

### Issue: Tests timeout

**Solution:** Increase timeout in `ConfigConstants.java`:
```java
public static final int WAIT_TIMEOUT_SECONDS = 15; // Increase from 10
```

### Issue: Can't find test data

**Solution:** Verify paths:
```
src/main/resources/testdata/users.json
src/main/resources/testdata/signup_data.json
testdata/documents/sample.pdf
```

---

## 📚 Framework Structure at a Glance

```
UI-Automation-JavaSelenium-Framework/
│
├── src/main/java/com/automation/
│   ├── base/              ← WebDriver setup/teardown
│   ├── pages/             ← Page actions (LoginPage, SignupPage, etc.)
│   ├── locators/          ← Element locators
│   ├── utils/             ← Reusable utilities
│   └── Config.java        ← Configuration loader
│
├── src/test/java/com/automation/
│   ├── login/             ← LoginTest
│   ├── signup/            ← SignupTest
│   └── upload/            ← UploadDocumentTest
│
├── src/main/resources/
│   ├── config.properties  ← App URL configuration
│   └── testdata/          ← JSON test data
│
├── testdata/documents/    ← Test files
├── screenshots/           ← Test execution screenshots
├── pom.xml                ← Maven dependencies
└── testng.xml             ← Test suite configuration
```

---

## ✅ Verification Checklist

- [ ] Java 17+ installed
- [ ] Maven 3.6+ installed
- [ ] Chrome browser installed
- [ ] Project built successfully (`mvn clean install`)
- [ ] config.properties updated with app URL
- [ ] Test data encrypted and stored in JSON
- [ ] First test run successful

---

## 🎯 Next Steps

1. **Update Application URL** - Configure your app domain
2. **Add Credentials** - Encrypt and store your test credentials
3. **Run Login Tests** - Verify basic login flow
4. **Run Full Suite** - Execute all tests
5. **Review Reports** - Check test results and logs
6. **Add Custom Tests** - Extend with your test scenarios

---

## 📞 Support Resources

- **README.md** - Comprehensive documentation
- **Code Comments** - Each class has JavaDoc comments
- **Testng.xml** - Test suite configuration
- **ConfigConstants.java** - All configuration values

---

**You're all set! Happy testing! 🚀**

