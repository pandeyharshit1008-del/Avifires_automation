package com.automation.utils;

import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptorTest {

    private static final Logger logger = LoggerFactory.getLogger(EncryptorTest.class);

    @Test
    public void generateEncryption() {
        String mobilePlain = "9103916450";
        String passwordPlain = "Abcd@12345";

        String encryptedMobile = EncryptionUtils.encrypt(mobilePlain);
        String encryptedPassword = EncryptionUtils.encrypt(passwordPlain);

        logger.info("ENCRYPTED_MOBILE:" + encryptedMobile);
        logger.info("ENCRYPTED_PASSWORD:" + encryptedPassword);
    }
}
