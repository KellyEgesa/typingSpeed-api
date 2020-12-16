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

    @Test
    public void getUserNameGetsCorrectUserName() {
        User user = setupUser();
        assertEquals("Kelly Egesa", user.getUserName());
    }

    @Test
    public void getEmailGetsCorrectEmail() {
        User user = setupUser();
        assertEquals("kelly.egesa@gmail.com", user.getEmail());
    }

//    @Test
//    public void thePasswordIsHashed() {
//        User user = setupUser();
//        assertNotEquals("kelly@123", user.getPassword());
//    }

    @Test
    public void checkPasswordChecksIfThePasswordIsTheSame() {
        User user = setupUser();
        assertTrue(user.checkPassword("kelly@123"));
    }

    @Test
    public void setIdSetsIdCorrectly() {
        User user = setupUser();
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @Test
    public void typingProficiencySetsCorrectly() {
        User user = setupUser();
        user.setTypingProficiency("AVERAGE");
        assertEquals("AVERAGE", user.getTypingProficiency());
    }

    @Test
    public void setWordsPerMinuteSetsCorrectly() {
        User user = setupUser();
        user.setWordsPerMinute(57);
        assertEquals(57, user.getWordsPerMinute());
    }
}