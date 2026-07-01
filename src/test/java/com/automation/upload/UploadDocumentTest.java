package com.automation.upload;

import com.automation.base.BaseTest;
import com.automation.pages.UploadDocumentPage;
import com.automation.utils.AssertUtils;
import com.automation.utils.FileUploadUtils;
import org.testng.annotations.Test;

/**
 * Test class for Document Upload functionality
 */
public class UploadDocumentTest extends BaseTest {

    /**
     * Test single PDF file upload
     */
    @Test
    public void testUploadPdfFile() {
        System.out.println("\n========== TEST: Upload PDF File ==========");
        
        UploadDocumentPage uploadPage = new UploadDocumentPage(driver);
        uploadPage.navigateToUploadPage();

        // Upload PDF file
        String fileName = "sample.pdf";
        boolean uploadSuccess = uploadPage.uploadFile(fileName);

        if (uploadSuccess && uploadPage.verifyUploadSuccess()) {
            System.out.println("✓ Test Passed: PDF file uploaded successfully");
        } else {
            System.out.println("✗ Test Failed: PDF file upload failed");
        }
    }

    /**
     * Test DOCX file upload
     */
    @Test
    public void testUploadDocxFile() {
        System.out.println("\n========== TEST: Upload DOCX File ==========");
        
        UploadDocumentPage uploadPage = new UploadDocumentPage(driver);
        uploadPage.navigateToUploadPage();

        // Upload DOCX file
        String fileName = "document.docx";
        boolean uploadSuccess = uploadPage.uploadFile(fileName);

        if (uploadSuccess && uploadPage.verifyUploadSuccess()) {
            System.out.println("✓ Test Passed: DOCX file uploaded successfully");
        } else {
            System.out.println("✗ Test Failed: DOCX file upload failed");
        }
    }

    /**
     * Test image file upload
     */
    @Test
    public void testUploadImageFile() {
        System.out.println("\n========== TEST: Upload Image File ==========");
        
        UploadDocumentPage uploadPage = new UploadDocumentPage(driver);
        uploadPage.navigateToUploadPage();

        // Upload image file
        String fileName = "image.png";
        boolean uploadSuccess = uploadPage.uploadFile(fileName);

        if (uploadSuccess && uploadPage.verifyUploadSuccess()) {
            System.out.println("✓ Test Passed: Image file uploaded successfully");
        } else {
            System.out.println("✗ Test Failed: Image file upload failed");
        }
    }

    /**
     * Test upload of non-existent file
     */
    @Test
    public void testUploadNonExistentFile() {
        System.out.println("\n========== TEST: Upload Non-Existent File ==========");
        
        UploadDocumentPage uploadPage = new UploadDocumentPage(driver);
        uploadPage.navigateToUploadPage();

        // Try to upload non-existent file
        String fileName = "non_existent_file.pdf";
        boolean uploadSuccess = uploadPage.uploadFile(fileName);

        if (!uploadSuccess) {
            System.out.println("✓ Test Passed: Upload rejected for non-existent file");
        } else {
            System.out.println("✗ Test Failed: Non-existent file was unexpectedly uploaded");
        }
    }

    /**
     * Test upload page elements visibility
     */
    @Test
    public void testUploadPageElements() {
        System.out.println("\n========== TEST: Upload Page Elements ==========");
        
        UploadDocumentPage uploadPage = new UploadDocumentPage(driver);
        uploadPage.navigateToUploadPage();

        // Verify all elements are visible
        boolean allElementsVisible = 
            AssertUtils.assertVisible(driver, com.automation.locators.UploadLocators.UPLOAD_AREA) &&
            AssertUtils.assertVisible(driver, com.automation.locators.UploadLocators.UPLOAD_BUTTON);

        if (allElementsVisible) {
            System.out.println("✓ Test Passed: All upload page elements are visible");
        } else {
            System.out.println("✗ Test Failed: Some upload page elements are not visible");
        }
    }

    /**
     * Test file extension validation
     */
    @Test
    public void testFileExtensionValidation() {
        System.out.println("\n========== TEST: File Extension Validation ==========");
        
        String fileName = "sample.pdf";
        
        // Check if PDF extension is allowed
        boolean isAllowed = FileUploadUtils.isFileExtensionAllowed(fileName, "pdf", "docx", "xlsx");

        if (isAllowed) {
            System.out.println("✓ Test Passed: PDF file extension is valid");
        } else {
            System.out.println("✗ Test Failed: PDF file extension validation failed");
        }
    }

    /**
     * Test file size retrieval
     */
    @Test
    public void testFileSize() {
        System.out.println("\n========== TEST: File Size Retrieval ==========");
        
        String fileName = "sample.pdf";
        
        // Verify file exists and get size
        if (FileUploadUtils.verifyFileExists(fileName)) {
            long fileSize = FileUploadUtils.getFileSize(fileName);
            if (fileSize > 0) {
                System.out.println("✓ Test Passed: File size retrieved: " + fileSize + " bytes");
            } else {
                System.out.println("✗ Test Failed: File size is 0");
            }
        } else {
            System.out.println("✗ Test Failed: File not found");
        }
    }

    /**
     * Test multiple file uploads
     */
    @Test
    public void testMultipleFileUploads() {
        System.out.println("\n========== TEST: Multiple File Uploads ==========");
        
        UploadDocumentPage uploadPage = new UploadDocumentPage(driver);
        uploadPage.navigateToUploadPage();

        String[] files = {"sample.pdf", "document.docx"};
        int successCount = 0;

        for (String fileName : files) {
            if (uploadPage.uploadFile(fileName) && uploadPage.verifyUploadSuccess()) {
                successCount++;
            }
            
            // Navigate back to upload page for next file
            if (successCount < files.length) {
                uploadPage.navigateToUploadPage();
            }
        }

        if (successCount == files.length) {
            System.out.println("✓ Test Passed: All files uploaded successfully");
        } else {
            System.out.println("✗ Test Failed: " + (files.length - successCount) + " file(s) failed to upload");
        }
    }

}
