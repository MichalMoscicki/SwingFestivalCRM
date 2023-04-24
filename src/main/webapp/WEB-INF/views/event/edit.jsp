<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="event">
    <form:input path="id" value="${event.id}" hidden="true"/><br>
    <form:input path="type" value="${event.type}" hidden="true"/><br>
    Nazwa<br>
    <form:input path="name" value="${event.name}"/><br>
    Data rozpoczęcia:<br>
    <form:input path="start" type="datetime-local" pattern="yyyy-MM-dd'T'HH:mm" value="${event.start}"/><br>
    Data zakończenia:<br>
    <form:input path="end" type="datetime-local" pattern="yyyy-MM-dd'T'HH:mm" value="${event.end}"/><br>
    Adres:<br>
    <form:input path="address" value="${event.address}"/><br>
    <form:textarea rows="4" cols="50" path="description" value="${event.description}"/>Opis<br>
    <c:choose>
        <c:when test="${event.type == 'party'}">
            Alkohol:<br>
            <form:radiobutton path="alcohol" value="barCashOnly" />Bar na miejscu, tylko gotówka<br>
            <form:radiobutton path="alcohol" value="barCashAndCard"/>Bar na miejscu, gotówka i karta<br>
            <form:radiobutton path="alcohol" value="bbob"/>BBOB<br>
            <br>
            Śliskość parkietu:<br>
            <form:radiobutton path="dancefloorLubricity" value="low"/>Tępy<br>
            <form:radiobutton path="dancefloorLubricity" value="middle"/>Umiarkowany<br>
            <form:radiobutton path="dancefloorLubricity" value="high"/>Śliski<br>
            <br>
            Konkursy:<br>
            <form:input path="competition" value="${event.competition}"/><br>
            <br>
            Dodatkowe informacje:<br>
            <form:checkbox path="warmDishes" value="${event.warmDishes}"/>Czy można zamówić ciepły posiłek na miejscu<br>
            <form:checkbox path="photoBooth" value="${event.photoBooth}"/>Czy będzie fotobudka<br>
            <form:checkbox path="liveMusic" value="${event.liveMusic}"/>Czy będzie muzyka na żywo<br>
            <form:checkbox path="taster" value="${event.taster}"/>Czy będzie taster<br>
            <form:checkbox path="merch" value="${event.merch}"/>Czy będzie dostępny merch<br>
            <form:checkbox path="market" value="${event.market}"/>Czy będą wystawcy<br>
        </c:when>
        <c:when test="${event.type == 'workshop'}">
            Czas trwania:<br>
            <form:input path="duration" value="${event.duration}"/><br>
            Prowadzący:<br>
            <form:input path="teachers" value="${event.teachers}"/><br>
            Poziom:<br>
            <form:input path="level" value="${event.level}"/><br>
            Styl:<br>
            <form:input path="style" value="${event.style}"/><br>
            Solo, czy w parach:<br>
            <form:radiobutton path="soloOrInPairs" value="Solo"/>Solo<br>
            <form:radiobutton path="soloOrInPairs" value="InPairs"/>W parach<br>
            <form:errors path="start" cssClass="error"  element="div" />
            <form:errors path="end" cssClass="error"  element="div" />
        </c:when>
    </c:choose>

    <input type="submit"><br>
    <form:errors path="start" cssClass="error"  element="div" />
    <form:errors path="end" cssClass="error"  element="div" />
</form:form>
<br>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>
</body>
</html>
