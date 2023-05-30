<%-- 
    Document   : aboutus
    Created on : Feb 6, 2023, 10:10:06 AM
    Author     : PHT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container-fluid page-header mb-5 wow fadeIn" data-wow-delay="0.1s">
    <div class="container">
        <h1 class="display-3 mb-3 animated slideInDown">About Us</h1>
    </div>
</div>
<!-- About Start -->
<div class="container-xxl py-5">
    <div class="container">
        <div class="row g-5 align-items-center">
            <div class="col-lg-6 wow fadeIn" data-wow-delay="0.1s">
                <div class="about-img position-relative overflow-hidden p-5 pe-0">
                    <img class="img-fluid w-100" src="<c:url value="/img/about.jpg" />">
                </div>
            </div>
            <div class="col-lg-6 wow fadeIn" data-wow-delay="0.5s">
                <h1 class="display-5 mb-4">Best Organic Fruits And Vegetables</h1>
                <p class="mb-4">Fruits and other agricultural products bring a lot of essential nutrients to our body. Our store always ensures the following 3 criteria:</p>
                <p><i class="fa fa-check text-primary me-3"></i>Quality assurance, origin</p>
                <p><i class="fa fa-check text-primary me-3"></i>Say no to goods of poor quality, expired and contain bad chemicals</p>
                <p><i class="fa fa-check text-primary me-3"></i>Fast delivery service, quality assurance</p>
            </div>
        </div>
    </div>
</div>
<!-- About End -->


<!-- Feature Start -->
<div class="container-fluid bg-light bg-icon py-6">
    <div class="container">
        <div class="section-header text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
            <h1 class="display-5 mb-3">Our Features</h1>
            <p>The features of our store, which you can trust.</p>
        </div>
        <div class="row g-4">
            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                <div class="bg-white text-center h-100 p-4 p-xl-5">
                    <img class="img-fluid mb-4" src="img/icon-1.png" alt="">
                    <h4 class="mb-3">Natural Process</h4>
                    <p class="mb-4">All fruits and agricultural products are grown 100% naturally in Vietnam.</p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                <div class="bg-white text-center h-100 p-4 p-xl-5">
                    <img class="img-fluid mb-4" src="img/icon-2.png" alt="">
                    <h4 class="mb-3">Organic Products</h4>
                    <p class="mb-4">Organically grown and harvested fruits and vegetables.</p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                <div class="bg-white text-center h-100 p-4 p-xl-5">
                    <img class="img-fluid mb-4" src="img/icon-3.png" alt="">
                    <h4 class="mb-3">Biologically Safe</h4>
                    <p class="mb-4">The fruits and agricultural products are cultivated and raised without the use of any chemical drugs.</p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Feature End -->