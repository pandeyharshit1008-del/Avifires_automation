package com.automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Utility class for encrypting and decrypting sensitive data like credentials
 * Uses AES encryption algorithm
 */
public class EncryptionUtils {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtils.class);

    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String ENCRYPTION_KEY = ConfigConstants.ENCRYPTION_KEY;

    /**
     * Encrypt a plain text string
     *
     * @param plainText The text to encrypt
     * @return Encrypted text in Base64 format
     */
    public static String encrypt(String plainText) {
        try {
            // Create AES key
            SecretKeySpec key = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), 0, ENCRYPTION_KEY.getBytes().length, ENCRYPTION_ALGORITHM);

            // Create cipher
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            // Encrypt
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

            // Return Base64 encoded string
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            logger.error("Error during encryption", e);
            return null;
        }
    }

    /**
     * Decrypt an encrypted Base64 string
     *
     * @param encryptedText The encrypted text in Base64 format
     * @return Decrypted plain text
     */
    public static String decrypt(String encryptedText) {
        try {
            // Create AES key
            SecretKeySpec key = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), 0, ENCRYPTION_KEY.getBytes().length, ENCRYPTION_ALGORITHM);

            // Create cipher
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);

            // Decode Base64
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);

            // Decrypt
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);

            // Return decrypted string
            return new String(decryptedBytes);
        } catch (Exception e) {
            logger.error("Error during decryption", e);
            return null;
        }
    }

}
