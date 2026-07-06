package com.automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for reading test data from JSON files
 */
public class TestDataReader {

    private static final Logger logger = LoggerFactory.getLogger(TestDataReader.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Read test data from a JSON file
     *
     * @param filePath Path to the JSON file
     * @return JsonNode containing the parsed JSON data
     */
    public static JsonNode readJsonFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                logger.info("Warning: Test data file not found at " + filePath);
                return null;
            }
            return mapper.readTree(file);
        } catch (IOException e) {
            logger.error("Error reading JSON file", e);
            return null;
        }
    }

    /**
     * Get test data for a specific user from users.json
     *
     * @param userType Type of user (e.g., "validUser", "invalidUser")
     * @return Map containing user data (mobile and password)
     */
    public static Map<String, String> getUser(String userType) {
        Map<String, String> userData = new HashMap<>();
        JsonNode root = readJsonFile(ConfigConstants.TESTDATA_USERS_PATH);
        if (root == null) {
            throw new RuntimeException("Test data file not found: " + ConfigConstants.TESTDATA_USERS_PATH);
        }
        if (!root.has(userType)) {
            throw new RuntimeException("User type not found in test data: " + userType);
        }

        JsonNode userNode = root.get(userType);
        String encryptedMobile = userNode.get("mobile").asText();
        String encryptedPassword = userNode.get("password").asText();

        try {
            String mobile = EncryptionUtils.decrypt(encryptedMobile);
            String password = EncryptionUtils.decrypt(encryptedPassword);

            if (mobile == null || password == null) {
                throw new RuntimeException("Decryption returned null for user: " + userType + ". Check encryption key and stored values.");
            }

            userData.put("mobile", mobile);
            userData.put("password", password);

            logger.info("✓ Test data loaded for user: " + userType);
            return userData;
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt credentials for user: " + userType + ". Cause: " + e.getMessage(), e);
        }
    }

    /**
     * Get a specific value from JSON by key path
     *
     * @param filePath Path to the JSON file
     * @param key      Key to fetch
     * @return Value as String
     */
    public static String getJsonValue(String filePath, String key) {
        try {
            JsonNode root = readJsonFile(filePath);
            if (root != null && root.has(key)) {
                return root.get(key).asText();
            }
        } catch (Exception e) {
            logger.info("Error fetching JSON value: " + e.getMessage());
        }
        return null;
    }

}
