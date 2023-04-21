<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 18/04/2023
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Dodaj imprezę:<br>
<br>
<form:form method="post" modelAttribute="event">
    <form:input path="type" value="party" hidden="true"/><br>
    Nazwa<br>
    <form:input path="name"/><br>
    Data rozpoczęcia:<br>
    <form:input path="start" type="datetime-local" pattern="yyyy-MM-dd'T'HH:mm"/><br>
    Data zakończenia:<br>
    <form:input path="end" type="datetime-local" pattern="yyyy-MM-dd'T'HH:mm"/><br>
    Adres:<br>
    <form:input path="address"/><br>
    <form:textarea rows="4" cols="50" path="description"/>Opis<br>
    <br>
    Alkohol:<br>
    <form:radiobutton path="alcohol" value="barCashOnly"/>Bar na miejscu, tylko gotówka<br>
    <form:radiobutton path="alcohol" value="barCashAndCard"/>Bar na miejscu, gotówka i karta<br>
    <form:radiobutton path="alcohol" value="bbob"/>BBOB<br>
    <br>
    Śliskość parkietu:<br>
    <form:radiobutton path="dancefloorLubricity" value="low"/>Tępy<br>
    <form:radiobutton path="dancefloorLubricity" value="middle"/>Umiarkowany<br>
    <form:radiobutton path="dancefloorLubricity" value="high"/>Śliski<br>
    <br>
    Konkursy:<br>
    <form:input path="competition"/><br>
    <br>
    Dodatkowe informacje:<br>
    <form:checkbox path="warmDishes" />Czy można zamówić ciepły posiłek na miejscu<br>
    <form:checkbox path="photoBooth" />Czy będzie fotobudka<br>
    <form:checkbox path="liveMusic" />Czy będzie muzyka na żywo<br>
    <form:checkbox path="taster" />Czy będzie taster<br>
    <form:checkbox path="merch" />Czy będzie dostępny merch<br>
    <form:checkbox path="market" />Czy będą wystawcy<br>
    <input type="submit"><br>
    <form:errors path="start" cssClass="error"  element="div" />
    <form:errors path="end" cssClass="error"  element="div" />
    <br>

</form:form>
<br>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>

</body>
</html>
