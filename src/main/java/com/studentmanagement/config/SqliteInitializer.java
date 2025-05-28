package com.studentmanagement.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class SqliteInitializer {

    private final JdbcTemplate sqliteJdbcTemplate;

    public SqliteInitializer(@Qualifier("sqliteJdbcTemplate") JdbcTemplate sqliteJdbcTemplate) {
        this.sqliteJdbcTemplate = sqliteJdbcTemplate;
    }

    @PostConstruct
    public void init() {
        try {
            sqliteJdbcTemplate.execute(
                    "CREATE TABLE IF NOT EXISTS keys (" +
                            "name TEXT NOT NULL PRIMARY KEY, " +
                            "publicKey BLOB NOT NULL, " +
                            "privateKey BLOB NOT NULL)");
            System.out.println("Table 'keys' created or already exists in SQLite.");
        } catch (Exception e) {
            System.err.println("Error creating table 'keys': " + e.getMessage());
        }
    }
}