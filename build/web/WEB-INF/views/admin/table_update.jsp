<%-- 
    Document   : talbe_edit_handler
    Created on : Mar 17, 2023, 7:20:02 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="container-fluid">
    <div class="row">
        <div class ="col">
            <form action="<c:url value="/admin/table_update_handler.do"/>">
                <div class="mb-3 mt-3">
                    <label for="id" class="form-label">ID:</label>
                    <input required disabled type="text" class="form-control" id="id" placeholder="Toy ID" name="id" value="${product.id}">
                    <input hidden type="text" class="form-control" id="id" placeholder="Toy ID" name="id" value="${product.id}">
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Name:</label>
                    <input required type="text" class="form-control" id="name" placeholder="Product name" name="name" value="${product.name}">
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Price:</label>
                    <input required type="number" class="form-control" step="0.1" id="price" placeholder="Product price" name="price" value="${product.price}">
                </div>
                <div class="mb-3">
                    <label for="discount" class="form-label">Discount</label>
                    <input required type="number"  step="0.1" class="form-control" id="discount" placeholder="Product discount" name="discount" value="${product.discount}">
                </div>
                <div class="mb-3">
                    <label for="img" class="form-label">Image</label>
                    <input required type="text" class="form-control" id="img" placeholder="Product image" name="img" value="${product.img}">
                </div>
                <div class="mb-3">
                    <label for="desc" class="form-label">Description</label>
                    <input required type="text" class="form-control" id="desc" placeholder="Product description" name="description" value="${product.description}">
                </div>
                <button type="submit" class="btn btn-outline-success" name = "op" value = "update"><i class="bi bi-check2-circle"></i> Update</button>
                <button type="submit" class="btn btn-outline-danger" name = "op" value = "cancel"><i class="bi bi-x-lg"></i> Cancel</button>
            </form>
        </div>
        <div class ="col text-center">
            <img  class = "product-pic" src="<c:url value="${product.img}"/>" alt=""/>
        </div>
    </div>
</div>
<p style="color:red">${msg}</p>
