package ru.innopolis.uni.course3.taxiapp.services;

import ru.innopolis.uni.course3.taxiapp.POJO.Car;
import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.UserDAOException;
import ru.innopolis.uni.course3.taxiapp.POJO.User;

/**
 * Created on 09.01.2017.
 *
 * @authot Julia Savicheva
 */
public interface UserService {
    String getMessage();

//    public String registerUser(String username, String password, String firstName, String phone, String userType,
//                               String carBrand, String carNumber);

    void registerUser(User user, Car car);

    User getUserByCredentials(User credentials) throws UserDAOException;

    User getUserByUsername(String username);
}
