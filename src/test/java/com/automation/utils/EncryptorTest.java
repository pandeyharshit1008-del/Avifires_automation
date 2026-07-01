package com.automation.utils;

import org.testng.annotations.Test;

public class EncryptorTest {

    @Test
    public void generateEncryption() {
        String mobilePlain = "9103916450";
        String passwordPlain = "Abcd@12345";

        String encryptedMobile = EncryptionUtils.encrypt(mobilePlain);
        String encryptedPassword = EncryptionUtils.encrypt(passwordPlain);

        System.out.println("ENCRYPTED_MOBILE:" + encryptedMobile);
        System.out.println("ENCRYPTED_PASSWORD:" + encryptedPassword);
    }
}
