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

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        return INSTANCE;
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            LOG.error("Can't load jdbc driver", e);
            throw new RuntimeException(e);
        }
    }

    public static final String DATABASE_URL = "jdbc:postgresql://127.0.0.1:6000/taxiapp";
    public static final String DATABASE_NAME = "postgres";
    public static final String DATABASE_PASS = "123";

    public static final String INSERT_USER_QUERY = "INSERT INTO \"user\" (USERNAME, PASSWORD, FIRST_NAME, PHONE, USER_TYPE) VALUES (?,?,?,?,?)";
    public static final String INSERT_CAR_QUERY = "INSERT INTO CAR (BREND, NUMBER, ID_USER) VALUES (?,?,?)";

    public void insertClient(String username, String password, String firstName, String phone) throws SQLException {
        try(Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_NAME, DATABASE_PASS);
//            Statement statement = conn.createStatement();
            PreparedStatement statement = conn.prepareStatement(INSERT_USER_QUERY)){
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, phone);
            statement.setString(5, "C");
            int result = statement.executeUpdate();
            LOG.info("{} record added! All Right!", result);
        }
    }

    public void insertDriver(String username, String password, String firstName, String phone, String brend, String number) throws SQLException {
        try(Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_NAME, DATABASE_PASS);
            PreparedStatement statement = conn.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)){
            int result;
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, phone);
            statement.setString(5, "D");

            result = statement.executeUpdate();
            ResultSet resSet = statement.getGeneratedKeys();
            long user_id = -1;
            if (resSet.next()) {
                user_id = resSet.getLong("id_user");
            }
//            long user_id = statement.getGeneratedKeys().getLong("id_user");
            LOG.info("{} record added with id_user = {}! All Right!", result, user_id);

            PreparedStatement statementCar = conn.prepareStatement(INSERT_CAR_QUERY);
            statementCar.setString(1, brend);
            statementCar.setString(2, number);
            statementCar.setLong(3, user_id);
            result = statementCar.executeUpdate();

            LOG.info("{} record added! All Right!", result);
        }
    }


}
