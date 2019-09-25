<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-expand-xl navbar-dark bg-primary">
    <a class="navbar-brand " href="/"><spring:message code="company.name"/> Helpdesk</a>

    <div class="collapse navbar-collapse">
        <sec:authorize access="isAuthenticated()">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item"><a href="" class="nav-link">Заявки</a></li>
                <c:choose>
                    <c:when test="${userRole != 'ROLE_USER'}">
                        <li class="nav-item"><a href="" class="nav-link">Проекти</a></li>
                    </c:when>
                </c:choose>


                <c:choose>
                    <c:when test="${userRole == 'ROLE_ADMIN'}">
                        <li class="nav-item"><a href="/settings/info" class="nav-link">Налаштування</a></li>
                    </c:when>
                </c:choose>
            </ul>
            <a href="/" class="nav-item" style="text-decoration: none">
                <span class="btn-block btn">${userFullName}</span></a>
        </sec:authorize>
    </div>

</nav>
