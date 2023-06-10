<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 07/06/2023
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="d-flex align-items-center justify-content-center mt-5 pt-5" style="height: 250px;">
        <div class="container bg-light border">

            <div class="row justify-content-md-center py-3 my-3">
                <div class="col-md-auto">
                   <h3>${admin.email}</h3>
                </div>
            </div>

            <form:form action="/admin/changePassword/1" method="post">

                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <label>
                            <input type="text" name="password1" placeholder="Wpisz hasło">
                        </label>
                    </div>
                </div>

                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <label>
                            <input type="text" name="password2" placeholder="Powtórz hasło">
                        </label>
                    </div>
                </div>

                <div class="row justify-content-md-center py-3 my-3">
                    <div class="col-md-auto">
                        <label>
                            <input type="submit">
                        </label>
                    </div>
                    <div class="col-md-auto">
                        <a href="<c:url value='/admin'/>">
                            <button type="button" class="btn btn-secondary">
                                <h5>Powrót</h5>
                            </button>
                        </a>
                    </div>
                </div>

            </form:form>
        </div>
    </div>
<jsp:include page="footer.jsp"/>