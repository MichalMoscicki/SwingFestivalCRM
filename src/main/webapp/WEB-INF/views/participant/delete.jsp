<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${participant.email}____${participant.firstName}____${participant.lastName}<br>
<br>
Czy na pewno chcesz usunąć uczestnika?<br>
<a href="/participant/delete/${festivalId}/${participant.id}">TAK</a><br>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>
</body>
</html>

