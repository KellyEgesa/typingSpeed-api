package Dao;

import models.User;
import org.sql2o.Connection;

import java.util.List;

public class Sql2oUser implements UserDao {
    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT id, userName, email, wordsPerMinute, typingProficiency FROM users";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT id,userName, email, wordsPerMinute, typingProficiency FROM users WHERE id = :id";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = :email";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("email", email)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users (userName, email, password) VALUES (:userName, :email,:password)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("userName", user.getUserName())
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword())
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        }
    }

    @Override
    public void clearAllUsers() {
        String sql = "DELETE FROM users";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        }
    }

    @Override
    public void setTypingProficiency(int id, String proficiency) {
        String sql = "UPDATE users SET typingProficiency =:typingProficiency WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("typingProficiency", proficiency)
                    .executeUpdate();
        }
    }

    @Override
    public void wordsPerMinute(int id, int wordsPerMinute) {
        String sql = "UPDATE users SET wordsPerMinute =:wordsPerMinute WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("wordsPerMinute", wordsPerMinute)
                    .executeUpdate();
        }
    }
}
