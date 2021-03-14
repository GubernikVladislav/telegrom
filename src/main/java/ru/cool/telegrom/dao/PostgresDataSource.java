package ru.cool.telegrom.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class PostgresDataSource {

    private final Logger LOGGER = LoggerFactory.getLogger(PostgresDataSource.class);

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "forcgt");
        } catch (SQLException throwables) {
            LOGGER.error("Ошибка подключения к БД");
        }
        return null;
    }
}
