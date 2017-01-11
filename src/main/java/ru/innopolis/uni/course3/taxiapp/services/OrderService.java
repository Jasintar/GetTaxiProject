package ru.innopolis.uni.course3.taxiapp.services;

import ru.innopolis.uni.course3.taxiapp.POJO.Order;
import ru.innopolis.uni.course3.taxiapp.POJO.User;

import java.util.List;

/**
 * Created on 28.12.2016.
 *
 * @authot Julia Savicheva
 */
public interface OrderService {

    Order getCurrentUserOrder(User user);

    List<Order> getNewOrders();

    Order insertOrder(Order order);

    void updateOrderStatus(long orderId, long driverId, String status);
}
