package ru.innopolis.uni.course3.taxiapp.DAO;

/**
 * Created on 27.12.2016.
 *
 * @authot Julia Savicheva
 */
public class Constants {
    public static final String INSERT_USER_QUERY = "INSERT INTO \"user\" (USERNAME, PASSWORD, FIRST_NAME, PHONE, USER_TYPE) VALUES (?,?,?,?,?)";
    public static final String INSERT_CAR_QUERY = "INSERT INTO CAR (BREND, NUMBER, ID_USER) VALUES (?,?,?)";

    public static final String INSERT_ORDER_QUERY = "INSERT INTO \"order\" (START, FINISH, ID_CLIENT) VALUES (?,?,?)";
    public static final String UPDATE_ORDER_STATUS = "UPDATE \"order\" SET STATUS = '%s', ID_DRIVER = '%s' WHERE ID_ORDER = '%s'";
    public static final String DELETE_ORDER_BY_ID = "DELETE \"order\" WHERE ID_ORDER = '%s'";

    public static final String SELECT_ORDERS_BY_STATUS = "SELECT * FROM \"order\" WHERE STATUS = '%s';";
    public static final String SELECT_ORDERS_BY_CLIENT_ID = "SELECT * FROM \"order\" WHERE ID_CLIENT = '%s';";
    public static final String SELECT_ORDERS_BY_DRIVER_ID = "SELECT * FROM \"order\" WHERE ID_DRIVER = '%s';";

    public static final String SELECT_CURRENT_ORDER_BY_CLIENT_ID = "SELECT * FROM \"order\" WHERE ID_CLIENT = '%s' AND STATUS != 'complete';";
    public static final String SELECT_CURRENT_ORDER_BY_DRIVER_ID = "SELECT * FROM \"order\" WHERE ID_DRIVER = '%s' AND STATUS = 'progress';";

    public static final String SELECT_USER = "SELECT * FROM \"user\" WHERE USERNAME = '%s' AND PASSWORD = '%s';";
    public static final String SELECT_USER_BY_USERNAME = "SELECT * FROM \"user\" WHERE USERNAME = '%s';";

}
