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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class CommentFacade {
    
    public List<Comment> getCommentOfProduct(int productId) throws SQLException {
        List<Comment> result = new ArrayList<>();
        String SQL = "select * from Comments where productId = ?";
        Connection con = DBContext.getConnection();
        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setInt(1, productId);
        ResultSet rs = stm.executeQuery();
        while(rs.next()) {
            Comment cmt = new Comment();
            cmt.setId(rs.getInt("id"));
            cmt.setContent(rs.getString("content"));
            cmt.setProductId(rs.getInt("productId"));
            cmt.setUserId(rs.getInt("userId"));
            cmt.setUsername(rs.getString("username"));
            cmt.setDate(rs.getDate("cmtDate"));
            result.add(cmt);
        }
        con.close();
        return result;
    }
    
    public void addComment(Comment c) throws SQLException {
        String SQL = "insert into Comments values(?, ?, ?, ?, ?)";
        Connection con = DBContext.getConnection();
        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setString(1, c.getContent());
        stm.setInt(2, c.getUserId());
        stm.setInt(3, c.getProductId());
        stm.setString(4, c.getUsername());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        stm.setString(5, sdf.format(c.getDate()));
        stm.executeUpdate();
        con.close();
    }
    
    public void deleteComment(int id) throws SQLException {
        String SQL = "delete from Comments where id = ?";
        Connection con = DBContext.getConnection();
        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setInt(1, id);
        stm.executeUpdate();
        con.close();
    }
    
    public int getTotalComments() throws SQLException {
        int result = 0;
        Connection con = DBContext.getConnection();
        String sql = "select count(id) as totalC from Comments";
        PreparedStatement stm1 = con.prepareStatement(sql);
        ResultSet rs = stm1.executeQuery();
        if(rs.next()) {
            result += rs.getInt("totalC");
        }
        return result;
    }
    
    public static void main(String[] args) throws SQLException {
        CommentFacade cf = new CommentFacade();
        System.out.println(cf.getTotalComments());
    }
}
