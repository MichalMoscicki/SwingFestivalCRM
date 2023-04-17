<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
FORMULARZ EDYCJI UCZESTNIKA<br>
<form:form method="post" modelAttribute="participant">
    Dane kontaktowe:<br>
    <form:input path="id" value="${participant.id}"/>Id<br>
    <form:input path="firstName" value="${participant.firstName}"/>Imię<br>
    <form:input path="lastName" value="${participant.lastName}"/>Nazwisko<br>
    <form:input path="email" value="${participant.email}"/>email<br>
    <form:input path="phone" value="${participant.phone}"/>Telefon<br>
    <form:input path="city" value="${participant.city}"/>Miasto<br>
    <form:input path="comments" value="${participant.comments}"/>Uwagi<br>
    Wybór eventów<br>
    <br>

    Wybór prezentów<br>
    <c:forEach items="${gifts}" var="gift">
        <form:checkbox path="gifts" value="${gift}"/>${gift.name} : ${gift.description} : ${gift.price} zł<br>
    </c:forEach>
    <input type="submit" value="Aktualizuj">
</form:form>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>

<a href="/festival/details">DODAJ</a><br>
<a href="/festival/details">POWRÓT</a><br>
</body>
</html>
