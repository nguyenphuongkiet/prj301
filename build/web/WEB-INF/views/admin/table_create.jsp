<%-- 
    Document   : table_create
    Created on : Mar 17, 2023, 7:36:58 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<div class="container-fluid">
    <div class="row">
        <div class ="col">
            <form action="<c:url value="/admin/table_create_handler.do"/>">
                <div class="mb-3">
                    <label for="name" class="form-label">Name:</label>
                    <input type="text" class="form-control" id="name" placeholder="Product name" name="name" value="${name}" required>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Price:</label>
                    <input type="number" class="form-control" step="0.1" id="price" placeholder="Product price" name="price" value="${price}" required>
                </div>
                <div class="mb-3">
                    <label for="discount" class="form-label">Discount</label>
                    <input type="number" class="form-control" id="discount" step = "0.1" placeholder="Product discount" name="discount" required value="${discount}">
                </div>
                <div class="mb-3">
                    <label for="img" class="form-label">Image</label>
                    <input type="text" class="form-control" id="img" placeholder="Product image" name="img" required value="${img}">
                </div>
                <div class="mb-3">
                    <label for="desc" class="form-label">Description</label>
                    <input type="text" class="form-control" id="desc" placeholder="Product description" required name="description" value="${description}">
                </div>
                <button type="submit" class="btn btn-outline-success" name = "op" value = "create"><i class="bi bi-check2-circle"></i> Create</button>
                <button type="submit" class="btn btn-outline-danger" name = "op" value = "cancel"><i class="bi bi-x-lg"></i> Cancel</button>
            </form>
        </div>
        <div class ="col">
            <img class = "product-pic" src="<c:url value="${product.img}"/>" alt=""/>
        </div>
    </div>
</div>
<p style="color:red">${msg}</p>
