<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="headerShorter.jsp"/>

<section class="login-page">
    <h2>Edytuj Dane</h2>
    <form:form modelAttribute="user" method="post">
        <div class="form-group">
            <form:input path="username" type="email" name="username"/>
        </div>
        <div class="form-group">
            <form:input path="firstname" type="text" name="firstname"/>
        </div>
        <div class="form-group">
            <form:input path="surname" type="text" name="surname"/>
        </div>
        <div class="form-group">
            <form:input path="password" type="password" name="password" placeholder="Hasło" />
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz hasło" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="/" class="btn btn--without-border">Anuluj</a>
            <button class="btn" type="submit">Edytuj</button>
        </div>
    </form:form>
</section>

<jsp:include page="footer.jsp"/>