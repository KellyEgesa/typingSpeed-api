package models;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class User {
    private String userName;
    private String email;
    private int id;
    private String password;
    private  String typingProficiency;
    private int wordsPerMinute;

    public User(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Boolean checkPassword(String passwordToVerify){
        if(password.equals(passwordToVerify)){
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypingProficiency() {
        return typingProficiency;
    }

    public void setTypingProficiency(String typingProficiency) {
        this.typingProficiency = typingProficiency;
    }

    public int getWordsPerMinute() {
        return wordsPerMinute;
    }

    public void setWordsPerMinute(int wordsPerMinute) {
        this.wordsPerMinute = wordsPerMinute;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getWordsPerMinute() == user.getWordsPerMinute() &&
                Objects.equals(getUserName(), user.getUserName()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(password, user.password) &&
                Objects.equals(getTypingProficiency(), user.getTypingProficiency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getEmail(), getId(), password, getTypingProficiency(), getWordsPerMinute());
    }
}
