<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 21/04/2023
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Nazwa:
${pass.name}<br>
Cena:
${pass.price} zł<br>
<br>
Wydarzenia:
<ol>
<c:forEach items="${events}" var="event">
    <li>
        ${event.name} ___ ${event.type} ___ ${event.start}
    </li>
</c:forEach>
</ol>
<br>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>
</body>
</html>
