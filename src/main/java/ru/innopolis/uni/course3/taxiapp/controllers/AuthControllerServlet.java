package ru.innopolis.uni.course3.taxiapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.UserDAOException;
import ru.innopolis.uni.course3.taxiapp.DBConnector;
import ru.innopolis.uni.course3.taxiapp.User;
import ru.innopolis.uni.course3.taxiapp.services.AuthService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
        try {
            User user = AuthService.getUserByCredentials(req.getParameter("username"), req.getParameter("password"));
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);

            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/order"));

        } catch (UserDAOException e) {
            String message = AuthService.getMessage();
            req.getSession().setAttribute("message", message);
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/"));
//            e.printStackTrace();
        }
    }
}
