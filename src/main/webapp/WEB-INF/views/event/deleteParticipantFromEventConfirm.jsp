<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 20/04/2023
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Czy na pewno chcesz usunąć:<br>
${participant.firstName} ${participant.lastName}, ${participant.email}<br>
<br>
z wydarzenia:<br>
${event.name}<br>
<br>
<a href="/participant/${festivalId}/deleteParticipantFromEvent/${event.id}/${participant.id}">Usuń</a><br>
<a href="/event/${festivalId}/details/${event.id}">Powrót</a><br>
</body>
</html>
