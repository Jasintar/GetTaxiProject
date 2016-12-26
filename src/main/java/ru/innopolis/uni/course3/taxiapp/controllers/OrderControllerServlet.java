package ru.innopolis.uni.course3.taxiapp.controllers;

import ru.innopolis.uni.course3.taxiapp.DBConnector;
import ru.innopolis.uni.course3.taxiapp.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created on 25.12.2016.
 *
 * @authot Julia Savicheva
 */
public class OrderControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession(false).getAttribute("user");
            DBConnector.getInstance().insertOrder(req.getParameter("start"), req.getParameter("finish"), user.getId());
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "view/client/currentOrder.jsp"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
