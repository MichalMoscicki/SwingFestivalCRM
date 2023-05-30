<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 12:41
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
FORMULARZ EDYCJI UCZESTNIKA<br>
<form:form method="post" modelAttribute="participant" action="/participant/${festivalId}/edit/${participant.id}">
    Dane kontaktowe:<br>
    <form:input path="id" value="${participant.id}" hidden="true"/>
    <form:input path="alreadyPaid" value="${participant.alreadyPaid}" hidden="true"/>
    <form:input path="giftsGiven" value="${participant.giftsGiven}" hidden="true"/>
    <form:input path="braceletGiven" value="${participant.braceletGiven}" hidden="true"/>
    <form:input path="firstName" value="${participant.firstName}"/>Imię<br>
    <form:input path="lastName" value="${participant.lastName}"/>Nazwisko<br>
    <form:input path="email" value="${participant.email}"/>email<br>
    <form:input path="phone" value="${participant.phone}"/>Telefon<br>
    <form:input path="city" value="${participant.city}"/>Miasto<br>
    <form:input path="role" value="${participant.role}"/>Lead/Follow<br>
    <form:input path="level" value="${participant.level}"/>Poziom<br>
    <form:input path="partnerName" value="${participant.partnerName}"/>Imię i nazwisko partnera<br>
    <form:input path="comments" value="${participant.comments}"/>Uwagi<br>
    <form:input path="amountToPay" value="${participant.amountToPay}" hidden="true"/>
    Aktualna cena: ${participant.amountToPay}. (Cena zaktualizuje się automatycznie po edycji).<br>
    Wybór eventów<br>
    <c:forEach items="${passes}" var="pass">
        <form:checkbox path="passes" value="${pass}"/>${pass.name} :  ${pass.price} zł<br>
    </c:forEach>
    <br>

    Wybór prezentów<br>
    <c:forEach items="${merch}" var="m">
        <form:checkbox path="merch" value="${m}"/>${m.name} : ${m.description} : ${m.price} zł<br>
    </c:forEach>
    <input type="submit" value="Aktualizuj">
</form:form>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>
</body>
</html>
