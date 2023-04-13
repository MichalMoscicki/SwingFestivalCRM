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

<form:form method="post" modelAttribute="festival">
    <form:input path="name"/>Nazwa<br>
    <form:input type="date" path="startDate"/>Data rozpoczęcia<br>
    <form:input type="date" path="endDate"/>Data zakończenia<br>
    <input type="submit"><br>
    <form:errors path="name" cssClass="error"  element="div" />
    <form:errors path="startDate" cssClass="error"  element="div" />
    <form:errors path="endDate" cssClass="error"  element="div" />
</form:form>
<br>
<a href="/festival">POWRÓT</a><br>

</body>
</html>
