package com.automation.utils;

/**
 * Centralized configuration constants for the automation framework
 */
public class ConfigConstants {

    // ===== Application Configuration =====
    public static final String APP_URL = "https://staging-mf-user-sa.vercel.app";
    public static final String APP_BASE_URL = "https://staging-mf-user-sa.vercel.app";

    // ===== OTP Configuration =====
    public static final String OTP_API_ENDPOINT = "https://staging-mf-user-sa.vercel.app/api/send-otp";
    public static final String PASSWORD_AUTH_ENDPOINT = "https://wqmbuqjnjc.ap.loclx.io/api/v1/user_auth_password";
    public static final String OTP_RESPONSE_FIELD = "otp";

    // ===== Wait Configuration =====
    public static final int WAIT_TIMEOUT_SECONDS = 10;
    public static final int POLLING_INTERVAL_MILLIS = 500;

    // ===== File Paths =====
    public static final String TESTDATA_USERS_PATH = "src/main/resources/testdata/users.json";
    public static final String TESTDATA_SIGNUP_PATH = "src/main/resources/testdata/signup_data.json";
    public static final String DOCUMENTS_PATH = "testdata/documents/";
    public static final String SCREENSHOTS_PATH = "screenshots/";

    // ===== Encryption Configuration =====
    public static final String ENCRYPTION_ALGORITHM = "AES";
    public static final String ENCRYPTION_KEY = "MySecretKey12345"; // 16 chars for AES-128

    // ===== Chrome Driver Configuration =====
    public static final String BROWSER = "chrome";
    public static final boolean HEADLESS_MODE = false;

    // ===== Page URLs =====
    public static final String LOGIN_PAGE_URL = APP_BASE_URL;
    public static final String SIGNUP_PAGE_URL = APP_BASE_URL;
    public static final String UPLOAD_PAGE_URL = APP_BASE_URL + "/upload";
    public static final String DASHBOARD_URL = APP_BASE_URL + "/user-dashboard";

    // ===== Log Messages =====
    public static final String LOG_BROWSER_LAUNCH = "Browser launched successfully";
    public static final String LOG_BROWSER_CLOSE = "Browser closed successfully";
    public static final String LOG_PAGE_NAVIGATION = "Navigated to page: ";
    public static final String LOG_OTP_CAPTURED = "OTP captured from network response: ";
    public static final String LOG_FILE_UPLOADED = "File uploaded successfully: ";

}
