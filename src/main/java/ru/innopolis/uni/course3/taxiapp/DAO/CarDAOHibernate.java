package ru.innopolis.uni.course3.taxiapp.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.CarDAOException;
import ru.innopolis.uni.course3.taxiapp.POJO.Car;

/**
 * Created on 16.01.2017.
 *
 * @authot Julia Savicheva
 */

@Repository("carDAOHibernate")
public class CarDAOHibernate implements CarDAO {
    private static final Logger LOG = LoggerFactory.getLogger(CarDAOJdbc.class);

    public CarDAOHibernate() {
    }

    public Car insertCar(Car car) throws CarDAOException {
//        try(PreparedStatement statement = DBConnector.getInstance().getPreparedStatement(INSERT_CAR_QUERY)) {
//            int result;
//            statement.setString(1, car.getBrand());
//            statement.setString(2, car.getNumber());
//            statement.setLong(3, car.getUserId());
//
//            result = statement.executeUpdate();
//            ResultSet resSet = statement.getGeneratedKeys();
//            long carId = -1;
//            if (resSet.next()) {
//                carId = resSet.getLong("id_user");
//                car.setId(carId);
//            }
//            LOG.info("{} record added in table CAR with id_car = {}! All Right!", result, carId);
//        } catch (SQLException e) {
//            LOG.warn("cannot add CarEntity");
//            LOG.warn(e.getMessage());
//            throw new CarDAOException("cannot add CarEntity to database");
//        }
        return car;
    }

}
