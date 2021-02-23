package server.dao.mysql;

import entity.user.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserMySQLTest {

    @Test
    void addUser() {
        User newUser = new User(13,"ADMIN", "FEMALE", "test", "asd","12412g", Date.valueOf("2010-01-01"));
        UserMySQL.getInstance().addUser(newUser);
        Assert.assertEquals(newUser,UserMySQL.getInstance().getUserByEmail(newUser.getEmail()));
    }

    @Test
    void checkUser() {
        Assert.assertTrue(UserMySQL.getInstance().checkUser("asd","12412g"));
        Assert.assertFalse(UserMySQL.getInstance().checkUser("asd","1asd"));
    }

}