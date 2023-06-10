<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 01/06/2023
  Time: 07:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="d-flex align-items-center justify-content-center mt-5 pt-5" style="height: 250px;">
        <div class="container bg-light border">
            <c:if test="${empty admins}">
                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <h1> Nie dodano jeszcze żadnego admina, więc jakim cudem to sę w ogóle wyswietla?</h1>
                    </div>
                </div>
                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <a href="<c:url value='/admin/add'/>">
                            <button type="button" class="btn btn-secondary">
                                <h5>Dodaj festiwal</h5>
                            </button>
                        </a>
                    </div>
                </div>
            </c:if>

            <c:if test="${not empty admins}">

                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <h1>Admini:</h1>
                    </div>
                </div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="text-center" scope="col"></th>
                        <th class="text-center" scope="col">Imię</th>
                        <th class="text-center" scope="col">Nazwsiko</th>
                        <th class="text-center" scope="col">Email</th>
                        <th class="text-center" scope="col"></th>
                        <th class="text-center" scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:set var="count" value="0" scope="page"/>
                    <c:forEach items="${admins}" var="a">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr scope="row">
                            <td>${count}.</td>
                            <td class="text-center align-middle">${a.firstName}</td>
                            <td class="text-center align-middle">${a.lastName}</td>
                            <td class="text-center align-middle">${a.email}</td>
                            <td class="text-center align-middle"></td>
                            <td class="text-center align-middle">
                                <a href="<c:url value='/admin/edit/${a.id}'/>">
                                <button type="button" class="btn btn-outline-secondary btn-sm">
                                    Edytuj
                                </button>
                            </a></td>

                            <sec:authorize access="hasRole('ADMIN')">
                                <td class="text-center align-middle"><a href="<c:url value='/admin/changePassword/${a.id}'/>">
                                    <button type="button" class="btn btn-outline-secondary btn-sm">
                                        Zmień hasło
                                    </button>
                                </a>
                                </td>
                                <td class="text-center align-middle"><a href="<c:url value='/admin/deleteConfirm/${a.id}'/>">
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
                        <a href="<c:url value='/admin/add'/>">
                            <button type="button" class="btn btn-secondary">
                                <h5>Dodaj admina</h5>
                            </button>
                        </a>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>