/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.CommentFacade;
import db.OrderFacade;
import db.Product;
import db.ProductFacade;
import db.User;
import db.UserFacade;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
 * @author ASUS
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

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
        HttpSession session1 = request.getSession(true);
        User user = (User) session1.getAttribute("user");
        if (user != null && user.getRole().equalsIgnoreCase("role_admin")) {
            try {
                switch (action) {
                    case "admin":
                        admin(request, response);
                        break;
                    case "charts":
                        charts(request, response);
                        break;
                    case "tables":
                        tables(request, response);
                        break;
                    case "table_create":
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "table_create_handler":
                        table_create_handler(request, response);
                        break;
                    case "table_update":
                        table_update(request, response);
                        break;
                    case "table_update_handler":
                        table_update_handler(request, response);
                        break;
                    case "table_delete":
                        table_delete(request, response);
                        break;
                    case "logout":
                        logout(request, response);
                        break;
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
                request.setAttribute("message", ex.getMessage());
                request.setAttribute("controller", "error");
                request.setAttribute("action", "error");
                request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/auth/login.do");
        }
    }

    protected void admin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        OrderFacade of = new OrderFacade();
        UserFacade uf = new UserFacade();
        CommentFacade cf = new CommentFacade();
        request.setAttribute("totalUsers", uf.getTotalUser());
        request.setAttribute("totalC", cf.getTotalComments());
        request.setAttribute("monthlyRevenue", of.getThisMonthReveneue());
        request.setAttribute("yearRevenue", of.getThisYearReveneue());
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    protected void charts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        OrderFacade of = new OrderFacade();
        ProductFacade pf = new ProductFacade();
        HttpSession session = request.getSession();
        pf = new ProductFacade();
        UserFacade uf = new UserFacade();
        session.setAttribute("revenueJsMonthArray", of.getJsMonthArray(of.getValidatedMonth()));
        session.setAttribute("revenueJsTotalMoneyMonthlyArray", of.getJsTotalMoneyArray(of.getTotalMoneyInMonth()));
        session.setAttribute("top5ProductJsArray", pf.getTop5ProductJsArray());
        session.setAttribute("top5ProductQuantityJsArray", pf.getQuantityTop5ProductJsArray());
        session.setAttribute("topUsersJsArray", uf.getTopUserJsArray());
        session.setAttribute("topUsersTotalMoneyJsArray", uf.getTopUserTotalMoneyJsArray());
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    protected void tables(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        ProductFacade pf = new ProductFacade();
        pf = new ProductFacade();
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
        List<Product> list = pf.pagingAccount(page);
        request.setAttribute("list", list);
        request.setAttribute("endP", endPage);
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    protected void table_create_handler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        String ope = request.getParameter("op");
        ProductFacade pf = new ProductFacade();
        switch (ope) {
            case "create":
                try {
                    //Doc du lieu tu client gui len 
                    String url = "/admin/table_create.do";
                    String name = request.getParameter("name");
                    double price = Double.parseDouble(request.getParameter("price"));
                    double discount = Double.parseDouble(request.getParameter("discount"));
                    String img = request.getParameter("img");
                    String desc = request.getParameter("description");
                    if (discount > 1 || discount < 0) {
                        request.setAttribute("name", name);
                        request.setAttribute("price", price);
                        request.setAttribute("discount", discount);
                        request.setAttribute("img", img);
                        request.setAttribute("description", desc);
                        request.setAttribute("msg", "Discount value must be between 0 and 1");
                    } else if (name==null || price ==0 || discount==0 || img == null || desc == null) {
                    }else {
                        Product p2 = new Product();
                        p2.setName(name);
                        p2.setPrice(price);
                        p2.setDiscount(discount);
                        p2.setImg(img);
                        p2.setDescription(desc);
                        pf.add(p2);
                        url = "/admin/tables.do";
                    }
                    request.getRequestDispatcher(url).forward(request, response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                }
                break;
            case "cancel":
                response.sendRedirect(request.getContextPath() + "/admin/tables.do");
                break;
        }
    }

    protected void table_update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductFacade pf = new ProductFacade();
        Product p = pf.getOne(id);
        request.setAttribute("product", p);
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    protected void table_update_handler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        String op = request.getParameter("op");
        ProductFacade pf = new ProductFacade();
        switch (op) {
            case "update":
                //Doc du lieu tu client gui len 
                String url = "/admin/table_update.do";
                int id2 = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                double price = Double.parseDouble(request.getParameter("price"));
                double discount = Double.parseDouble(request.getParameter("discount"));
                String img = request.getParameter("img");
                String desc = request.getParameter("description");
                if (discount > 1 || discount < 0) {
                    Product p2 = new Product(id2, desc, img, name, price, discount);
                    request.setAttribute("product", p2);
                    request.setAttribute("msg", "Discount value must be between 0 and 1");
                } else {
                    Product p1 = new Product(id2, desc, img, name, price, discount);
                    pf.update(p1);
                    url="/admin/tables.do";
                }
                //Cap nhat du lieu vao db

                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "cancel":
                response.sendRedirect(request.getContextPath() + "/admin/tables.do");
                break;
        }
    }

    protected void table_delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        ProductFacade pf = new ProductFacade();
        int id1 = Integer.parseInt(request.getParameter("id"));
        pf.delete(id1);
        response.sendRedirect(request.getContextPath() + "/admin/tables.do");
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/home/index.do");
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
