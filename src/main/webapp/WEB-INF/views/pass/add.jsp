<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 21/04/2023
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Dodaj pass:<br>
<form:form modelAttribute="pass">
    <form:input path="name"/>Nazwa<br>
    <form:input type="number" step="0.01" path="price"/>Cena<br>
    <br>
    <c:forEach items="${events}" var="event">
        <form:checkbox path="events" value="${event}"/>${event.name}  :  ${event.type}  :  ${event.start}<br>
    </c:forEach>
    <br>
    <input type="submit" value="Dodaj">
</form:form>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>
</body>
</html>
