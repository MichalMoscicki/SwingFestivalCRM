<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
DANE UCZESTNIKA<br>
Imię i nazwisko: ${participant.firstName} ${participant.lastName}<br>
Email: ${participant.email} <br>
Telefon: ${participant.phone}<br>
Miasto: ${participant.city}<br>
<br>
Data rejestracji: ${participant.registrationDate}<br>
Lista wydarzeń:<br>
Merch:<br>
Kwota do zapłaty: ${participant.amountToPay}<br>
<br>
Rejestracja:<br>
<c:choose>
    <c:when test="${participant.alreadyPaid}">Zapłacono</c:when>
    <c:when test="true"><a href="/participant/${festivalId}/${participant.id}/confirmPayment">Potwierdź płatność</a></c:when>
</c:choose><br>
<c:choose>
    <c:when test="${participant.giftsGiven}">Wydano merch</c:when>
    <c:when test="true"><a href="/participant/${festivalId}/${participant.id}/giveMerch">Wydaj merch</a></c:when>
</c:choose><br>
<c:choose>
    <c:when test="${participant.braceletGiven}">Wydano opaskę</c:when>
    <c:when test="true"><a href="/participant/${festivalId}/${participant.id}/giveBracelet">Wydaj opaskę</a></c:when>
</c:choose><br>
<br>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>
<br>
<br>

----do tego widoku można dostać się z dwóch miejsc (wyszukiwarka, lista wszystkich) - co zrobić, żeby przekierowyało w dwa rózne miejsca?
----czy udostępnić opcję "cofnij" dla rejestracji?
</body>
</html>
