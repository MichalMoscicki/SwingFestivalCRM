<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 26/05/2023
  Time: 09:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet"  href="/css/bootstrap.min.css">
    <script src="/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <div class="col-md-3 mb-2 mb-md-0">
            <a href="/" class="d-inline-flex link-body-emphasis text-decoration-none">
                <svg class="bi" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
            </a>
        </div>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="#" class="nav-link px-2 link-secondary">Home</a></li>
            <li><a href="#" class="nav-link px-2">Features</a></li>
            <li><a href="#" class="nav-link px-2">Pricing</a></li>
            <li><a href="#" class="nav-link px-2">FAQs</a></li>
            <li><a href="#" class="nav-link px-2">About</a></li>
        </ul>

        <div class="col-md-3 text-end">
            <button type="button" class="btn btn-outline-primary me-2">Logowanie</button>
            <button type="button" class="btn btn-primary" onclick="window.location.href='https://w3docs.com';">Rejestracja</button>
        </div>
    </header>
</div>
</body>
</html>

