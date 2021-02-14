package server.dao.mysql;

import entity.hall.Column;
import entity.hall.Hall;
import entity.hall.Seat;
import entity.session.Session;
import org.apache.log4j.Logger;
import server.dao.HallDAO;
import server.dao.mysql.queries.HallQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class HallMySQL implements HallDAO, Connectable {
    private static final Logger log = Logger.getLogger(HallMySQL.class);

    private static HallMySQL dao;

    public static HallMySQL getInstance() {
        log.trace("Try get instance.");
        if (dao == null) {
            log.trace("Instance not found. Create new.");
            dao = new HallMySQL();
        }
        return dao;
    }

    @Override
    public Hall getHall(int id) {
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement(HallQueries.GET_HALL);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()){
                return new Hall(rs.getInt("id_hall"),rs.getInt("seats_amount"), rs.getInt("column_amount"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return null;
    }

    public Column getColumn(int id){
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        Column result;
        try{
            st = con.prepareStatement(HallQueries.GET_COLUMN);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()){
                result = unmapColumn(rs);
                unmapSeatsForColumn(con,result);
                return result;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return null;
    }

    public List<Column> getColumns(){
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Column> result = new ArrayList<>();
        try{
            st = con.createStatement();
            rs = st.executeQuery(HallQueries.GET_ALL_COLUMN);
            while (rs.next()){
                result.add(unmapColumn(rs));
            }
            for (Column c: result
                 ) {
                unmapSeatsForColumn(con,c);
            }
            return result;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return null;
    }
    public Column unmapColumn(ResultSet rs){
        try {
           return new Column(rs.getInt("id_column"),
                    rs.getInt("price"),HallMySQL.getInstance().getHall(1),null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void unmapSeatsForColumn(Connection con,Column c) throws SQLException {
            PreparedStatement st;
            ResultSet rs;
            List<Seat> seats = new ArrayList<>();
            st = con.prepareStatement(HallQueries.GET_ALL_SEATS_FOR_COLUMN);
            st.setInt(1, c.getId());
            rs = st.executeQuery();
            while (rs.next()){
                seats.add(new Seat(rs.getInt("id_seat"),rs.getInt("price"),c));
            }
            c.setSeats(seats);
    }
    public List<Seat> getBookedSeats(Session session){
        Connection con = getConnection();
        CallableStatement st = null;
        ResultSet rs = null;
        ArrayList<Seat> result = new ArrayList<>();
        try {
            st = con.prepareCall(HallQueries.GET_BOOKED_SEATS);
            st.setInt(1,session.getId());
            rs = st.executeQuery();
            while (rs.next()){
                result.add(new Seat(rs.getInt("id_seat"),rs.getInt("price")
                        ,HallMySQL.getInstance().getColumn(rs.getInt("id_column"))));
            }
            return result;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
