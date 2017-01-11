package ru.innopolis.uni.course3.taxiapp.DAO;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 05.01.2017.
 *
 * @authot Julia Savicheva
 */
public class UserDAOTest {
    private static Logger logger = LoggerFactory.getLogger(UserDAOTest.class);
    private static final UserDAOJdbc userDao = new UserDAOJdbc();

    @BeforeClass
    public void initialization() {
//        User user = new User("name", "pass", "Firstname", "+00000000000", "client");
//        try {
//            userDao.insertUser(user);
//        } catch (UserDAOException e) {
//            e.printStackTrace();
//        }
    }

    @AfterClass
    public void deleteTestData() {

    }

}
