package com.yohans.userbankinfo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class EncryptionConfig {

    private SecretKey secretKey;

    @Value("${encryption.key}")
    private String encryptionKey;

    // Method to load the encryption key after injection
    @PostConstruct
    public void init() {
        if (encryptionKey != null && !encryptionKey.isEmpty()) {
            System.out.println("Encryption Key Injected: " + encryptionKey);  // Debugging line
            byte[] decodedKey = Base64.getDecoder().decode(encryptionKey);
            secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        } else {
            throw new RuntimeException("Encryption key is not provided in application properties.");
        }
    }

    public String encrypt(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to encrypt cannot be null");
        }

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    public String decrypt(String encryptedData) {
        if (encryptedData == null) {
            throw new IllegalArgumentException("Encrypted data cannot be null");
        }

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }
}