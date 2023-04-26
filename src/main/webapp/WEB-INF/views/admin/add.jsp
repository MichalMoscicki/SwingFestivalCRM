<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Dodawanie admina:<br>
<form:form method="post" modelAttribute="admin">
    <form:input path="firstName"/>Imię<br>
    <form:input path="lastName"/>Nazwisko<br>
    <form:input path="email"/>Email<br>
    <form:input path="password"/>Hasło<br>
    <input type="submit"><br>
</form:form>
<br>
<a href="/main">POWRÓT</a><br>

</body>
</html>
