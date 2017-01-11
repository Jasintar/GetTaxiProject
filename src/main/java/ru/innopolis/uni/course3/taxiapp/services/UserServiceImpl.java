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

//    @Override
//    public String registerUser(String username, String password, String firstName, String phone, String userType,
//                                      String carBrand, String carNumber) {
//        User user;
//        Car car = null;
//        if ("client".equals(userType)) {
//            userType = "C";
//        } else if ("driver".equals(userType)) {
//            userType = "D";
//            car = new Car(carBrand, carNumber);
//        }
//        password = Encryptor.hashPassword(password, Encryptor.SALT);
//        user = new User(username, password, firstName, phone ,userType);
//        String message;
//
//        try {
//            if (this.userDao.getUserByUsername(username) == null) {
//                userDao.insertUser(user);
//                message = "user added";
//                if (car != null) {
//                    car.setUserId(user.getId());
//                    carDao.insertCar(car);
//                }
//            } else  {
//                message = "user with this username already exist!";
//            }
//        } catch (UserDAOException e) {
//            message = "Cannot add user";
//            LOG.warn("UserDAOException. Cannot add user");
//        } catch (CarDAOException e) {
//            message = "Cannot add car";
//            LOG.warn("CarDAOException. Cannot add car");
//        }
//
//        return message;
//    }

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
}
