package ru.innopolis.uni.course3.taxiapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.uni.course3.taxiapp.DAO.Constants.*;

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

//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            LOG.error("Can't load jdbc driver", e);
//            throw new RuntimeException(e);
//        }
//    }

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

//    public static final String INSERT_USER_QUERY = "INSERT INTO \"user\" (USERNAME, PASSWORD, FIRST_NAME, PHONE, USER_TYPE) VALUES (?,?,?,?,?)";
//    public static final String INSERT_CAR_QUERY = "INSERT INTO CAR (BREND, NUMBER, ID_USER) VALUES (?,?,?)";
//    public static final String INSERT_ORDER_QUERY = "INSERT INTO \"order\" (START, FINISH, ID_CLIENT) VALUES (?,?,?)";
//    public static final String UPDATE_ORDER_STATUS = "UPDATE \"order\" SET STATUS = '%s', ID_DRIVER = '%s' WHERE ID_ORDER = '%s'";
//    public static final String SELECT_QUERY = "SELECT * FROM STUDENT;";
//    public static final String SELECT_ORDERS_BY_STATUS = "SELECT * FROM \"order\" WHERE STATUS = '%s';";
//    public static final String SELECT_USER = "SELECT * FROM \"user\" WHERE USERNAME = '%s' AND PASSWORD = '%s';";

    public void updateOrderStatus(String orderId, String driverId, String newStatus) throws SQLException {
        String query = String.format(UPDATE_ORDER_STATUS, newStatus, driverId, orderId);
        int result = statement.executeUpdate(query);
    }
    // TODO удалить метод
    public User getUser(String username, String password) throws SQLException {
        User user;

        String query = String.format(SELECT_USER, username, password);
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet == null) {
            LOG.info("Incorrect username or password");
            throw new SQLException("Auth failed");
        }
        resultSet.next();
        LOG.info("Correct credentials. Found user ({})", resultSet.getString("username"));
        user = new User(resultSet.getString("username"), resultSet.getString("first_name"), resultSet.getString("phone"), resultSet.getString("user_type"), resultSet.getLong("id_user"));

        return user;
    }

    public List<Order> getNewOrders() throws SQLException {
        List<Order> orders = new ArrayList<Order>();
        Order order;

        String query = String.format(SELECT_ORDERS_BY_STATUS, "new");
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()) {
            order = new Order(resultSet.getLong("id_order"), resultSet.getString("start"),
                    resultSet.getString("finish"), resultSet.getString("status"), resultSet.getLong("id_client"));
            orders.add(order);
        }

        return orders;
    }

    public void insertOrder(String start, String finish, long clientId) throws SQLException {
        try(PreparedStatement statement = conn.prepareStatement(INSERT_ORDER_QUERY)){
            statement.setString(1, start);
            statement.setString(2, finish);
            statement.setLong(3, clientId);
            int result = statement.executeUpdate();
            LOG.info("{} record added! All Right!", result);
        }
    }
}
