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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class OrderFacade {

    public void addOrder(User user, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            Connection con = DBContext.getConnection();

            String sql = "insert into Orders values(?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(2, date);
            stm.setInt(1, user.getId());
            stm.setDouble(3, cart.getTotalMoney());
            stm.executeUpdate();
            String sql1 = "select top 1 id from Orders order by id desc";
            PreparedStatement stm1 = con.prepareStatement(sql1);
            ResultSet rs = stm1.executeQuery();
            while (rs.next()) {
                int oid = rs.getInt("id");
                for (Item item : cart.getItems()) {
                    String sql2 = "insert into OrderDetail values(?, ?, ?, ?)";
                    PreparedStatement stm2 = con.prepareStatement(sql2);
                    stm2.setInt(1, oid);
                    stm2.setInt(2, item.getProduct().getId());
                    stm2.setInt(3, item.getQuantity());
                    stm2.setDouble(4, item.getPrice());
                    stm2.executeUpdate();
                }
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public double getThisMonthReveneue() throws SQLException {
        double result = 0;
        Connection con = DBContext.getConnection();
        String sql = "select totalMoney from Orders where MONTH(buyDate) = MONTH(GETDATE())";
        PreparedStatement stm1 = con.prepareStatement(sql);
        ResultSet rs = stm1.executeQuery();
        while(rs.next()) {
            result += rs.getDouble("totalMoney");
        }
        return result;
    }
    
    public double getThisYearReveneue() throws SQLException {
        double result = 0;
        Connection con = DBContext.getConnection();
        String sql = "select totalMoney from Orders where year(buyDate) = year(GETDATE())";
        PreparedStatement stm1 = con.prepareStatement(sql);
        ResultSet rs = stm1.executeQuery();
        while(rs.next()) {
            result += rs.getDouble("totalMoney");
        }
        return result;
    }
    
    public List<Integer> getValidatedMonth() {
        List<Boolean> m = new ArrayList<Boolean>();
        for (int i = 0; i < 12; i++) {
            m.add(false);
        }
        try {
            Connection con = DBContext.getConnection();
            String sql = "select * from Orders";
            PreparedStatement stm1 = con.prepareStatement(sql);
            ResultSet rs = stm1.executeQuery();
            while (rs.next()) {
                java.util.Date date = rs.getDate("buyDate");
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int month = cal.get(Calendar.MONTH);
                m.set(month, true);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < 12; i++) {
            if (m.get(i) == true) {
                result.add(i + 1);
            }
        }
        return result;
    }

    public String getJsMonthArray(List<Integer> l) {
        String result = "";

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        for (int i = 0; i < l.size(); i++) {
            if (i == 0) {
                result += "['" + months[l.get(i) - 1] + "',";
            } else if (i == l.size() - 1) {
                result += "'" + months[l.get(i) - 1] + "']";
            } else {
                result += "'" + months[l.get(i) - 1] + "',";
            }
        }
        return result;
    }

    public List<Double> getTotalMoneyInMonth() {
        List<Double> l = new ArrayList<Double>();
        for (int i = 0; i < 12; i++) {
            l.add(0.0);
        }
        try {
            Connection con = DBContext.getConnection();
            String sql = "select * from Orders";
            PreparedStatement stm1 = con.prepareStatement(sql);
            ResultSet rs = stm1.executeQuery();
            while (rs.next()) {
                java.util.Date date = rs.getDate("buyDate");
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int month = cal.get(Calendar.MONTH);
                l.set(month, l.get(month) + rs.getDouble("totalMoney"));
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        List<Double> result = new ArrayList<Double>();
        for (Double d : l) {
            if (d != 0.0) {
                result.add(d);
            }
        }
        return result;
    }

    public String getJsTotalMoneyArray(List<Double> l) {
        String result = "";

        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) != 0.0) {
                if (i == 0) {
                    result += "[" + l.get(i) + ",";
                } else if (i == l.size() - 1) {
                    result += l.get(i) + "]";
                } else {
                    result += l.get(i) + ",";
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws SQLException {
        OrderFacade of = new OrderFacade();
        System.out.println(of.getThisYearReveneue());
    }
}
