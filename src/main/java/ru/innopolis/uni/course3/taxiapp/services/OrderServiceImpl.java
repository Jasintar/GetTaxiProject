package ru.innopolis.uni.course3.taxiapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.OrderDAOException;
import ru.innopolis.uni.course3.taxiapp.DAO.OrderDAO;
import ru.innopolis.uni.course3.taxiapp.DAO.UserDAO;
import ru.innopolis.uni.course3.taxiapp.POJO.Order;
import ru.innopolis.uni.course3.taxiapp.POJO.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 11.01.2017.
 *
 * @authot Julia Savicheva
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderDAO orderDao;
    private UserDAO userDao;

    private String message;

    @Autowired
    public OrderServiceImpl(@Qualifier("orderDAOHibernate") OrderDAO orderDAOImpl, @Qualifier("userDAOHibernate") UserDAO userDAOImpl) {
        this.orderDao = orderDAOImpl;
        this.userDao = userDAOImpl;
    }

    @Override
    public Order getCurrentUserOrder(User user) {
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
        }
        return order;
    }

    @Override
    public List<Order> getNewOrders() {
        List<Order> orderList = new ArrayList<>();
        try {
            orderList = orderDao.getOrdersByStatus("new");
        } catch (OrderDAOException e) {
            LOG.warn(e.getMessage());
        }
        return orderList;
    }

    @Override
    public Order insertOrder(Order order) {
        Order result = order;
        try {
            result = orderDao.insertOrder(order);
        } catch (OrderDAOException e) {
            LOG.warn(e.getMessage());
        }
        return result;
    }

    @Override
    public void updateOrderStatus(long orderId, long driverId, String status) {
        try {
            orderDao.updateOrderStatus(orderId, driverId, status);
        } catch (OrderDAOException e) {
            LOG.warn(e.getMessage());
        }
    }
}
