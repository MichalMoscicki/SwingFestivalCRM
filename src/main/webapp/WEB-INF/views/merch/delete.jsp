<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 18/04/2023
  Time: 09:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${merch.name}____${merch.description}____${merch.price}<br>
<br>
Czy na pewno chcesz usunąć?<br>
<a href="/merch/delete/${merch.id}">TAK</a>
<a href="/main">POWRÓT</a>
</body>
</html>