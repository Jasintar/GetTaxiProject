package ru.innopolis.uni.course3.taxiapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.course3.taxiapp.DBConnector;
import ru.innopolis.uni.course3.taxiapp.Order;
import ru.innopolis.uni.course3.taxiapp.User;
import ru.innopolis.uni.course3.taxiapp.services.AuthService;
import ru.innopolis.uni.course3.taxiapp.services.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created on 25.12.2016.
 *
 * @authot Julia Savicheva
 */
public class OrderControllerServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(OrderControllerServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession(false).getAttribute("user");
        if ("C".equals(user.getUsertype())) {
            Order newOrder = new Order(req.getParameter("start"), req.getParameter("finish"), user.getId());
            OrderService.insertOrder(newOrder);
//            try {
//                DBConnector.getInstance().insertOrder(req.getParameter("start"), req.getParameter("finish"), user.getId());
//                resp.sendRedirect(String.format("%s%s", req.getContextPath(), "view/client/currentOrder.jsp"));
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        } else if ("D".equals(user.getUsertype())) {
            OrderService.updateOrderStatus(new Long(req.getParameter("orderId")), user.getId(), "progress");
//            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "order"));
        }
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "order"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession(false).getAttribute("user");
        String path = AuthService.getPathToRedirrect(user);

        Order order = OrderService.getCurrentUserOrder(user);
        req.setAttribute("order", order);
        LOG.info("order getted");
        List orderList = OrderService.getNewOrders();
        LOG.info("list of new orders getted");
        req.setAttribute("orders", orderList);

        RequestDispatcher dispatcher = req.getRequestDispatcher(String.format("%s%s", req.getContextPath(), path));
        dispatcher.forward(req, resp);
    }
}
