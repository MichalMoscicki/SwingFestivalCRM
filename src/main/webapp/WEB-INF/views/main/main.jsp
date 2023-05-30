<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="src/main/resources/static/css/bootstrap.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
Festiwale:<br>
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
    <c:set var="count" value="0" scope="page"/>
    <c:forEach items="${festivals}" var="festival">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td>${count}.</td>
            <td>${festival.name}</td>
            <td>${festival.startDate}</td>
            <td>${festival.endDate}</td>
                <td><a href="festival/details/${festival.id}">[szczegóły]</a></td>
            <sec:authorize access="hasRole('ADMIN')">
                <td><a href="festival/edit/${festival.id}">[edytuj]</a></td>
                <td><a href="festival/deleteConfirm/${festival.id}">[usuń]</a></td>
            </sec:authorize>
        </tr>
    </c:forEach>
</table>
<br>
<sec:authorize access="hasRole('ADMIN')">
    <div>
        ************<br>
        <a href="festival/add">DODAJ FESTIVAL (superAdminOnly)</a><br>
        *************<br>
    </div>
</sec:authorize>
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
    <c:set var="count" value="0" scope="page"/>
    <c:forEach items="${gifts}" var="gift">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td>${count}.</td>
            <td>${gift.name}</td>
            <td>${gift.description}</td>
            <td>${gift.price}</td>
            <sec:authorize access="hasRole('ADMIN')">
                <td><a href="merch/edit/${gift.id}">[edytuj]</a></td>
                <td><a href="merch/deleteConfirm/${gift.id}">[usuń]</a></td>
            </sec:authorize>
        </tr>
    </c:forEach>
</table>
<sec:authorize access="hasRole('ADMIN')">
************<br>
<a href="merch/add">DODAJ MERCH (superAdminOnly)</a><br>
*************<br>

Admini:<br>
<table>
    <tr>
        <th></th>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Email</th>
        <th></th>
        <th></th>
    </tr>
    <c:set var="count" value="0" scope="page"/>
    <c:forEach items="${admins}" var="admin">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td>${count}.</td>
            <td>${admin.firstName}</td>
            <td>${admin.lastName}</td>
            <td>${admin.email}</td>
            <td><a href="admin/edit/${admin.id}">[edytuj(superAdminOnly)]</a></td>
            <td><a href="admin/deleteConfirm/${admin.id}">[usuń(superAdminOnly)]</a></td>
        </tr>
    </c:forEach>
</table>
<a href="admin/add">DODAJ ADMINA (superAdminOnly)</a><br>
</sec:authorize>
</body>
<form action="<c:url value="/logout"/>" method="post">
    <input class="fa fa-id-badge" type="submit" value="Wyloguj">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</html>
