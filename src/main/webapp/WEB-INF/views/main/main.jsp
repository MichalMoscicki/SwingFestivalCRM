<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<Festiwale></Festiwale>:<br>
<br>
<table>
    <tr>
        <th></th>
        <th>Nazwa</th>
        <th>Początek</th>
        <th>Koniec</th>
        <th></th>
        <th></th>
    </tr>
    <c:set var="count" value="0" scope="page" />
    <c:forEach items="${festivals}" var="festival">
        <c:set var="count" value="${count + 1}" scope="page"/>
    <tr>
        <td>${count}.</td>
        <td>${festival.name}</td>
        <td>${festival.startDate}</td>
        <td>${festival.endDate}</td>
        <td><a href="festival/details/${festival.id}">[szczegóły]</a></td>
        <td><a href="festival/edit/${festival.id}">[edytuj(superAdminOnly)]</a></td>
        <td><a href="festival/deleteConfirm/${festival.id}">[usuń(superAdminOnly)]</a></td>
    </tr>
    </c:forEach>
</table>
<br>
************<br>
<a href="festival/add">DODAJ FESTIVAL (superAdminOnly)</a><br>
*************<br>
<br>
Merch:<br>
<br>
<table>
    <tr>
        <th></th>
        <th>Nazwa</th>
        <th>Opis</th>
        <th>Cena</th>
        <th></th>
        <th></th>
    </tr>
    <c:set var="count" value="0" scope="page" />
    <c:forEach items="${gifts}" var="gift">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td>${count}.</td>
            <td>${gift.name}</td>
            <td>${gift.description}</td>
            <td>${gift.price}</td>
            <td><a href="gift/edit/${gift.id}">[edytuj(superAdminOnly)]</a></td>
            <td><a href="gift/deleteConfirm/${gift.id}">[usuń(superAdminOnly)]</a></td>
        </tr>
    </c:forEach>
</table>
************<br>
<a href="gift/add">DODAJ MERCH (superAdminOnly)</a><br>
*************<br>

admin crud<br>
</body>
</html>
