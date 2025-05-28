package com.studentmanagement.key.repository.Impl;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.studentmanagement.key.repository.KeyRepository;
import com.studentmanagement.util.CryptoUtil;

@Repository
public class KeyRepositoryImpl implements KeyRepository {
    private final JdbcTemplate sqliteJdbcTemplate;
    private final CryptoUtil cryptoUtil;

    public KeyRepositoryImpl(
            @Qualifier("sqliteJdbcTemplate") JdbcTemplate sqliteJdbcTemplate,
            CryptoUtil cryptoUtil) {
        this.sqliteJdbcTemplate = sqliteJdbcTemplate;
        this.cryptoUtil = cryptoUtil;
    }

    @Override
    public void insertKeyPair(String name, PublicKey publicKey, PrivateKey privateKey) {
        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateKeyBytes = privateKey.getEncoded();

        String sql = "INSERT OR REPLACE INTO keys (name, publicKey, privateKey) VALUES (?, ?, ?)";
        sqliteJdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setBytes(2, publicKeyBytes);
            ps.setBytes(3, privateKeyBytes);
            return ps;
        });
    }

    @Override
    public PublicKey getPublicKey(String name) {
        String selectSQL = "SELECT publicKey FROM keys WHERE name = ?";

        try {
            return sqliteJdbcTemplate.queryForObject(selectSQL, (rs, rowNum) -> {
                byte[] keyBytes = rs.getBytes("publicKey");

                try {
                    KeyFactory keyFactory = KeyFactory.getInstance(cryptoUtil.getEncryptAlgorithm());
                    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
                    return keyFactory.generatePublic(keySpec);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to reconstruct PublicKey", e);
                }
            }, name);
        } catch (Exception e) {
            System.err.println("PublicKey '" + name + "' not found or error occurred: " + e.getMessage());
            return null;
        }
    }

    @Override
    public PrivateKey getPrivateKey(String name) {
        String selectSQL = "SELECT privateKey FROM keys WHERE name = ?";

        try {
            return sqliteJdbcTemplate.queryForObject(selectSQL, (rs, rowNum) -> {
                byte[] keyBytes = rs.getBytes("privateKey");

                try {
                    KeyFactory keyFactory = KeyFactory.getInstance(cryptoUtil.getEncryptAlgorithm());
                    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
                    return keyFactory.generatePrivate(keySpec);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to reconstruct PrivateKey", e);
                }
            }, name);
        } catch (Exception e) {
            System.err.println("PrivateKey '" + name + "' not found or error occurred: " + e.getMessage());
            return null;
        }
    }
}
