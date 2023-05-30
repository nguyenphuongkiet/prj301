/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Source;

/**
 *
 * @author vsngh
 */
public class UserFacade {

    Connection connect = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public User Login(String email, String password) throws SQLException {
        //select * from tbl User where email = ? and password=?
        String query = "select * from Account where email=? and password=?";
        try {
            connect = new DBContext().getConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
            }
        } catch (SQLException ex) {
            System.err.println("Error at Login UserFacade");
        }
        return null;
    }

    public int getTotalUser() throws SQLException {
        int result = 0;
        Connection con = DBContext.getConnection();
        String sql = "select count(id) as totalUsers from Account";
        PreparedStatement stm1 = con.prepareStatement(sql);
        ResultSet rs = stm1.executeQuery();
        if(rs.next()) {
            result += rs.getInt("totalUsers");
        }
        return result;
    }
    
    public User Check(String email) throws SQLException {
        //select * from tbl User where email = ? and password=?
        String query = "select * from Account where email=?";
        try {
            connect = new DBContext().getConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
            }
        } catch (SQLException ex) {
            System.err.println("Error at Check User Facade");
        }
        return null;
    }

    public User Signup(String username, String address, String phone, String email, String password) throws SQLException {
        //String query = "insert into account values(?,?,?,?,?,?,?)";
        String query = "insert into Account values (?, ?, ?, ?, ?, 'ROLE_CUSTOMER')";

        try {
            connect = new DBContext().getConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error at Signup UserFacade");
        }
        return null;
    }

    public String getTopUserJsArray() throws SQLException {
        String result = "";
        List<Integer> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String SQL = "select cId, SUM(totalMoney) as totalMoney from Orders group by cId order by totalMoney desc";
        PreparedStatement stm = con.prepareStatement(SQL);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            list.add(rs.getInt("cid"));
        }
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                result += "[" + list.get(i) + ",";
            } else if (i == list.size() - 1) {
                result += list.get(i) + "]";
            } else {
                result += list.get(i) + ",";
            }

        }
        return result;
    }
    
    public String getTopUserTotalMoneyJsArray() throws SQLException {
        String result = "";
        List<Double> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String SQL = "select cId, SUM(totalMoney) as totalMoney from Orders group by cId order by totalMoney desc";
        PreparedStatement stm = con.prepareStatement(SQL);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            list.add(rs.getDouble("totalMoney"));
        }
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                result += "[" + list.get(i) + ",";
            } else if (i == list.size() - 1) {
                result += list.get(i) + "]";
            } else {
                result += list.get(i) + ",";
            }

        }
        return result;
    }
    
    public User edit(String username, String address, String phone, String id) throws SQLException {
        //Tạo connection để kết nối vào DBMS
        connect = new DBContext().getConnection();
        //Tạo đối tượng PreparedStatement
        String query = "update Account set name = ?, address = ?, phone = ? where id = ?";
        try{
            ps = connect.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setString(4, id);
        //Thực thi lệnh sql
        ps.executeUpdate();    
        } catch (SQLException ex) {
            System.out.println("Error at edit profile UserFacade");
        }
        //Đóng kết nối
        connect.close();
        return null;
    }
    
    public User changePass(String pass, String id) throws SQLException {
        //Tạo connection để kết nối vào DBMS
        connect = new DBContext().getConnection();
        //Tạo đối tượng PreparedStatement
        String query = "update Account set password = ? where id = ?";
        try{
            ps = connect.prepareStatement(query);
            ps.setString(1, pass);
            ps.setString(2, id);
        //Thực thi lệnh sql
        ps.executeUpdate();    
        } catch (SQLException ex) {
            System.out.println("Error at ChangePass UserFacade");
        }
        //Đóng kết nối
        connect.close();
        return null;
    }
    public static void main(String[] args) throws SQLException {
        UserFacade uf = new UserFacade();
        System.out.println(uf.getTopUserJsArray());
    }
}
