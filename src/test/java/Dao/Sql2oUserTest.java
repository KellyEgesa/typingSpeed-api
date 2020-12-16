package Dao;

import models.User;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oUserTest {

    private static Sql2oUser userDao;

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @BeforeClass
    public static void setUp(){
        userDao = new Sql2oUser();
    }

    private User setupUser(){
        return new User("Kelly Egesa", "kelly.egesa@gmail.com", "kelly@123");
    }

    @Test
    public void saveUserSetsIdCorrectly() {
        User user = setupUser();
        int originalId = user.getId();
        userDao.saveUser(user);
        assertNotEquals(originalId, user.getId());
    }

    @Test
    public void saveUserSavesTheUserInTheDb() {
        User user = setupUser();
        int originalId = user.getId();
        userDao.saveUser(user);
        assertTrue(user.equals(userDao.getAllUsers().get(0)));
    }

    @Test
    public void getAllUsersReturnsAllUsers_2() {
        User user = setupUser();
        User userSecond =new User("Brian Kamau", "brian.kamau@gmail.com", "Kamau@123");
        userDao.saveUser(user);
        userDao.saveUser(userSecond);
        assertEquals(2, userDao.getAllUsers().size());
    }

    @Test
    public void getUserByIdReturnsTheCorrectUser_True() {
        User user = setupUser();
        User userSecond =new User("Brian Kamau", "brian.kamau@gmail.com", "Kamau@123");
        userDao.saveUser(user);
        userDao.saveUser(userSecond);
        int originalId = user.getId();
        assertTrue(user.equals(userDao.getUserById(originalId)));
    }

    @Test
    public void getUserByEmailReturnsTheCorrectUser_True() {
        User user = setupUser();
        User userSecond =new User("Brian Kamau", "brian.kamau@gmail.com", "Kamau@123");
        userDao.saveUser(user);
        userDao.saveUser(userSecond);
        String email = user.getEmail();
        assertTrue(user.equals(userDao.getUserByEmail(email)));
    }

    @Test
    public void setTypingProficiencyAddsCorrectly() {
        User user = setupUser();
        userDao.saveUser(user);
        int id = user.getId();
        userDao.setTypingProficiency(id, "AVERAGE");
        assertEquals("AVERAGE", userDao.getUserById(id).getTypingProficiency());
    }

    @Test
    public void wordsPerMinute() {
        User user = setupUser();
        userDao.saveUser(user);
        int id = user.getId();
        userDao.wordsPerMinute(id, 92);
        assertEquals(92, userDao.getUserById(id).getWordsPerMinute());
    }

    @Test
    public void clearAllUsersRemovesAllUsers() {
        User user = setupUser();
        userDao.saveUser(user);
        userDao.clearAllUsers();
        assertEquals(0, userDao.getAllUsers().size());
    }


}