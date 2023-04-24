<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 22/04/2023
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Wybierz plik:<br>
<form action="" method="post">
    <input type="file" id="fileUpload" name="filePath"><br>
    <input type="submit" value="Dodaj"><br>

</form>
<a href="/festival/details/${festivalId}">POWRÓT</a>
</body>
</html>
