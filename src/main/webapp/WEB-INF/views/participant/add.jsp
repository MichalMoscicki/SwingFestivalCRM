<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
FORMULARZ DODAWANIA UCZESTNIKA<br>
<form:form method="post" modelAttribute="participant">
Dane kontaktowe:<br>
    <form:input path="firstName"/>Imię<br>
    <form:input path="lastName"/>Nazwisko<br>
    <form:input path="email"/>email<br>
    <form:input path="phone"/>Telefon<br>
    <form:input path="city"/>Miasto<br>
    <form:input path="comments"/>Uwagi<br>
    Wybór eventów<br>
    <br>

    Wybór prezentów<br>
    <c:forEach items="${gifts}" var="gift">
        <form:checkbox path="gifts" value="${gift}"/>${gift.name} : ${gift.description} : ${gift.price} zł<br>
    </c:forEach>
    <input type="submit" value="Dodaj">
</form:form>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>
</body>
</html>
