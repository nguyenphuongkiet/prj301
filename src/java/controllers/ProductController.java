/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.Comment;
import db.CommentFacade;
import db.Product;
import db.ProductFacade;
import db.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "ProductController", urlPatterns = {"/product"})
public class ProductController extends HttpServlet {

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
                case "product":
                    product(request, response);
                    break;
                case "detail":
                    detail(request, response);
                    break;
                case "addComment":
                    addComment(request, response);
                    break;
                case "search":
                    search(request, response);
                    break;
                case "search_handler":
                    break;
                case "sort":
                    sort(request, response);
                    break;
                case "sort_handler":
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("controller", "error");
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }

    }

    protected void addComment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int productId = Integer.parseInt(request.getParameter("productId"));
        
        if (user == null) {
            request.setAttribute("message", "Log in to comment");
            request.getRequestDispatcher("/product/detail.do?id=" + productId).forward(request, response);
        } else {    
            int userId = Integer.parseInt(request.getParameter("userId")); 
            String content = (String) request.getParameter("content");
            String username = (String) request.getParameter("username");
            Date curDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            CommentFacade cf = new CommentFacade();
            Comment c = new Comment();
            c.setProductId(productId);
            c.setUserId(userId);
            c.setContent(content);
            c.setDate(curDate);
            c.setUsername(username);
            cf.addComment(c);
            response.sendRedirect(request.getContextPath() + "/product/detail.do?id=" + productId);
        }
        
    }

    protected void product(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        ProductFacade pf = new ProductFacade();
        String indexPage = request.getParameter("page");
        if (indexPage == null) {
            indexPage = "1";
        }
        int page = Integer.parseInt(indexPage);
        int count = pf.getAllProduct();
        int endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }
        List<Product> list = pf.pagingAccount(page);
        request.setAttribute("list", list);
        request.setAttribute("endP", endPage);
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
    }

    protected void detail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int id = Integer.parseInt(request.getParameter("id"));
        ProductFacade pf = new ProductFacade();
        Product product = pf.getOne(id);
        CommentFacade cf = new CommentFacade();
        List<Comment> listComments = cf.getCommentOfProduct(id);
        request.setAttribute("noCmts", listComments.size());
        request.setAttribute("comments", listComments);
        request.setAttribute("product", product);
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);

    }

    protected void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String search = request.getParameter("search");
        ProductFacade pf = new ProductFacade();
        String indexPage = request.getParameter("page");
        if (indexPage == null) {
            indexPage = "1";
        }
        int page = Integer.parseInt(indexPage);
            //Lay tong so luong san pham trong db
            int count = pf.getAllProduct();
            int endPage = count / 8;
            if (count % 8 != 0) {
                endPage++;
            }
        List<Product> list = pf.getListProduct(search, page);
        request.setAttribute("endP", endPage);
        request.setAttribute("list_result", list);
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
    }

    protected void sort(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sort = request.getParameter("sort");
        String sort_order = request.getParameter("sort_order");
        ProductFacade pf = new ProductFacade();
        String indexPage = request.getParameter("page");
        if (indexPage == null) {
            indexPage = "1";
        }
        int page = Integer.parseInt(indexPage);
        try {
            //Lay tong so luong san pham trong db
            int count = pf.getAllProduct();
            int endPage = count / 8;
            if (count % 8 != 0) {
                endPage++;
            }
            List<Product> list;
            switch (sort) {
                case "alphabet_asc":
                    list = pf.sortByNameAscending(page);
                    break;
                case "alphabet_desc":
                    list = pf.sortByNameDescending(page);
                    break;
                case "price_asc":
                    list = pf.sortByPriceAscending(page);
                    break;
                case "price_desc":
                    list = pf.sortByPriceDescending(page);
                    break;
                default:
                    list = pf.sortByNameAscending(page);
                    break;
            }
            request.setAttribute("list", list);
            request.setAttribute("sort", sort);
            request.setAttribute("endP", endPage);
            request.setAttribute("sort_order", sort_order);
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        } catch (SQLException ex) {
            // Hiện trang thông báo lỗi
            ex.printStackTrace();
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("controller", "error");
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
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
