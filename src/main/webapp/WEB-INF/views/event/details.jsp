<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Nazwa: ${event.name}<br>
<br>
Początek: ${event.start}<br>
Koniec: ${event.end}<br>
Adres: ${event.address}<br>
Opis: ${event.description}<br>
<br>
<c:choose>
    <c:when test="${event.type == 'party'}">
        Alkohol: ${event.alcohol}<br>
        Kuchnia: ${event.warmDishes}<br>
        Śliskość parkietu: ${event.dancefloorLubricity}<br>
        Konkursy: ${event.competition}<br>
        Fotobudka: ${event.photoBooth}<br>
        Muzyka na żywo: ${event.liveMusic}<br>
        Taster: ${event.taster}<br>
        Merch do kupienia: ${event.merch}<br>
        Wystawcy: ${event.market}<br>
    </c:when>
    <c:when test="${event.type == 'workshop'}">
        Czas trwania: ${event.duration}<br>
        Nauczyciele: ${event.teachers}<br>
        Poziom: ${event.level}<br>
        Styl: ${event.style}<br>
        Solo czy w parach: ${event.soloOrInPairs}<br>
    </c:when>
</c:choose>
<br>
Lista uczestników:<br>
<ol>
<c:forEach items="${participants}" var="participant">
   <li> ${participant.firstName} ${participant.lastName}, ${participant.email}
   <a href="/participant/${festivalId}/details/${participant.id}">[Szczegóły]</a>
       <a href="/participant/${festivalId}/deleteParticipantFromEventConfirm/${participant.id}/${event.id}">[Usuń z listy uczestników]</a>
   </li>
</c:forEach>
</ol>
<br>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>

</body>
</html>

