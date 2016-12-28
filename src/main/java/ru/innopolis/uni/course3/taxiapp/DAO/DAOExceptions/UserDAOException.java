package ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions;

/**
 * Created on 27.12.2016.
 *
 * @authot Julia Savicheva
 */
public class UserDAOException extends Exception {
    public UserDAOException() {
    }

    public UserDAOException(String message) {
        super(message);
    }
}
