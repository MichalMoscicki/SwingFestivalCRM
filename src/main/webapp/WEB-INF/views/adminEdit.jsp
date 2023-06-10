<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 07/06/2023
  Time: 01:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="d-flex align-items-center justify-content-center mt-5 pt-5" style="height: 250px;">
        <div class="container bg-light border">
            <form:form modelAttribute="admin" method="post">
                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        Imię <form:input path="firstName" value="${admin.firstName}"/>
                    </div>
                    <div class="col-md-auto">
                        Nazwisko <form:input path="lastName" value="${admin.lastName}"/>
                    </div>
                </div>
                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        Email <form:input path="email" value="${admin.email}"/>
                    </div>
                    <div class="col-md-auto">
                        <c:forEach items="${roles}" var="role">
                            <div class="col-md-auto">
                                <form:checkbox path="roles" value="${role}"/>${role.name}
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col-md-auto">
                        <input type="submit">
                    </div>
                </div>
            </form:form>
            <div class="row justify-content-md-center py-3 my-3">
                <div class="col-md-auto">
                    <a href="<c:url value='/admin'/>">
                        <button type="button" class="btn btn-secondary">
                            <h5>Powrót</h5>
                        </button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
