package com.automation.pages;

import com.automation.locators.UploadLocators;
import com.automation.utils.AssertUtils;
import com.automation.utils.ConfigConstants;
import com.automation.utils.FileUploadUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Document Upload page object
 * Contains all document upload-related actions and workflows
 */
public class UploadDocumentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Constructor to initialize driver and wait
     *
     * @param driver WebDriver instance
     */
    public UploadDocumentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigConstants.WAIT_TIMEOUT_SECONDS));
    }

    /**
     * Navigate to upload page
     */
    public void navigateToUploadPage() {
        driver.navigate().to(ConfigConstants.UPLOAD_PAGE_URL);
        System.out.println("✓ Navigated to upload page");
        AssertUtils.assertVisible(driver, UploadLocators.UPLOAD_FORM);
    }

    /**
     * Upload a file using the file input element
     * This method uses sendKeys to send file path to hidden file input element
     *
     * @param fileName Name of the file to upload (from testdata/documents/ directory)
     * @return true if file upload was successful, false otherwise
     */
    public boolean uploadFile(String fileName) {
        System.out.println("\n--- Starting File Upload ---");
        
        // Verify file exists
        if (!FileUploadUtils.verifyFileExists(fileName)) {
            System.out.println("✗ File not found: " + fileName);
            return false;
        }

        try {
            // Get full file path
            String filePath = FileUploadUtils.getFilePath(fileName);

            // Wait for file input element and send file path
            wait.until(ExpectedConditions.presenceOfElementLocated(UploadLocators.FILE_INPUT));
            driver.findElement(UploadLocators.FILE_INPUT).sendKeys(filePath);
            System.out.println("✓ File selected: " + fileName);

            // Wait for and click upload button
            AssertUtils.assertClickable(driver, UploadLocators.UPLOAD_BUTTON);
            driver.findElement(UploadLocators.UPLOAD_BUTTON).click();
            System.out.println("✓ Upload button clicked");

            // Wait for upload to complete
            wait.until(ExpectedConditions.presenceOfElementLocated(UploadLocators.PROGRESS_BAR));
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(UploadLocators.PROGRESS_BAR)));

            // Verify upload success
            if (verifyUploadSuccess()) {
                System.out.println(ConfigConstants.LOG_FILE_UPLOADED + fileName);
                return true;
            } else {
                System.out.println("✗ Upload failed for: " + fileName);
                return false;
            }

        } catch (Exception e) {
            System.out.println("✗ Error during file upload: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verify that file upload was successful
     *
     * @return true if success message is visible, false otherwise
     */
    public boolean verifyUploadSuccess() {
        return AssertUtils.assertVisible(driver, UploadLocators.SUCCESS_MESSAGE);
    }

    /**
     * Verify that upload error message is displayed
     *
     * @return true if error message is visible, false otherwise
     */
    public boolean verifyUploadError() {
        return AssertUtils.assertVisible(driver, UploadLocators.ERROR_MESSAGE);
    }

    /**
     * Get upload error message text
     *
     * @return Error message text
     */
    public String getErrorMessage() {
        try {
            return driver.findElement(UploadLocators.ERROR_MESSAGE).getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get the name of the selected file
     *
     * @return Selected file name
     */
    public String getSelectedFileName() {
        try {
            return driver.findElement(UploadLocators.SELECTED_FILE_NAME).getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Delete an uploaded file
     *
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteUploadedFile() {
        try {
            AssertUtils.assertClickable(driver, UploadLocators.DELETE_FILE_BUTTON);
            driver.findElement(UploadLocators.DELETE_FILE_BUTTON).click();
            System.out.println("✓ Uploaded file deleted");
            return true;
        } catch (Exception e) {
            System.out.println("✗ Error deleting file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get upload status text
     *
     * @return Upload status message
     */
    public String getUploadStatus() {
        try {
            return driver.findElement(UploadLocators.UPLOAD_STATUS).getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Verify that uploaded file is visible in the list
     *
     * @return true if uploaded files list is visible, false otherwise
     */
    public boolean verifyUploadedFilesList() {
        return AssertUtils.assertVisible(driver, UploadLocators.UPLOADED_FILES_LIST);
    }

}
