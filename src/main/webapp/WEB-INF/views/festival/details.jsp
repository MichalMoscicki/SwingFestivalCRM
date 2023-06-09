<%@ page import="pl.coderslab.finalproject.models.event.Event" %><%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${festival.name}<br>
<br>
LISTA EVENTÓW:<br>
<table>
    <tr>
        <th></th>
        <th>Typ</th>
        <th>Nazwa</th>
        <th>Początek</th>
        <th>Koniec</th>
        <th></th>
    </tr>
    <c:set var="count" value="0" scope="page" />
    <c:forEach items="${events}" var="event">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td>${count}.</td>
            <td>${event.type}</td>
            <td>${event.name}</td>
            <td>${event.start}</td>
            <td>${event.end}</td>
            <td><a href="/event/${festival.id}/details/${event.id}">[szczegóły]</a></td>
            <td><a href="/event/${festival.id}/edit/${event.id}">[edytuj(superAdminOnly)]</a></td>
            <td><a href="/event/${festival.id}/deleteConfirm/${event.id}">[usuń(superAdminOnly)]</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/event/${festival.id}/eventTypeChoice">[DODAJ WYDARZENIE(superadminOnly)]</a><br>
************<br>
<br>
PASS:<br>
<c:forEach items="${passes}" var="pass">
    <c:set var="count2" value="${count2 + 1}" scope="page"/>
    <tr>
        <td>${count2}.</td>
        <td>${pass.name}</td>
        <td>${pass.price} zł</td>
        <td><a href="/pass/${festival.id}/details/${pass.id}">[szczegóły]</a></td>
        <td><a href="/pass/${festival.id}/edit/${pass.id}">[edytuj(superAdminOnly)]</a></td>
        <td><a href="/pass/${festival.id}/confirmDelete/${pass.id}">[usuń(superAdminOnly)]</a></td>
        <br>
    </tr>
</c:forEach>
<br>
<a href="/pass/${festival.id}/add">dodaj pass</a><br>
************<br>
<br>
UCZESTNICY:<br>
<a href="/participant/all/${festival.id}">wszyscy uczestnicy</a><br>
Znajdź uczestnika:<br>
<form:form method="post" action="/participant/${festival.id}/findByEmail">
    <input name="email" placeholder="email"/>
    <input type="submit" value="Wyszukaj">
</form:form>
<form:form method="post" action="/participant/${festival.id}/findByLastName">
    <input type="text" name="lastName" placeholder="Nazwisko">
    <input type="submit" value="Wyszukaj">
</form:form>
<a href="/participant/${festival.id}/add">dodaj uczestnika</a><br>
<a href="/addFromFile/${festival.id}/chooseFile">dodaj uczestnika z pliku(superadmin only)</a><br>
****************<br>
<a href="/sendEmails/${festival.id}">Wyślij mailing</a><br>
<br>
<a href="/main">POWRÓT DO LISTY FESTIWALI</a>
</body>
</html>
