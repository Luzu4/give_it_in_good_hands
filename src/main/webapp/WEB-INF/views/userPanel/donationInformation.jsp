<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="header.jsp"/>

<!-- Content Wrapper -->
<div id="content-wrapper" class="d-flex flex-column">

    <!-- Main Content -->
    <div id="content">

        <!-- Begin Page Content -->
        <div class="container-fluid">

            <!-- Page Heading -->
            <h1 class="h3 mb-4 text-gray-800">Donation Information</h1>

            <label>
                City:
                <input disabled="true" type="text" name="city" value="${donation.city}">
            </label>
            <br>
            <label>
                Phone Number:
                <input disabled="true" type="text" name="phoneNumber" value="${donation.phoneNumber}">
            </label>
            <br>
            <label>
                Pick Up Comment:
                <input disabled="true" type="text" placeholder="pickUpComment" name="pickUpComment" <c:if
                        test="${donation.pickUpComment != null}"> value="${donation.pickUpComment}"</c:if>>
            </label>
            <br>
            <label>
                Pick up Time:
                <input disabled="true" type="text" name="pickUpTime" value="${donation.pickUpTime}">
            </label>
            <br>
            <label>
                Pick up Date:
                <input disabled="true" type="text" name="pickUpDate" value="${donation.pickUpDate}">
            </label>
            <br>
            <label>
                Quantity:
                <input disabled="true" type="text" name="quantity" value="${donation.quantity}">
            </label>
            <br>
            <label>
                Street:
                <input disabled="true" type="text" name="street" value="${donation.street}">
            </label>
            <br>
            <label>
                Zip Code:
                <input disabled="true" type="text" name="zipCode" value="${donation.zipCode}">
            </label>
            <br>
            <label>
                Taken:
                <input disabled="true" type="text" name="taken" value="${donation.taken}">
            </label>
            <br>
            <label>
                Taken Date:
                <input disabled="true" type="text" name="takenDate" value="${donation.takenDate}">
            </label>
            <br>
            <label>
                Created At:
                <input disabled="true" type="text" name="createdAt" value="${donation.createdAt}">
            </label>
            <br>
            <label>
                <a href="/user/donations" class="btn btn-success btn-circle">
                    <i class="fas fa-warning">Back</i>
                </a>
            </label>
        </div>
        <!-- /.container-fluid -->