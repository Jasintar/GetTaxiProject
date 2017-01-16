package ru.innopolis.uni.course3.taxiapp.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.OrderDAOException;
import ru.innopolis.uni.course3.taxiapp.DBConnector;
import ru.innopolis.uni.course3.taxiapp.POJO.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.uni.course3.taxiapp.DAO.Constants.*;

/**
 * Created on 16.01.2017.
 *
 * @authot Julia Savicheva
 */
@Repository
public class OrderDAOHibernate implements OrderDAO{
    private static final Logger LOG = LoggerFactory.getLogger(OrderDAOJdbc.class);

    public OrderDAOHibernate() {
    }

    @Override
    public Order insertOrder(Order order) throws OrderDAOException {
//        try(PreparedStatement statement = DBConnector.getInstance().getPreparedStatement(INSERT_ORDER_QUERY)){
//            statement.setString(1, order.getStart());
//            statement.setString(2, order.getFinish());
//            statement.setLong(3, order.getClientId());
//            int result = statement.executeUpdate();
//            LOG.info("{} record added in table Order! All Right!", result);
//        } catch (SQLException e) {
//            LOG.warn("cannot add order. ".concat(e.getMessage()));
//            throw new OrderDAOException(e.getMessage());
//        }
        return order;
    }

    @Override
    public void updateOrderStatus(long orderId, long driverId, String newStatus) throws OrderDAOException {
//        String query = String.format(UPDATE_ORDER_STATUS, newStatus, driverId, orderId);
//        Statement statement = DBConnector.getInstance().getStatement();
//        try {
//            int result = statement.executeUpdate(query);
//            LOG.info("{} record updated in table ORDER ! All Right!", result);
//        } catch (SQLException e) {
//            LOG.warn(e.getMessage());
//            throw new OrderDAOException(e.getMessage());
//        }
    }

    @Override
    public List<Order> getOrdersByStatus(String status) throws OrderDAOException {
        List<Order> orderList = new ArrayList<Order>();
//        Order order;
//
//        String query = String.format(SELECT_ORDERS_BY_STATUS, status);
//        try {
//            Statement statement = DBConnector.getInstance().getStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                order = new Order(resultSet.getLong("id_order"), resultSet.getString("start"),
//                        resultSet.getString("finish"), resultSet.getString("status"), resultSet.getLong("id_client"));
//                orderList.add(order);
//            }
//        } catch (SQLException e) {
//            LOG.warn(e.getMessage());
//            throw new OrderDAOException();
//        }
        return orderList;
    }

    @Override
    public Order getCurrentClientOrder(long clientId) throws OrderDAOException {
        Order order = null;
//        String query = String.format(SELECT_CURRENT_ORDER_BY_CLIENT_ID, clientId);
//        try {
//            Statement statement = DBConnector.getInstance().getStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            if (resultSet.next()) {
//                order = new Order(resultSet.getLong("id_order"), resultSet.getString("start"),
//                        resultSet.getString("finish"), resultSet.getString("status"), resultSet.getLong("id_client"),
//                        resultSet.getLong("id_driver"));
//                LOG.info("Order found. From {} to {}.", order.getStart(), order.getFinish());
//            }
//        } catch (SQLException e) {
//            LOG.warn(e.getMessage());
//        }
        return order;
    }

    @Override
    public Order getCurrentDriverOrder(long driverId) throws OrderDAOException {
        Order order = null;
//        String query = String.format(SELECT_CURRENT_ORDER_BY_DRIVER_ID, driverId);
//        try {
//            Statement statement = DBConnector.getInstance().getStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            if (resultSet.next()) {
//                order = new Order(resultSet.getLong("id_order"), resultSet.getString("start"),
//                        resultSet.getString("finish"), resultSet.getString("status"), resultSet.getLong("id_client"),
//                        resultSet.getLong("id_driver"));
//                LOG.info("Order found. From {} to {}.", order.getStart(), order.getFinish());
//            }
//        } catch (SQLException e) {
//            LOG.warn(e.getMessage());
//        }
        return order;
    }
}
