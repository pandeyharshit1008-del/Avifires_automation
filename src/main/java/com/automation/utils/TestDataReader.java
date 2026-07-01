package com.automation.utils;

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
                System.out.println("Warning: Test data file not found at " + filePath);
                return null;
            }
            return mapper.readTree(file);
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
            e.printStackTrace();
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

            System.out.println("✓ Test data loaded for user: " + userType);
            return userData;
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt credentials for user: " + userType + ". Cause: " + e.getMessage(), e);
        }
    }

    /**
     * Get signup test data from signup_data.json
     *
     * @param dataKey Key for the specific signup data (e.g., "validSignup", "invalidEmail")
     * @return Map containing signup data
     */
    public static Map<String, String> getSignupData(String dataKey) {
        Map<String, String> signupData = new HashMap<>();
        JsonNode root = readJsonFile(ConfigConstants.TESTDATA_SIGNUP_PATH);
        if (root == null) {
            throw new RuntimeException("Signup test data file not found: " + ConfigConstants.TESTDATA_SIGNUP_PATH);
        }
        if (!root.has(dataKey)) {
            throw new RuntimeException("Signup data key not found in test data: " + dataKey);
        }

        JsonNode dataNode = root.get(dataKey);
        try {
            dataNode.fields().forEachRemaining(entry -> {
                String value = entry.getValue().asText();
                // Decrypt if it's a sensitive field
                if (entry.getKey().equals("email") || entry.getKey().equals("mobile") || entry.getKey().equals("password")) {
                    String decrypted = EncryptionUtils.decrypt(value);
                    if (decrypted == null) {
                        throw new RuntimeException("Failed to decrypt signup field '" + entry.getKey() + "' for key: " + dataKey);
                    }
                    value = decrypted;
                }
                signupData.put(entry.getKey(), value);
            });

            System.out.println("✓ Signup data loaded for: " + dataKey);
            return signupData;
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching signup data for key: " + dataKey + ". Cause: " + e.getMessage(), e);
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
            System.out.println("Error fetching JSON value: " + e.getMessage());
        }
        return null;
    }

}
