<%--
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
Nazwa festiwalu:<br>
${festival.name}<br>
<br>
LISTA EVENTÓW:<br>
1. impreza czwartkowa - <a href="/event/details">szczegóły</a> - <a href="/event/edit">[edytuj(superadminOnly)]</a> - <a
        href="/event/delete">[usuń(superadminOnly)]</a><br>
2. impreza piątkowa - <a href="/event/details">szczegóły</a> - <a href="/event/edit">[edytuj(superadminOnly)]</a> - <a
        href="/event/delete">[usuń(superadminOnly)]</a><br>
<br>
<a href="/event/add">[DODAJ EVENT(superadminOnly)]</a><br>
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
<a href="/festival">POWRÓT DO LISTY FESTIWALI</a>
</body>
</html>
