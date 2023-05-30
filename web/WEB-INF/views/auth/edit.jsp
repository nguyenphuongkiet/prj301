<%-- 
    Document   : edprofile
    Created on : Mar 15, 2023, 1:43:12 AM
    Author     : vsngh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <h3 class="text-primary">Edit Profile</h3>
                        </div>
                        <form action="<c:url value="/auth/edit_profile.do" />" method="post">
                            <table>
                                <div class="input-group mb-3">
                                    
                                    
                                    <input type="hidden" class="form-control" name="id" id="id" placeholder="User id" value="${sessionScope.user.id}">
                                    <input type="hidden" class="form-control" name="email" id="email" placeholder="User Email" value="${sessionScope.user.email}">
                                    <input type="hidden" class="form-control" name="password" id="password" placeholder="User Password" value="${sessionScope.user.password}">
                                    
                                    <div class="input-group mb-3">
                                        <span class="input-group-text bg-primary"><i
                                                class="bi bi-person-plus-fill text-white"></i></span>
                                        <label for="name" class="form-label">Name: </label>
                                        <input type="text" class="form-control" id="name" placeholder="User name" name="name" value="${sessionScope.user.name}">
                                    </div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text bg-primary"><i
                                                class="bi bi-key-fill text-white"></i></span>
                                        <label for="address" class="form-label">Address: </label>
                                        <input type="text" class="form-control" id="name" placeholder="User address" name="address" value="${sessionScope.user.address}">
                                    </div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text bg-primary"><i
                                                class="bi bi-person-plus-fill text-white"></i></span>
                                        <label for="phone" class="form-label">Phone: </label>
                                        <input type="text" class="form-control" id="name" placeholder="User phone" name="phone" value="${sessionScope.user.phone}">
                                    </div>
                                    <button class="btn btn-primary mt-2 signup" name="op" value="comfirm" type="submit" >
                                        Comfirm
                                    </button>
                                    <button class="btn btn-primary mt-2 signup" name="op" value="cancel" type="submit" >
                                        Cancel
                                    </button>
                                    <a color="red">${message}</a>
                                    <!--<p class="text-center text-primary">Forgot your password?</p>-->
                                </div>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
