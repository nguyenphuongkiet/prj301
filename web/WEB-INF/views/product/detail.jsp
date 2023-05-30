<%-- 
    Document   : detail
    Created on : Feb 24, 2023, 2:38:50 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container-fluid page-header mb-5 wow fadeIn" data-wow-delay="0.1s">
    <div class="container">
        <h1 class="display-3 mb-3 animated slideInDown">Product Detail</h1>
        <!--                <nav aria-label="breadcrumb animated slideInDown">
                            <ol class="breadcrumb mb-0">
                                <li class="breadcrumb-item"><a class="text-body" href="#">Home</a></li>
                                <li class="breadcrumb-item"><a class="text-body" href="#">Pages</a></li>
                                <li class="breadcrumb-item text-dark active" class="text-body">Products</li>
                                <li class="breadcrumb-item text-dark active">View Detail</li>            
                            </ol>
                        </nav>-->
    </div>
</div>

<div class="row">
    <div class="col-5" >
        <img style="float:right; width: 288px; height: 298px" src="${product.img}" >
    </div>
    <div class="col-7">
        <div class="p-4">
            <h3>${product.name}</h3>
            <p class="mt-5"><span class="text-bold">Old Price </span>$${product.price}</p>  
            <p class="mt-5"><span class="text-bold">New Price: </span>$${product.getPrice() - (product.getPrice() * product.getDiscount())}</p>
            <p class="mt-5"><span class="text-bold">Description: </span>${product.description}</p>
        </div>
        <div class="p-2 mr-2" style="display: flex;">
            <div class="col-6" text-center>
                <a href="<c:url value="/cart/addToCart.do?id=${product.id}"/>" role="button" class="btn btn-md btn-dark"><i class="bi bi-basket"></i> Add to cart</a><br/>
                <a href="<c:url value="/cart/addFromCart.do?id=${product.id}"/>" class="btn btn-md btn-success mt-3" role="button"><i class="bi bi-currency-dollar"> Buy now</a>
            </div>
        </div>
    </div>
</div>
<section style="background-color: #f7f6f6;">
    <div class="container my-5 py-5 text-dark">
        <div class="row d-flex justify-content-center">
            <div class="col-md-12 col-lg-10 col-xl-8">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h4 class="text-dark mb-0">Comments (<span>${noCmts}</span>)</h4>                   
                </div>
                <form action="<c:url value="/product/addComment.do"/>" id = "cmtForm">
                    <input type="number" hidden name="userId" value="${user.id}">
                    <input type="text" hidden name="username" value="${user.name}">
                    <input type="number" hidden name="productId" value="${product.id}">
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="d-flex flex-start">
                                <div class="w-100">
                                    <div class="d-flex justify-content-between align-items-center mb-3">                                    
                                        <textarea rows="4" class="w-100" name="content" form="cmtForm" placeholder="Share your thoughts about the product"></textarea>
                                    </div>
                                    ${message}
                                    <div class="d-flex justify-content-between align-items-center">
                                        <p class="small mb-0" style="color: #aaa;">
                                            <button type="submit" class="btn btn-outline-success">Post</button>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> 
                    
                </form>
                
                <hr>
                <c:forEach var= "cmt" items="${comments}">
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="d-flex flex-start">
                                <img class="rounded-circle shadow-1-strong me-3"
                                     src="<c:url value="/img/user.png"/>" alt="avatar" width="40"
                                     height="40" />
                                <div class="w-100">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h6 class="text-primary fw-bold mb-0">
                                            ${cmt.username}
                                            <span class="text-dark ms-2">${cmt.content}</span>
                                        </h6>
                                    </div>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <p class="small mb-0" style="color: #aaa;">
                                            <a href="#!" class="link-grey">${cmt.date}</a>
                                        </p>
                                        <div class="d-flex flex-row">
                                            <i class="fas fa-star text-warning me-2"></i>
                                            <i class="far fa-check-circle" style="color: greenyellow;"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> 
                </c:forEach>                                
            </div>
        </div>
    </div>
</section>               
