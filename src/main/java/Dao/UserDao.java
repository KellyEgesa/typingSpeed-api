package Dao;

import models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByEmail(String email);
    void saveUser(User user);
    void clearAllUsers();
    void setTypingProficiency(int id, String proficiency);
    void wordsPerMinute(int id, int wordsPerMinute);

}
