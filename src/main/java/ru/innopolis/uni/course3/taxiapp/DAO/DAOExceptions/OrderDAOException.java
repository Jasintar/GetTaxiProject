package ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions;

/**
 * Created on 28.12.2016.
 *
 * @authot Julia Savicheva
 */
public class OrderDAOException extends Exception{
    public OrderDAOException() {
    }

    public OrderDAOException(String message) {
        super(message);
    }
}
