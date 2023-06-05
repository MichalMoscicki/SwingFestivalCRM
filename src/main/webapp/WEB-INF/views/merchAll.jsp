<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 01/06/2023
  Time: 07:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="d-flex align-items-center justify-content-center mt-5 pt-5" style="height: 250px;">
        <div class="container bg-light border">
            <c:if test="${empty merch}">
                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <h1> Nie dodano jeszcze merchu</h1>
                    </div>
                </div>
                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <a href="<c:url value='/merch/add'/>">
                            <button type="button" class="btn btn-secondary">
                                <h5>Dodaj merch</h5>
                            </button>
                        </a>
                    </div>
                </div>
            </c:if>

            <c:if test="${not empty merch}">

                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <h1> Merch:</h1>
                    </div>
                </div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="text-center" scope="col"></th>
                        <th class="text-center" scope="col">Nazwa</th>
                        <th class="text-center" scope="col">Opis</th>
                        <th class="text-center" scope="col">Cena</th>
                        <th class="text-center" scope="col"></th>
                        <th class="text-center" scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:set var="count" value="0" scope="page"/>
                    <c:forEach items="${merch}" var="m">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr scope="row">
                            <td>${count}.</td>
                            <td class="text-center align-middle">${m.name}</td>
                            <td class="text-center align-middle">${m.description}</td>
                            <td class="text-center align-middle">${m.price}</td>
                            <sec:authorize access="hasRole('ADMIN')">
                                <td><a href="<c:url value='/merch/edit/${m.id}'/>">
                                    <button type="button" class="btn btn-outline-secondary btn-sm">
                                        Edytuj
                                    </button>
                                </a>
                                </td>
                                <td><a href="<c:url value='/merch/deleteConfirm/${m.id}'/>">
                                    <button type="button" class="btn btn-outline-secondary btn-sm">
                                        Usuń
                                    </button>
                                </a>
                                </td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <a href="<c:url value='/merch/add'/>">
                            <button type="button" class="btn btn-secondary">
                                <h5>Dodaj merch</h5>
                            </button>
                        </a>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>