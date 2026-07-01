package com.automation.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for handling file upload operations
 */
public class FileUploadUtils {

    /**
     * Get the full file path for a document file
     * Files should be stored in testdata/documents/ directory
     *
     * @param fileName Name of the file (e.g., "sample.pdf")
     * @return Full path to the file
     */
    public static String getFilePath(String fileName) {
        String filePath = ConfigConstants.DOCUMENTS_PATH + fileName;
        return new File(filePath).getAbsolutePath();
    }

    /**
     * Verify that a file exists at the specified path
     *
     * @param fileName Name of the file
     * @return true if file exists, false otherwise
     */
    public static boolean verifyFileExists(String fileName) {
        String filePath = getFilePath(fileName);
        boolean exists = new File(filePath).exists();
        if (exists) {
            System.out.println("✓ File exists: " + filePath);
        } else {
            System.out.println("✗ File not found: " + filePath);
        }
        return exists;
    }

    /**
     * Get file size in bytes
     *
     * @param fileName Name of the file
     * @return File size in bytes, 0 if file not found
     */
    public static long getFileSize(String fileName) {
        try {
            Path filePath = Paths.get(getFilePath(fileName));
            if (Files.exists(filePath)) {
                long size = Files.size(filePath);
                System.out.println("✓ File size: " + size + " bytes");
                return size;
            }
        } catch (Exception e) {
            System.out.println("✗ Error getting file size: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Get file extension
     *
     * @param fileName Name of the file
     * @return File extension (e.g., "pdf", "docx")
     */
    public static String getFileExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot > 0) {
            return fileName.substring(lastDot + 1).toLowerCase();
        }
        return "";
    }

    /**
     * Verify file extension is allowed
     *
     * @param fileName Name of the file
     * @param allowedExtensions Allowed file extensions (e.g., "pdf", "docx", "xlsx")
     * @return true if extension is allowed, false otherwise
     */
    public static boolean isFileExtensionAllowed(String fileName, String... allowedExtensions) {
        String extension = getFileExtension(fileName);
        for (String allowed : allowedExtensions) {
            if (extension.equalsIgnoreCase(allowed)) {
                System.out.println("✓ File extension is allowed: " + extension);
                return true;
            }
        }
        System.out.println("✗ File extension not allowed: " + extension);
        return false;
    }

}
