package ru.innopolis.uni.course3.taxiapp.DAO;

import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.OrderDAOException;
import ru.innopolis.uni.course3.taxiapp.POJO.Order;

import java.util.List;

/**
 * Created on 09.01.2017.
 *
 * @authot Julia Savicheva
 */
public interface OrderDAO {
    public Order insertOrder(Order order) throws OrderDAOException;

    public void updateOrderStatus(long orderId, long driverId, String newStatus) throws OrderDAOException;

    public List<Order> getOrdersByStatus(String status) throws OrderDAOException;

    public Order getCurrentClientOrder(long clientId) throws OrderDAOException;

    public Order getCurrentDriverOrder(long driverId) throws OrderDAOException;
}
