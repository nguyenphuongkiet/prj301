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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ProductFacade {

    public List<Product> select() throws SQLException {
        List<Product> list = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        Statement stm = con.createStatement();
        //Thực thi lệnh SELECT
        ResultSet rs = stm.executeQuery("select * from product");
        list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setDescription(rs.getString("description"));
            product.setImg(rs.getString("img"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDiscount(rs.getDouble("discount"));
            list.add(product);
        }
        con.close();
        return list;
    }

    public int getAllProduct() throws SQLException {
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        Statement stm = con.createStatement();
        //Thực thi lệnh tính sl
        ResultSet rs = stm.executeQuery("select count(*) from product");
        while (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public List<Product> pagingAccount(int index) throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        //Thực thi lệnh tính sl
        PreparedStatement stm = con.prepareStatement("select * from product order by id offset ? rows fetch next 8 rows only");
        stm.setInt(1, ((index - 1) * 8));
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setDescription(rs.getString("description"));
            product.setImg(rs.getString("img"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDiscount(rs.getDouble("discount"));
            list.add(product);
        }
        return list;
    }

    public List<Product> getListProduct(String search, int index) throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        PreparedStatement stm = con.prepareStatement("select id, description ,img, [name], price, discount from Product where name like ? order by name offset ? rows fetch next 8 rows only");
        stm.setString(1, "%" + search + "%");
        stm.setInt(2, ((index - 1) * 8));
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String description = rs.getString("description");
            String img = rs.getString("img");
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            double discount = rs.getDouble("discount");
            list.add(new Product(id, description ,img, name, price, discount));
        }
        con.close();
        return list;
    }

    public Product getOne(int id) throws SQLException {
        Product product = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng PreparedStatement
        PreparedStatement stm = con.prepareStatement("select * from product where id = ?");
        stm.setInt(1, id);
        //Thực thi lệnh sql
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            product = new Product();
            product.setId(rs.getInt("id"));
            product.setDescription(rs.getString("description"));
            product.setImg(rs.getString("img"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDiscount(rs.getDouble("discount"));
        }
        con.close();
        return product;
    }

    public List<Product> topProduct() throws SQLException {
        List<Product> list = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        Statement stm = con.createStatement();
        //Thực thi lệnh SELECT
        ResultSet rs = stm.executeQuery("SELECT TOP 4 *\n"
                + "FROM Product\n"
                + "ORDER BY id;");
        list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setDescription(rs.getString("description"));
            product.setImg(rs.getString("img"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDiscount(rs.getDouble("discount"));
            list.add(product);
        }
        con.close();
        return list;
    }

    public List<Product> sortByPriceAscending(int index) throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        //Thực thi lệnh tính sl
        PreparedStatement stm = con.prepareStatement("select id, [name], [description], img, price, discount, price-price*discount as actualPrice from Product order by actualPrice asc offset ? rows fetch next 8 rows only");
        stm.setInt(1, ((index - 1) * 8));
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setDescription(rs.getString("description"));
            product.setImg(rs.getString("img"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDiscount(rs.getDouble("discount"));
            list.add(product);
        }
        return list;
    }

    public List<Product> sortByPriceDescending(int index) throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        //Thực thi lệnh tính sl
        PreparedStatement stm = con.prepareStatement("select id, [name], [description], img, price, discount, price-price*discount as actualPrice from Product order by actualPrice desc offset ? rows fetch next 8 rows only");
        stm.setInt(1, ((index - 1) * 8));
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setDescription(rs.getString("description"));
            product.setImg(rs.getString("img"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDiscount(rs.getDouble("discount"));
            list.add(product);
        }
        return list;
    }

    public List<Product> sortByNameAscending(int index) throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        //Thực thi lệnh tính sl
        PreparedStatement stm = con.prepareStatement("select * from product order by name asc offset ? rows fetch next 8 rows only");
        stm.setInt(1, ((index - 1) * 8));
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setDescription(rs.getString("description"));
            product.setImg(rs.getString("img"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDiscount(rs.getDouble("discount"));
            list.add(product);
        }
        return list;
    }

    public List<Product> sortByNameDescending(int index) throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        //Thực thi lệnh tính sl
        PreparedStatement stm = con.prepareStatement("select * from product order by name desc offset ? rows fetch next 8 rows only");
        stm.setInt(1, ((index - 1) * 8));
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setDescription(rs.getString("description"));
            product.setImg(rs.getString("img"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDiscount(rs.getDouble("discount"));
            list.add(product);
        }
        return list;
    }

    public Product getProductById(int ID) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM Product WHERE id = ?";

        Product product = new Product();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            pre = con.prepareStatement(SQL);
            // code go la phai nam duoi pre = con.pre
            pre.setString(1, "" + ID);
            rs = pre.executeQuery();
            while (rs.next()) {
                // output tu database
                product.setId(rs.getInt("id"));
                product.setDescription(rs.getString("description"));
                product.setImg(rs.getString("img"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDiscount(rs.getDouble("discount"));
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (rs != null) {
                rs.close();
            }

        }
        return product;
    }
    public void update(Product p) throws SQLException, ClassNotFoundException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng preparedStatement
        PreparedStatement stm = con.prepareStatement("update product set name = ?, price = ?, discount = ?, img = ?, description = ? where ID = ?");
        stm.setString(1, p.getName());
        stm.setDouble(2, p.getPrice());
        stm.setDouble(3, p.getDiscount());
        stm.setString(4, p.getImg());
        stm.setString(5, p.getDescription());
        stm.setInt(6, p.getId());

        //Thực thi lệnh SELECT
        int count = stm.executeUpdate();
        //Load dữ liệu vào đối tượng toy nếu có 

        // Đóng kết nối
        con.close();
    }
    public void add(Product p) throws SQLException, ClassNotFoundException {
        Connection con = DBContext.getConnection();
        //Tạo đối tượng preparedStatement
        PreparedStatement stm = con.prepareStatement("insert into product values(?,?,?,?,?)");
        stm.setString(3, p.getName());
        stm.setDouble(4, p.getPrice());
        stm.setDouble(5, p.getDiscount());
        stm.setString(2, p.getImg());
        stm.setString(1, p.getDescription());
        

        //Thực thi lệnh SELECT
        int count = stm.executeUpdate();
        //Load dữ liệu vào đối tượng toy nếu có 

        // Đóng kết nối
        con.close();
    }
    
    public void delete(int id) throws SQLException {
        Connection con = DBContext.getConnection();
        PreparedStatement stm = con.prepareStatement("delete from product where id = ?");
        stm.setInt(1, id);
        int count = stm.executeUpdate();
        con.close();
    }
    public String getTop5ProductJsArray() throws SQLException, ClassNotFoundException {
        String rsult = "";
        List<Product> list = new ArrayList<Product>();
        String SQL = "select p.id, p.name, t.quantity from Product as p join (select top 5 productId, SUM(quantity) as quantity from OrderDetail group by productId order by quantity desc) as t on p.id=t.productId";
        Connection con = DBContext.getConnection();
        PreparedStatement stm = con.prepareStatement(SQL);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            list.add(getProductById(id));
        }

        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                rsult += "['" + list.get(i).getName() + "',";
            } else if (i == list.size() - 1) {
                rsult += "'" + list.get(i).getName() + "']";
            } else {
                rsult += "'" + list.get(i).getName() + "',";
            }
        }
        return rsult;
    }

    public String getQuantityTop5ProductJsArray() throws SQLException, ClassNotFoundException {
        String rsult = "";
        List<Integer> list = new ArrayList<Integer>();
        String SQL = "select p.id, p.name, t.quantity from Product as p join (select top 5 productId, SUM(quantity) as quantity from OrderDetail group by productId order by quantity desc) as t on p.id=t.productId";
        Connection con = DBContext.getConnection();
        PreparedStatement stm = con.prepareStatement(SQL);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int quantity = rs.getInt("quantity");
            list.add(quantity);
        }

        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                rsult += "[" + list.get(i) + ",";
            } else if (i == list.size() - 1) {
                rsult += +list.get(i) + "]";
            } else {
                rsult += +list.get(i) + ",";
            }
        }
        return rsult;
    }

}
