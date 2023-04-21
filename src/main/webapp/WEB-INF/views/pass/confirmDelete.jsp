<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 21/04/2023
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${pass.name}<br>
<br>
Czy na pewno chcesz usunąć?<br>
<a href="/pass/delete/${festivalId}/${pass.id}">TAK</a><br>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>
</body>
</html>
