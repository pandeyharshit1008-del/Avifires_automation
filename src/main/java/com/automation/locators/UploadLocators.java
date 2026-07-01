package com.automation.locators;

import org.openqa.selenium.By;

/**
 * Locators for Document Upload page
 * Contains all By locators used in document upload flow
 */
public class UploadLocators {

    // File input (hidden file upload element)
    public static final By FILE_INPUT = By.id("fileInput");

    // Upload button
    public static final By UPLOAD_BUTTON = By.id("uploadBtn");

    // File name display
    public static final By SELECTED_FILE_NAME = By.id("selectedFileName");

    // Upload progress bar
    public static final By PROGRESS_BAR = By.id("progressBar");

    // Upload success message
    public static final By SUCCESS_MESSAGE = By.className("upload-success");

    // Upload error message
    public static final By ERROR_MESSAGE = By.className("upload-error");

    // Uploaded file list
    public static final By UPLOADED_FILES_LIST = By.id("uploadedFilesList");

    // Delete file button
    public static final By DELETE_FILE_BUTTON = By.className("deleteFileBtn");

    // Download file link
    public static final By DOWNLOAD_FILE_LINK = By.className("downloadFileLink");

    // Upload area (drag and drop)
    public static final By UPLOAD_AREA = By.id("uploadArea");

    // Upload status text
    public static final By UPLOAD_STATUS = By.id("uploadStatus");

    // Cancel upload button
    public static final By CANCEL_UPLOAD_BUTTON = By.id("cancelUploadBtn");

    // Document upload form container
    public static final By UPLOAD_FORM = By.id("uploadForm");

}
