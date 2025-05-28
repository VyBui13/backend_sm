package com.studentmanagement.key.repository;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface KeyRepository {
    void insertKeyPair(String name, PublicKey publicKey, PrivateKey privateKey);

    PublicKey getPublicKey(String name);

    PrivateKey getPrivateKey(String name);
}
