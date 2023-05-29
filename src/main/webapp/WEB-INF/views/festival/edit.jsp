<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 11:53
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
EDYCJA<br>
<form:form method="post" modelAttribute="festival">
    <form:input path="id" value="${festival.id}" hidden="true"/>
    <form:input path="name" value="${festival.name}"/>Nazwa<br>
    <form:input type="date" path="startDate" value="${festival.startDate}"/>Data rozpoczęcia<br>
    <form:input type="date" path="endDate" value="${festival.endDate}"/>Data zakończenia<br>
    <input type="submit"><br>
    <form:errors path="*" cssClass="error" element="div"/>
</form:form>
<br>
<a href="/main">POWRÓT</a><br>
</body>
</html>
