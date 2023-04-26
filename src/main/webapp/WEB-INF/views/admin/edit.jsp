<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 18/04/2023
  Time: 09:29
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
EDYCJA ADMINA<br>
<form:form method="post" modelAttribute="admin">
    <form:input path="id" value="${admin.id}" hidden="true"/>
    <form:input path="firstName" value="${admin.firstName}"/>Imię<br>
    <form:input path="lastName" value="${admin.lastName}"/>Nazwisko<br>
    <form:input path="email" value="${admin.email}"/>Email<br>
    <form:input path="password" value="${admin.password}"/>Hasło<br>
    <input type="submit"><br>
</form:form>
<br>
<a href="/main">POWRÓT</a><br>
</body>
</html>