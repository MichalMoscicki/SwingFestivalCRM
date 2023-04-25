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
Uwaga - plik powinien być w formacie .csv!
Wybierz plik:<br>
<form method="POST" enctype="multipart/form-data" action="/addFromFile/${festivalId}/chooseFile">
    <table>
        <tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
        <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
    </table>
</form>
<a href="/festival/details/${festivalId}">POWRÓT</a>
</body>
</html>
