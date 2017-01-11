package ru.innopolis.uni.course3.taxiapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created on 22.12.2016.
 *
 * @authot Julia Savicheva
 */
public class DBConnector {
    private static final DBConnector INSTANCE = new DBConnector();
    private static final Logger LOG = LoggerFactory.getLogger(DBConnector.class);

    private Connection conn;
    private Statement statement;

    public Statement getStatement() {
        if (statement == null) {
            try {
                statement = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        PreparedStatement st;
        try {
            st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            LOG.warn("cannot resolve query: ".concat(query));
            throw new SQLException("cannot get PreparedStatement from query: ".concat(query));
        }
        return st;
    }

    public void closePreparedStatement(PreparedStatement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            LOG.warn("cannot close PreparedStatement");
        }
    }

    private DBConnector() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            LOG.error("Can't load jdbc driver", e);
            throw new RuntimeException(e);
        }
        try {
            conn = DriverManager.getConnection(DATABASE_URL, DATABASE_NAME, DATABASE_PASS);
            statement = conn.createStatement();
        } catch (SQLException e) {
            LOG.warn("Cannot connect to database");
        }
    }

    public static DBConnector getInstance() {
        return INSTANCE;
    }

    public static final String DATABASE_URL = "jdbc:postgresql://127.0.0.1:6000/taxiapp";
    public static final String DATABASE_NAME = "postgres";
    public static final String DATABASE_PASS = "123";
}
