/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.User;
import db.UserFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vsngh
 */
@WebServlet(name = "UserController", urlPatterns = {"/auth"})
public class UserController extends HttpServlet {

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
                case "login":
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "login_handler":
                    login_handler(request, response);
                    break;
                case "logout":
                    logout_handler(request, response);
                    break;
                case "signup":
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "signup_handler":
                    signup_handler(request, response);
                    break;
                case "profile":
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "edit":
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "edit_profile":
                    edit_profile(request, response);
                    break;
                case "change_pass":
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "changepass_handler":
                    changePass(request, response);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("controller", "error");
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }

    }

    protected void login_handler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String op = request.getParameter("op");
        switch (op) {
            case "submit_login":
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String rem = (String) request.getParameter("rem");
                Cookie cu = new Cookie("cEmail", email);
                Cookie cp = new Cookie("cPass", password);
                Cookie cr = new Cookie("cRem", rem);
                if (rem != null) {
                    cu.setMaxAge(60 * 60 * 24 * 7);
                    cp.setMaxAge(60 * 60 * 24 * 7);
                    cr.setMaxAge(60 * 60 * 24 * 7);
                } else {
                    cu.setMaxAge(0);
                    cp.setMaxAge(0);
                    cr.setMaxAge(0);
                }
                response.addCookie(cu);
                response.addCookie(cp);
                response.addCookie(cr);
                UserFacade uf = new UserFacade();
                User user = uf.Login(email, password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/home/index.do");
                } else {
                    request.setAttribute("message", "Incorrect email or password");
                    request.getRequestDispatcher("/auth/login.do").forward(request, response);
                }
                break;
            case "cancle":
                response.sendRedirect(request.getContextPath() + "/home/index.do");
        }
    }

    protected void logout_handler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //xoa session
        HttpSession session = request.getSession();
        session.invalidate();
        //quay ve home
        response.sendRedirect(request.getContextPath() + "/home/index.do");
    }

    protected void signup_handler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String op = request.getParameter("op");
        switch (op) {
            case "submit_signup":
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String username = request.getParameter("username");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                String repass = request.getParameter("repass");
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                request.setAttribute("username", username);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.setAttribute("repass", repass);
                if (!repass.equals(password)) {
                    request.setAttribute("message", "Passwords does not match");

                    request.getRequestDispatcher("/auth/signup.do").forward(request, response);
                } else {
                    UserFacade uf = new UserFacade();
                    User user = uf.Check(email);
                    if (user == null) {
                        uf.Signup(username, address, phone, email, password);
                        response.sendRedirect(request.getContextPath() + "/home/index.do");
                    } else {
                        request.setAttribute("message", "Email has already been signed up");
                        request.getRequestDispatcher("/auth/signup.do").forward(request, response);
                    }
                    break;
                }
            case "cancel":
                response.sendRedirect(request.getContextPath() + "/home/index.do");
                break;
        }
    }

    protected void edit_profile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String op = request.getParameter("op");
        switch (op) {
            case "comfirm":
                try {
                    // Đọc dữ liệu từ client gửi lên
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    String address = request.getParameter("address");
                    String phone = request.getParameter("phone");
                    if (id == null && name == null && address == null && phone == null) {
                        //Nếu nhập chưa đúng newpass và repass thì cho nhập lại       
                        //trả về câu lệnh báo lỗi vào request
                        request.setAttribute("message", "You must fill all boxes");
                        //quay ve home page
                        request.getRequestDispatcher("/auth/edit.do").forward(request, response);
                    } else {
                        // Cập nhật dữ liệu vào db
                        UserFacade uf = new UserFacade();
                        User user = uf.edit(name, address, phone, id);

                        // Lưu thông tin vào session
                        request.getSession().setAttribute("user", user);
                    }
                    // Hiển thị danh sách các mẫu tin của table user
                    response.sendRedirect(request.getContextPath() + "/auth/login.do");
                } catch (Exception ex) {
                    ex.printStackTrace(); // In ra chi tiết thông tin lỗi
                    throw new ServletException(ex); // Ném lại ngoại lệ để xử lý ở phía khác
                }
                break;
            case "cancel":
                //Hiển thị danh sách các mẫu tin của table toy
                response.sendRedirect(request.getContextPath() + "/auth/changepass.do");
                break;
        }
    }

    protected void changePass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String op = request.getParameter("op");
        switch (op) {
            case "comfirm":
                try {
                    // Đọc dữ liệu từ client gửi lên
                    String id = request.getParameter("id");
                    String oldpass = request.getParameter("password");
                    String pass = request.getParameter("newpass");
                    String repass = request.getParameter("repass");
                    request.setAttribute("newpass", pass);
                    request.setAttribute("password", oldpass);
                    request.setAttribute("repass", repass);
                    if (pass == null && repass == null) {
                        //Nếu nhập chưa đúng newpass và repass thì cho nhập lại       
                        //trả về câu lệnh báo lỗi vào request
                        request.setAttribute("message", "You must fill both 2 boxes");
                        //quay ve home page
                        request.getRequestDispatcher("/auth/change_pass.do").forward(request, response);
                    } else if (oldpass.equals(pass)) {
                        //trả về câu lệnh báo lỗi vào request
                        request.setAttribute("message", "New Password is same as Old Password");
                        //quay ve home page
                        request.getRequestDispatcher("/auth/change_pass.do").forward(request, response);
                    } else if (!pass.equals(repass)) {
                        //trả về câu lệnh báo lỗi vào request
                        request.setAttribute("message", "New Password and Re_Enter Password must be the same");
                        //quay ve home page
                        request.getRequestDispatcher("/auth/change_pass.do").forward(request, response);
                    } else {
                        UserFacade uf = new UserFacade();
                        // Cập nhật dữ liệu vào db
                        User user = uf.changePass(pass, id);
                        // Lưu thông tin vào session
                        request.getSession().setAttribute("user", user);
                        // Trả về login để user login lại
                        response.sendRedirect(request.getContextPath() + "/auth/login.do");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace(); // In ra chi tiết thông tin lỗi
                    throw new ServletException(ex); // Ném lại ngoại lệ để xử lý ở phía khác
                }
                break;
            case "cancel":
                //Hiển thị danh sách các mẫu tin của table toy
                response.sendRedirect(request.getContextPath() + "/auth/profile.do");
                break;
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
