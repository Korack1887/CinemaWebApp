package server.dao;

import entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO {
    boolean addUser(User user);

    boolean checkUser(String email, String name);

    User getUserByEmail(String email);

    User unmapUser(ResultSet rs) throws SQLException;
}
