package ru.innopolis.uni.course3.taxiapp.DAO;

import ru.innopolis.uni.course3.taxiapp.POJO.Car;
import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.CarDAOException;

/**
 * Created on 09.01.2017.
 *
 * @authot Julia Savicheva
 */
public interface CarDAO {
    public Car insertCar(Car car) throws CarDAOException;
}
