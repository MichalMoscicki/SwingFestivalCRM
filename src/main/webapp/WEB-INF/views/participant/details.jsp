<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
DANE UCZESTNIKA<br>
${participant.firstName} ${participant.lastName}<br>
${participant.email} <br>
lista wydarzeń w których bierze udział <br>
<br>
(funkcjonalności dotyczące rejestracji w dniu festiwalu):<br>
<a href="/festival/details">wydaj T-shirt</a><br>
<a href="/festival/details">wydaj opaskę</a><br>
<a href="/festival/details">potwierdź płatność</a><br>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>
----do tego widoku można dostać się z dwóch miejsc (wyszukiwarka, lista wszystkich) - co zrobić, żeby przekierowyało w dwa rózne miejsca?
</body>
</html>
