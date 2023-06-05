<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 31/05/2023
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>SwingApp</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
</head>
<body>
<header>
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark mb-5">
        <div class="container-fluid">
            <a href="<c:url value='/main'/>" class="navbar-brand">SwingApp</a>
            <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav">
                    <a href="<c:url value='/festival/all'/>" class="nav-item nav-link active">Festiwale</a>
                    <a href="<c:url value='/merch'/>" class="nav-item nav-link">Merch</a>
                    <a href="<c:url value='/admins'/>" class="nav-item nav-link">Admini</a>
                    <a href="<c:url value='/mailin'/>" class="nav-item nav-link">Mailing</a>
                    <a href="<c:url value='/instruction'/>" class="nav-item nav-link">Instrukcja</a>
                </div>
                <div class="navbar-nav ms-auto">
                    <form action="<c:url value='/logout'/>" method="post">
                        <input class="nav-item nav-link" type="submit" value="Wyloguj">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
        </div>
    </nav>
</header>