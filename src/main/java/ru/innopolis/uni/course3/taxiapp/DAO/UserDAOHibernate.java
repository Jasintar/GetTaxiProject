package ru.innopolis.uni.course3.taxiapp.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;
import ru.innopolis.uni.course3.taxiapp.DAO.DAOExceptions.UserDAOException;
import ru.innopolis.uni.course3.taxiapp.POJO.User;

import static ru.innopolis.uni.course3.taxiapp.DAO.Constants.*;

/**
 * Created on 16.01.2017.
 *
 * @authot Julia Savicheva
 */

public class UserDAOHibernate implements UserDAO {
    private static final Logger LOG = LoggerFactory.getLogger(UserDAOHibernate.class);

    public UserDAOHibernate() {
    }

    @Override
    public User getUserByCredentials(String login, String password) throws UserDAOException {
        User user = null;
//        Statement statement = DBConnector.getInstance().getStatement();
//
//        String query = String.format(SELECT_USER, login, password);
//        try {
//            ResultSet resultSet = statement.executeQuery(query);
//            if (resultSet == null) {
//                LOG.info("Incorrect username or password");
//                throw new SQLException("Auth failed");
//            }
//            resultSet.next();
//            LOG.info("Correct credentials. Found user ({})", resultSet.getString("username"));
//            user = new User(resultSet.getString("username"), resultSet.getString("first_name"), resultSet.getString("phone"), resultSet.getString("user_type"), resultSet.getLong("id_user"));
//        } catch (SQLException e) {
//            LOG.warn("Cannot find user - ".concat(login));
//            throw new UserDAOException("Incorrect username or password");
//        }
        return user;
    }

    @Override
    public User getUserByUsername(String login) throws UserDAOException {
        User user = null;
//        Statement statement = DBConnector.getInstance().getStatement();
//        ResultSet resultSet;
//        String query = String.format(SELECT_USER_BY_USERNAME, login);
//        try {
//            resultSet = statement.executeQuery(query);
//            if (resultSet.next()) {
//                user = new User(resultSet.getString("username"), resultSet.getString("first_name"), resultSet.getString("phone"), resultSet.getString("user_type"), resultSet.getLong("id_user"));
//            }
//        } catch (SQLException e) {
//            LOG.warn("cannoot resolve query: ".concat(query));
//            throw new UserDAOException("cannoot resolve query: ".concat(query));
//        }
        return user;
    }

    @Override
    public User insertUser(User user) throws UserDAOException {
//        try(PreparedStatement statement = DBConnector.getInstance().getPreparedStatement(INSERT_USER_QUERY)) {
//            int result;
//            statement.setString(1, user.getUsername());
//            statement.setString(2, user.getPassword());
//            statement.setString(3, user.getFirstname());
//            statement.setString(4, user.getPhone());
//            statement.setString(5, user.getUsertype());
//
//            result = statement.executeUpdate();
//            ResultSet resSet = statement.getGeneratedKeys();
//            long user_id = -1;
//            if (resSet.next()) {
//                user_id = resSet.getLong("id_user");
//                user.setId(user_id);
//            }
//            LOG.info("{} record added with id_user = {}! All Right!", result, user_id);
//        } catch (SQLException e) {
//            LOG.warn("cannot add user");
//            LOG.warn(e.getMessage());
//        }
        return user;
    }
}
