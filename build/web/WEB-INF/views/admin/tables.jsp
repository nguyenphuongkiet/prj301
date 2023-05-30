<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Tables</h1>
    <p class="mb-4">This table is for creating, reading, updating, deleting a product.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">DataTables E  xample</h6>
            <a href="<c:url value="/admin/table_create.do"/>">Create</a>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Discount</th>
                            <th>Operation</th>
                        </tr>
                    </thead>
                    <c:forEach var="product" items="${list}" varStatus="loop">
                        <tbody>
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>${product.price}</td>
                                <td>${product.discount}</td>
                                <td>
                                    <a href="<c:url value="/admin/table_update.do?id=${product.id}" />">Update</a> |
                                    <a href="<c:url value="/admin//table_delete.do?id=${product.id}"/>" id="delete" data-toggle="modal" data-target="#deleteModal">
                                        Delete
                                    </a>                         
                                </td>
                            </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->
<!--Paging-->
<nav aria-label="Page navigation example" class="col-lg-12" style="display: flex; justify-content: center">
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${endP}">
            <li class="page-item"><a class="page-link" href="<c:url value="/admin/tables.do?page=${i}" />">${i}</a></li>
            </c:forEach>
    </ul>
</nav>
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Do you want to delete this product?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Delete" below if you are ready to delete this product.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a id="deleteLink" class="btn btn-primary" href="<c:url value="/admin//table_delete.do?id=${product.id}"/>"/>Delete</a>
            </div>
        </div>
    </div>
</div>

<!--Paging End-->

<!-- End of Main Content -->

