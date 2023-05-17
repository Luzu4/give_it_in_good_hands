<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="header.jsp"/>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Donations</h1>
    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Donations</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Institution</th>
                        <th>Category</th>
                        <th>Taken</th>
                        <th>Taken Date</th>
                        <th>Quantity</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>Id</th>
                        <th>Institution</th>
                        <th>Category</th>
                        <th>Taken</th>
                        <th>Taken Date</th>
                        <th>Quantity</th>
                        <th>Actions</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <c:forEach items="${donationList}" var="donation">
                        <tr>
                            <td>${donation.id}</td>
                            <td>${donation.institution.name}</td>
                            <td>
                                <c:forEach items="${donation.categories}" var="category">
                                    ${category.name}
                                </c:forEach>
                            </td>
                            <td>
                                <c:if test="${donation.taken}" >
                                    Yes
                                </c:if>
                                <c:if test="${!donation.taken}" >
                                    No
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${donation.takenDate != null}">
                                    ${donation.takenDate}
                                </c:if>
                            </td>

                            <td>${donation.quantity}</td>
                            <td>
                                <a href="/donations/${donation.id}" class="btn btn-warning btn-circle">
                                    <i class="fas fa-warning">Show</i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <a href="/admin/donations/create" class="btn btn-success btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-check"></i>
                                        </span>
                                <span class="text">Add New</span>
                            </a>

                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<!-- Footer -->
<footer class="sticky-footer bg-white">
    <div class="container my-auto">
        <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2020</span>
        </div>
    </div>
</footer>
<!-- End of Footer -->

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="<c:url value="/resources/admin/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value="/resources/admin/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value="/resources/admin/js/sb-admin-2.min.js"/>"></script>

<!-- Page level plugins -->
<script src="<c:url value="/resources/admin/vendor/datatables/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="/resources/admin/vendor/datatables/dataTables.bootstrap4.min.js"/>"></script>

<!-- Page level custom scripts -->
<script src="<c:url value="/resources/admin/js/demo/datatables-demo.js"/>"></script>

</body>

</html>