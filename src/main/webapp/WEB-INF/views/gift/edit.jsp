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
EDYCJA<br>
<form:form method="post" modelAttribute="gift">
    <form:input path="id" value="${gift.id}" hidden="true"/>
    <form:input path="name" value="${gift.name}"/>Nazwa<br>
    <form:input path="description" value="${gift.description}"/>Data rozpoczęcia<br>
    <form:input path="price" value="${gift.price}"/>Data zakończenia<br>
    <input type="submit"><br>
</form:form>
<br>
<a href="/main">POWRÓT</a><br>
</body>
</html>