<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 31/05/2023
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="d-flex align-items-center justify-content-center mt-5 pt-5" style="height: 250px;">
        <div class="container bg-light border">
            <c:if test="${empty festivals}">
                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <h1> Nie dodano jeszcze żadnego festiwalu</h1>
                    </div>
                </div>
                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <a href="<c:url value='/festival/add'/>">
                            <button type="button" class="btn btn-secondary">
                                <h5>Dodaj festiwal</h5>
                            </button>
                        </a>
                    </div>
                </div>
            </c:if>

            <c:if test="${not empty festivals}">

                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <h1> Festiwale:</h1>
                    </div>
                </div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="text-center" scope="col"></th>
                        <th class="text-center" scope="col">Nazwa</th>
                        <th class="text-center" scope="col">Początek</th>
                        <th class="text-center" scope="col">Koniec</th>
                        <th class="text-center" scope="col"></th>
                        <th class="text-center" scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:set var="count" value="0" scope="page"/>
                    <c:forEach items="${festivals}" var="festival">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr scope="row">
                            <td>${count}.</td>
                            <td class="text-center align-middle">${festival.name}</td>
                            <td class="text-center align-middle">${festival.startDate}</td>
                            <td class="text-center align-middle">${festival.endDate}</td>
                            <td class="text-center align-middle"><a
                                    href="<c:url value='/festival/details/${festival.id}'/>">
                                <button type="button" class="btn btn-outline-secondary btn-sm">
                                    Szczegóły
                                </button>
                            </a></td>

                            <sec:authorize access="hasRole('ADMIN')">
                                <td><a href="<c:url value='/festival/edit/${festival.id}'/>">
                                    <button type="button" class="btn btn-outline-secondary btn-sm">
                                        Edytuj
                                    </button>
                                </a>
                                </td>
                                <td><a href="<c:url value='/festival/deleteConfirm/${festival.id}'/>">
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
                        <a href="<c:url value='/festival/add'/>">
                            <button type="button" class="btn btn-secondary">
                                <h5>Dodaj festiwal</h5>
                            </button>
                        </a>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>