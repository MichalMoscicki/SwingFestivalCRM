<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 15/04/2023
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Szukane nazwisko: ${lastName}<br>
<br>
<table>
    <tr>
        <th></th>
        <th>Email</th>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th></th>
        <th></th>
    </tr>
    <c:set var="count" value="0" scope="page" />
    <c:forEach items="${participants}" var="participant">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td>${count}.</td>
            <td>${participant.email}</td>
            <td>${participant.firstName}</td>
            <td>${participant.lastName}</td>
            <td><a href="participant/all">[szczegóły]</a></td>
            <td><a href="participant/all">[edytuj]</a></td>
            <td><a href="/participant/deleteConfirm/${festival.id}/${participant.id}">[usuń]</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/festival/details/${festivalId}">Powrót</a><br>
Uwaga - jak klikniesz tutaj w szczegóły, to możliwość powrotu w dwa miejsca - do listy wyszukanych i do festivaldetails.
</body>
</html>
