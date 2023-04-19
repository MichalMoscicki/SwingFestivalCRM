<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 18/04/2023
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Dodaj imprezę:<br>
<form:form method="post" modelAttribute="event">
    <form:input path="name"/>Nazwa<br>
    <input type="submit"><br>

</form:form>
<br>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>

</body>
</html>
