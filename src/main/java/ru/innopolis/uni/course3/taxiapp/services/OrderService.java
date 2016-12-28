package ru.innopolis.uni.course3.taxiapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.course3.taxiapp.DAO.CarDAO;
import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.OrderDAOException;
import ru.innopolis.uni.course3.taxiapp.DAO.OrderDAO;
import ru.innopolis.uni.course3.taxiapp.DAO.UserDAO;
import ru.innopolis.uni.course3.taxiapp.Order;
import ru.innopolis.uni.course3.taxiapp.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 28.12.2016.
 *
 * @authot Julia Savicheva
 */
public class OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);
    private static final UserDAO userDao = new UserDAO();
    private static final CarDAO carDao = new CarDAO();
    private static final OrderDAO orderDao = new OrderDAO();

    public static Order getCurrentUserOrder(User user) {
        Order order = null;
        long userId = user.getId();
        try {
            if ("C".equals(user.getUsertype())) {
                order = orderDao.getCurrentClientOrder(userId);
            }
            if ("D".equals(user.getUsertype())) {
                order = orderDao.getCurrentDriverOrder(userId);
            }
        } catch (OrderDAOException e) {
            LOG.warn(e.getMessage());
//            e.printStackTrace();
        }
        return order;
    }

    public static List<Order> getNewOrders() {
        List<Order> orderList = new ArrayList<>();
        try {
            orderList = orderDao.getOrdersByStatus("new");
        } catch (OrderDAOException e) {
            LOG.warn(e.getMessage());
        }
        return orderList;
    }

    /*
    public static Order getCurrentClientOrder(long clientId) {
        Order order = null;
        try {
            order = orderDao.getCurrentClientOrder(clientId);
        } catch (OrderDAOException e) {
            LOG.warn(e.getMessage());
            e.printStackTrace();
        }
        return order;
    }

    public static Order getCurrentDriverOrder(long driverId) {
        Order order = null;
        try {
            order = orderDao.getCurrentDriverOrder(driverId);
        } catch (OrderDAOException e) {
            LOG.warn(e.getMessage());
            e.printStackTrace();
        }
        return order;
    }
*/
}
