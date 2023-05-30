<%-- 
    Document   : index.jsp
    Created on : Feb 21, 2023, 4:02:01 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="alert alert-success">${msg}</div>
        </c:if>
        <div class="container-fluid" style="margin-top:10px; margin-bottom: 10px">
            <div class="" style="margin-top:10px; margin-bottom: 10px">
                <div class="rounded d-flex justify-content-center">
                    <div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light" style="width: 50%">
                        <div class="text-center">
                            <h3 class="text-primary">Sign Up</h3>
                        </div>
                        <form action="<c:url value="/auth/signup_handler.do" />">
                            <div class="p-4">
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary"><i
                                            class="bi bi-person-plus-fill text-white"></i></span>
                                    <input type="text" class="form-control" name="username" placeholder="Enter Username" required="" value="${username}">
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary"><i
                                            class="bi bi-key-fill text-white"></i></span>
                                    <input type="text" class="form-control" name="address" placeholder="Enter Address" required="" value="${address}">
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary"><i
                                            class="bi bi-person-plus-fill text-white"></i></span>
                                    <input type="text" class="form-control" name="phone" placeholder="Enter Phone Number" required="" value="${phone}">
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary"><i
                                            class="bi bi-person-plus-fill text-white"></i></span>
                                    <input type="text" class="form-control" name="email" placeholder="Enter Email" required="" value="${email}">
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary"><i
                                            class="bi bi-person-plus-fill text-white"></i></span>
                                    <input type="password" class="form-control" name="password" placeholder="Enter Password" required="" value="${password}">
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary"><i
                                            class="bi bi-key-fill text-white"></i></span>
                                    <input type="password" class="form-control" name="repass" placeholder="Enter Confirm Password" required="" value="${repass}">
                                </div>
                                <button class="btn btn-primary mt-2 signup" name="op" value="submit_signup" type="submit" >
                                    Sign Up
                                </button>
                                <p style="color:red">${message}</p>
                                <p class="text-center mt-5">Do you have an account?
                                    <a href="<c:url value="/auth/login.do"/>" style="text-decoration: none">Log In</a>
                                </p>
                                <!--<p class="text-center text-primary">Forgot your password?</p>-->
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
