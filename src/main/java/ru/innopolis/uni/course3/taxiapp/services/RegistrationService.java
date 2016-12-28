package ru.innopolis.uni.course3.taxiapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.course3.taxiapp.Car;
import ru.innopolis.uni.course3.taxiapp.DAO.CarDAO;
import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.CarDAOException;
import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.UserDAOException;
import ru.innopolis.uni.course3.taxiapp.DAO.UserDAO;
import ru.innopolis.uni.course3.taxiapp.Encryptor;
import ru.innopolis.uni.course3.taxiapp.User;

/**
 * Created on 25.12.2016.
 *
 * @authot Julia Savicheva
 */
public class RegistrationService {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationService.class);
    private static final UserDAO userDao = new UserDAO();
    private static final CarDAO carDao = new CarDAO();

    public static String registerUser(String username, String password, String firstName, String phone, String userType,
                                      String carBrand, String carNumber) {
        User user;
        Car car = null;
        if ("client".equals(userType)) {
            userType = "C";
        } else if ("driver".equals(userType)) {
            userType = "D";
            car = new Car(carBrand, carNumber);
        }
        password = Encryptor.hashPassword(password, Encryptor.SALT);
        user = new User(username, password, firstName, phone ,userType);
        String message;

        try {
            if (userDao.getUserByUsername(username) == null) {
                userDao.insertUser(user);
                message = "user added";
                if (car != null) {
                    car.setUserId(user.getId());
                    carDao.insertCar(car);
                }
            } else  {
                message = "user with this username already exist!";
            }
        } catch (UserDAOException e) {
            message = "Cannot add user";
            LOG.warn("UserDAOException. Cannot add user");
        } catch (CarDAOException e) {
            message = "Cannot add car";
            LOG.warn("CarDAOException. Cannot add car");
        }

        return message;
    }
}
