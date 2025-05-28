package com.studentmanagement.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Component;

@Component
public class CryptoUtil {
    private final String ENCRYPT_ALGORITHM = "RSA";
    private final String HASH_ALGORITHM = "SHA-1";
    private final KeyPairGenerator keyPairGenerator;
    private final Cipher encryptCipher;
    private final Cipher decryptCipher;
    private final MessageDigest messageDigest;

    public CryptoUtil() {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ENCRYPT_ALGORITHM);
            keyPairGenerator.initialize(2048);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Encryption algorithm not found: " + ENCRYPT_ALGORITHM, e);
        }

        try {
            encryptCipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
            decryptCipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Cipher initialization failed for algorithm: " + ENCRYPT_ALGORITHM, e);
        }

        try {
            messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found: " + HASH_ALGORITHM, e);
        }
    }

    public String getEncryptAlgorithm() {
        return ENCRYPT_ALGORITHM;
    }

    public String getHashAlgorithm() {
        return HASH_ALGORITHM;
    }

    public KeyPair generateKeyPair() {
        return keyPairGenerator.generateKeyPair();
    }

    public byte[] encrypt(String data, PublicKey publicKey) {
        try {
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return encryptCipher.doFinal(data.getBytes(StandardCharsets.UTF_16LE));
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid public key for encryption", e);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    public byte[] decrypt(byte[] encryptedData, PrivateKey privateKey) {
        try {
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
            return decryptCipher.doFinal(encryptedData);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid private key for decryption", e);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }

    public byte[] hash(String data) {
        try {
            return messageDigest.digest(data.getBytes(StandardCharsets.UTF_16LE));
        } catch (Exception e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }
}
