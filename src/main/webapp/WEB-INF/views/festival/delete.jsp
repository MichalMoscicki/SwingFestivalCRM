<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${festival.name}____${festival.startDate}____${festival.endDate}<br>
<br>
Czy na pewno chcesz usunąć?<br>
<a href="/festival/delete/${festival.id}">TAK</a>
<a href="/festival">POWRÓT</a>
</body>
</html>
