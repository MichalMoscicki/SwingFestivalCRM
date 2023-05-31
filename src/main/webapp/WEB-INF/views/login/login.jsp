<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 24/05/2023
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SwingApp</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
</head>
<body>
<main class="form-signin w-100 m-auto">
    <div class="d-flex align-items-center justify-content-center vh-100" style="height: 250px;">
        <div class="container bg-light border py-3 my-3">
            <div class="row justify-content-md-center">
                <div class="col-md-auto">
                    <form method="post">
                        <div class="d-flex justify-content-center">
                            <h1 class="h3 mb-3 fw-normal">SwingApp</h1>
                        </div>
                        <div class="form-floating">
                            <input type="email" name="username" class="form-control" id="floatingInput"
                                   placeholder="name@example.com">
                            <label for="floatingInput">Email</label>
                        </div>
                        <div class="form-floating">
                            <input name="password" type="password" class="form-control" id="floatingPassword"
                                   placeholder="Password">
                            <label for="floatingPassword">Password</label>
                        </div>
                        <button class="w-100 btn btn-lg btn-primary" type="submit">Zaloguj</button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
</body>
</html>
