package com.yohans.userbankinfo;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;


public class KeyGeneratorExample {
    public static void main(String[] args) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        System.out.println("Generated Key: " + Base64.getEncoder().encodeToString(secretKey.getEncoded()));
    }
}

