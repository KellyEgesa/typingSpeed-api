package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User setupUser(){
        return new User();
    }

    @Test
    public void userInstantiatesCorrectly() {
        User user = setupUser();
        assertTrue(user instanceof User);
    }
}