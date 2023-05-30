<%-- 
    Document   : index
    Created on : Feb 24, 2023, 5:53:16 PM
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
                            <h3 class="text-primary">Profile</h3>
                        </div>
                        <table>
                            <div class="p-4">
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary"><i class="bi bi-person-circle text-white"></i></span>
                                    <a style="display: flex; justify-content: flex-start; margin: auto 10px 10px">${user.name}</a>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary"><i class="bi bi-geo-alt-fill text-white"></i></span>
                                    <a style="display: flex; justify-content: flex-start; margin: auto 10px 10px">${user.address}</a>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary"><i class="bi bi-telephone-fill text-white"></i></span>
                                    <a style="display: flex; justify-content: flex-start; margin: auto 10px 10px">${user.phone}</a>
                                </div>                    
                                <a class="btn btn-primary mt-2 signup"  href="<c:url value ="/auth/edit.do"/>" >
                                    Edit
                                </a>
                                <a class="btn btn-primary mt-2 signup"  href="<c:url value ="/auth/change_pass.do"/>" >
                                    Change Password
                                </a>

                                <!--<p class="text-center text-primary">Forgot your password?</p>-->
                            </div>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>