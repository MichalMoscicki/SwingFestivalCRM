<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 19/04/2023
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/event/${festivalId}/eventTypeChoice">
    <input type="radio" id="party" name="eventType" value="party">
    <label for="party">Impreza</label><br>
    <input type="radio" id="workshop" name="eventType" value="workshop">
    <label for="party">Warsztat</label><br>
    <input type="radio" id="specialEvent" name="eventType" value="specialEvent">
    <label for="party">Wydarzenie specjalne</label><br>
    <input type="submit" value="Dalej">
</form>
<br>
<a href="/festival/details/${festivalId}">POWRÓT</a><br>
</body>
</html>
