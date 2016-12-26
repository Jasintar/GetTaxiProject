package ru.innopolis.uni.course3.taxiapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.course3.taxiapp.DBConnector;
import ru.innopolis.uni.course3.taxiapp.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created on 25.12.2016.
 *
 * @authot Julia Savicheva
 */
public class AuthControllerServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AuthControllerServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.info(req.getParameter("username"));
        LOG.info(req.getParameter("password"));
        try {
            User user = DBConnector.getInstance().getUser(req.getParameter("username"), req.getParameter("password"));
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);
            if ("C".equals(user.getUsertype())) {
                resp.sendRedirect(String.format("%s%s", req.getContextPath(), "view/client/createOrder.jsp"));
            } else if ("D".equals(user.getUsertype())) {
                resp.sendRedirect(String.format("%s%s", req.getContextPath(), "view/client/createOrder.jsp"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
