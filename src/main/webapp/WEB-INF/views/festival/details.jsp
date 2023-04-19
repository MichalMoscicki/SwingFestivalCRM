<%@ page import="pl.coderslab.finalproject.models.festivalEvents.Event" %><%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <th>Nazwa</th>
        <th>Początek</th>
        <th>Koniec</th>
        <th>Typ</th>
        <th></th>
    </tr>
    <c:set var="count" value="0" scope="page" />
    <c:forEach items="${events}" var="event">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td>${count}.</td>
            <td>${event.name}</td>
            <td></td>
            <td></td>
            <td>
                TYP - JAK GO WYCIĄGNĄć?</td>
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
<a href="/participant/addFromFile">dodaj uczestnika z pliku(superadmin only)</a><br>
****************<br>
WYŚLIJ MAILING (superAdminOnly)<br>
[docelowo ta funkcjonalność ma być podpięta pod googleDocs - maile będe wysyłać się automatycznie po rejestracji i dzień
przed festiwalem]<br>
<a href="/main">POWRÓT DO LISTY FESTIWALI</a>
</body>
</html>
