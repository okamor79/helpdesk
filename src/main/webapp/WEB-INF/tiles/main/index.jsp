<%--
  Created by IntelliJ IDEA.
  User: OKaminskyi
  Date: 13.09.2019
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@include file="/WEB-INF/taglibs.jsp" %>

<div class="container-fluid">
    Welcome to main page ${role}
    <sec:authorize access="isAuthenticated()">
        <c:choose>
            <c:when test="${role == 'ROLE_USER'}">
            </c:when>
        </c:choose>
    </sec:authorize>
    <a href="/logout" class="hyperlink">Logout</a>
</div>
