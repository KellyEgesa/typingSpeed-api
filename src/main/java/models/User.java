package models;

public class User {
    private String userName;
    private String email;
    private int id;
    private String password;
    private  String typingProficiency;

    public User(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = new HashPassword().hash(password);
    }
}
