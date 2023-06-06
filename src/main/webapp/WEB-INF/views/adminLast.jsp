<%--
  Created by IntelliJ IDEA.
  User: michalmoscicki
  Date: 06/06/2023
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="d-flex align-items-center justify-content-center mt-5 pt-5" style="height: 250px;">
        <div class="container bg-light border row justify-content-md-center">


            <div class="row justify-content-md-center py-3 my-3">
                <div class="col-md-auto">
                    <h1>Nie można usunąć ostatniego admina.</h1>
                </div>
            </div>

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
<jsp:include page="footer.jsp"/>
