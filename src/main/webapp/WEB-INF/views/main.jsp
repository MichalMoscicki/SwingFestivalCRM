<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 12/04/2023
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="d-flex align-items-md-center justify-content-center" style="height: 250px;">
        <div class="container bg-light border py-3 my-3">
            <c:if test="${empty recentlyAddedFestival}">
                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <h1> Nie dodano jeszcze żadnego festiwalu</h1>
                    </div>
                </div>
            </c:if>

            <c:if test="${not empty recentlyAddedFestival}">
                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <h1> ${recentlyAddedFestival.name}</h1>
                    </div>
                </div>
                <div class="row justify-content-evenly">
                    <div class="col-md-auto">
                        <a href="<c:url value='/festival/${recentlyAddedFestival.id}/participants'/>">
                            <button type="button" class="btn btn-secondary">
                                <h5>Uczestnicy</h5>
                            </button>
                        </a>
                    </div>
                    <div class="col-md-auto">
                        <a href="<c:url value='/festival/${recentlyAddedFestival.id}/events'/>">
                            <button type="button" class="btn btn-secondary">
                                <h5>Wydarzenia</h5>
                            </button>
                        </a>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
<jsp:include page="footer.jsp"/>