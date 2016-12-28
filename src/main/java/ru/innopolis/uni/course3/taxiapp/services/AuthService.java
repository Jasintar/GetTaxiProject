package ru.innopolis.uni.course3.taxiapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.UserDAOException;
import ru.innopolis.uni.course3.taxiapp.DAO.UserDAO;
import ru.innopolis.uni.course3.taxiapp.Encryptor;
import ru.innopolis.uni.course3.taxiapp.Order;
import ru.innopolis.uni.course3.taxiapp.User;

/**
 * Created on 25.12.2016.
 *
 * @authot Julia Savicheva
 */
public class AuthService {
    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);
    private static final UserDAO userDao = new UserDAO();
    private static String message = null;

    public static User getUserByCredentials(String login, String password) throws UserDAOException {
        User user;
        password = Encryptor.hashPassword(password, Encryptor.SALT);
        try {
            user = userDao.getUserByCredentials(login, password);
        } catch (UserDAOException e) {
            LOG.warn("UserDAOException: ".concat(e.getMessage()));
            message = e.getMessage();
            throw new UserDAOException(e.getMessage());
        }
        return user;
    }

    public static String getPathToRedirrect(User user) {
        String path = "/";
        if (user == null) {
            path = "/";
        } else {
            Order order = OrderService.getCurrentUserOrder(user);
            switch (user.getUsertype()) {
                case "C" :
                    if (order != null) {
                        path = "view/client/currentOrder.jsp";
                    } else {
                        path = "view/client/createOrder.jsp";
                    }

                break;
                case "D":
                    if (order != null) {
                        path = "view/driver/currentOrder.jsp";
                    } else {
                        path = "view/driver/ordersList.jsp";
                    }
                break;
            }
        }

        return path;
    }
    public static String getMessage() {
        return message;
    }
}
