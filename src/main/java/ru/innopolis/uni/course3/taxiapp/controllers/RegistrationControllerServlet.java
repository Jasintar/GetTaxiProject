package ru.innopolis.uni.course3.taxiapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.course3.taxiapp.DBConnector;

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
        try {
            switch (req.getParameter("userType")) {
                case "client":
                    DBConnector.getInstance().insertClient(req.getParameter("username"), req.getParameter("password"),
                            req.getParameter("phone"), req.getParameter("firstName"));
                    resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/"));
                    break;
                case "driver":
                    LOG.info(req.getParameter("carNumber"));
                    DBConnector.getInstance().insertDriver(req.getParameter("username"), req.getParameter("password"),
                            req.getParameter("phone"), req.getParameter("firstName"), req.getParameter("carBrand"),
                            req.getParameter("carNumber"));
                    resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/"));
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
