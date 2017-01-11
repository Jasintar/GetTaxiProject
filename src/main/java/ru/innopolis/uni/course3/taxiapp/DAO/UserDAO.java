package ru.innopolis.uni.course3.taxiapp.DAO;

import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.UserDAOException;
import ru.innopolis.uni.course3.taxiapp.POJO.User;

/**
 * Created on 09.01.2017.
 *
 * @authot Julia Savicheva
 */
public interface UserDAO {
    public User getUserByCredentials(String login, String password) throws UserDAOException;

    public User getUserByUsername(String login) throws UserDAOException;

    public User insertUser(User user) throws UserDAOException;
}
