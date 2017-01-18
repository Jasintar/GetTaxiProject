package ru.innopolis.uni.course3.taxiapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.uni.course3.taxiapp.POJO.Car;
import ru.innopolis.uni.course3.taxiapp.DAO.CarDAO;
import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.CarDAOException;
import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.UserDAOException;
import ru.innopolis.uni.course3.taxiapp.DAO.UserDAO;
import ru.innopolis.uni.course3.taxiapp.Encryptor;
import ru.innopolis.uni.course3.taxiapp.POJO.User;

/**
 * Created on 09.01.2017.
 *
 * @authot Julia Savicheva
 */

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDAO userDao;
    private CarDAO carDao;

    private String message;

    @Autowired
    public UserServiceImpl(UserDAO userDAOImpl, CarDAO carDAOImpl) {
        this.userDao = userDAOImpl;
        this.carDao = carDAOImpl;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void registerUser(User user, Car car) {
        User newUser = new User(user.getUsername(), Encryptor.hashPassword(user.getPassword(), Encryptor.SALT),
                user.getFirstname(), user.getPhone(), user.getUsertype());
        Car newCar = null;

        switch (newUser.getUsertype()) {
            case "C" :
                break;
            case "D" :
                newCar = new Car(car.getBrand(), car.getNumber());
                break;
            default :
                break;
        }

        try {
            if (this.userDao.getUserByUsername(newUser.getUsername()) == null) {
                userDao.insertUser(newUser);
                message = "Success! User added";
                if (newCar != null) {
                    newCar.setUserId(newUser.getId());
                    carDao.insertCar(newCar);
                }
                LOG.info("User added: ".concat(newUser.getUsername()));
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
    }

    @Override
    public User getUserByCredentials(User credentials) throws UserDAOException {
        User user;
        credentials.setPassword(Encryptor.hashPassword(credentials.getPassword(), Encryptor.SALT));
        try {
            user = userDao.getUserByCredentials(credentials.getUsername(), credentials.getPassword());
        } catch (UserDAOException e) {
            LOG.warn("UserDAOException: ".concat(e.getMessage()));
            message = e.getMessage();
            throw new UserDAOException(e.getMessage());
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) throws UserDAOException {
        return userDao.getUserByUsername(username);
    }
}
