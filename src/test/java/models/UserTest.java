package models;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class UserTest {

    private User setupUser(){
        return new User("Kelly Egesa", "kelly.egesa@gmail.com", "kelly@123");
    }

    @Test
    public void userInstantiatesCorrectly(){
        User user = setupUser();
        assertTrue(user instanceof User);
    }
}