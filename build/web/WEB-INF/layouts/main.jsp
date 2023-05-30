<%-- 
    Document   : main
    Created on : Feb 2, 2023, 12:51:25 PM
    Author     : PHT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

        <title>Fruit Store</title>

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500&family=Lora:wght@600;700&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="<c:url value="/lib/animate/animate.min.css" />" rel="stylesheet">
        <link href="<c:url value="/lib/owlcarousel/assets/owl.carousel.min.css" />" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="<c:url value="/css/style.css" />" rel="stylesheet">



    </head>
    <body>
        <!--header-->
        <!-- Navbar Start -->
        <div class="container-fluid px-0 wow fadeIn" data-wow-delay="0.1s">
            <div class="top-bar row gx-0 align-items-center d-none d-lg-flex">
                <div class="col-lg-6 px-5 text-start">
                    <small><i class="fa fa-map-marker-alt me-2"></i>FPT University, District 9, TPHCM</small>
                    <small class="ms-4"><i class="fa fa-envelope me-2"></i>group7@gmail.com</small> 
                </div>
                <div class="col-lg-6 px-5 text-end">
                    <small>Follow us:</small>
                    <a class="text-body ms-3" href=""><i class="fab fa-facebook-f"></i></a>
                    <a class="text-body ms-3" href=""><i class="fab fa-twitter"></i></a>
                    <a class="text-body ms-3" href="https://www.youtube.com/shorts/dBXRhG1YX9g"><i class="fab fa-youtube"></i></a>
                    <a class="text-body ms-3" href=""><i class="fab fa-instagram"></i></a>
                </div>
            </div>

            <nav class="navbar navbar-expand-lg navbar-light py-lg-0 px-lg-5 wow fadeIn" data-wow-delay="0.1s">
                <a href="<c:url value="/" />" class="navbar-brand ms-4 ms-lg-0">
                    <h1 class="fw-bold text-primary m-0">Gr<span class="text-secondary">ou</span>p7</h1>
                </a>
                <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto p-4 p-lg-0">
                        <a href="<c:url value="/" />" class="nav-item nav-link active">Home</a>
                        <a href="<c:url value="/home/aboutus.do" />" class="nav-item nav-link">About Us</a>
                        <a href="<c:url value="/product/product.do" />" class="nav-item nav-link">Products</a>
                        <!--                        <div class="nav-item dropdown">
                                                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                                                    <div class="dropdown-menu m-0">
                                                        <a href="blog.html" class="dropdown-item">Blog Grid</a>
                                                        <a href="feature.html" class="dropdown-item">Our Features</a>
                                                        <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                                                        <a href="404.html" class="dropdown-item">404 Page</a>
                                                    </div>
                                                </div>-->
                        <a href="<c:url value="/home/contact.do" />" class="nav-item nav-link">Contact Us</a>
                    </div>
                    <div class="d-none d-lg-flex ms-2">
                        <form action="<c:url value="/product/search.do"/>" method="post">
                            <input class="searchBox" type="text" name="search" size="15" required/>
                            <button type="submit" class="btn btn-outline-success" name="action" value="Search">Search</button>
                        </form>
                        <div class="nav-item dropdown">
                            <div class="dropdown-menu ms-auto">
                                <c:if test="${user == null}">
                                    <!-- Neu user chua login -->
                                    <a href="<c:url value="/auth/login.do" />" class="dropdown-item">Log In</a>
                                </c:if>
                                <c:if test="${user != null and user.role == 'ROLE_CUSTOMER'}">
                                    <!-- Neu user da login -->
                                    <a href="<c:url value="/auth/profile.do" />" class="dropdown-item">${user.name}</a>

                                    <a href="<c:url value="/auth/logout.do" />" class="dropdown-item">Log Out</a>
                                </c:if>
                                <c:if test="${user != null and user.role == 'ROLE_ADMIN'}">
                                    <!-- Neu user da login -->
                                    <a href="<c:url value="/auth/profile.do" />" class="dropdown-item">${user.name}</a>
                                    <a href="<c:url value="/admin/admin.do" />" class="dropdown-item">Dashboard</a>
                                    <a href="<c:url value="/auth/logout.do" />" class="dropdown-item">Log Out</a>
                                </c:if>    
                            </div>
                            <a class="btn-sm-square bg-white rounded-circle ms-3 nav-link" data-bs-toggle="dropdown" href="<c:url value="/" />">
                                <small class="fa fa-user text-body"></small>
                            </a>
                        </div>
                        <a style="width:50px; border-radius: 10px" class="btn-sm-square bg-white ms-3" href="/store/cart/cart.do">
                            <small class="fa fa-shopping-bag text-body" style="margin-right:5px"></small>
                            <span>${size}</span>
                        </a>
                    </div>
                </div>
            </nav>
        </div>
        <!-- Navbar End -->


        <!--view-->
        <div class="row">
            <div class="col">
                <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp" />
            </div>
        </div>

        <!--footer-->
        <div class="container-fluid bg-dark footer mt-5 pt-5 wow fadeIn" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="row g-5">
                    <div class="col-lg-3 col-md-6">
                        <h1 class="fw-bold text-primary mb-4">Gr<span class="text-secondary">ou</span>p7</h1>
                        <p>We bring you the best services, the freshest foods every day.</p>
                        <div class="d-flex pt-2">
                            <a class="btn btn-square btn-outline-light rounded-circle me-1" href=""><i class="fab fa-twitter"></i></a>
                            <a class="btn btn-square btn-outline-light rounded-circle me-1" href=""><i class="fab fa-facebook-f"></i></a>
                            <a class="btn btn-square btn-outline-light rounded-circle me-1" href="https://www.youtube.com/shorts/dBXRhG1YX9g"><i class="fab fa-youtube"></i></a>
                            <a class="btn btn-square btn-outline-light rounded-circle me-0" href=""><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h4 class="text-light mb-4">Address</h4>
                        <p><i class="fa fa-map-marker-alt me-3"></i>FPT University, District 9, TPHCM</p>
                        <p><i class="fa fa-phone-alt me-3"></i>+012-345-6789</p>
                        <p><i class="fa fa-envelope me-3"></i>group7@gmail.com</p>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h4 class="text-light mb-4">Quick Links</h4>
                        <a class="btn btn-link" href="<c:url value="/home/aboutus.do" />">About Us</a>
                        <a href="<c:url value="/product/product.do" />" class="btn btn-link">Products</a>
                        <a class="btn btn-link" href="<c:url value="/home/contact.do" />">Contact Us</a>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h4 class="text-light mb-4">Newsletter</h4>
                        <p>Enter your email to receive the fastest product information.</p>
                        <div class="position-relative mx-auto" style="max-width: 400px;">
                            <input class="form-control bg-transparent w-100 py-3 ps-4 pe-5" type="text" placeholder="Your email">
                            <button type="button" class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid copyright">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                            &copy; <a href="#">Group 7 Fruit Store</a>, All Right Reserved.
                        </div>
                        <div class="col-md-6 text-center text-md-end">
                            <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                            Designed By <a href="https://htmlcodex.com">HTML Codex</a>
                            <br>Distributed By: <a href="https://themewagon.com" target="_blank">ThemeWagon</a>
                        </div>
                    </div>
                </div>
            </div>           
        </div>

        <div class="modal fade" id="checkOutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Confirm" below if you are ready to purchase our products.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" href="<c:url value="/cart/checkOut.do" />">Confirm</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square rounded-circle back-to-top"><i class="bi bi-arrow-up"></i></a>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="<c:url value="/vendor/jquery/jquery.min.js"/>"></script>
        <script src="<c:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script> 

        <!-- Template Javascript -->
        <script src="js/main.js"></script>

    </body>
</html>
