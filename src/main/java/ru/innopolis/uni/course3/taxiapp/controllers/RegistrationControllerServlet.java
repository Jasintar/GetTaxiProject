package ru.innopolis.uni.course3.taxiapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.course3.taxiapp.DBConnector;
import ru.innopolis.uni.course3.taxiapp.services.RegistrationService;

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
public class RegistrationControllerServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationControllerServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String message = RegistrationService.registerUser(req.getParameter("username"), req.getParameter("password"),
                req.getParameter("firstName"), req.getParameter("phone"), req.getParameter("userType"),
                req.getParameter("carBrand"), req.getParameter("carNumber"));
        req.getSession().setAttribute("message", message);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/"));
    }
}
