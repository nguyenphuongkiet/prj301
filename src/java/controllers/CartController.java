/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.Cart;
import db.Item;
import db.OrderFacade;
import db.Product;
import db.ProductFacade;
import db.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
        try {
            switch (action) {
                case "cart":
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "addToCart":
                    addToCart(request, response);
                    break;
                case "addFromCart":
                    addFromCart(request, response);
                    break;
                case "subtractFromCart":
                    subtractFromCart(request, response);
                    break;
                case "removeFromCart":
                    removeFromCart(request, response);
                    break;
                case "checkOut":
                    check_out(request, response);
                    break;

            }
        } catch (SQLException | ClassNotFoundException ex) {

        }

    }

    protected void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String id = request.getParameter("id");
        ProductFacade pf = new ProductFacade();
        Product p = pf.getProductById(Integer.parseInt(id));
        Item item = new Item(p, 1, p.getPrice() - (p.getPrice()*p.getDiscount()));
        cart.addItem(item);
        List<Item> list = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        List<Product> list1 = pf.select();
        request.getRequestDispatcher("/product/product.do").forward(request, response);

    }

    protected void addFromCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        HttpSession session1 = request.getSession(true);
        Cart cart1 = null;
        Object o1 = session1.getAttribute("cart");
        if (o1 != null) {
            cart1 = (Cart) o1;
        } else {
            cart1 = new Cart();
        }
        String id1 = request.getParameter("id");
        ProductFacade pf1 = new ProductFacade();

        Product p1 = pf1.getProductById(Integer.parseInt(id1));
        Item i1 = new Item(p1, 1,  p1.getPrice() - (p1.getPrice()*p1.getDiscount()));
        cart1.addItem(i1);
        List<Item> list2 = cart1.getItems();
        request.setAttribute("controller", "cart");
        request.setAttribute("action", "cart");
        session1.setAttribute("cart", cart1);
        session1.setAttribute("size", list2.size());
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
    }

    protected void subtractFromCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session2 = request.getSession(true);
        Cart cart2 = null;
        Object o2 = session2.getAttribute("cart");
        if (o2 != null) {
            cart2 = (Cart) o2;
        } else {
            cart2 = new Cart();
        }
        String id2 = request.getParameter("id");

        if (cart2.getQuantityById(Integer.parseInt(id2)) <= 1) {
            cart2.removeItem(Integer.parseInt(id2));
        } else {
            ProductFacade pf2 = new ProductFacade();
            Product p2 = pf2.getProductById(Integer.parseInt(id2));
            Item i2 = new Item(p2, -1,  p2.getPrice() - (p2.getPrice()*p2.getDiscount()));
            cart2.addItem(i2);
        }
        List<Item> list3 = cart2.getItems();
        session2.setAttribute("cart", cart2);
        session2.setAttribute("size", list3.size());
        request.setAttribute("controller", "cart");
        request.setAttribute("action", "cart");
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);

    }

    protected void removeFromCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session3 = request.getSession(true);
        Cart cart3 = null;
        Object o3 = session3.getAttribute("cart");
        if (o3 != null) {
            cart3 = (Cart) o3;
        } else {
            cart3 = new Cart();
        }
        String id3 = request.getParameter("id");
        cart3.removeItem(Integer.parseInt(id3));
        List<Item> list4 = cart3.getItems();
        request.setAttribute("controller", "cart");
        request.setAttribute("action", "cart");
        session3.setAttribute("cart", cart3);
        session3.setAttribute("size", list4.size());
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
    }

    protected void check_out(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(true);
            Cart cart = null;
            Object o = session.getAttribute("cart");
            if (o != null) {
                cart = (Cart) o;
            } else {
                cart = new Cart();
            }
            User user = null;
            Object o1 = session.getAttribute("user");
            int size = (Integer) session.getAttribute("size");
            if (o1 != null && size != 0) {
                user = (User) o1;
                OrderFacade of = new OrderFacade();
                of.addOrder(user, cart);
                session.removeAttribute("cart");
                session.setAttribute("size", 0);
                request.getRequestDispatcher("/cart/cart.do").forward(request, response);

            } else if (o1 != null && size == 0) {
                request.setAttribute("noItem", "No product in your cart!");
                request.getRequestDispatcher("/cart/cart.do").forward(request, response);
            } else {
                request.setAttribute("message", "You need to log in to check out!");
                request.getRequestDispatcher("/auth/login.do").forward(request, response);
            }
        } catch (Exception ex) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
