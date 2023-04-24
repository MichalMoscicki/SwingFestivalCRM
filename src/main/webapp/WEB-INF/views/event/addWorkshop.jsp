<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Dodaj warsztat:<br>
<form:form method="post" modelAttribute="event">
    <form:input path="type" value="workshop" hidden="true"/><br>
    Nazwa<br>
    <form:input path="name"/><br>
    Data rozpoczęcia:<br>
    <form:input path="start" type="datetime-local" pattern="yyyy-MM-dd'T'HH:mm"/><br>
    Data zakończenia:<br>
    <form:input path="end" type="datetime-local" pattern="yyyy-MM-dd'T'HH:mm"/><br>
    Adres:<br>
    <form:input path="address"/><br>
    <form:textarea rows="4" cols="50" path="description"/>Opis<br>
    Czas trwania:<br>
    <form:input path="duration"/><br>
    Prowadzący:<br>
    <form:input path="teachers"/><br>
    Poziom:<br>
    <form:input path="level"/><br>
    Styl:<br>
    <form:input path="style"/><br>
    Solo, czy w parach:<br>
    <form:radiobutton path="soloOrInPairs" value="Solo"/>Solo<br>
    <form:radiobutton path="soloOrInPairs" value="InPairs"/>W parach<br>
    <input type="submit"><br>
    <form:errors path="start" cssClass="error"  element="div" />
    <form:errors path="end" cssClass="error"  element="div" />
    <br>

</form:form>
<br>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>
</body>
</html>