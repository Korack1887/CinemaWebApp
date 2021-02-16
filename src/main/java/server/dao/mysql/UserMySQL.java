package server.dao.mysql;
import entity.user.Gender;
import entity.user.User;
import server.dao.UserDAO;
import server.dao.mysql.queries.UserQueries;

import java.sql.*;
import java.util.List;


public class UserMySQL implements Connectable, UserDAO {


    private static UserMySQL dao;

    public static UserMySQL getInstance() {
        log.trace("Try get instance.");
        if (dao == null) {
            log.trace("Instance not found. Create new.");
            dao = new UserMySQL();
        }
        return dao;
    }


    public boolean addUser(User user){
        Connection con = getConnection();
        PreparedStatement st = null;
        try{
            st = con.prepareStatement(UserQueries.ADD_USER);
            st.setInt(1, user.getId());
            st.setString(2, user.getRole().toString());
            st.setString(3, user.getName());
            st.setString(4, user.getEmail());
            st.setString(5, user.getPassword());
            st.setDate(6, user.getBirthday());
            st.setString(7, user.getGender().toString());
            st.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return false;
    }

    public boolean checkUser(String email, String pass){
        Connection con = getConnection();
        CallableStatement st = null;
        ResultSet rs = null;
        try{
            st = con.prepareCall("select check_user(?, ?) as result");
            st.setString(1,email);
            st.setString(2, pass);
            rs = st.executeQuery();
            while (rs.next()){
                return rs.getBoolean("result");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return false;
    }

    public User getUserByEmail(String email){
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = con.prepareCall(UserQueries.GET_USER_BY_EMAIL);
            st.setString(1,email);
            rs = st.executeQuery();
            while (rs.next()){
                return unmapUser(rs);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return null;
    }
    public User unmapUser(ResultSet rs) throws SQLException {
        User result;
        result = new User(rs.getInt("id_user"), rs.getString("role")
    , rs.getString("gender"), rs.getString("name"), rs.getString("email")
    , rs.getString("password"), rs.getDate("birthday"));
    return result;
    }
}
