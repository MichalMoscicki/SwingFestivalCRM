<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Nazwa festiwalu:<br>
${festival.name}<br>
<br>
Details<br>
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
<a href="/participant">wyszukaj po emailu</a> (formularz z jednym polem)<br>
<a href="/participant">wyszukaj po nazwisku</a> (formularz z jednym polem)<br>
<a href="/participant/all">wszyscy uczestnicy</a><br>
<a href="/participant/add">dodaj uczestnika</a><br>
<a href="/participant/addFromFile">dodaj uczestnika z pliku(superadmin only)</a><br>
****************<br>
<br>
LISTA ADMINÓW (superadmin only)<br>
1. admin jeden <br>
2. admin dwa <br>
<br>
dodaj admina <br>
<br>
*******************<br>
WYŚLIJ MAILING (superAdminOnly)<br>
[docelowo ta funkcjonalność ma być podpięta pod googleDocs - maile będe wysyłać się automatycznie po rejestracji i dzień
przed festiwalem]
<a href="/festival">POWRÓT DO LISTY FESTIWALI</a>
</body>
</html>
