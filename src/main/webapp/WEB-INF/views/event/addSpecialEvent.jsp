<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 18/04/2023
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Dodaj wydarzenie specjalne:<br>
<br>
<form:form method="post" modelAttribute="event">
    <form:input path="type" value="specialEvent" hidden="true"/><br>
    Nazwa<br>
    <form:input path="name"/><br>
    Data rozpoczęcia:<br>
    <form:input path="start" type="datetime-local" pattern="yyyy-MM-dd'T'HH:mm"/><br>
    Data zakończenia:<br>
    <form:input path="end" type="datetime-local" pattern="yyyy-MM-dd'T'HH:mm"/><br>
    Adres:<br>
    <form:input path="address"/><br>
    <form:textarea rows="4" cols="50" path="description"/>Opis<br>
    <input type="submit"><br>
    <br>
</form:form>
<br>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>


</body>
</html>